package at.htlkaindorf.exa_103_currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvLeft;
    private TextView tvRight;
    private TextView tvOutput;
    private ImageButton ibSwitch;
    private EditText edInput;
    private boolean isEurLeft = true;
    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLeft = findViewById(R.id.tvLeft);
        tvRight = findViewById(R.id.tvRight);
        tvOutput = findViewById(R.id.tvOutput);
        ibSwitch = findViewById(R.id.ibSwitch);
        edInput = findViewById(R.id.edInput);

        edInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                convertAndUpdate(s);
            }
        });

        tvOutput.setText(getString(R.string.output, 1.0, 1.11));

        ibSwitch.setOnClickListener(this::onChangeCurrency);
    }

    private void convertAndUpdate(Editable s) {
        try {
            String inputText = edInput.getText().toString();
            double inputNumber = getInputNumber(inputText);
            double endResult = 0;

            if(isEurLeft) {
                endResult = inputNumber * 1.10435;
            } else {
                endResult = inputNumber * 0.905536;
            }

            tvOutput.setText(getString(R.string.output, inputNumber, endResult));
        } catch(Exception e) {
            tvOutput.setText(R.string.error);
        }
    }

    private void onChangeCurrency(View view) {
        String inputText = edInput.getText().toString();
        try {
            double inputNumber = getInputNumber(edInput.getText().toString());
            double endResult = 0;

            if (isEurLeft) {
                isEurLeft = false;
                tvLeft.setText(R.string.usd);
                tvRight.setText(R.string.eur);
                edInput.setHint(R.string.inputHintUSD);

                endResult = inputNumber * 0.91;
            } else {
                isEurLeft = true;
                tvLeft.setText(R.string.eur);
                tvRight.setText(R.string.usd);
                edInput.setHint(R.string.inputHintEUR);

                endResult = inputNumber * 1.11;
            }
            tvOutput.setText(getString(R.string.output, inputNumber, endResult));
        } catch (Exception e) {
            tvOutput.setText(R.string.error);
        }
    }

    private double getInputNumber(String inputText) throws Exception {
        double inputNumber = 0;

        try {
            if (inputText.length() == 0) {
                inputNumber = 1.0;
            } else if(Double.isNaN(Double.parseDouble(inputText))) {
                throw new Exception("inputNumber wrong!");
            } else {
                inputNumber = Double.parseDouble(inputText);
            }
        } catch (NumberFormatException e) {
            Log.d(TAG, inputText + "ex");
            throw new Exception("inputNumber too low! ex");
        }

        if (inputNumber < 0) {
            Log.d(TAG, inputText + "bot");
            throw new Exception("inputNumber too low! bot");
        }

        return inputNumber;
    }
}
