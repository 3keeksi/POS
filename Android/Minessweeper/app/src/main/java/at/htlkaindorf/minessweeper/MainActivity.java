package at.htlkaindorf.minessweeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

import at.htlkaindorf.minessweeper.beans.Field;
import at.htlkaindorf.minessweeper.beans.FieldState;
import at.htlkaindorf.minessweeper.bl.GameLogic;

public class MainActivity extends AppCompatActivity {
    private Button[][] btns = new Button[9][9];

    private GridLayout gridLayout;
    private Timer timer;
    private GameLogic gl;

    private TextView tvTime, tvRemaining;
    private Button btReset;

    private AtomicInteger seconds = new AtomicInteger(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = findViewById(R.id.tlButtons);
        tvTime = findViewById(R.id.tvTime);
        tvRemaining = findViewById(R.id.tvRemaining);
        btReset = findViewById(R.id.btReset);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        gl = new GameLogic(this);

        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                Button bt = new Button(this);
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

                int finalX = x;
                int finalY = y;

                bt.setOnClickListener(v -> firstClick(v, finalY, finalX));
            }
        }

        btReset.setOnClickListener(this::reset);
    }

    private void reset(View v) {
        timer.cancel();
        for (int i = btns.length - 1; i >= 0; i--) {
            for (int j = btns[i].length - 1; j >= 0; j--) {
                int finalI = i;
                int finalJ = j;
                btns[i][j].setOnClickListener(v2 -> firstClick(v2, finalI, finalJ));
            }
        }
    }

    private void firstClick(View v, int y, int x) {
        gl.placeMines(y, x);
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seconds.incrementAndGet();
                int minutes = seconds.get() / 60;
                int second = seconds.get() % 60;
                tvTime.setText(String.format("%02d:%02d", minutes, second));
            }
        }, 0, 1000);

        handleClick((Button) v, y, x);

        for (int i = btns.length - 1; i >= 0; i--) {
            for (int j = btns[i].length - 1; j >= 0; j--) {
                int finalI = i;
                int finalJ = j;
                btns[i][j].setOnClickListener(v2 -> handleClick((Button) v2, finalI, finalJ));
                btns[i][j].setOnLongClickListener(v2 -> handleLongClick((Button) v2, finalI, finalJ));
            }
        }
    }

    private void setBtn(Button btn, Field field) {
        if (field.getState() == FieldState.DISCOVERED) {
            if(field.isBomb()) {
                btn.setBackgroundResource(R.drawable.bomb);
            }

            String text = "";
            if (field.getNearBombs() > 0) {
                text = field.getNearBombs() + "";
            }

            btn.setBackgroundResource(R.drawable.pressed);
            btn.setText(text);
        }
    }

    public void setBtns(Field[][] fields) {
        for (int y = 0; y < fields.length; y++) {
            for (int x = 0; x < fields[y].length; x++) {
                setBtn(btns[y][x], fields[y][x]);
            }
        }
    }

    private void handleClick(Button v, int y, int x) {
        Field[][] fields = gl.getFields();
        boolean recursive = gl.makeMove(y, x);
        tvRemaining.setText(gl.getRemaining() + "");
        Log.e("main", recursive + "");
        if (recursive) {
            setBtns(fields);
        } else {
            setBtn(v, gl.getFields()[y][x]);
        }
    }

    private boolean handleLongClick(Button v, int y, int x) {
        Field[][] fields = gl.getFields();
        Field field = fields[y][x];

        int remaining = gl.getRemaining();
        if (field.getState() == FieldState.FLAGGED) {
            remaining--;
        } else {
            remaining++;
        }
        gl.setRemaining(remaining);
        tvRemaining.setText(remaining + "");

        v.setBackgroundResource(R.drawable.flagged);
        v.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.colorPrimaryDark)));
        return true;
    }
}