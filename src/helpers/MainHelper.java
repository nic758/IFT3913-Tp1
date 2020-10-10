package helpers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainHelper {

    public Boolean isValidateFilePath(String[] args) {
        return args[0].equals("-fp") || args[0].equals("-filepath");

    }
    public Boolean isValidOutput(String[] args){
        if(args.length < 4)
            return false;

        return (args[2].equals("-o") || args[2].equals("-out")) && hasOutputPath(args) ;
    }
    public Boolean hasOutputPath(String[] args){
        File f = new File(args[3]);
        if(!f.isDirectory() || !f.exists()){
            var e = new Exception("You must specify an existing folder with the -o or -out flag");
            System.err.println(e);
            System.exit(1);
            return false;
        }
        return true;
    }

    private String[] getAllFiles(String path,String ending) {
        try {
            return Files.walk(Paths.get(path))
                    .filter(Files::isRegularFile)
                    .filter(x -> x.toString().toLowerCase().endsWith(ending))
                    .map(Path::toString)
                    .toArray(String[]:: new);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String[] getAllJavaFiles(String path) {
        return getAllFiles(path,"java");
    }
}
