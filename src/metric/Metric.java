package metric;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Metric {
    private Console console;
    private MetricHelper mh;
    //This is in case we have a .java with more than one class.
    private List<JavaClass> classes;

    public Metric() {
        console = new Console();
        classes = new ArrayList<>();
        mh = new MetricHelper();
    }

    /**
     * @param filePath
     */
    public void generateMetrics(String filePath) {
        var rawFile = getFileContent(filePath);

        if(!rawFile.contains("class")){
            console.printErr("The file has no classes.");
            return;
        }

        var file = removeEmptylines(rawFile);
        instantiateJavaClasses(file);
    }

    private String removeEmptylines(String s){
        return s.replace("\n\n", "\n");
    }

    private void instantiateJavaClasses(String file)
    {
        String[] lines = file.split("(?<=\n)");

        String currentClass = "";
        JavaClass classe=null;
        for (int i=0; i<lines.length; i++) {
            if(lines[i].contains("class"))
            {
                if(classe != null){
                    classes.add(classe);
                }

                var classeName = lines[i].split("class")[1].split("(?=\\{)")[0];
                classe = new JavaClass(classeName, console, mh);
            }

            currentClass += lines[i] ;

            //only a closing bracket, we are at the end.
            if (lines[i].equals("}\n") | lines[i].equals("}"))
            {
                if (classe!=null){
                    classe.generateMetrics(currentClass);
                }

                currentClass = "";
            }
        }

        classes.add(classe);
    }


    /**
     * Print metrics to the console.
     */
    public void printMetrics(){
        for (JavaClass c:classes) {
           c.printMetrics();
        }
    }

    private Boolean isJavaFile(String path) {
        var list = path.split("\\.");

        return list.length != 0 && list[list.length - 1].equals("java");
    }

    private String getFileContent(String fp) {
        try {
            if (isJavaFile(fp)) {
                return Files.readString(Paths.get(fp));
            }

            console.printErr("Error: Not a valid java file");
            return null;
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
            return null;
        }
    }
}