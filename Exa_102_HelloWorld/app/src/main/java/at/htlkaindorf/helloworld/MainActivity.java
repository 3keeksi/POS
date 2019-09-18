package at.htlkaindorf.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText etInput;
    private Button btContinue;
    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput = findViewById(R.id.edName);
        btContinue = findViewById(R.id.btContinue);

        //create event handler for onClick event
        btContinue.setOnClickListener(v -> {
            Log.d(TAG, "button clicked");
        });
    }
}
