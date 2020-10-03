package metric;

public class MetricHelper {

    public Boolean hasComment(String s){
        return s.contains("//") || s.contains("/*");
    }
    public Boolean isEndOfClass(String s){
        return s.equals("}");
    }
    public Boolean isProp(String s){
        var words = s.trim().split(" ");
        return (words[0].contains("public")||words[0].contains("private")) && words[words.length-1].contains(";");
    }

    public Boolean isComment(String s)
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
}
