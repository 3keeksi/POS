package at.htlkaindorf.bsp_development;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import at.htlkaindorf.bsp_development.beans.Person;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {

    public static final String TAG = MainActivity.class.getSimpleName();

    private Button bt1;
    private Button bt2;
    private Button bt3;
    private Button bt4;
    private Button bt5;
    private Button btSignIn;

    private GestureDetectorCompat gdc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AssetManager am = getAssets();

        try {
            InputStream is = am.open("daten.csv");
            List<Person> persons = new BufferedReader(new InputStreamReader(is))
                    .lines().skip(1).map(Person::new).collect(Collectors.toList());
        } catch (IOException e) {
            Log.e(TAG, "gang");
        }

//        bt1 = findViewById(R.id.bt1);
//        bt2 = findViewById(R.id.bt2);
//        bt3 = findViewById(R.id.bt3);
//        bt4 = findViewById(R.id.bt4);
//        bt5 = findViewById(R.id.bt5);

//        MyClickListener mcl = new MyClickListener();
//        bt1.setOnClickListener(mcl);
//        bt2.setOnClickListener(mcl);
//        bt3.setOnClickListener(new MyClickListener());
//        bt3.setOnLongClickListener(this);
//        bt4.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                onButton4Click(v);
//            }
//        });
//        bt5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onButton5Click(v);
//            }
//        });
    }

    public void onSignIn(View view) {

    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        public static final int MIN_DIST = 100;
        public static final int MAX_DIST = 1000;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float deltaX = e1.getX() - e2.getX();
            float deltaY = e1.getY() - e2.getY();
            float deltaXAbs = Math.abs(deltaX);
            float deltaYAbs = Math.abs(deltaY);

            if(MIN_DIST < deltaXAbs && deltaXAbs < MAX_DIST) {
                if(deltaX > 0) {
                    Log.i(TAG, "swipe left");
                } else {
                    Log.i(TAG, "swipe right");
                }
            }
            else if(MIN_DIST < deltaYAbs && deltaYAbs < MAX_DIST) {
                if(deltaY > 0) {
                    Log.i(TAG, "swipe up");
                } else {
                    Log.i(TAG, "swipe down");
                }
            }

            return true;
        }
    }

    private void onButton4Click(View v) {
        Map<String, Integer> myMap = new HashMap<>();
        myMap.put("Homer", 1200);
        myMap.put("Lisa", 1200);
        myMap.put("Bart", 1200);
        Toast.makeText(getApplicationContext(), myMap.get("Bart").toString(), Toast.LENGTH_LONG).show();
    }

    private void onButton5Click(View v) {
        Button bt = (Button) v;
        Toast.makeText(
                getApplicationContext(),
                "Button 5 Clicked" + bt.getText().toString(),
                Toast.LENGTH_LONG
        ).show();
    }

    @Override
    public boolean onLongClick(View v) {
        Button bt = (Button) v;
        Toast.makeText(
                getApplicationContext(),
                "Button LONG Click" + bt.getText().toString(),
                Toast.LENGTH_LONG).show();
        return true;
    }

    public class MyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Button bt = (Button) v;
            Toast.makeText(
                    getApplicationContext(),
                    "Button " + bt.getText().toString(),
                    Toast.LENGTH_LONG
            ).show();
        }
    }
}
