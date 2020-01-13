package at.htlkaindorf.exa_205_game2048.bl;

import android.util.Log;
import android.widget.Button;

import java.util.Arrays;
import java.util.Random;

import at.htlkaindorf.exa_205_game2048.MainActivity;
import at.htlkaindorf.exa_205_game2048.beans.Direction;

public class GameLogic {
    private int[][] values = new int[4][4];
    private int fieldsSet = 0;
    private int points = 0;
    private MainActivity m;
    private Random rand = new Random();
    private boolean moved = false;

    public GameLogic(MainActivity m) {
        this.m = m;
        for (int[] array : values) {
            Arrays.fill(array, 0);
        }
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
        if(moved) {
            setNewValue();
            m.setPoints(points);
            moved = false;
            m.setIvDirBG(dir);
            m.setButtons(values);
        }
    }

    public boolean checkIfLost() {
        if (fieldsSet >= 16) {
            int [][] valuesCopy = new int[values.length][];
            int fieldsSetCopy = fieldsSet;
            for(int i = 0; i < values.length; i++) valuesCopy[i] = values[i].clone();
            verticalMerge(Direction.UP);
            horizontalMerge(Direction.RIGHT);
            if(!moved) {
                return true;
            }
            values = valuesCopy;
            fieldsSet = fieldsSetCopy;
        }
        return false;
    }

    public void resetGame() {
        clearLayout();
        points = 0;
        fieldsSet = 0;
        moved = false;
        setNewValue();
        setNewValue();
        m.setPoints(points);
        m.setButtons(values);
    }

    public void clearLayout() {
        for (int i = 0; i < values.length; i++) {
            Arrays.fill(values[i], 0);
        }
    }

    public void setNewValue() {
        if(fieldsSet >=16) return;
        boolean set = false;
        do {
            int y = rand.nextInt(4);
            int x = rand.nextInt(4);
            int value = values[y][x];
            if (value == 0) {
                set = true;
                fieldsSet++;
                int num = rand.nextInt(3) < 2 ? 2 : 4;
                values[y][x] = num;
                points += num;
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
            int searchNum = -1;
            y = yStart;
            for (; 0 <= y && y < 4; y += plus) {
                int btnValue = values[y][x];
                if (searchNum == btnValue) {
                    int num = btnValue * 2;
                    values[searchY][x] = num;
                    values[y][x] = 0;
                    moved = true;
                    fieldsSet--;
                    replaceY = searchY + plus;
                    searchNum = -1;
                    searchY = -1;
                } else if (btnValue != 0) {
                    if (replaceY != -1) {
                        moved = true;
                        searchY = replaceY;
                        values[searchY][x] = btnValue;
                        values[y][x] = 0;
                        replaceY = searchY + plus;
                    } else {
                        searchY = y;
                    }
                    searchNum = btnValue;
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
//            String searchNum = "";
            int searchNum = -1;
            x = xStart;
            for (; 0 <= x && x < 4; x += plus) {
                int btnValue = values[y][x];
                if (searchNum == btnValue) {
                    int num = btnValue * 2;
                    values[y][searchX] = num;
                    values[y][x] = 0;
                    moved = true;
                    fieldsSet--;
                    replaceX = searchX + plus;
                    searchX = -1;
                    searchNum = -1;
                } else if (btnValue != 0) {
                    if (replaceX != -1) {
                        searchX = replaceX;
                        values[y][searchX] = btnValue;
                        values[y][x] = 0;
                        moved = true;
                        replaceX = searchX + plus;
                    } else {
                        searchX = x;
                    }
                    searchNum = btnValue;
                } else if (replaceX == -1) {
                    replaceX = x;
                }
            }
        }
    }
}
