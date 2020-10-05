package metric;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JavaClass extends JavaMember {

    private List<JavaMethod> methods;
    public float WMC;

    public JavaClass(String name, Console c, MetricHelper mh){
        super(name, c, mh);
        methods = new ArrayList<>();
    }

    private String[] extractMethodsString(String[] lines){
        var methodsFirstLine = 0;
        var methodsLastLine= lines.length-1;//because last line is the ending bracket of the class.

        for (int i=0; i<lines.length; i++) {
            if(mh.isComment(lines[i])){
                //comment for the class
                CLOC += 1;
                continue;
            }
            if(lines[i].contains("class")){
                if(lines[i].contains("{"))
                {
                 methodsFirstLine = i+1;   //starting bracket is on that line.
                }
                else {
                    methodsFirstLine = i+2;//starting bracket is on the other line.
                }
                break;
            }
            if(lines[i].contains("package") || lines[i].contains("import")){
                continue;
            }
        }

        return Arrays.copyOfRange(lines, methodsFirstLine, methodsLastLine);
    }

    private void instanciateMethods(String[] rawMethods){
        var methodString="";
        JavaMethod m = null;

        for (String line:rawMethods) {
            if(mh.isProp(line)){
                continue;
            }

            methodString+=line;

            //we have a method signature
            if (line.contains("public") || line.contains("private") || line.contains("protected")){
                var words = line.split(" ");
                var name = "Cannot parse this method name";

                //) is being ignored. i need to put it back.
                var params = line.substring(line.indexOf('('), line.lastIndexOf(')'))+")";
                for (String w: words) {
                    if (w.contains("(")){
                        name = w.split("\\(")[0] + params;
                    }
                }

                m = new JavaMethod(name, console, mh);
            }
            //Standard for end of method
            if(line.equals("    }\n"))
            {
                m.generateMetrics(methodString);
                methods.add(m);

                methodString="";
            }
        }
    }

    public void generateMetrics(String classBody){
        var lines = classBody.trim().split(("(?<=\n)"));
        var rawMethods = extractMethodsString(lines);

        instanciateMethods(rawMethods);

        LOC = lines.length;
        DC = CLOC/LOC;
        WMC = calculateWMC();
        BC = DC/WMC;
    }

    public int calculateWMC(){
        var sum = 0;

        for (JavaMethod m :methods) {
            sum += m.CC;
        }

        return sum;
    }

    @Override
    public void printMetrics() {
        console.printInfo("Metrics for class "+ name);
        super.printMetrics();
        console.print("WMC: " + WMC);

        for (JavaMethod m:methods) {
            m.printMetrics();
        }
    }
}