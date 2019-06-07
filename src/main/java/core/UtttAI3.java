package core;

import java.util.*;

public class UtttAI3 {
    UtttBoard board;
    TTTBoard tttBoard;
    TTTCell[][] combos;
    TwoPlayersChip turn;
    Cell move;

    public UtttAI3(UtttBoard board) {
        this.board = board;
    }

    public void launch(TwoPlayersChip turn) {
        this.turn = turn;
        Integer a = board.getNextBoardNum();
        if (a != null) {
            tttBoard = board.getBoard(board.getNextBoardNum());
            combos = tttBoard.getCombos();
        } else {
            List<TTTBoard> list = Arrays.asList(board.getBoards());
            for (TTTBoard b: list) {
                if (b.winner() == null) {
                    tttBoard = board.getBoard(b.getNumber());
                    combos = tttBoard.getCombos();
                    break;
                }
            }
        }
        find();
    }

    private void find() {
        if (!findWinning() && !findBlocking()) {
            Random a = new Random(System.currentTimeMillis());
            int i = a.nextInt(2);
            if (i == 0) {
                boolean b = moveCenter();
                if (!b)  {
                    boolean b2 = moveCorner();
                    if (!b2) {
                        move();
                    }
                }
            } else {
                boolean b = moveCorner();
                if (!b)  {
                    boolean b2 = moveCenter();
                    if (!b2) {
                        move();
                    }
                }
            }
        }
    }

    private boolean findWinning() {
        for (TTTCell[] combo: combos) {
            if (combo[0].getChip() == combo[1].getChip() && combo[0].getChip() == turn
                    && combo[2].getChip() == TwoPlayersChip.EMPTY) {
                move = combo[2];
                return true;
            }
            if (combo[1].getChip() == combo[2].getChip() && combo[1].getChip() == turn
                    && combo[0].getChip() == TwoPlayersChip.EMPTY) {
                move = combo[0];
                return true;
            }
            if (combo[0].getChip() == combo[2].getChip() && combo[0].getChip() == turn
                    && combo[1].getChip() == TwoPlayersChip.EMPTY) {
                move = combo[1];
                return true;
            }
        }
        return false;
    }

    private boolean findBlocking() {
        for (TTTCell[] combo: combos) {
            if (combo[0].getChip() == combo[1].getChip() && combo[0].getChip() == turn.opposite()
                    && combo[2].getChip() == TwoPlayersChip.EMPTY) {
                move = combo[2];
                return true;
            }
            if (combo[1].getChip() == combo[2].getChip() && combo[1].getChip() == turn.opposite()
                    && combo[0].getChip() == TwoPlayersChip.EMPTY) {
                move = combo[0];
                return true;
            }
            if (combo[0].getChip() == combo[2].getChip() && combo[0].getChip() == turn.opposite()
                    && combo[1].getChip() == TwoPlayersChip.EMPTY) {
                move = combo[1];
                return true;
            }
        }
        return false;
    }

    private boolean moveCenter () {
        boolean a = (combos[1][1].getChip() == TwoPlayersChip.EMPTY);
        if (a) move = combos[1][1];
        return a;
    }

    private boolean moveCorner () {
        List<TTTCell> moves = new ArrayList<>();
        moves.add(tttBoard.getCell(0));
        moves.add(tttBoard.getCell(2));
        moves.add(tttBoard.getCell(6));
        moves.add(tttBoard.getCell(8));
        Collections.shuffle(moves);
        for (int i = 0; i < 4; i++) {
            if (moves.get(i).getChip() == TwoPlayersChip.EMPTY) {
                move = moves.get(i);
                return true;
            }
        }
        return false;
    }

    private boolean move () {
        List<TTTCell> list = Arrays.asList(tttBoard.cells);
        for (int i = 0; i < 9; i++) {
            if (list.get(i).getChip() == TwoPlayersChip.EMPTY) {
                move = list.get(i);
                return true;
            }
        }
        return false;
    }

    public TTTCell getMove() {
        return (TTTCell) move;
    }
}