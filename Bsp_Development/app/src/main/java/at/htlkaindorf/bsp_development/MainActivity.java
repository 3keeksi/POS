package at.htlkaindorf.bsp_development;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button bt1;
    private Button bt2;
    private Button bt3;
    private Button bt4;
    private Button bt5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        bt4 = findViewById(R.id.bt4);
        bt5 = findViewById(R.id.bt5);

        MyClickListener mcl = new MyClickListener();
        bt1.setOnClickListener(new MyClickListener());
        bt2.setOnClickListener(new MyClickListener());
        bt3.setOnClickListener(new MyClickListener());
        bt4.setOnClickListener(new MyClickListener());
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButton5Click(v);
            }
        });
    }

    private void onButton4Click(View v) {
        Button bt = (Button) v;
        Toast.makeText(
                getApplicationContext(),
                "variante 4" + bt.getText().toString(),
                Toast.LENGTH_LONG
        ).show();
    }

    private void onButton5Click(View v) {
        Button bt = (Button) v;
        Toast.makeText(
                getApplicationContext(),
                "Button 5 Clicked" + bt.getText().toString(),
                Toast.LENGTH_LONG
        ).show();
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
