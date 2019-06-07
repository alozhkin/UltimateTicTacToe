package core;

import java.util.Objects;

public class TTTCell implements Cell {

    final private int number;
    private Chip chip;

    TTTCell(int number) {
        this.number = number;
        chip = TwoPlayersChip.EMPTY;
    }

    public TTTCell(int number, Chip chip) {
        this.number = number;
        this.chip = chip;
    }

    @Override
    public int getNumber() {
        return number;
    }

    public Chip getChip() {
        return chip;
    }

    void setChip(Chip chip) {
        this.chip = chip;
    }

    boolean isEmpty() {
        return chip == TwoPlayersChip.EMPTY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TTTCell tttCell = (TTTCell) o;
        return number == tttCell.number &&
                Objects.equals(chip, tttCell.chip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "TTTCell{" +
                "number=" + number +
                ", chip=" + chip +
                '}';
    }
}
