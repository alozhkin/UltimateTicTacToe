package visual;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

class ChoosePlayerDialog extends Dialog<ButtonType> {
    boolean isPlayer1Computer;
    boolean isPlayer2Computer;
    boolean isHD;
    private final static int fontSize = 16;

    ChoosePlayerDialog() {
        GridPane motherOfAllPanes = new GridPane();

        Label text1 = new Label("Choose player 1");
        text1.setFont(new Font(fontSize));
        RadioButton player1Computer = new RadioButton("Computer");
        player1Computer.setFont(new Font(fontSize));
        RadioButton player1Human = new RadioButton("Human being");
        player1Human.setFont(new Font(fontSize));
        ToggleGroup player1Group = new ToggleGroup();
        player1Computer.setToggleGroup(player1Group);
        player1Human.setToggleGroup(player1Group);
        player1Human.setSelected(true);
        EventHandler<ActionEvent> listener1 = event -> isPlayer1Computer = player1Computer.isSelected();
        player1Computer.setOnAction(listener1);
        player1Human.setOnAction(listener1);
        VBox leftBox = new VBox(text1, player1Computer, player1Human);
        leftBox.setPadding(new Insets(20));
        motherOfAllPanes.add(leftBox, 0, 0);

        Label text2 = new Label("Choose player 2");
        text2.setFont(new Font(fontSize));
        RadioButton player2Computer = new RadioButton("Computer");
        player2Computer.setFont(new Font(fontSize));
        RadioButton player2Human = new RadioButton("Human being");
        player2Human.setFont(new Font(fontSize));
        ToggleGroup player2Group = new ToggleGroup();
        player2Computer.setToggleGroup(player2Group);
        player2Human.setToggleGroup(player2Group);
        player2Human.setSelected(true);
        EventHandler<ActionEvent> listener2 = event -> isPlayer2Computer = player2Computer.isSelected();
        player2Computer.setOnAction(listener2);
        player2Human.setOnAction(listener2);
        VBox centerBox = new VBox(text2, player2Computer, player2Human);
        centerBox.setPadding(new Insets(20));
        motherOfAllPanes.add(centerBox, 1, 0);

        Label text3 = new Label("Choose screen resolution");
        text3.setFont(new Font(fontSize));
        RadioButton hd = new RadioButton("1280x720");
        hd.setFont(new Font(fontSize));
        RadioButton fullHD = new RadioButton("1920x1080");
        fullHD.setFont(new Font(fontSize));
        ToggleGroup resolutionGroup = new ToggleGroup();
        hd.setToggleGroup(resolutionGroup);
        fullHD.setToggleGroup(resolutionGroup);
        fullHD.setSelected(true);
        EventHandler<ActionEvent> listener3 = event -> isHD = hd.isSelected();
        hd.setOnAction(listener3);
        fullHD.setOnAction(listener3);
        VBox rightBox = new VBox(text3, hd, fullHD);
        rightBox.setPadding(new Insets(20));
        motherOfAllPanes.add(rightBox, 2, 0);

        DialogPane dp = new DialogPane();

        Button close = new Button("OK");
        close.setFont(new Font(fontSize));
        close.setOnAction(event -> {
            Stage stage = (Stage) close.getScene().getWindow();
            stage.close();
        });
        motherOfAllPanes.add(close, 0, 1);

        dp.setContent(motherOfAllPanes);
        this.setTitle("Select settings");
        this.setDialogPane(dp);
    }
}

