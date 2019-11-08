package at.htlkaindorf.plfhappinessindicatorimeri;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SeekBar sbSlider;
    private EditText edInput;
    private TextView tvOutput;
    private ImageView imgView;
    private Button btSend;
    private TextView tvHappiness;
    private HappinessModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        model = new HappinessModel();

        sbSlider = findViewById(R.id.sbSlider);
        edInput = findViewById(R.id.edInput);
        tvOutput = findViewById(R.id.tvOutput);
        imgView = findViewById(R.id.imgView);
        btSend = findViewById(R.id.btSend);
        tvHappiness = findViewById(R.id.tvHappiness);

        sbSlider.setOnSeekBarChangeListener(new SeekBarListener());
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSend(v);
            }
        });

        tvOutput.setText(model.getTopThreeString());
    }

    private void onSend(View v) {
        String inputText = edInput.getText().toString();
        int happiness = sbSlider.getProgress();

        if(inputText.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter a name!", Toast.LENGTH_LONG).show();
            return;
        }

        model.addHappinessValue(inputText, happiness);
        tvOutput.setText(model.getTopThreeString());
    }

    private class SeekBarListener implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            tvHappiness.setText(getString(R.string.indicatorFormat, progress));
            if (1 <= progress && progress <= 3) {
                imgView.setImageResource(R.drawable.smiley1);
            } else if (4 <= progress && progress <= 7) {
                imgView.setImageResource(R.drawable.smiley2);
            } else if (8 <= progress && progress <= 10) {
                imgView.setImageResource(R.drawable.smiley3);
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
}
