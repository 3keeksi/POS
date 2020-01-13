package at.htlkaindorf.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etInput;
    private Button btContinue;
    private TextView tvMessage;
    private final String TAG = MainActivity.class.getSimpleName();
    private boolean firstClick = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_rel_layout);

//        etInput = findViewById(R.id.edName);
//        btContinue = findViewById(R.id.btContinue);
//        tvMessage = findViewById(R.id.tvMessage);

        btContinue.setEnabled(false);
        etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                btContinue.setEnabled(s.length() > 0);
            }
        });

        //create event handler for onClick event
        btContinue.setOnClickListener(v -> {
            if (firstClick) {
                Log.d(TAG, "button clicked");
                String input = etInput.getText().toString();
                if(input.equals("")) {
                    finish();
                }
                tvMessage.setText(getString(R.string.outputMsg, input));
                etInput.setVisibility(View.INVISIBLE);
                btContinue.setText(R.string.finsihed);
            } else {
                finish();
            }
        });
    }
}
