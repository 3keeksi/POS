package at.htlkaindorf.exa_205_game2048;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final GestureDetectorCompat gdc = new GestureDetectorCompat(this, new SwipeListener());

        Button[][] btns = new Button[4][4];
        for (int i = 0; i < btns.length; i++) {
            for (int j = 0; j < btns[i].length; j++) {
                btns[i][j] = findViewById(getResources().getIdentifier("btNum" + (i * 4 + j + 1), "id", getPackageName()));
                btns[i][j].setBackground(getDrawable(R.drawable.tile));
                btns[i][j].setOnTouchListener((v, evt) -> gdc.onTouchEvent(evt));
                setButton(btns[i][j], "C0");
            }
        }
        gl = new GameLogic(btns, this);

//        setButton(btns[0][0], "C2");
//        setButton(btns[2][0], "C4");

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        tlContainer = findViewById(R.id.tlContainer);
        tlContainer.getLayoutParams().height = size.x;

        btReset = findViewById(R.id.btReset);
        btReset.setOnClickListener(v -> gl.resetGame());

        tvPoints = findViewById(R.id.tvPoints);

        gl.resetGame();
    }

    public void setButton(Button btn, String button) {
        btn.setBackgroundTintList(getBackground(button));
        btn.setText(getValue(button));
        btn.setTextColor(getFontColor(button));
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
        private static final int MIN_DIST = 10;
        private static final int MAX_DIST = 10000;

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

            gl.makeMove(finalDirection);
            return true;
        }
    }
}
