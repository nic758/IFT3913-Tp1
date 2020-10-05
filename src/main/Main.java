package main;

import metric.Metric;

import java.io.File;

public class Main {

    public static void main(String[] args){

        if (args.length < 2 || !isValidateFilePath(args)) {
            var e = new Exception("Invalid flag: '-fp' or '-filepath' must be present. eg: -fp {path-to-file}");
            System.err.println(e);
            System.exit(1);
        }

        var m = new Metric();
        m.generateMetrics(args[1]);
        m.printMetrics();

        if(isValidOutput(args)){
            m.saveMetricToCSV(args[3]);
        }
   }

    private static Boolean isValidateFilePath(String[] args) {
        return args[0].equals("-fp") || args[0].equals("-filepath");

    }
    private static Boolean isValidOutput(String[] args){
        if(args.length < 4)
            return false;

        return (args[2].equals("-o") || args[2].equals("-out")) && hasOutputPath(args) ;
    }
    private static Boolean hasOutputPath(String[] args){
        File f = new File(args[3]);
        if(!f.isDirectory() || !f.exists()){
            var e = new Exception("You must specify an existing folder with the -o or -out flag");
            System.err.println(e);
            System.exit(1);
            return false;
        }
        return true;
    }
}
