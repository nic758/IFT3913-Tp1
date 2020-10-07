package metric.tests;

import metric.Console;
import metric.JavaClass;
import metric.MetricHelper;


import static org.junit.jupiter.api.Assertions.*;

class JavaClassTest {


    @org.junit.jupiter.api.Test
    void generateMetrics() {

        Console c = new Console();
        MetricHelper mh = new MetricHelper();
        JavaClass jc = new JavaClass("Console",c,mh,"src/metric/Console.java");
        String consS = c.toString();
        jc.generateMetrics(consS);
        assertEquals(1,jc.LOC);
        assertEquals(0,jc.CLOC);
        assertEquals(0,jc.BC);
        assertEquals(0,jc.WMC);
        assertEquals(0,jc.DC);

    }

    @org.junit.jupiter.api.Test
    void calculateWMC() {
        Console c = new Console();
        MetricHelper m = new MetricHelper();
        JavaClass jc = new JavaClass("m",c,m,"src/metric/Console.java");
        assertEquals(0,jc.calculateWMC());
    }

}