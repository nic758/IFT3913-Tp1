package metric.tests;


import metric.MetricHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MetricHelperTest {

    @Test
    void isPredicatTest() {
        MetricHelper mh = new MetricHelper();
        String predic1 = "while(true) {\n" +
                "    switch (test) {\n" +
                "\tcase x : //code\n" +
                "\tbreak;\n" +
                "\tcase y : // code\n" +
                "\tbreak;\n" +
                "}";
        assertTrue(mh.isPredicat(predic1));

        String predic2 = "if(statement) {\n" +
                "\tfor (i=0;i>50;i++) {\n" +
                "\t// do something\n" +
                "\t}\n" +
                "} else if(statement2) {\n" +
                "\t//code\n" +
                "} ";
        assertTrue(mh.isPredicat(predic2));

    }


    @Test
    void isCommentTest() {
        MetricHelper mh  = new MetricHelper();
        String com1 = "/* /**/ */";
        assertTrue(mh.isComment(com1));

        String com2 = "/** /** **/ /* */ **/";
        assertTrue(mh.isComment(com2));

        String com3 = "/ / /**";
        assertFalse(mh.isComment(com3));
    }

    @Test
    void isClassTest() {
        MetricHelper mh = new MetricHelper();
        String c1 = "public class";
        assertTrue(mh.isClass(c1));

        String c2 = "private class";
        assertTrue(mh.isClass(c2));

        String c3 = "interface";
        assertTrue(mh.isClass(c3));

        String c4 = "method";
        assertFalse(mh.isClass(c4));
    }
}
