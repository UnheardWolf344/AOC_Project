package org.example.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class AOCFileIO {
    public static String parseFile (String fileName) {
        try {
            String nam = System.getProperty("user.name");
            Path path = Paths.get("/home/", nam, "/Desktop/", fileName);
            
            return new String(Files.readAllBytes(path));
        } catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println(e.getMessage());
            return null;
        }
    }
}
