import core.TTTBoard;
import core.TTTCell;
import core.TwoPlayersChip;
import core.UtttBoard;
import org.junit.Assert;
import org.junit.Test;

public class CoreTest {
    TwoPlayersChip player1Chip = TwoPlayersChip.PLAYER1;
    TwoPlayersChip emptyChip = TwoPlayersChip.EMPTY;
    TwoPlayersChip player2Chip = TwoPlayersChip.PLAYER2;
    @Test
    public void TTTBoardTest() {
        TTTBoard board = new TTTBoard();
        Assert.assertNull(board.makeTurn(-1, player1Chip));
        Assert.assertNull(board.makeTurn(10, player1Chip));
        board.makeTurn(0, player1Chip);
        Assert.assertNull(board.makeTurn(0, player1Chip));
        board.makeTurn(3, player1Chip);
        board.makeTurn(6, player1Chip);
        Assert.assertEquals(player1Chip, board.winner());
        TTTCell emptyCell = new TTTCell(0, emptyChip);
        TTTCell player1Cell = new TTTCell(0, player1Chip);
        TTTCell player2Cell = new TTTCell(0, player2Chip);
        TTTCell[] ar6 = { player1Cell, emptyCell, emptyCell, player1Cell, emptyCell, emptyCell, player1Cell, emptyCell, emptyCell };
        TTTBoard wonBoard = new TTTBoard(0, ar6);
        Assert.assertEquals(player1Chip, wonBoard.winner());
        TTTBoard emptyBoard = new TTTBoard();
        Assert.assertNull(emptyBoard.winner());
        TTTCell[] drawCells = { player1Cell, player2Cell, player1Cell, player1Cell, player2Cell, player1Cell,
                player2Cell, player1Cell, player2Cell};
        TTTBoard drawBoard = new TTTBoard(0, drawCells);
        Assert.assertEquals(emptyChip, drawBoard.winner());
    }

    @Test
    public void UtttBoardTest() {
        UtttBoard board = new UtttBoard();
        Assert.assertNull(board.makeTurn(-1, player1Chip));
        Assert.assertNull(board.makeTurn(101, player1Chip));
        board.makeTurn(0, player1Chip);
        Assert.assertEquals(new Integer(0), board.getNextBoardNum());
        Assert.assertNull(board.makeTurn(0, player1Chip));
        board.makeTurn(3, player1Chip);
        Assert.assertNull(board.makeTurn(1, player1Chip));
        Assert.assertEquals(new Integer(3), board.getNextBoardNum());
        board.makeTurn(27, player1Chip);
        Assert.assertEquals(new Integer(0), board.getNextBoardNum());
        TTTCell player1Cell = new TTTCell(0, player1Chip);
        TTTCell player2Cell = new TTTCell(0, player2Chip);
        TTTCell emptyCell = new TTTCell(0, emptyChip);
        TTTCell[] ar6 = { player1Cell, emptyCell, emptyCell, player1Cell, emptyCell, emptyCell, player1Cell, emptyCell, emptyCell };
        TTTBoard wonBoard = new TTTBoard(0, ar6);
        TTTBoard emptyBoard = new TTTBoard();
        TTTBoard[] ar = { wonBoard, wonBoard, wonBoard, wonBoard, wonBoard, wonBoard, wonBoard, wonBoard, wonBoard };
        board = new UtttBoard(ar);
        Assert.assertEquals(player1Chip, board.winner());
        TTTBoard[] ar2 = { emptyBoard, emptyBoard, emptyBoard, emptyBoard, emptyBoard, emptyBoard, emptyBoard, emptyBoard, emptyBoard };
        board = new UtttBoard(ar2);
        Assert.assertNull(board.winner());
        TTTCell[] drawCells = { player1Cell, player2Cell, player1Cell, player1Cell, player2Cell, player1Cell,
                player2Cell, player1Cell, player2Cell};
        TTTBoard drawBoard = new TTTBoard(0, drawCells);
        TTTBoard[] ar3 = { drawBoard, drawBoard, drawBoard, drawBoard, drawBoard, drawBoard, drawBoard, drawBoard, drawBoard};
        board = new UtttBoard(ar3);
        Assert.assertEquals(emptyChip, board.winner());
    }
}
