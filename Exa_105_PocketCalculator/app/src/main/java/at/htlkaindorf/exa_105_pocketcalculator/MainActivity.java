package at.htlkaindorf.exa_105_pocketcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import at.htlkaindorf.exa_105_pocketcalculator.bl.Stack;

public class MainActivity extends AppCompatActivity {

    private Stack stack = new Stack();
    private boolean firstInput = true;

    private String TAG = MainActivity.class.getSimpleName();

    private TextView tvOutput;
    private Button btClr;
    private Button btComma;
    private Button btSign;
    private Button btEnter;
    private Button btMinus;
    private Button btMult;
    private Button btPlus;
    private Button btSlash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOutput = findViewById(R.id.tvOutput);
        tvOutput.setText("");

        btClr = findViewById(R.id.btClr);
        btClr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClear(v);
            }
        });

        btComma = findViewById(R.id.btComma);
        btComma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onComma(v);
            }
        });

        btSign = findViewById(R.id.btSign);
        btSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSign(v);
            }
        });

        btEnter = findViewById(R.id.btEnter);
        btEnter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onEnter(v);
            }
        });

        btMinus = findViewById(R.id.btMinus);
        btPlus = findViewById(R.id.btPlus);
        btSlash = findViewById(R.id.btSlash);
        btMult = findViewById(R.id.btMult);

        OperatorOnClickListener opListener = new OperatorOnClickListener();
        btMinus.setOnClickListener(opListener);
        btPlus.setOnClickListener(opListener);
        btSlash.setOnClickListener(opListener);
        btMult.setOnClickListener(opListener);
    }

    public void onDigitClick(View v) {
        Button btn = (Button) v;
        String btnText = btn.getText().toString();
        if (tvOutput.getText().toString().length() >= 10) {
            return;
        }
        if (firstInput) {
            tvOutput.setText(btnText);
        } else {
            tvOutput.append(btnText);
        }

        firstInput = false;
    }

    public void onClear(View v) {
        tvOutput.setText("");
        stack.clear();
    }

    public void onComma(View v) {
        String textOutput = tvOutput.getText().toString();

        if (textOutput.contains(",")) {
            return;
        }

        tvOutput.append(",");
    }

    public void onSign(View v) {
        String textOutput = tvOutput.getText().toString();

        if (textOutput.length() == 0) {
            return;
        }

        try {
            if (textOutput.startsWith("-")) {
                textOutput = textOutput.replace("-", "");
            } else {
                textOutput = "-" + textOutput;
            }
            tvOutput.setText(textOutput);
        } catch (NumberFormatException e) {
            Log.e(TAG, "NumberFormatException in onSign()");
        }
    }

    private class OperatorOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Button btn = (Button) v;
            String operator = btn.getText().toString();

            String output = tvOutput.getText().toString();
            if (firstInput) {
                Toast.makeText(v.getContext(), "Enter a new number!", Toast.LENGTH_LONG).show();
                return;
            }

            double outputNum = Double.parseDouble(output);
            stack.push(outputNum);

            if (stack.getTos() < 2) {
                Toast.makeText(v.getContext(), "Not enough numbers entered", Toast.LENGTH_LONG).show();
                return;
            }

            double numbers[] = new double[2];
            for (int i = 0; i < 2; i++) {
                numbers[i] = stack.pop();
            }

            double result = 0;
            switch (operator) {
                case "+":
                    result = numbers[1] + numbers[0];
                    break;
                case "-":
                    result = numbers[1] - numbers[0];
                    break;
                case "*":
                    result = numbers[1] * numbers[0];
                    break;
                default:
                    if (numbers[0] == 0) {
                        stack.clear();
                        tvOutput.setText("");
                        Toast.makeText(getApplicationContext(), "Division by 0 -> resetted stack", Toast.LENGTH_LONG).show();
                        firstInput = true;
                        return;
                    }
                    result = numbers[1] / numbers[0];
                    break;
            }

//            Log.d(TAG, numbers[0] + operator + numbers[1]);
            stack.push(result);
//            Log.d(TAG, stack.getArray());

            firstInput = true;
            tvOutput.setText(String.valueOf(result).replaceAll("\\.", ","));
        }
    }

    public void onOperator(View v) {

    }

    public void onEnter(View v) {
        String text = tvOutput.getText().toString();
        text = text.replaceAll(",", "\\.");
        try {
            stack.push(Double.parseDouble(text));
            firstInput = true;
        } catch (NumberFormatException e) {
            Toast.makeText(v.getContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
        }
    }
}
