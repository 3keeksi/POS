package at.htlkaindorf.exa_108_numberpuzzlegame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import at.htlkaindorf.exa_108_numberpuzzlegame.beans.Swipe;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private Button[][] btns;
    private int[] emptyIndices = new int[2];

    private Button fromButton;

    private Button btReset;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btReset = findViewById(R.id.btReset);

        btReset.setOnClickListener(new ResetListener());

        btns = new Button[4][4];

        btns[0][0] = findViewById(R.id.bt1);
        btns[0][1] = findViewById(R.id.bt2);
        btns[0][2] = findViewById(R.id.bt3);
        btns[0][3] = findViewById(R.id.bt4);
        btns[1][0] = findViewById(R.id.bt5);
        btns[1][1] = findViewById(R.id.bt6);
        btns[1][2] = findViewById(R.id.bt7);
        btns[1][3] = findViewById(R.id.bt8);
        btns[2][0] = findViewById(R.id.bt9);
        btns[2][1] = findViewById(R.id.bt10);
        btns[2][2] = findViewById(R.id.bt11);
        btns[2][3] = findViewById(R.id.bt12);
        btns[3][0] = findViewById(R.id.bt13);
        btns[3][1] = findViewById(R.id.bt14);
        btns[3][2] = findViewById(R.id.bt15);
        btns[3][3] = findViewById(R.id.bt16);

//        setRight();
//        if (hasFinished()) {
//            Toast.makeText(getApplicationContext(), "finished", Toast.LENGTH_LONG).show();
//        }

        initButtons();
    }

    @SuppressLint("ClickableViewAccessibility")
    public void initButtons() {
        Set<Integer> alreadySet = new HashSet<>();
        SwipeListener sl = new SwipeListener();
        final GestureDetectorCompat gdc = new GestureDetectorCompat(getApplicationContext(), sl);
        boolean gotANumber = false;
        Random rand = new Random();
        int number;
        boolean emptyNotSet = true;

        for (int y = 0; y < btns.length; y++) {
            Button[] btnRow = btns[y];
            for (int x = 0; x < btnRow.length; x++) {
                Button btn = btnRow[x];
                btn.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        fromButton = (Button) v;
                        return gdc.onTouchEvent(event);
                    }
                });
                if (emptyNotSet) {
                    if (rand.nextInt(3) == 0) {
                        setNumber(btn, 0);
                        emptyIndices[0] = y;
                        emptyIndices[1] = x;
                        emptyNotSet = false;
                        continue;
                    }
                }
                while (!gotANumber) {
                    number = rand.nextInt(15);
                    if (!alreadySet.contains(number + 1) && alreadySet.size() < 16) {
                        gotANumber = true;
                        setNumber(btn, number + 1);
                        alreadySet.add(number + 1);
                    }
                }
                gotANumber = false;
            }
            if (emptyNotSet) {
                setNumber(btns[3][3], 0);
                emptyIndices[0] = 3;
                emptyIndices[1] = 3;
                emptyNotSet = false;
            }
        }
    }

    class SwipeListener extends GestureDetector.SimpleOnGestureListener {
        private static final int MIN_DIST = 100;
        private static final int MAX_DIST = 1000;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float deltaX = e1.getX() - e2.getX();
            float deltaY = e1.getY() - e2.getY();
            float deltaXAbs = Math.abs(deltaX);
            float deltaYAbs = Math.abs(deltaY);

            Swipe YDirection = Swipe.NONE;
            Swipe XDirection = Swipe.NONE;
            Swipe finalDirection;

            if (MIN_DIST < deltaXAbs && deltaXAbs < MAX_DIST) {
                if (deltaX > 0) {
                    XDirection = Swipe.LEFT;
                } else {
                    XDirection = Swipe.RIGHT;
                }
            }
            if (MIN_DIST < deltaYAbs && deltaYAbs < MAX_DIST) {
                if (deltaY > 0) {
                    YDirection = Swipe.UP;
                } else {
                    YDirection = Swipe.DOWN;
                }
            }

            if (XDirection != Swipe.NONE && YDirection != Swipe.NONE) {
                if (deltaXAbs > deltaYAbs) {
                    finalDirection = XDirection;
                } else {
                    finalDirection = YDirection;
                }
            } else if (XDirection != Swipe.NONE) {
                finalDirection = XDirection;
            } else if (YDirection != Swipe.NONE) {
                finalDirection = YDirection;
            } else {
                return false;
            }

            String btnTag = fromButton.getTag().toString();
            int yIdx = Integer.parseInt(btnTag.split(",")[0]);
            int xIdx = Integer.parseInt(btnTag.split(",")[1]);

            if (!(isNextToEmpty(xIdx, yIdx) == finalDirection)) {
                return false;
            }

            String text = fromButton.getText().toString();
            setNumber(fromButton, 0);
            setNumber(btns[emptyIndices[0]][emptyIndices[1]], Integer.parseInt(text));
            fromButton.setText("");
            emptyIndices[0] = yIdx;
            emptyIndices[1] = xIdx;

            if (hasFinished()) {
                Toast.makeText(getApplicationContext(), "finished", Toast.LENGTH_LONG).show();
            }

            return true;
        }
    }

    private void setNumber(Button btn, int number) {
        if (number == 0) {
            btn.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.emptyBG)));
            return;
        }
        btn.setText("" + number);
        if (number % 2 == 0) {
            btn.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.evenBG)));
        } else {
            btn.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.oddBG)));
        }
    }

    private Swipe isNextToEmpty(int x, int y) {
        if (y + 1 == emptyIndices[0] && x == emptyIndices[1]) {
            return Swipe.DOWN;
        } else if (y == emptyIndices[0] && x + 1 == emptyIndices[1]) {
            return Swipe.RIGHT;
        } else if (y - 1 == emptyIndices[0] && x == emptyIndices[1]) {
            return Swipe.UP;
        } else if (y == emptyIndices[0] && x - 1 == emptyIndices[1]) {
            return Swipe.LEFT;
        }
        return Swipe.NONE;
    }

    private class ResetListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            initButtons();
        }
    }

    public boolean hasFinished() {
        int num = 1;
        boolean returnVal;
        for (Button[] btnRow : btns) {
            for (Button btn : btnRow) {
                String text = btn.getText().toString();

                if (text.isEmpty() && num == 16) {
                    return true;
                } else if(text.isEmpty() && num < 16) {
                    return false;
                } if(!text.isEmpty() && num == 16){
                    return false;
                }
                returnVal = Integer.parseInt(text) == num;
                if(!returnVal) {
                    return false;
                }
                num++;
            }
        }
        return true;
    }
}
