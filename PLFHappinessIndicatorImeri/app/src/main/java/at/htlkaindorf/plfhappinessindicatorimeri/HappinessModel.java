package at.htlkaindorf.plfhappinessindicatorimeri;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;

public class HappinessModel {
    private HashMap<String, ArrayList<Integer>> values = new HashMap<>();
    ArrayList<String> names = new ArrayList<>();
    Random rand = new Random();
    private String TAG = HappinessModel.class.getSimpleName();

    public HappinessModel() {
        for (int i = 0; i < 20; i++) {
            String name = "";
            if (i == 0) {
                name = "Homer";
            } else if (i == 1) {
                name = "Marge";
            } else if (i == 2) {
                name = "Lisa";
            } else if (i == 3) {
                name = "Bart";
            } else {
                name = names.get(rand.nextInt(names.size()));
            }
            addHappinessValue(name, rand.nextInt(10) + 1);
            ;
        }
    }

    public void addHappinessValue(String name, int value) {
        if (!names.contains(name)) {
            names.add(name);
            values.put(name, new ArrayList<Integer>());
        }
        values.get(name).add(value);
//        Log.d(TAG, "name: " + name + " value: " + value);
    }

    public String getTopThreeString() {
        HashMap<String, Double> avgMap = new HashMap<>();
        for (String name : values.keySet()) {
            int sum = 0;
            for (int num : values.get(name)) {
                sum += num;
            }
            avgMap.put(name, (double) sum / values.get(name).size());
        }

        HashMap<Integer, ArrayList<String>> top3 = new HashMap<>();
        ArrayList<Double> alreadyUsed = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            double max = 0;
            ArrayList<String> nameList = new ArrayList<>();
            for (String name : avgMap.keySet()) {
                if (avgMap.get(name) > max && !alreadyUsed.contains(avgMap.get(name))) {
                    max = avgMap.get(name);
                    nameList.clear();
                    nameList.add(name);
                } else if (avgMap.get(name) == max) {
                    nameList.add(name);
                }
            }
            alreadyUsed.add(max);
            top3.put(i, nameList);
        }

        String output = "";
        for (Integer top : top3.keySet()) {
            output += String.format("%.2f", alreadyUsed.get(top)) + " - ";

            for (int i = 0;i<top3.get(top).size();i++) {
                output += i == top3.get(top).size()-1 ? top3.get(top).get(i) : top3.get(top).get(i) + ",";
            }
            output+="\n";
        }
        for (String name : avgMap.keySet()) {
            Log.d(TAG, name + ": " + avgMap.get(name));
        }
        return output;
    }
}
