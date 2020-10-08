package metric.tests;

import metric.Metric;

import static org.junit.jupiter.api.Assertions.*;

class MetricTest {
    @org.junit.jupiter.api.Test
    void getFileContentTest() {
        Metric m = new Metric();
        String fileContent = m.getFileContent("src/metric/Console.java");
        assertEquals("package metric;\r\n" +
                "/**\r\n" +
                " * Need to parse this correctly:\r\n" +
                " */\r\n" +
                "public class Console {\r\n" +
                "    private String ANSI_RED = \"\\u001B[31m\";\r\n" +
                "    private String ANSI_RESET = \"\\u001B[0m\";\r\n" +
                "    private String ANSI_BLUE = \"\\u001B[34m\";\r\n" +
                "\r\n" +
                "    public void printErr(String msg){\r\n" +
                "        System.err.println(ANSI_RED + msg + ANSI_RESET);\r\n" +
                "        System.exit(1);\r\n" +
                "    }\r\n" +
                "\r\n" +
                "    public void printInfo(String msg){\r\n" +
                "        System.out.println(ANSI_BLUE+\"INFO: \"+ANSI_RESET+msg);\r\n" +
                "    }\r\n" +
                "\r\n" +
                "    public void print(String msg){\r\n" +
                "        System.out.println(msg);\r\n" +
                "    }\r\n" +
                "}",fileContent);

    }
}
