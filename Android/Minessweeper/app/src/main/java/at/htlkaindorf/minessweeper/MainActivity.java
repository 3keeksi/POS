package at.htlkaindorf.minessweeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button[][] btns = new Button[9][9];

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

        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                Button bt = new Button(this);
//                bt.setText(y + "," + x);
                bt.setId(y * 10 + x);
                bt.setBackgroundResource(R.drawable.tile);
                gridLayout.addView(bt);
                btns[y][x] = bt;

                ViewGroup.LayoutParams params = bt.getLayoutParams();
                GridLayout.LayoutParams lp = (GridLayout.LayoutParams) bt.getLayoutParams();
                int margin = 4;
                lp.setMargins(margin, margin, margin, margin);
                params.width = width / 9 - 2 * margin;
                params.height = width / 9 - 2 * margin;
            }
        }
    }
}