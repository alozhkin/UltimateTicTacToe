package core;

public interface Board {
    Chip winner();
    Cell makeTurn(int numberOfCell, Chip chip);
    void print();
}

