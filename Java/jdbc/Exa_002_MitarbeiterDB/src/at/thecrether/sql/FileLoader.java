/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.thecrether.sql;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 *
 * @author crether
 */
public class FileLoader {

    private static final Path base;

    static {
        base = Paths.get(System.getProperty("user.dir"), "src", "at",
                "thecrether", "sql");
    }

    public static String loadSql(String sqlName) {
        try {
            FileReader fr = new FileReader(getPath(sqlName).toFile());
            BufferedReader br = new BufferedReader(fr);
            return br.lines().
                    collect(Collectors.joining("\n"));
        } catch (FileNotFoundException ex) {
            return "";
        }
    }

    /**
     * makes me a path with the file
     *
     * @param filename the filename which should be appended
     * @return the path after appending
     */
    private static Path getPath(String filename) {
        return Paths.get(base.toString(), filename);
    }
}
