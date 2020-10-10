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
    void generateMetricsTest_ClassWithMultipleMethods() throws IOException {

        Metric m = new Metric();
        Console c= new Console();
        // On windows comments are in \r\n and in linux are \n , we want to remove both to an empty string
        String fc = Files.readString(Paths.get("src/metric/tests/Console.txt")).replaceAll("\r\n","\n");
        MetricHelper mh = new MetricHelper();
        JavaClass jc = new JavaClass("Console",c,mh,"src/metric/tests/Console.txt");
        jc.generateMetrics(fc);
        assertEquals(19,jc.LOC);
        assertEquals(3.0,jc.CLOC);
        assertEquals(0.15789473056793213,jc.DC);
        assertEquals(0.052631575614213943,jc.BC);
        assertEquals(3,jc.WMC);


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
        assertEquals(16,jc3.LOC);
        assertEquals(5,jc3.CLOC);
        assertEquals(0.3125,jc3.DC);
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