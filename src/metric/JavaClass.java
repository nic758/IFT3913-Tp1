package metric;

public class JavaClass {
    private float class_LOC;
    private float method_LOC;
    private float class_CLOC;
    private float method_CLOC;
    private float class_DC;
    private float method_DC;
    private final String className;
    private final Console console;
    private final MetricHelper mh;
    private float CC;
    private float WMC;
    private float class_BC;
    private float method_BC;

    public JavaClass(String name, Console c, MetricHelper mh){
        className = name.trim();
        console = c;
        this.mh = mh;
    }

    public void generateMetrics(String classBody){
        var lines = classBody.trim().split(("\n"));
        var isBeforeClass = true;
        class_LOC = lines.length;

        for (String line:lines) {

            if(isBeforeClass && mh.isComment(line)){
                class_CLOC += 1;
                continue;
            }else if(mh.isComment(line)){
                method_CLOC +=1;
                continue;
            }

            if(line.contains("class")){
                isBeforeClass=false;
                continue;
            }
            if(line.contains("package")){
                continue;
            }

            if(!mh.isProp(line) && !mh.isEndOfClass(line)){
                method_LOC+=1;
                if(mh.hasComment(line)){
                    method_CLOC+=1;
                }
                continue;
            }
        }
        class_DC = class_CLOC/class_LOC;
        method_DC = method_CLOC/method_LOC;
    }

    public void printMetrics(){
        console.printInfo("Metrics for class "+className+"\n"
        +"class_LOC: "+class_LOC+"\n"
        +"class_CLOC: "+ class_CLOC +"\n"
        +"method_LOC: "+method_LOC+"\n"
        +"method_CLOC: "+method_CLOC+"\n"
        +"class_DC: "+class_DC+"\n"
        +"class_DC: "+method_DC+"\n"
        +"CC: "+CC+"\n"
        +"WMC: "+WMC+"\n"
        +"class_BC: "+class_BC+"\n"
        +"method_BC: "+method_BC);
    }
}