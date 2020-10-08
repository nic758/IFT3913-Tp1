package metric.tests;

import metric.Console;
import metric.JavaClass;
import metric.JavaMethod;
import metric.MetricHelper;

import static org.junit.jupiter.api.Assertions.*;

class JavaMethodTest {

    @org.junit.jupiter.api.Test
    void generateMetricsTest() {
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

        String printInfo = "public void printInfo(String msg){\n" +
                "        System.out.println(ANSI_BLUE+\"INFO: \"+ANSI_RESET+msg);\n" +
                "    }";

        JavaMethod jm2 = new JavaMethod("printInfo",c,mh,"src/metric/Console.java");
        jm2.generateMetrics(printInfo);
        assertEquals(3,jm2.LOC);
        assertEquals(0,jm2.BC);
        assertEquals(1.0,jm2.CC);
        assertEquals(0,jm2.CLOC);
        assertEquals(0,jm2.DC);

        String print = "public void print(String msg){\n" +
                "        System.out.println(msg);\n" +
                "    }";

        JavaMethod jm3 = new JavaMethod("print",c,mh,"src/metric/Console.java");
        jm3.generateMetrics(print);
        assertEquals(3,jm3.LOC);
        assertEquals(0,jm3.BC);
        assertEquals(1.0,jm3.CC);
        assertEquals(0,jm3.CLOC);
        assertEquals(0,jm3.DC);
    }

}