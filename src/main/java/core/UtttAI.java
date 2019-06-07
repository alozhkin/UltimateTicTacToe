package core;

import java.util.*;

public class UtttAI {
//    final static int MIN_VALUE = 0;
//    final static int MAX_VALUE = 255;
//    Chip chip;
//    public Cell answer;
//    UtttBoard board;
//    int maxDepth;
//
//    public UtttAI(Chip chip, UtttBoard board, int maxDepth) {
//        this.chip = chip;
//        this.board = board;
//        this.maxDepth = maxDepth;
//    }
//
//    void run (TwoPlayersChip player, int maxDepth) {
//        if (maxDepth < 1) {
//            throw new IllegalArgumentException();
//        }
//        this.maxDepth = maxDepth;
//        runMinMax();
//    }
//
//    public int getHeuristicEvaluation() {
//        Random r = new Random();
//        return r.nextInt();
//    }
//
//    public int runMinMax(TwoPlayersChip turn, int depth, int alpha, int beta) {
//        int test = MAX_VALUE;
//        Cell bestMove = null;
//        boolean isPlayer1 = turn == TwoPlayersChip.PLAYER1;
//        int minMax = isPlayer1 ? MIN_VALUE : MAX_VALUE;
//
//        if (depth == 0 || board.winner() != null) {
//            return getHeuristicEvaluation();
//        }
//        List<TTTCell> list = new ArrayList<>();
//        Integer a = board.getNextBoardNum();
//        if (a != null) {
//            Collections.addAll(list, board.getBoard(a).getCells());
//        } else {
//            for (TTTBoard el: board.getBoards()) {
//                Collections.addAll(list, el.getCells());
//            }
//        }
////        for (TTTCell el: list) {
//            if (board.makeTurn(el.getNumber(), turn) != null) {
////                test = runMinMax((TwoPlayersChip) turn.opposite(), depth - 1, alpha, beta);
////                if ((test > minMax && turn == TwoPlayersChip.PLAYER1) ||
////                        (test <= minMax && turn == TwoPlayersChip.PLAYER2)) {
////                    minMax = test;
////                    bestMove = el;
////                }
////                if (isPlayer1) {
////                    alpha = Integer.max(alpha, test);
////                } else {
////                    beta = Integer.min(beta, test);
////                }
////                board.takeTurnBack();
////                if (alpha > beta) break;
////            }
////        }
////        if (depth == 5) {
////            answer = bestMove;
////        }
////        return minMax;
//
//        if (chip == turn) {
//
//        }
//    }
//
//    public int solverTTT() {
//        return 42;
//    }
}
