package view.gameView;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import model.unit.Unit;

public class GameView extends BorderPane {

    private GridPane board;
    private ListView<Unit> redCapturedUnits;
    private ListView<Unit> blueCapturedUnits;
    private Button saveBtn;
    private Button loadBtn;

    public GameView() {
        intialiseNodes();
        layoutNodes();
    }

    private void intialiseNodes() {
        board = new GridPane();
        int paneColums = 10;
        int paneRows = 10;
        for (int i = 0; i < paneRows; i++) {
            for (int j = 0; j < paneColums; j++) {
                Button btn = new Button();
                board.add(btn,i,j);
            }
        }
        redCapturedUnits = new ListView<>();
        blueCapturedUnits = new ListView<>();
        saveBtn = new Button("Save Game");
        loadBtn = new Button("Load Game");
    }

    private void layoutNodes() {
        board.getStylesheets().add("test.css");
        Image image = new Image("grid.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(1.0,1.0, true, true, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        board.setBackground(background);
        board.setAlignment(Pos.CENTER);
        for (Node btn : this.board.getChildren()) {
            ((Button)btn).setMinSize(75,75);
            ((Button)btn).setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
            GridPane.setHgrow(btn, Priority.ALWAYS);
            GridPane.setVgrow(btn, Priority.ALWAYS);
            GridPane.setFillWidth(btn, true);
            GridPane.setFillHeight(btn, true);
        }
        redCapturedUnits.setPrefHeight(50);
        redCapturedUnits.setOrientation(Orientation.HORIZONTAL);
        blueCapturedUnits.setPrefHeight(50);
        blueCapturedUnits.setOrientation(Orientation.HORIZONTAL);
        this.setPadding(new Insets(10));
        this.setCenter(board);
        this.setTop(blueCapturedUnits);
        this.setBottom(redCapturedUnits);
        this.setLeft(saveBtn);
        this.setRight(loadBtn);
    }

    protected GridPane getBoard() {
        return board;
    }

    protected ListView<Unit> getRedCapturedUnits() {
        return redCapturedUnits;
    }

    protected ListView<Unit> getBlueCapturedUnits() {
        return blueCapturedUnits;
    }

    protected Button getSaveBtn() {
        return saveBtn;
    }

    protected Button getLoadBtn() {
        return loadBtn;
    }
}
