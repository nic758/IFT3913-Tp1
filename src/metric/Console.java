package metric;

/**
 * Need to parse this correctly:
 */
public class Console {
    private String ANSI_RED = "\u001B[31m";
    private String ANSI_RESET = "\u001B[0m";
    private String ANSI_BLUE = "\u001B[34m";

    public void printErr(String msg){
        System.err.println(ANSI_RED + msg + ANSI_RESET);
        System.exit(1);
    }

    public void printInfo(String msg){
        System.out.println(ANSI_BLUE+"INFO: "+ANSI_RESET+msg);
    }

    public void print(String msg){
        System.out.println(msg);
    }
}

class Console1 {
    public void test(){
        System.out.println("Test");//this is a test of comment
    }
}