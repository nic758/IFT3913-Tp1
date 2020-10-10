package metric.tests;


import metric.Metric;
import metric.MetricHelper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class MetricHelperTest {

    @Test
    void isPredicatTest_SwitchInWhile() {
        MetricHelper mh = new MetricHelper();
        String predic1 = "while(true) {\n" +
                "    switch (test) {\n" +
                "\tcase x : //code\n" +
                "\tbreak;\n" +
                "\tcase y : // code\n" +
                "\tbreak;\n" +
                "}";
        assertTrue(mh.isPredicat(predic1));

    }

    @Test
    void removeEmptyLine() throws IOException {
        MetricHelper mh = new MetricHelper();
        String emptyLines = Files.readString(Paths.get("src/metric/tests/EmptyLines.txt"));
        var actual = mh.removeEmptyLines(emptyLines);
        assertEquals("\ntesting removeEmptyLines method\n",actual);
    }

    @Test
    void isPredicatTest_ForInIf(){
        MetricHelper mh = new MetricHelper();
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
    void isCommentTest_CommentsInComments() {
        MetricHelper mh  = new MetricHelper();
        String com1 = "/* /**/ */";
        assertTrue(mh.isComment(com1));


    }

    @Test
    void isCommentTest_NestedComments(){
        MetricHelper mh = new MetricHelper();
        String com2 = "/** /** **/ /* */ **/";
        assertTrue(mh.isComment(com2));

    }

    @Test
    void isCommentTest_CommentError(){
        MetricHelper mh = new MetricHelper();
        String com3 = "/ / /**";
        assertFalse(mh.isComment(com3));
    }

    @Test
    void isClassTest_PublicClass() {
        MetricHelper mh = new MetricHelper();
        String c1 = "public class";
        assertTrue(mh.isClass(c1));

    }

    @Test
    void isClassTest_PrivateClass(){
        MetricHelper mh = new MetricHelper();
        String c2 = "private class";
        assertTrue(mh.isClass(c2));

    }

    @Test
    void isClassTest_Interface(){
        MetricHelper mh = new MetricHelper();
        String c3 = "interface";
        assertTrue(mh.isClass(c3));
    }

    @Test
    void isClassTest_Method(){
        MetricHelper mh = new MetricHelper();
        String c4 = "method";
        assertFalse(mh.isClass(c4));
    }

    @Test
    void isClassTest_Enum(){
        MetricHelper mh = new MetricHelper();
        String c5 = "enum Type {\n" +
                "    CLASS,\n" +
                "    INTERFACE,\n" +
                "    ENUM\n" +
                "}";
        assertTrue(mh.isClass(c5));
    }
}
