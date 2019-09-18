package at.htlkaindorf.exa_103_currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvLeft;
    private TextView tvRight;
    private TextView tvOutput;
    private ImageButton ibSwitch;
    private EditText edInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLeft = findViewById(R.id.tvLeft);
        tvRight = findViewById(R.id.tvRight);
        tvOutput = findViewById(R.id.tvOutput);
        ibSwitch = findViewById(R.id.ibSwitch);
        edInput = findViewById(R.id.edInput);

        ibSwitch.setOnClickListener(v -> {

        });
    }
}
