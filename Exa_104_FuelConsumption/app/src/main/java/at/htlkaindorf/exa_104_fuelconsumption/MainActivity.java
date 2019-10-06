package at.htlkaindorf.exa_104_fuelconsumption;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btCalc;
    private EditText edFuel;
    private EditText edDistance;
    private TextView tvOutput;

    private boolean isFuelEmpty = true;
    private boolean isDistanceEmpty = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btCalc = findViewById(R.id.btCalc);
        edFuel = findViewById(R.id.edFuel);
        edDistance = findViewById(R.id.edDistance);
        tvOutput = findViewById(R.id.tvOutput);

        btCalc.setOnClickListener(this::onCalcConsumption);
    }

    private void onCalcConsumption(View v) {
        String fuelInput = edFuel.getText().toString();
        String distanceInput = edDistance.getText().toString();

        if (fuelInput.length() == 0 && distanceInput.length() == 0) {
            Toast.makeText(getApplicationContext(), "no Content entered", Toast.LENGTH_LONG);

            return;
        }

        try {
            Double fuel = Double.parseDouble(fuelInput);
            int distance = Integer.parseInt(distanceInput);
            Double result = fuel / distance * 100;
            tvOutput.setText(getString(R.string.consumptionFormat, result));
        } catch (NumberFormatException e) {
            tvOutput.setText(R.string.illegal);
        }
    }
}
