package at.htlkaindorf.exa_205_game2048.bl;

import android.util.Log;
import android.widget.Button;

import java.util.Random;

import at.htlkaindorf.exa_205_game2048.MainActivity;
import at.htlkaindorf.exa_205_game2048.beans.Direction;

public class GameLogic {
    private int[][] values = new int[4][4];
    private int fieldsSet = 0;
    private int points = 0;
    private Button[][] btns;
    private MainActivity m;
    private Random rand = new Random();

    public GameLogic(Button[][] btns, MainActivity m) {
        this.btns = btns;
        this.m = m;
    }

    public void makeMove(Direction dir) {
        switch (dir) {
            case UP:
            case DOWN:
                verticalMerge(dir);
                break;
            case LEFT:
            case RIGHT:
                horizontalMerge(dir);
                break;
        }
        setNewValue();
    }

    public boolean checkIfLost() {
        return false;
    }

    public void resetGame() {
        clearLayout();
        points = 0;
        fieldsSet = 0;
        setNewValue();
        setNewValue();
        m.setPoints(points);
    }

    public void clearLayout() {
        for (int i = 0; i < btns.length; i++) {
            for (int j = 0; j < btns[i].length; j++) {
                m.setButton(btns[i][j], "C0");
            }
        }
    }

    public void setNewValue() {
        int y = rand.nextInt(4);
        int x = rand.nextInt(4);
        boolean set = false;
        do {
            String text = btns[y][x].getText().toString();
            if (text.equals("0")) {
                set = true;
                fieldsSet++;
                int num = rand.nextInt(3) < 2 ? 2 : 4;
                m.setButton(btns[y][x], "C" + num);
                points = num;
            }
        } while (!set);
    }

    public void verticalMerge(Direction dir) {
        int yStart;
        int y;
        int plus;
        if (dir == Direction.UP) {
            yStart = 0;
            plus = 1;
        } else {
            yStart = 3;
            plus = -1;
        }

        for (int x = 0; x < 4; x++) {
            int searchY = -1;
            int replaceY = -1;
            String searchNum = "";
            y = yStart;
            for (; 0 <= y && y < 4; y += plus) {
                Button btn = btns[y][x];
                String text = btn.getText().toString();
                if (text.equals(searchNum)) {
                    int num = Integer.parseInt(text) * 2;
                    Button btnToChange = btns[searchY][x];
                    m.setButton(btnToChange, "C" + num);
                    m.setButton(btn, "C0");
                    searchY = -1;
                    replaceY = -1;
                } else if (!text.equals("0")) {
                    if (replaceY != -1) {
                        searchY = replaceY;
                        m.setButton(btns[searchY][x], "C" + text);
                        m.setButton(btn, "C0");
                        replaceY = searchY += plus;
                    } else {
                        searchY = y;
                    }
                    searchNum = text;
                } else if (replaceY == -1) {
                    replaceY = y;
                }
            }
        }
    }

    public void horizontalMerge(Direction dir) {
        int x;
        int xStart;
        int plus;
        if (dir == Direction.LEFT) {
            xStart = 0;
            plus = 1;
        } else {
            xStart = 3;
            plus = -1;
        }
        for (int y = 0; y < 4; y++) {
            int searchX = -1;
            int replaceX = -1;
            String searchNum = "";
            x = xStart;
            for (; 0 <= x && x < 4; x += plus) {
                Button btn = btns[y][x];
                String text = btn.getText().toString();
                if (text.equals(searchNum)) {
                    int num = Integer.parseInt(text) * 2;
                    Button btnToChange = btns[y][searchX];
                    m.setButton(btnToChange, "C" + num);
                    m.setButton(btn, "C0");
                    searchX = -1;
                    replaceX = -1;
                } else if (!text.equals("0")) {
                    if (replaceX != -1) {
                        searchX = replaceX;
                        m.setButton(btns[y][searchX], "C" + btn.getText().toString());
                        m.setButton(btn, "C0");
                        replaceX = searchX += plus;
                    } else {
                        searchX = x;
                    }
                    searchNum = text;
                } else if (replaceX == -1) {
                    replaceX = x;
                }
            }
        }
    }
}
