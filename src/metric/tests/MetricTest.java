package metric.tests;

import metric.Metric;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class MetricTest {

    @org.junit.jupiter.api.Test
    void getFileContentTest_Console() throws IOException {
        Metric m = new Metric();
        String fileContent = m.getFileContent("src/metric/Console.java");
        assertEquals(Files.readString(Paths.get("src/metric/tests/RawConsole.txt")),fileContent);

    }

    @org.junit.jupiter.api.Test
    void getContentFileTest_EmptyFile(){
        Metric m = new Metric();
        String fileContent = m.getFileContent("src/metric/tests/Empty.java");
        assertEquals("",fileContent);
    }
}
