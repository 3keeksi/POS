import java.io.*;
import java.net.SocketTimeoutException;
import java.nio.Buffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

public class Dictionary {
    private static TreeMap<Character, TreeSet<Word>> words = new TreeMap<>();

    public static void readWords() throws IOException {

        String basePath = System.getProperty("user.dir");
        Path path = Paths.get(basePath, "src", "GermanCommonWords.txt");

        FileReader fr = new FileReader(path.toFile());
        BufferedReader br = new BufferedReader(fr);

        String line = "";
        while ((line = br.readLine()) != null) {
            char init = Character.toUpperCase(line.charAt(0));
            if (words.get(init) == null) {
                words.put(init, new TreeSet<>());
            }
            words.get(init).add(new Word(line));

        }
    }

    public static void printWordsByFirstLetter(char firstLetter) {
        firstLetter = Character.toUpperCase(firstLetter);
        TreeSet<Word> wordSet = words.get(firstLetter);
        int i = 1;
        String line = "";

        for (Word word : wordSet) {
            if (i % 10 == 0) {
                line += word.getWord();
            } else {
                line += word.getWord() + ", ";
            }
            if (i % 10 == 0) {
                System.out.println(line);
                line = "";
            }
            i++;
        }
    }

    public static void main(String[] args) {
        try {
            readWords();
            printWordsByFirstLetter('A');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}