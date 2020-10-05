package metric;

public class JavaMethod extends JavaMember {
    public float CC;
    public JavaMethod(String name, Console c, MetricHelper m) {
        super(name, c, m);
        CC=1;
    }

    @Override
    public void generateMetrics(String methodBody) {
        var lines = methodBody.trim().split(("\n"));
        LOC = lines.length;

        for (String line: lines){
            if(mh.isComment(line)){
                CLOC += 1;
                continue;
            }
            if(mh.isPredicat(line)){
                CC += 1;
            }
            if(mh.hasComment(line)){
                CLOC+=1;
            }
        }

        DC=CLOC/LOC;
        BC = DC/CC;

    }

    @Override
    public void printMetrics() {
        console.printInfo("Metrics for method "+ name);
        super.printMetrics();
        console.print("CC: "+CC);
    }
}
