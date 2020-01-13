package at.htlkaindorf.exa_205_game2048;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import at.htlkaindorf.exa_205_game2048.beans.ColorScheme;
import at.htlkaindorf.exa_205_game2048.beans.Direction;
import at.htlkaindorf.exa_205_game2048.bl.GameLogic;

public class MainActivity extends AppCompatActivity {
    private LinearLayout layout;
    private TableLayout tlContainer;
    private ImageButton btReset;
    private GameLogic gl;
    private TextView tvPoints;
    private Button[][] btns;
    private ImageView ivDir;
    private TextView tvLost;
    private GestureDetectorCompat gdc;
    private FrameLayout flLost;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gdc = new GestureDetectorCompat(this, new SwipeListener());

        btns = new Button[4][4];
        for (int i = 0; i < btns.length; i++) {
            for (int j = 0; j < btns[i].length; j++) {
                btns[i][j] = findViewById(getResources().getIdentifier("btNum" + (i * 4 + j + 1), "id", getPackageName()));
                btns[i][j].setBackground(getDrawable(R.drawable.tile));
                setButton(btns[i][j], "C0");
            }
        }
        gl = new GameLogic(this);

        ivDir = findViewById(R.id.ivDir);
        ivDir.setBackgroundResource(R.drawable.tile);
        ivDir.setOnClickListener(v -> {
            gameLost();
        });

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        tlContainer = findViewById(R.id.tlContainer);
        tlContainer.getLayoutParams().height = size.x;

        btReset = findViewById(R.id.btReset);
        btReset.setOnClickListener(v -> resetGame());

        tvLost = findViewById(R.id.tvLost);
        flLost = findViewById(R.id.flLost);
        tvPoints = findViewById(R.id.tvPoints);

        resetGame();
    }

    public void setButtons(int[][] values) {
        boolean won = false;
        for (int i = 0; i < btns.length; i++) {
            for (int j = 0; j < btns[i].length; j++) {
                if(values[i][j] == 2048) {
                    won = true;
                }
                setButton(btns[i][j], "C" + values[i][j]);
            }
        }
        if (won) {
            gameWon();
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    public void resetGame() {
        btReset.setBackgroundTintList(ColorStateList.valueOf(0xff555555));
        tvLost.setTextColor(0x00000000);
        tvLost.setBackgroundTintList(ColorStateList.valueOf(0x00000000));
        flLost.setOnTouchListener((v, evt) -> {
//            Log.d("main", "reeee");
            return !gdc.onTouchEvent(evt);
        });
        gl.resetGame();
    }

    @SuppressLint("ClickableViewAccessibility")
    public void gameLost() {
        flLost.setOnTouchListener((v, evt) -> true);
        tvLost.setBackgroundResource(R.drawable.tile);
        tvLost.setBackgroundTintList(ColorStateList.valueOf(0xbb555555));
        tvLost.setTextColor(0xCCFF5100);

        btReset.setBackgroundTintList(ColorStateList.valueOf(0xffffa500));
    }

    @SuppressLint("ClickableViewAccessibility")
    public void gameWon() {
        flLost.setOnTouchListener((v, evt) -> true);
        tvLost.setBackgroundResource(R.drawable.tile);
        tvLost.setBackgroundTintList(ColorStateList.valueOf(0xbb555555));
        tvLost.setTextColor(0xdd00ff00);
        tvLost.setText("You have won!\nCongratulations!");

        btReset.setBackgroundTintList(ColorStateList.valueOf(0xffffa500));
    }

    public void setButton(Button btn, String button) {
        btn.setBackgroundTintList(getBackground(button));
        btn.setText(getValue(button));
        btn.setTextColor(getFontColor(button));
    }

    public void setIvDirBG(Direction dir) {
        String transName = "trans_" + dir.toString().toLowerCase();
        ivDir.setBackgroundResource(
                getResources()
                        .getIdentifier(transName,
                                "drawable",
                                getPackageName())
        );
        AnimationDrawable dirAnim = (AnimationDrawable) ivDir.getBackground();
        dirAnim.start();
    }

    public static ColorStateList getBackground(String button) {
        return ColorStateList.valueOf(ColorScheme.valueOf(button).getBackgroundColor());
    }

    public static String getValue(String button) {
        return String.valueOf(ColorScheme.valueOf(button).getValue());
    }

    public static ColorStateList getFontColor(String button) {
        return ColorStateList.valueOf(ColorScheme.valueOf(button).getFontColor());
    }

    public void setPoints(int points) {
        tvPoints.setText(getString(R.string.points, points));
    }

    private class SwipeListener extends GestureDetector.SimpleOnGestureListener {
        private static final int MIN_DIST = 100;
        private static final int MAX_DIST = 1000;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float deltaX = e1.getX() - e2.getX();
            float deltaY = e1.getY() - e2.getY();
            float deltaXAbs = Math.abs(deltaX);
            float deltaYAbs = Math.abs(deltaY);

            Direction YDirection = Direction.NONE;
            Direction XDirection = Direction.NONE;
            Direction finalDirection = Direction.NONE;

            if (MIN_DIST < deltaXAbs && deltaXAbs < MAX_DIST) {
                if (deltaX > 0) {
                    XDirection = Direction.LEFT;
                } else {
                    XDirection = Direction.RIGHT;
                }
            }
            if (MIN_DIST < deltaYAbs && deltaYAbs < MAX_DIST) {
                if (deltaY > 0) {
                    YDirection = Direction.UP;
                } else {
                    YDirection = Direction.DOWN;
                }
            }

            if (XDirection != Direction.NONE && YDirection != Direction.NONE) {
                if (deltaXAbs > deltaYAbs) {
                    finalDirection = XDirection;
                } else {
                    finalDirection = YDirection;
                }
            } else if (XDirection != Direction.NONE) {
                finalDirection = XDirection;
            } else if (YDirection != Direction.NONE) {
                finalDirection = YDirection;
            } else {
                return false;
            }
            try {

                gl.makeMove(finalDirection);

                if (gl.checkIfLost()) {
                    gameLost();
                }
            } catch (Exception e) {
                Log.e("fling", e.toString());
                for (StackTraceElement ste : e.getStackTrace()) {
                    Log.e("fling", ste.toString());
                }
            }
            return true;
        }
    }
}
