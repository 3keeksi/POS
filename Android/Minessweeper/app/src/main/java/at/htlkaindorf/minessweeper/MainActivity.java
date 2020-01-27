package at.htlkaindorf.minessweeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private GridLayout gridLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = findViewById(R.id.tlButtons);

        ViewGroup.LayoutParams llLP = gridLayout.getLayoutParams();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Button bt = new Button(this);
                bt.setText(i + "," + j);
                bt.setId(i*10 + j);
                gridLayout.addView(bt);
                ViewGroup.LayoutParams params = bt.getLayoutParams();
                bt.setBackgroundResource(R.drawable.btn);
                GridLayout.LayoutParams lp = (GridLayout.LayoutParams) bt.getLayoutParams();
                int margin = 4;
                lp.setMargins(margin,margin,margin,margin);
                params.width = width / 9 - 2*margin;
                params.height = width / 9 - 2*margin;
            }
        }
    }
}