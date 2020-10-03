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
    private float CC;
    private float WMC;
    private float class_BC;
    private float method_BC;

    public JavaClass(String name){
        className = name;
        console = new Console();
    }

    public void generateMetrics(String classBody){
        var lines = classBody.trim().split(("\n"));
        var isBeforeClass = true;
        class_LOC = lines.length;

        for (String line:lines) {

            if(isBeforeClass && isComment(line)){
                class_CLOC += 1;
                continue;
            }else if(isComment(line)){
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

            if(!isProp(line) && !isEndOfClass(line)){
                method_LOC+=1;
                if(hasComment(line)){
                    method_CLOC+=1;
                }
                continue;
            }
        }
        class_DC = class_CLOC/class_LOC;
        method_DC = method_CLOC/method_LOC;
    }

    private Boolean hasComment(String s){
        return s.contains("//") || s.contains("/*");
    }
    private Boolean isEndOfClass(String s){
        return s.equals("}");
    }
   private Boolean isProp(String s){
        var words = s.trim().split(" ");
        return (words[0].contains("public")||words[0].contains("private")) && words[words.length-1].contains(";");
   }

    private Boolean isComment(String s)
    {
        var arr=s.toCharArray();
        return isSingleLineComment(arr) || isMultipleLineComment(arr);
    }

    private Boolean isMultipleLineComment(char[] arr){
        return  isStartOfMultipleLineComment(arr)
                || isMiddleOfMultipleLineComment(arr)
                || isEndOfMultipleLineComment(arr);
    }
    private Boolean isStartOfMultipleLineComment (char[] arr)
    {
        return (arr[0]=='/'&&arr[1]=='*');
    }
    private Boolean isMiddleOfMultipleLineComment (char[] arr){
        return (arr[0] == 32 && arr[1]=='*' );
    }
    private Boolean isEndOfMultipleLineComment(char[] arr){
        if(arr.length<2){
            return false;
        }

        return (arr[arr.length-2]=='*'&&arr[arr.length-1]=='/');
    }
    private Boolean isSingleLineComment(char[] arr){
        return arr[0] == '/' && arr[1]=='/';
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
        +"class_BC"+class_BC+"\n"
        +"method_BC"+method_BC);
    }
}
