package at.htlkaindorf.bsp_development;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {

    private Button bt1;
    private Button bt2;
    private Button bt3;
    private Button bt4;
    private Button bt5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_old);

//        bt1 = findViewById(R.id.bt1);
//        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
//        bt4 = findViewById(R.id.bt4);
        bt5 = findViewById(R.id.bt5);

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
