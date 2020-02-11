package at.htlkaindorf.minessweeper.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;

public class Field {
    private List<Integer[]> emptyNeighbours;
    private boolean bomb;
    private int nearBombs;
    private FieldState state;

    public Field() {
        this.bomb = false;
        this.nearBombs = 0;
        this.state = FieldState.NOT_DISCOVERED;
        this.emptyNeighbours = new ArrayList<>();
    }

    public boolean isBomb() {
        return bomb;
    }

    public void setBomb(boolean bomb) {
        this.bomb = bomb;
    }

    public int getNearBombs() {
        return nearBombs;
    }

    public void setNearBombs(int nearBombs) {
        this.nearBombs = nearBombs;
    }

    public FieldState getState() {
        return state;
    }

    public void setState(FieldState state) {
        this.state = state;
    }

    public List<Integer[]> getEmpty() {
        return emptyNeighbours;
    }

    public void setEmpty(List<Integer[]> empty) {
        this.emptyNeighbours = empty;
    }
}