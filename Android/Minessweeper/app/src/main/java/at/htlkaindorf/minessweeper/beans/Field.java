package at.htlkaindorf.minessweeper.beans;

import java.util.Objects;

public class Field {
    private boolean bomb;
    private int nearBombs;
    private boolean discovered;

    public Field(boolean bomb, int nearBombs, boolean discovered) {
        this.bomb = isBomb;
        this.nearBombs = nearBombs;
        this.discovered = discovered;
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

    public boolean isDiscovered() {
        return discovered;
    }

    public void setDiscovered(boolean discovered) {
        this.discovered = discovered;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return isBomb() == field.isBomb() &&
                getNearBombs() == field.getNearBombs() &&
                isDiscovered() == field.isDiscovered();
    }

    @Override
    public int hashCode() {
        return Objects.hash(isBomb(), getNearBombs(), isDiscovered());
    }
}
