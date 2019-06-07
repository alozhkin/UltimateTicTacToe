package visual;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import core.*;
import core.Cell;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.SepiaTone;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

class UtttGui extends Application {

    private UtttBoard aUtttBoard;
    private Chip move = TwoPlayersChip.PLAYER1;
    private BiMap<TTTCell, Button> aUtttBoardButtons;
    private GridPane motherOfAllPanes;
    private Node highlightedBoard;
    private TTTCell highlightedCell;
    private TTTCell previousHighlightedCell;
    private boolean allHighlighted;
    private boolean isPlayer1Computer;
    private boolean isPlayer2Computer;
    private boolean bot;
    private BooleanProperty isBotMove;
    private boolean isHD;
    private Stage guiStage;
    UtttAI3 ai;
    Timer t;
    private static final int BOT_SPEED = 10;

    @Override
    public void start(Stage stage) {
        aUtttBoard = new UtttBoard();
        aUtttBoardButtons = HashBiMap.create();
        aUtttBoardButtons.clear();
        motherOfAllPanes = new GridPane();
        highlightedBoard = null;
        highlightedCell = null;
        previousHighlightedCell = null;
        allHighlighted = false;
        bot = false;
        isBotMove = new SimpleBooleanProperty();
        guiStage = stage;
        ai = new UtttAI3(aUtttBoard);
        BorderPane root = new BorderPane();

        for (TTTBoard el: aUtttBoard.getBoards()) {
            for (TTTCell cell : el.getCells()) {
                Button button = new Button();
                button.getStyleClass().add("defaultbutton");
                button.setOnAction(event -> {
                    TTTCell aCell = aUtttBoardButtons.inverse().get(button);
                    Cell n;
                    n = aUtttBoard.makeTurn(aCell.getNumber(), move);
                    if (n  != null) {
                        move = move.opposite();
                        highlightedCell = (TTTCell) n;
                        this.checkStateOfTheBoard();
                    }
                });
                aUtttBoardButtons.put(cell, button);
            }
        }

        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                GridPane pane = new GridPane();
                pane.setAlignment(Pos.CENTER);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        pane.add(aUtttBoardButtons.get(aUtttBoard.getBoard(a * 3 + b).getCell(i * 3 + j)), i, j);
                    }
                }
                pane.getStyleClass().add("defaultpane");
                motherOfAllPanes.add(pane, a, b);
            }
        }
        motherOfAllPanes.getStyleClass().add("motherofallpanes");
        motherOfAllPanes.setAlignment(Pos.CENTER);


        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(event -> stage.close());
        MenuItem restart = new MenuItem("Restart");
        restart.setOnAction(event -> this.restart());
        MenuItem rules = new MenuItem("Rules");
        rules.setOnAction(event -> {
            Hyperlink h = new Hyperlink("https://en.wikipedia.org/wiki/Ultimate_tic-tac-toe");
            h.setFont(new Font(18));
            h.setOnAction(event1 -> getHostServices().showDocument("https://en.wikipedia.org/wiki/Ultimate_tic-tac-toe"));
            GridPane p = new GridPane();
            p.setMinSize(100,50);
            p.getStyleClass().add("defaultpane");
            p.setAlignment(Pos.CENTER);
            p.add(h, 0, 0);
            Scene sc = new Scene(p);
            Stage st = new Stage();
            st.setScene(sc);
            st.show();
        });
        Menu game = new Menu("Game", null, restart, rules, exit);
        MenuBar menu = new MenuBar(game);
        menu.getStyleClass().add("menu");

        root.setCenter(motherOfAllPanes);
        root.setTop(menu);
        Scene scene = new Scene(root);
        String resolution = isHD ? "hd.css" : "fullhd.css";
        scene.getStylesheets().add(resolution);
        stage.setScene(scene);
        stage.setTitle("Tic-tac-toe");
        isBotMove.addListener((observable, oldValue, newValue) -> {
            aiMove();
        });
        stage.show();

        t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                if (move == TwoPlayersChip.PLAYER1 && isPlayer1Computer
                        || move == TwoPlayersChip.PLAYER2 && isPlayer2Computer) {
                    Platform.runLater(() -> {
                        bot = !bot;
                        isBotMove.setValue(bot);
                    });

                }
            }
        };
        t.scheduleAtFixedRate(tt, BOT_SPEED, BOT_SPEED);

    }

    private void checkStateOfTheBoard() {
        this.highlightBoard();
        this.highlightCell();
        this.findWinner();
    }

    private void nextMove() {
        if (move == TwoPlayersChip.PLAYER1 && isPlayer1Computer) aiMove();
        if (move == TwoPlayersChip.PLAYER2 && isPlayer2Computer) aiMove();
    }

    private void aiMove() {
        ai.launch((TwoPlayersChip) move);
        //System.out.println(aUtttBoardButtons.keySet().contains(ai.getMove()));
        TTTCell aCell = aUtttBoardButtons.inverse().get(aUtttBoardButtons.get(ai.getMove()));
        Cell n;
        n = aUtttBoard.makeTurn(aCell.getNumber(), move);
        if (n != null) {
            move = move.opposite();
            highlightedCell = (TTTCell) n;
            this.checkStateOfTheBoard();
        }

        //System.out.println(ai.getMove().getNumber());
    }

    private void highlightBoard() {
        if (allHighlighted) {
            ObservableList<Node> list = motherOfAllPanes.getChildren();
            for (int i = 0; i < 9; i++) {
                Node board = list.get(i);
                board.getStyleClass().clear();
                board.getStyleClass().add("defaultpane");
            }
            allHighlighted = false;
        } else if (highlightedBoard != null) {
            highlightedBoard.getStyleClass().clear();
            highlightedBoard.getStyleClass().add("defaultpane");
            highlightedBoard = null;
        }
        if (aUtttBoard.getNextBoardNum() != null) {
            ObservableList<Node> list = motherOfAllPanes.getChildren();
            highlightedBoard = list.get(aUtttBoard.getNextBoardNum());
            highlightedBoard.getStyleClass().clear();
            highlightedBoard.getStyleClass().add("highlightedboard");
        } else {
            ObservableList<Node> list = motherOfAllPanes.getChildren();
            for (int i = 0; i < 9; i++) {
                Node board = list.get(i);
                if (board.getEffect() == null) {
                    board.getStyleClass().clear();
                    board.getStyleClass().add("highlightedboard");
                }
            }
            allHighlighted = true;
        }
    }

    private void highlightCell() {
        if (previousHighlightedCell != null) {
            Button button = aUtttBoardButtons.get(previousHighlightedCell);
            button.getStyleClass().clear();
            if (previousHighlightedCell.getChip() == TwoPlayersChip.PLAYER1) {
                button.getStyleClass().add("player1button");
            } else {
                button.getStyleClass().add("player2button");
            }
        }
        if (highlightedCell != null) {
            Button button = aUtttBoardButtons.get(highlightedCell);
            button.getStyleClass().clear();
            if (highlightedCell.getChip() == TwoPlayersChip.PLAYER1) {
                button.getStyleClass().add("highlightedplayer1button");
            } else {
                button.getStyleClass().add("highlightedplayer2button");
            }
            previousHighlightedCell = highlightedCell;
        }
    }

    private void findWinner() {
        for (TTTBoard board: aUtttBoard.getBoards()) {
            Chip winner = board.winner();
            if (winner != null) {
                GridPane winningPane = (GridPane) motherOfAllPanes.getChildren().get(board.getNumber());
                if (winner == TwoPlayersChip.PLAYER1) {
                    winningPane.getChildren().clear();
                    winningPane.getStyleClass().add("player1winningpane");
                } else if (winner == TwoPlayersChip.PLAYER2) {
                    winningPane.getChildren().clear();
                    winningPane.getStyleClass().add("player2winningpane");
                } else {
                    winningPane.setEffect(new SepiaTone());
                    winningPane.setDisable(true);
                }
            }
        }
        Chip winner = aUtttBoard.winner();
        if (winner != null) {
            t.cancel();
            String winnerName;
            if (winner == TwoPlayersChip.PLAYER1) {
                winnerName = "Player1 won";
            } else if (winner == TwoPlayersChip.PLAYER2) {
                winnerName = "Player2 won";
            } else {
                winnerName = "Draw";
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, winnerName);
            alert.showAndWait();
            this.restart();
        }
    }

    void setSettings(boolean isPlayer1Computer, boolean isPlayer2Computer, boolean isHD) {
        this.isPlayer1Computer = isPlayer1Computer;
        this.isPlayer2Computer = isPlayer2Computer;
        this.isHD = isHD;
    }

    private void restart() {
        guiStage.close();
        this.start(new Stage());
    }

}