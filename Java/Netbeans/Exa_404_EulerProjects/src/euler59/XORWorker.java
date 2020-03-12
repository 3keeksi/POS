/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euler59;

import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

/**
 *
 * @author crether
 */
public class XORWorker implements Callable<Map.Entry<Integer[], Boolean>> {

    private Integer[] key;

    public XORWorker(Integer[] key) {
        this.key = key;

    }

    @Override
    public Map.Entry<Integer[], Boolean> call() throws Exception {
        int count = 0;
        long sum = 0;
        String converted = "";
        for (Integer ch : XORLauncher.input) {
            int XORed = ch ^ key[count % 3];
            count++;
            sum+=XORed;
            converted += (char) XORed;
        }
        String k = Arrays.stream(key).map(t -> String.valueOf((char) t.intValue())).collect(Collectors.joining());
        Path out = Paths.get(System.getProperty("user.dir"), "src", "euler59", "out", k + ".txt");

        count = 0;
        for (String string : converted.split(" ")) {
            if (XORLauncher.words.contains(string)) {
                count++;
            }
        }
        if (count >= 30) {
            FileWriter fw = new FileWriter(out.toFile());
            fw.write(sum+"/"+converted);
            fw.close();
            return Map.entry(key, true);
        }
        return Map.entry(key, false);
    }

}
