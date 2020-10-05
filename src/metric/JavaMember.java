package metric;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JavaMember {
    public String[] headers;

    public float LOC;
    public float CLOC;
    public float DC;
    public String name;
    public float BC;
    public Console console;
    public MetricHelper mh;

    public JavaMember(String n, Console c, MetricHelper mh) {
        LOC = 0;
        CLOC = 0;
        DC = 0;
        name = n;
        BC = 0;
        console = c;
        this.mh = mh;
    }

    public void generateMetrics(String classBody) {
        System.out.println("Not implemented.");
        System.exit(1);
    }

    public void printMetrics() {
        console.print("LOC: " + LOC + "\n"
                + "CLOC: " + CLOC + "\n"
                + "DC: " + DC +"\n"
                + "BC: " + BC );
    }

    public void saveMetricToCSV(String path){
        System.out.println("not implemented.");
        System.exit(1);
    }
    public void saveMetricToCSV(String path, String[] values){
        try {
            FileWriter output = new FileWriter(new File(path), true);
            var csvWriter = new CSVWriter(output);
            csvWriter.writeNext(headers);
            csvWriter.writeNext(values);

            csvWriter.close();
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }

    }
}
