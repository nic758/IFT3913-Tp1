package metric.tests;

import metric.Console;
import metric.JavaMethod;
import metric.MetricHelper;

import static org.junit.jupiter.api.Assertions.*;

class JavaMethodTest {

    @org.junit.jupiter.api.Test
    void generateMetricsTest_MultipleLinesMethod() {
        String printErr = "public void printErr(String msg){\n" +
                "        System.err.println(ANSI_RED + msg + ANSI_RESET);\n" +
                "        System.exit(1);\n" +
                "    }" ;
        Console c = new Console();
        MetricHelper mh = new MetricHelper();
        JavaMethod jm = new JavaMethod("printErr",c,mh,"src/metric/Console.java");
        jm.generateMetrics(printErr);
        assertEquals(4,jm.LOC);
        assertEquals(0,jm.BC);
        assertEquals(1.0,jm.CC);
        assertEquals(0,jm.CLOC);
        assertEquals(0,jm.DC);


    }

    @org.junit.jupiter.api.Test
    void generateMetricsTest_SingleLineMethod() {
        String printInfo = "public void printInfo(String msg){System.out.println(ANSI_BLUE+\"INFO: \"+ANSI_RESET+msg) }";

        Console c = new Console();
        MetricHelper mh = new MetricHelper();
        JavaMethod jm2 = new JavaMethod("printInfo",c,mh,"src/metric/Console.java");
        jm2.generateMetrics(printInfo);
        assertEquals(1,jm2.LOC);
        assertEquals(0,jm2.BC);
        assertEquals(1.0,jm2.CC);
        assertEquals(0,jm2.CLOC);
        assertEquals(0,jm2.DC);

    }

    @org.junit.jupiter.api.Test
    void generateMetricsTest_MethodWithCodeAndComments(){
        String print = "public void print(String msg) {System.out.println(msg); // print a message }";
        Console c = new Console();
        MetricHelper mh = new MetricHelper();
        JavaMethod jm3 = new JavaMethod("print",c,mh,"src/metric/Console.java");
        jm3.generateMetrics(print);
        assertEquals(1,jm3.LOC);
        assertEquals(1,jm3.BC);
        assertEquals(1.0,jm3.CC);
        assertEquals(1,jm3.CLOC);
        assertEquals(1,jm3.DC);
    }

    @org.junit.jupiter.api.Test
    void generateMetricsTest_MethodWithJavaDoc(){
        String javaDocMethod = "/**\n" +
                "     *\n" +
                "     * @param path path were the CSV will be stored\n" +
                "     * @param values values to be stored\n" +
                "     */\n" +
                "    public void saveMetricToCSV(String path, String[] values){\n" +
                "        try {\n" +
                "            FileWriter output = new FileWriter(new File(path), true);\n" +
                "            var csvWriter = new CSVWriter(output);\n" +
                "            csvWriter.writeNext(headers);\n" +
                "            csvWriter.writeNext(values);\n" +
                "\n" +
                "            csvWriter.close();\n" +
                "        } catch (IOException e) {\n" +
                "            System.err.println(e);\n" +
                "            System.exit(1);\n" +
                "        }\n" +
                "\n" +
                "    }";
        Console c = new Console();
        MetricHelper mh = new MetricHelper();
        JavaMethod jm4 = new JavaMethod("saveMetricToCSV",c,mh,"src/metric/JavaMember.java");
        jm4.generateMetrics(javaDocMethod);
        assertEquals(19,jm4.LOC);
        assertEquals(0.10526315867900848,jm4.BC);
        assertEquals(1.0,jm4.CC);
        assertEquals(2,jm4.CLOC);
        assertEquals(0.10526315867900848,jm4.DC);
    }

}