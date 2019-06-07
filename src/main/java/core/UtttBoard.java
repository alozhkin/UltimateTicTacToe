package core;

import java.util.*;

//клетки выводятся сверху вниз 0 3 6
//                             1 4 7
//                             2 5 8
public class UtttBoard implements Board {

    private TTTBoard[] boards = new TTTBoard[9];

    private TTTBoard[][] combos = new TTTBoard[8][3];
    private Integer nextBoard = null;
    private Map<Integer, Integer> mapOfPreviousNextBoards = new HashMap<>();
    private Deque<Integer> previousTurns = new ArrayDeque<>();
    private TwoPlayersChip move = TwoPlayersChip.PLAYER1;

    public Integer getNextBoardNum() {
        return nextBoard;
    }

    public TTTBoard[][] getCombos() {
        return combos;
    }

    public TTTBoard[] getBoards() {
        return boards;
    }

    public TTTBoard getBoard(int a) {
        return boards[a];
    }

    public UtttBoard() {
        TTTCell[] cells = new TTTCell[81];
        for (int i = 0; i < 81; i++) {
            cells[i] = new TTTCell(i);
        }
        for (int i = 0; i < 9; i++) {
            TTTCell[] aCells = new TTTCell[9];
            System.arraycopy(cells, i * 9, aCells, 0, 9);
            boards[i] = new TTTBoard(i, aCells);
        }
        for (int i = 0; i < 3; i++) {
            combos[0][i] = boards[i];
            combos[1][i] = boards[i + 3];
            combos[2][i] = boards[i + 6];
            combos[3][i] = boards[i * 3];
            combos[4][i] = boards[i * 3 + 1];
            combos[5][i] = boards[i * 3 + 2];
            combos[6][i] = boards[i * 4];
            combos[7][i] = boards[i * 2 + 2];
        }
    }

    public UtttBoard(TTTBoard... array) {
        if (array.length != 9) throw new IllegalArgumentException();
        System.arraycopy(array, 0, boards, 0, 9);
        for (int i = 0; i < 3; i++) {
            combos[0][i] = boards[i];
            combos[1][i] = boards[i + 3];
            combos[2][i] = boards[i + 6];
            combos[3][i] = boards[i * 3];
            combos[4][i] = boards[i * 3 + 1];
            combos[5][i] = boards[i * 3 + 2];
            combos[6][i] = boards[i * 4];
            combos[7][i] = boards[i * 2 + 2];
        }
    }

    @Override
    public Chip winner() {
        boolean hasEmpty = false;
        for (TTTBoard[] array : combos) {
            Chip chip = array[0].winner();
            if (array[0].winner == null) {
                if (!hasEmpty) hasEmpty = true;
                continue;
            }
            if (chip == array[1].winner() && chip == array[2].winner()) {
                return chip;
            }
        }
        if (!hasEmpty) {
            return TwoPlayersChip.EMPTY;
        }
        return null;
    }

    @Override
    public void print() {
        for (TTTBoard array: boards) {
            array.print();
            System.out.println();
        }
    }

    @Override
    public Cell makeTurn(int numberOfCell, Chip chip) {
        if (numberOfCell < 0 || numberOfCell > 80) return null;
        int boardNum = numberOfCell / 9;
        int boardBasedCellNum = numberOfCell % 9;
        if (nextBoard != null && boardNum != nextBoard) return null;
        Cell c = boards[boardNum].makeTurn(boardBasedCellNum, chip);
        if (c == null) return null;
        previousTurns.addFirst(numberOfCell);;
        mapOfPreviousNextBoards.put(numberOfCell, nextBoard);
        nextBoard = boards[boardBasedCellNum].winner() == null ? boardBasedCellNum : null;
        return c;
    }

    public Cell takeTurnBack() {
        Integer previousTurn = previousTurns.pollFirst();
        if (previousTurn == null) return null;
        int boardNum = previousTurn / 9;
        int boardBasedCellNum = previousTurn % 9;
        Cell c = boards[boardNum].takeTurnBack(boardBasedCellNum);
        nextBoard = mapOfPreviousNextBoards.get(previousTurn);
        mapOfPreviousNextBoards.remove(previousTurn);
        return c;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (TTTBoard array: boards) {
            sb.append(array.toString());
        }
        return sb.toString();
    }
}
