package utils;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class Utils {
    public static void main(String[] args) throws URISyntaxException {
        URL res = Utils.class.getClassLoader().getResource("database/assignment1.csv");
        File file = Paths.get(res.toURI()).toFile();
        String absolutePath = file.getAbsolutePath();
        System.out.println(absolutePath);
    }
}
