package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class UtttAI2 {
//    UtttBoard board;
//    private int maxDepth;
//    private TwoPlayersChip turn;
//    private TwoPlayersChip firstTurn;
//
//    void launch(TwoPlayersChip player, int maxDepth) {
//        if (maxDepth < 1 || player == TwoPlayersChip.EMPTY) throw new IllegalArgumentException();
//        this.maxDepth = maxDepth;
//        this.turn = player;
//        minMax(player, Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
//    }
//
//    public int getHeuristicEvaluation() {
//        Random r = new Random();
//        return r.nextInt();
//    }
//
//    private int minMax(TwoPlayersChip player, int alpha, int beta, int depth) {
//        if (depth == maxDepth || board.winner() != null) {
//            return getHeuristicEvaluation();
//        }
//        if (player == turn) {
//            return getMax(player, alpha, beta, depth);
//        } else {
//            return getMin(player, alpha, beta, depth);
//        }
//    }
//
//    private int getMax(TwoPlayersChip player, int alpha, int beta, int depth) {
//        Integer bestMove = null;
//
//        List<TTTCell> list = new ArrayList<>();
//        Integer a = board.getNextBoardNum();
//        if (a != null) {
//            Collections.addAll(list, board.getBoard(a).getCells());
//        } else {
//            for (TTTBoard el: board.getBoards()) {
//                Collections.addAll(list, el.getCells());
//            }
//        }
//
//        for (Cell el: list) {
//            if (board.makeTurn(el.getNumber(), player) != null) {
//                int test = minMax(player, alpha, beta, depth);
//                if (test > alpha) {
//                    alpha = test;
//                    bestMove = alpha;
//                }
//
//                if (alpha > beta) {
//                    break;
//                }
//                board.takeTurnBack();
//            }
//        }
//        if (bestMove != null) {
//            board.makeTurn(bestMove, );
//        }
//        return alpha;
//    }
//
//    private int getMin(TwoPlayersChip player, int alpha, int beta, int depth) {
//        Integer bestMove = null;
//
//        List<TTTCell> list = new ArrayList<>();
//        Integer a = board.getNextBoardNum();
//        if (a != null) {
//            Collections.addAll(list, board.getBoard(a).getCells());
//        } else {
//            for (TTTBoard el: board.getBoards()) {
//                Collections.addAll(list, el.getCells());
//            }
//        }
//
//        for (Cell el: list) {
//            if (board.makeTurn(el.getNumber(), player) != null) {
//                int test = minMax(player, alpha, beta, depth);
//                if (test < beta) {
//                    beta = test;
//                    bestMove = alpha;
//                }
//
//                if (alpha > beta) {
//                    break;
//                }
//                board.takeTurnBack();
//            }
//        }
//        if (bestMove != null) {
//            board.makeTurn(bestMove, firstTurn);
//        }
//        return beta;
//    }
}
