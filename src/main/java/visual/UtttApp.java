package visual;

import javafx.stage.Stage;

public class UtttApp extends UtttGui {

    @Override
    public void start(Stage stage) {
        ChoosePlayerDialog c = new ChoosePlayerDialog();
        c.showAndWait();
        super.setSettings(c.isPlayer1Computer, c.isPlayer2Computer, c.isHD);
        super.start(stage);
    }
}
