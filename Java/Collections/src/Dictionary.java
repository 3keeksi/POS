import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class Dictionary {

    private TreeMap<Character, TreeSet<String>> words = new TreeMap<>();

    public static void main(String[] args) {
        Dictionary d = new Dictionary();

        d.readWords();
        d.printWordsByFirstLetter('a');
    }

    private void readWords(){
        File file = Paths.get(System.getProperty("user.dir"), "src", "CommonWordsGerman.txt").toFile();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line = "";
            while((line = br.readLine()) != null){
                char start = line.toLowerCase().charAt(0);

                if(words.containsKey(start)){
                    words.get(start).add(line);
                }else{
                    TreeSet<String> set = new TreeSet<>(new Comparator<String>() {
                        @Override
                        public int compare(String o1, String o2) {
                            return o1.toUpperCase().compareTo(o2.toUpperCase());
                        }
                    });
                    set.add(line);
                    words.put(start, set);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printWordsByFirstLetter(char firstLetter){
        int counter = 0;
        for(String word : words.get(firstLetter)){
            System.out.print(word);
            counter++;

            if(counter > 0 && counter % 10 == 0){
                System.out.println();
            }else{
                System.out.print(", ");
            }
        }
    }

    private void printAllWorlds(){
        for(Map.Entry<Character, TreeSet<String>> entry : words.entrySet()){
            System.out.println(entry.getKey().toString().toUpperCase() + ":");
            printWordsByFirstLetter(entry.getKey());
            System.out.println();
        }
    }
}
