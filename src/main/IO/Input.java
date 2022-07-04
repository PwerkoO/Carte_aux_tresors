package main.IO;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Input {

    public final static String path = "maps\\Map1.txt";
    public static List<String> readMap() throws Exception {
        return Files.readAllLines(Paths.get(path));
    }
}
