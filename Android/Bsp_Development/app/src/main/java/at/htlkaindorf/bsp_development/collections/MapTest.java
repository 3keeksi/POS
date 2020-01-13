package at.htlkaindorf.bsp_development.collections;

import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
    public static void main(String[] args) {
        Map<String, Integer> myMap = new HashMap<>();
        myMap.put("Homer", 1200);
        myMap.put("Lisa", 9800);
        myMap.put("Bart", 2100);
        System.out.println(myMap.get("Bart"));
    }
}
