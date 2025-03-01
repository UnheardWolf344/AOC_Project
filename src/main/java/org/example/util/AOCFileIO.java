package org.example.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AOCFileIO {
    public static String parseFile (String fileName) throws IOException {
        String nam = System.getProperty("user.name");
        Path path = Paths.get("/home/", nam, "/Desktop/", fileName);
        
        return new String(Files.readAllBytes(path));
    }
}
