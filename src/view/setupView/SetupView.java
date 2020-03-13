package view.setupView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.unit.Unit;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;


public class SetupView extends BorderPane {

    private GridPane board;
    private ListView<Unit> listOfUnplacedUnits;
    private VBox vBox;
    private TextArea infoText;
    private Button continueBtn;
    private Button standardConfigBtn;
    private Button exitBtn;
    private VBox rightVBox;
    private Label unplacedUnitsLabel;

    public SetupView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        board = new GridPane();
        int paneColumns = 10;
        int paneRows = 10;
        for (int i = 0; i < paneRows; i++) {
            for (int j = 0; j < paneColumns; j++) {
                Button btn = new Button();
                board.add(btn,i,j);
            }
        }
        board.setMaxWidth(500);
        board.setMaxHeight(500);
        listOfUnplacedUnits = new ListView<>();
        vBox = new VBox();
        infoText = new TextArea("Welcome to the setup phase of Stratego. Place your units on the bottom 4 rows of the board. Units can be overwritten. A standard configuration is available. Click the next button once all your units have been placed and you want to continue.");
        continueBtn = new Button("Continue");
        standardConfigBtn = new Button("Use standard unit preset");
        exitBtn = new Button("Exit");
        rightVBox = new VBox();
        unplacedUnitsLabel = new Label("Unplaced Units");
        unplacedUnitsLabel.setTextFill(Color.WHITE);
        unplacedUnitsLabel.setStyle("-fx-font-size: 15");

        //css id's benoemen
        continueBtn.setId("setupBtn");
        standardConfigBtn.setId("setupBtn");
        exitBtn.setId("setupBtn");
    }

    private void layoutNodes() {
        this.getStylesheets().add("/stylesheets/css.css");
        this.setBackground(new Background(new BackgroundImage(new Image("stratego.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                new BackgroundPosition(Side.LEFT,0,false,Side.BOTTOM,0,false),
                new BackgroundSize(BackgroundSize.AUTO,BackgroundSize.AUTO,true,true,true,true))));
        Image image = new Image("grid.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(1.0,1.0, true, true, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        board.setBackground(background);
        board.setAlignment(Pos.CENTER);
        listOfUnplacedUnits.setPrefWidth(200);
        vBox.setPrefWidth(200);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(0,10,0,0));
        listOfUnplacedUnits.setPadding(new Insets(0,0,0,10));
        infoText.setWrapText(true);
        infoText.setFont(new Font(15));
        infoText.setEditable(false);
        infoText.setPrefHeight(400);
        continueBtn.setMaxWidth(Double.MAX_VALUE);
        standardConfigBtn.setMaxWidth(Double.MAX_VALUE);
        exitBtn.setMaxWidth(Double.MAX_VALUE);
        rightVBox.getChildren().addAll(unplacedUnitsLabel, listOfUnplacedUnits);
        vBox.getChildren().addAll(infoText, continueBtn, standardConfigBtn, exitBtn);
        listOfUnplacedUnits.setEditable(true);
        for (Node btn : this.board.getChildren()) {
            ((Button)btn).setMinSize(75,75);
            ((Button)btn).setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
            GridPane.setHgrow(btn, Priority.ALWAYS);
            GridPane.setVgrow(btn, Priority.ALWAYS);
            GridPane.setFillWidth(btn, true);
            GridPane.setFillHeight(btn, true);
        }
        setCenter(board);
        setRight(rightVBox);
        setLeft(vBox);
        rightVBox.setMinSize(100,300);
        vBox.setMinSize(100,200);
    }

    protected ListView<Unit> getListOfUnplacedUnits() {
        return listOfUnplacedUnits;
    }

    protected GridPane getBoard() {
        return board;
    }

    protected Button getStandardConfigBtn() {
        return standardConfigBtn;
    }

    protected Button getExitBtn() {
        return exitBtn;
    }

    protected Button getContinueBtn() {
        return continueBtn;
    }

}