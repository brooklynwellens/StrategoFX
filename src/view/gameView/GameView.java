package view.gameView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import view.boardView.BoardView;

public class GameView extends BorderPane {

    private GridPane board;
    private TilePane redCapturedUnits;
    private TilePane blueCapturedUnits;
    private VBox vBox;
    private Button saveBtn;
    private Button exitBtn;
    private Label log;

    public GameView() {
        intialiseNodes();
        layoutNodes();
    }

    private void intialiseNodes() {
        board = new BoardView();
        redCapturedUnits = new TilePane();
        blueCapturedUnits = new TilePane();

        vBox = new VBox();
        saveBtn = new Button("Save Game");
        exitBtn = new Button("Exit");
        log = new Label();
    }

    private void layoutNodes() {
        this.getStylesheets().add("stratego.css");
        saveBtn.setId("setupBtn");
        exitBtn.setId("setupBtn");
        redCapturedUnits.setId("capturedUnitsPane");
        blueCapturedUnits.setId("capturedUnitsPane");
        log.setId("logLbl");
        log.setMinWidth(200);
        Image image = new Image("stratego.png");
        BackgroundSize backgroundSize = new BackgroundSize(1.0,1.0, true, true, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        this.setBackground(background);
        redCapturedUnits.setPrefColumns(20);
        redCapturedUnits.setAlignment(Pos.CENTER_RIGHT);
        redCapturedUnits.setMinHeight(50);
        redCapturedUnits.setMaxWidth(board.getMaxWidth()*2);
        blueCapturedUnits.setMaxWidth(board.getMaxWidth()*2);
        blueCapturedUnits.setMinHeight(50);
        blueCapturedUnits.setAlignment(Pos.CENTER);
        blueCapturedUnits.setPrefColumns(20);
        vBox.setAlignment(Pos.BOTTOM_CENTER);
        this.setPadding(new Insets(10));
        this.setCenter(board);
        this.setTop(blueCapturedUnits);
        this.setBottom(redCapturedUnits);
        vBox.getChildren().addAll(log, saveBtn, exitBtn);
        board.setAlignment(Pos.CENTER);
        this.setLeft(vBox);
    }

    protected GridPane getBoard() {
        return board;
    }

    protected Button getSaveBtn() {
        return saveBtn;
    }

    protected Label getLog() {
        return log;
    }

    protected Button getExitBtn() {
        return exitBtn;
    }

    protected TilePane getRedCapturedUnits() {
        return redCapturedUnits;
    }

    public TilePane getBlueCapturedUnits() {
        return blueCapturedUnits;
    }
}
