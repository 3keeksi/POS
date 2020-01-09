package at.htlkaindorf.exa_205_game2048;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import at.htlkaindorf.exa_205_game2048.beans.ColorScheme;
import at.htlkaindorf.exa_205_game2048.beans.Direction;

public class MainActivity extends AppCompatActivity {
    private Button[][] btns = new Button[4][4];
    private LinearLayout layout;
    private TableLayout tlContainer;
    private ImageButton btReset;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < btns.length; i++) {
            for (int j = 0; j < btns[i].length; j++) {
                btns[i][j] = findViewById(getResources().getIdentifier("btNum" + (i * 4 + j + 1), "id", getPackageName()));
                setButton(btns[i][j], "C0");
            }
        }

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        tlContainer = findViewById(R.id.tlContainer);
        tlContainer.getLayoutParams().height = size.x;

        final GestureDetectorCompat gdc = new GestureDetectorCompat(this, new SwipeListener());

        tlContainer.setOnTouchListener(new TableLayout.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gdc.onTouchEvent(event);
            }
        });

        btReset = findViewById(R.id.btReset);
        btReset.setOnClickListener(v -> resetGame());
    }

    public void makeMove(Direction dir) {
        switch(dir) {

        }
    }

    public void resetGame() {

    }

    public void setNewValue() {

    }

    public void setButton(Button btn, String button) {
        btn.setBackground(getDrawable(R.drawable.tile));
        btn.setBackgroundTintList(getBackground("C0"));
        btn.setText("");
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
            Direction finalDirection;

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

            return true;
        }
    }
}
