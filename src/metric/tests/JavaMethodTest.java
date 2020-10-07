package metric.tests;

import metric.Console;
import metric.JavaMethod;
import metric.MetricHelper;

import static org.junit.jupiter.api.Assertions.*;

class JavaMethodTest {

    @org.junit.jupiter.api.Test
    void generateMetrics() {
        Console cons = new Console();
        MetricHelper mh1 = new MetricHelper();
        JavaMethod jm = new JavaMethod("printErr",cons,mh1, "src/metric/Console.java");
        assertNotEquals(22,jm.LOC);
        assertEquals(0,jm.LOC);
        assertEquals(0,jm.CLOC);
        assertEquals(1.0,jm.CC);
        assertEquals(0,jm.BC);
        assertEquals(0,jm.DC);

        JavaMethod jm2 = new JavaMethod("printInfo",cons,mh1,"src/metric/Console.java");
        assertEquals(1,jm2.CC);
        assertEquals(0,jm2.LOC);
        assertEquals(0,jm2.DC);
        assertEquals(0,jm2.BC);
        assertEquals(0,jm2.CLOC);

        JavaMethod jm3 = new JavaMethod("print",cons,mh1,"src/metric/Console.java");
        assertEquals(0,jm3.LOC);
        assertEquals(0,jm3.DC);
        assertEquals(1,jm3.CC);
        assertEquals(0,jm3.BC);
        assertEquals(0,jm3.CLOC);

    }

}