package main;

import metric.Metric;

public class Main {

    public static void main(String[] args){

        if (args.length < 2 || !isValidateArgs(args)) {
            var e = new Exception("Invalid flag: '-fp' or '-filepath' must be present. eg: -fp {path-to-file}");
            System.err.println(e);
            System.exit(1);
        }

        var m = new Metric();
        m.generateMetrics(args[1]);
        m.printMetrics();


   }

    private static Boolean isValidateArgs(String[] args) {
        return args[0].equals("-fp") || args[0].equals("-filepath");

    }
}
