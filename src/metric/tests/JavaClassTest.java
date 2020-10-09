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
    void generateMetricsTest_ClassWithMultipleMethods()  {

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



    }

    @org.junit.jupiter.api.Test
    void generateMetricsTest_EmptyClass() throws IOException {

        Metric m = new Metric();
        String emptyClass = Files.readString(Paths.get("src/metric/tests/EmptyClass.txt"));
        Console c = new Console();
        MetricHelper mh = new MetricHelper();
        JavaClass jc2 = new JavaClass("EmptyClass", c , mh , "src/metric/tests/EmptyClass.txt");
        jc2.generateMetrics(emptyClass);
        assertEquals(2,jc2.LOC);
        assertEquals(0,jc2.CLOC);
        assertEquals(0,jc2.DC);
        assertEquals(0,jc2.BC);
        assertEquals(0,jc2.WMC);

    }

    @org.junit.jupiter.api.Test
    void generateMetricsTest_ClassWithNestedComments() throws IOException {
        Metric m = new Metric();
        String nestedCommentsClass = Files.readString(Paths.get("src/metric/tests/NestedCommentsClass.txt"));
        Console c = new Console();
        MetricHelper mh = new MetricHelper();
        JavaClass jc3 = new JavaClass("NestedCommentsClass", c , mh , "src/metric/tests/NestedCommentsClass.txt");
        jc3.generateMetrics(nestedCommentsClass);
        assertEquals(18,jc3.LOC);
        assertEquals(2,jc3.CLOC);
        assertEquals(0.1111111119389534,jc3.DC);
        assertEquals(0,jc3.BC);
        assertEquals(0,jc3.WMC);
    }

    @org.junit.jupiter.api.Test
    void generateMetricsTest_ClassWithCommentedMethods() throws IOException {
        Metric m = new Metric();
        String methodsWithCommentsClass = Files.readString(Paths.get("src/metric/tests/MethodsWithCommentsClass.txt"));
        Console c = new Console();
        MetricHelper mh = new MetricHelper();
        JavaClass jc4 = new JavaClass("MethodsWithCommentsClass", c , mh , "src/metric/tests/MethodsWithCommentsClass.txt");
        jc4.generateMetrics(methodsWithCommentsClass);
        assertEquals(11,jc4.LOC);
        assertEquals(0,jc4.CLOC);
        assertEquals(0.0,jc4.DC);
        assertEquals(0,jc4.BC);
        assertEquals(0,jc4.WMC);
    }


    @org.junit.jupiter.api.Test
    void calculateWMCTest_Console()  {
        Metric m = new Metric();
        String fc = m.getFileContent("src/metric/Console.java");
        Console c= new Console();
        MetricHelper mh = new MetricHelper();
        JavaClass jc = new JavaClass("Console",c,mh,"src/metric/Console.java");
        assertEquals(0,jc.calculateWMC());


    }

    @org.junit.jupiter.api.Test
    void calculateWMCTest_EmptyClass() throws IOException {

        String emptyClass = Files.readString(Paths.get("src/metric/tests/EmptyClass.txt"));
        Console c = new Console();
        MetricHelper mh = new MetricHelper();
        JavaClass jc2 = new JavaClass("EmptyClass",c,mh,"src/metric/tests/EmptyClass.txt");
        assertEquals(0,jc2.calculateWMC());
    }

}