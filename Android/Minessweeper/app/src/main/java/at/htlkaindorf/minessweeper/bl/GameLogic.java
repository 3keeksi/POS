package at.htlkaindorf.minessweeper.bl;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.IntFunction;

import at.htlkaindorf.minessweeper.MainActivity;
import at.htlkaindorf.minessweeper.beans.Field;
import at.htlkaindorf.minessweeper.beans.FieldState;

public class GameLogic {
    private Field[][] fields = new Field[9][9];
    private boolean firstMove = true;
    private int[] pos = new int[2];
    private Random rand = new Random();
    private MainActivity main;
    private int remaining = 9;

    public GameLogic(MainActivity main) {
        this.main = main;
        clearFields();
    }

    public void placeMines(int notY, int notX) {
        int toPlace = 9;
        int[] combinations = {-1, 0, 1};
        int y, x;
        do {
            do {
                y = rand.nextInt(9);
                x = rand.nextInt(9);
            } while (y == notY && x == notX);
            Field field = fields[y][x];
            if (rand.nextBoolean() && !field.isBomb()) {
                field.setBomb(true);
                toPlace--;
                Log.e("logic", toPlace + "");
                for (int c1 : combinations) {
                    for (int c2 : combinations) {
                        if (c1 == 0 && c2 == 0) {
                            continue;
                        }
                        int yNew = y + c1;
                        int xNew = x + c2;
                        if (yNew >= 0 && yNew < 9 && xNew >= 0 && xNew < 9) {
                            Field newField = fields[yNew][xNew];
                            newField.setNearBombs(newField.getNearBombs() + 1);
                        }
                    }
                }
            }
        } while (toPlace > 0);
    }

    public void clearFields() {
        fields = new Field[9][9];

        for (int y = 0; y < fields.length; y++) {
            Arrays.setAll(fields[y], value -> new Field());
        }
    }

    public boolean makeMove(int y, int x) {
        Field field = fields[y][x];
        if (field.getNearBombs() == 0) {
            // TODO

            return true;
        } else {
            field.setState(FieldState.DISCOVERED);
            return false;
        }
    }

    public Field[][] getFields() {
        return fields;
    }

    public void setFields(Field[][] fields) {
        this.fields = fields;
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    public int[] getPos() {
        return pos;
    }

    public void setPos(int[] pos) {
        this.pos = pos;
    }

    public Random getRand() {
        return rand;
    }

    public void setRand(Random rand) {
        this.rand = rand;
    }

    public MainActivity getMain() {
        return main;
    }

    public void setMain(MainActivity main) {
        this.main = main;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }
}
