package metric.tests;

import metric.Console;
import metric.JavaClass;
import metric.Metric;
import metric.MetricHelper;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class JavaClassTest {


    @org.junit.jupiter.api.Test
    void generateMetricsTest() throws IOException {

        Metric m = new Metric();
        String fc = m.getFileContent("src/metric/Console.java");
        Console c= new Console();
        MetricHelper mh = new MetricHelper();
        JavaClass jc = new JavaClass("Console",c,mh,"src/metric/Console.java");
        jc.generateMetrics(fc);
        assertEquals(22,jc.LOC);
        assertEquals(3.0,jc.CLOC);
        assertEquals(0.13636364042758942,jc.DC);
        assertEquals(0,jc.BC);
        assertEquals(0,jc.WMC);

        String testClass = Files.readString(Paths.get("src/metric/tests/TestClass.txt"));
        JavaClass jc2 = new JavaClass("TestClass",c,mh,"src/metric/tests/TestClass.txt");
        jc2.generateMetrics(testClass);
        assertEquals(269,jc2.LOC);
        assertEquals(3,jc2.CLOC);
        assertEquals(0.011152416467666626,jc2.DC);
        assertEquals(0,jc2.BC);
        assertEquals(0,jc2.WMC);

    }

    @org.junit.jupiter.api.Test
    void calculateWMCTest() {


    }

}