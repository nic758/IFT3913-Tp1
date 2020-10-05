package metric;

public class JavaMethod extends JavaMember {

    public float CC;

    public JavaMethod(String name, Console c, MetricHelper m) {
        super(name, c, m);
        CC = 1;
        headers = new String[]{"Chemin", "Classe", "Methode", "Methode_LOC", "Methode_CLOC", "Methode_DC",
                "Methode_BC", "Methode_CC"};
    }

    @Override
    public void generateMetrics(String methodBody) {
        var lines = methodBody.trim().split(("\n"));
        LOC = lines.length;
        for (String line : lines) {
            if (mh.isComment(line)) {
                CLOC += 1;
                continue;
            }
            if (mh.isPredicat(line)) {
                CC += 1;
            }
            if (mh.hasComment(line)) {
                CLOC += 1;
            }
        }

        DC = CLOC / LOC;
        BC = DC / CC;

    }

    @Override
    public void printMetrics() {
        console.printInfo("Metrics for method " + name);
        super.printMetrics();
        console.print("CC: " + CC);
    }

    public void saveMetricToCSV(String path, String classeName) {
        var values = new String[]{path, classeName, formatSignature(name), Float.toString(LOC), Float.toString(CLOC)
                , Float.toString(DC), Float.toString(BC), Float.toString(CC)};
        super.saveMetricToCSV(path+"methode.csv", values);
    }

    private String formatSignature(String s){
        var words = s.replaceAll("\\(|\\)", " ").split(" ");
        String formatedSignature = words[0];

        for (int i=0; i< words.length; i++) {
            if (mh.isOdd(i)){
                formatedSignature += "_".concat(words[i]);
            }
        }
        return formatedSignature;
    }
}
