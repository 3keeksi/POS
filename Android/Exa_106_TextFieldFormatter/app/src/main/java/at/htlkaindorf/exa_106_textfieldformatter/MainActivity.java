package at.htlkaindorf.exa_106_textfieldformatter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText edInput;
    private CheckBox cbBold;
    private CheckBox cbItalic;
    private SeekBar sbSize;
    private RadioGroup rgFonts;
    private TextView tvSize;

    private int currentFontId;

    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edInput = findViewById(R.id.edInput);
        cbBold = findViewById(R.id.cbBold);
        cbItalic = findViewById(R.id.cbItalic);
        sbSize = findViewById(R.id.sbSize);
        rgFonts = findViewById(R.id.rgFonts);
        tvSize = findViewById(R.id.tvSize);

        CbOnClickListener cbListener = new CbOnClickListener();
        cbBold.setOnClickListener(cbListener);
        cbItalic.setOnClickListener(cbListener);

        sbSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                onChangeFontSize(seekBar, progress, fromUser);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        rgFonts.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                onChangeFont(group, checkedId);
            }
        });

        currentFontId = R.font.source_code_pro;
    }

    public class CbOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            onChangeFontStyle(v);
        }
    }

    public void onChangeFontStyle(View v) {
        setFont(currentFontId);
    }

    public void onChangeFontSize(SeekBar seekbar, int progress, boolean fromUser) {
        tvSize.setText(getString(R.string.sizeFormat, progress));
        edInput.setTextSize(progress);
    }

    public void onChangeFont(RadioGroup group, int checkedId) {
        Log.d(TAG, checkedId + "");
        RadioButton bt = findViewById(checkedId);
        String btText = bt.getText().toString();

        String source_code = getString(R.string.source_code_pro);
        String ubuntu = getString(R.string.ubuntu);
        String permanent_maker = getString(R.string.permanent_marker);

        if (btText.equalsIgnoreCase(source_code)) {
            setFont(R.font.source_code_pro);
        } else if (btText.equalsIgnoreCase(ubuntu)) {
            setFont(R.font.ubuntu);
        } else if (btText.equalsIgnoreCase(permanent_maker)) {
            setFont(R.font.permanent_marker);
        }
    }

    public void setFont(int fontId) {
        Typeface font = ResourcesCompat.getFont(getApplicationContext(), fontId);
        currentFontId = fontId;
        boolean italic = cbItalic.isChecked();
        boolean bold = cbBold.isChecked();

        if(italic && bold) {
            edInput.setTypeface(font, Typeface.BOLD_ITALIC);
        } else if(italic) {
            edInput.setTypeface(font, Typeface.ITALIC);
        } else if(bold) {
            edInput.setTypeface(font, Typeface.BOLD);
        } else {
            edInput.setTypeface(font, Typeface.NORMAL);
        }
    }
}
