package metric;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
enum Type {
    CLASS,
    INTERFACE,
    ENUM
}

public class JavaClass extends JavaMember {

    private List<JavaMethod> methods;
    private Type type;
    public float WMC;

    public JavaClass(String name, Type t, Console c, MetricHelper mh, String path) {
        super(name, c, mh, path);
        methods = new ArrayList<>();
        headers = new String[]{"Chemin", "Classe", "Classe_LOC", "Classe_CLOC", "Classe_DC", "Classe_BC",
                "Classe_WMC",};
        type = t;
    }

    private String[] extractMethodsString(String[] lines) {
        var methodsFirstLine = 0;
        var methodsLastLine = lines.length - 1;//because last line is the ending bracket of the class.

        for (int i = 0; i < lines.length; i++) {
            if (mh.isComment(lines[i]) || mh.hasComment(lines[i])) {
                //comment for the class
                CLOC += 1;
                continue;
            }
            if (lines[i].contains("class")) {
                if (lines[i].contains("{")) {
                    methodsFirstLine = i + 1;   //starting bracket is on that line.
                } else {
                    methodsFirstLine = i + 2;//starting bracket is on the other line.
                }
                break;
            }
            if (lines[i].contains("package") || lines[i].contains("import")) {
                continue;
            }
        }

        return Arrays.copyOfRange(lines, methodsFirstLine, methodsLastLine);
    }
    private String getMethodName(String line){

        var words = line.split(" ");
        var name = "Cannot parse this method name";

        //) is being ignored. i need to put it back.
        var params = line.substring(line.indexOf('('), line.lastIndexOf(')')) + ")";
        for (String w : words) {
            if (w.contains("(")) {
                name = w.split("\\(")[0] + params;
            }
        }

        return name;
    }

    private void instantiateMethods(String[] rawMethods) {
        var methodString = "";
        JavaMethod m = null;

        for (String line : rawMethods) {
            if (mh.isProp(line) && type != Type.INTERFACE) {
                continue;
            }
            if(mh.isInterface(line, type)){
                //we have and interface
               var name = getMethodName(line);
               m = new JavaMethod(name, console, mh, path);
               //method end on the same line, because it can't be implemented.
                m.generateMetrics(line);
               methods.add(m);
               methodString="";
               continue;
            }

            methodString += line;

            //we have a method signature
            if (line.contains("public") || line.contains("private") || line.contains("protected")) {
                var name = getMethodName(line);
                m = new JavaMethod(name, console, mh, path);
            }
            //Standard for end of method
            if (line.equals("    }\n")) {
                m.generateMetrics(methodString);
                methods.add(m);

                methodString = "";
            }
        }
    }
    
    /**
     * Generates all metrics of a class
     * @param classBody represents all lines of code of a class and its methods as a string
     */

    public void generateMetrics(String classBody) {
        var lines = classBody.trim().split(("(?<=\n)"));

        var rawMethods = extractMethodsString(lines);
        if(type!=Type.ENUM){
            instantiateMethods(rawMethods);
        }

        LOC = lines.length;
        DC = CLOC / LOC;
        WMC = calculateWMC();
        BC = WMC != 0 ? DC / WMC:0;
    }

    public int calculateWMC() {
        var sum = 0;

        for (JavaMethod m : methods) {
            sum += m.CC;
        }

        return sum;
    }

    @Override
    public void printMetrics() {
        console.printInfo("Metrics for " + type.toString().toLowerCase()+" " + name);
        super.printMetrics();
        console.print("WMC: " + WMC);

        for (JavaMethod m : methods) {
            m.printMetrics();
        }
    }

    @Override
    public void saveMetricToCSV(String path) {
        var values = new String[]{this.path, name, Float.toString(LOC), Float.toString(CLOC)
                , Float.toString(DC), Float.toString(BC), Float.toString(WMC)};
        var p = path.endsWith("/") ? path : path+"/";
        super.saveMetricToCSV(p+"classe.csv", values);

        for (JavaMethod m : methods) {
            m.saveMetricToCSV(p, name);
        }
    }
}