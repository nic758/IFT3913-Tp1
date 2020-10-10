package main;

import helpers.MainHelper;
import metric.Metric;

public class Main {
    private static final MainHelper mainHelper = new MainHelper();
    public static void main(String[] args){
        if (args.length < 2 || !mainHelper.isValidateFilePath(args)) {
            var e = new Exception("Invalid flag: '-fp' or '-filepath' must be present. eg: -fp {path-to-file}");
            System.err.println(e);
            System.exit(1);
        }

        var files = mainHelper.getAllJavaFiles(args[1]);
        for (String f:files) {
            var m = new Metric();
            m.generateMetrics(f);
            m.printMetrics();

            if(mainHelper.isValidOutput(args)){
                m.saveMetricToCSV(args[3]);
            }
        }

   }

}
