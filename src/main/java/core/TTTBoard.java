package core;

public class TTTBoard implements Board {

    Chip winner = null;
    private int number;

    TTTCell[] cells = new TTTCell[9];
    private TTTCell[][] combos = new TTTCell[8][3];

    public TTTCell[] getCells() {
        return cells;
    }

    public TTTCell getCell(int a) { return cells[a]; }

    public TTTCell[][] getCombos() {
        return combos;
    }

    public TTTBoard() {
        for (int i = 0; i < 9; i++) {
            cells[i] = new TTTCell(i);
        }
        for (int i = 0; i < 3; i++) {
            combos[0][i] = cells[i];
            combos[1][i] = cells[i + 3];
            combos[2][i] = cells[i + 6];
            combos[3][i] = cells[i * 3];
            combos[4][i] = cells[i * 3 + 1];
            combos[5][i] = cells[i * 3 + 2];
            combos[6][i] = cells[i * 4];
            combos[7][i] = cells[i * 2 + 2];
        }
    }

    public TTTBoard(int n, TTTCell... aCells) {
        number = n;
        if (aCells.length != 9) throw new IllegalArgumentException();
        System.arraycopy(aCells, 0, cells, 0, 9);
        for (int i = 0; i < 3; i++) {
            combos[0][i] = cells[i];
            combos[1][i] = cells[i + 3];
            combos[2][i] = cells[i + 6];
            combos[3][i] = cells[i * 3];
            combos[4][i] = cells[i * 3 + 1];
            combos[5][i] = cells[i * 3 + 2];
            combos[6][i] = cells[i * 4];
            combos[7][i] = cells[i * 2 + 2];
        }
    }

    @Override
    public Chip winner() {
        if (winner == null) {
            int count = 0;
            for (TTTCell[] array : combos) {
                Chip chip0 = array[0].getChip();
                Chip chip1 = array[1].getChip();
                Chip chip2 = array[2].getChip();
                if (chip0 != TwoPlayersChip.EMPTY && chip0 == chip1 && chip0 == chip2) {
                    winner = chip0;
                    return winner;
                }
                if (chip0 != TwoPlayersChip.EMPTY && chip1 != TwoPlayersChip.EMPTY && chip0 != chip1) {
                    count++;
                } else if (chip0 != TwoPlayersChip.EMPTY && chip2 != TwoPlayersChip.EMPTY && chip0 != chip2) {
                    count++;
                } else if (chip1 != TwoPlayersChip.EMPTY && chip2 != TwoPlayersChip.EMPTY && chip1 != chip2) {
                    count++;
                }
            }
            if (count == 8) {
                winner = TwoPlayersChip.EMPTY;
                return TwoPlayersChip.EMPTY;
            }
        } else {
            return winner;
        }
        return null;
    }

    @Override
    public Cell makeTurn(int numberOfCell, Chip turn) {
        if (numberOfCell < 0 || numberOfCell > 8 || !cells[numberOfCell].isEmpty()) return null;
        cells[numberOfCell].setChip(turn);
        return cells[numberOfCell];
    }

    @Override
    public void print() {
        for (int i = 0; i < 3; i++) {
            System.out.print(combos[0][i].getChip() + " ");
        }
        System.out.println();
        for (int i = 0; i < 3; i++) {
            System.out.print(combos[1][i].getChip() + " ");
        }
        System.out.println();
        for (int i = 0; i < 3; i++) {
            System.out.print(combos[2][i].getChip() + " ");
        }
        System.out.println();
    }

    public int getNumber() {
        return number;
    }

    public Cell takeTurnBack(int numberOfCell) {
        if (numberOfCell < 0 || numberOfCell > 8) return null;
        cells[numberOfCell].setChip(TwoPlayersChip.EMPTY);
        return new TTTCell(numberOfCell);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append(combos[0][i].getChip().toString());
        }
        for (int i = 0; i < 3; i++) {
            sb.append(combos[1][i].getChip().toString());
        }
        for (int i = 0; i < 3; i++) {
            sb.append(combos[2][i].getChip().toString());
        }
        return sb.toString();
    }
}
