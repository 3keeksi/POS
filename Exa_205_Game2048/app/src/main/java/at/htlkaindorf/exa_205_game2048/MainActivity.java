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
        final GestureDetectorCompat gdc = new GestureDetectorCompat(this, new SwipeListener());

        for (int i = 0; i < btns.length; i++) {
            for (int j = 0; j < btns[i].length; j++) {
                btns[i][j] = findViewById(getResources().getIdentifier("btNum" + (i * 4 + j + 1), "id", getPackageName()));
                btns[i][j].setBackground(getDrawable(R.drawable.tile));
                btns[i][j].setOnTouchListener((v, evt) -> gdc.onTouchEvent(evt));
                setButton(btns[i][j], "C0");
            }
        }

        setButton(btns[0][0], "C2");
        setButton(btns[2][0], "C2");

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        tlContainer = findViewById(R.id.tlContainer);
        tlContainer.getLayoutParams().height = size.x;

        btReset = findViewById(R.id.btReset);
        btReset.setOnClickListener(v -> resetGame());
    }

    public void makeMove(Direction dir) {
        switch (dir) {
            case UP:
                verticalMerge(dir);
                break;
            case DOWN:
                verticalMerge(dir);
                break;
            case LEFT:
                horizontalMerge(dir);
                break;
            case RIGHT:
                horizontalMerge(dir);
                break;
        }
    }

    public void resetGame() {

    }

    public void setNewValue() {

    }

    public void verticalMerge(Direction dir) {
        int y = 0;
        int plus = 0;
        if (dir == Direction.UP) {
            y = 0;
            plus = 1;
        } else {
            y = 3;
            plus = -1;
        }
        for (int x = 0; x < 4; x++) {
            int searchY = -1;
            int replaceY = -1;
            String searchNum = "";
            for (; 0 <= y && y < 4; y += plus) {
                Button btn = btns[y][x];
                String text = btn.getText().toString();
                if (text.equals(searchNum)) {
                    int num = Integer.parseInt(text) * 2;
                    Button btnToChange = btns[searchY][x];
                    setButton(btnToChange, "C" + num);
                    setButton(btn, "C0");
                    searchY=-1;
                } else if (!text.equals("0")) {
//                    setButton(btn, "C0");
//                    Log.d("main", " ");
//                    Log.d("main", String.format("[%d][%d]", y, x));
//                    Log.d("main", replaceY + " : " + y);
                    searchY = y;
                    searchNum = text;
                } else {
                    if (searchY == -1) {
                        replaceY = y;
                    }
                }
            }
        }
    }

    public void horizontalMerge(Direction dir) {
        int x = 0;
        int plus = 0;
        if (dir == Direction.LEFT) {
            x = 0;
            plus = 1;
        } else {
            x = 3;
            plus = -1;
        }
        for (int y = 0; y < 4; y++) {
            int searchX = -1;
            int replaceX = -1;
            String searchNum = "";
            for (; 0 <= x && x < 4; x += plus) {
                Button btn = btns[y][x];
                String text = btn.getText().toString();
                if (text.equals(searchNum)) {
                    int num = Integer.parseInt(text) * 2;
                    Button btnToChange = btns[y][searchX];
                    setButton(btnToChange, "C" + num);
                    setButton(btn, "C0");
                } else if (!text.equals("0")) {
                    setButton(btn, "C0");
                    Log.d("main", "");
                    Log.d("main", String.format("[%d][%d]", y, x));
                    Log.d("main", replaceX + "");
                    searchX = replaceX;
                    searchNum = text;
                } else {
                    replaceX = x;
                }
            }
        }
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

            makeMove(finalDirection);

            return true;
        }
    }
}
