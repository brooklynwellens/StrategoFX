package setupView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import unit.Unit;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;


public class SetupView extends HBox {

    private GridPane board;
    private ListView<Unit> listOfUnplacedUnits;
    private VBox vBox;
    private TextArea infoText;
    private Button continueBtn;
    private Button standardConfigBtn;
    private Button exitBtn;

    public SetupView() {
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
        listOfUnplacedUnits = new ListView<>();
        vBox = new VBox();
        infoText = new TextArea("Welcome to the setup phase of Stratego. Place your units on the bottom 4 rows of the board. Units can be overwritten. A standard configuration is available. Click the next button once all your units have been placed and you want to continue.");
        continueBtn = new Button("Continue");
        standardConfigBtn = new Button("Use unit preset");
        exitBtn = new Button("Exit");
    }

    private void layoutNodes() {
        board.getStylesheets().add("test.css");
        infoText.getStylesheets().add("test.css");
        Image image = new Image("grid.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(750, 750, false, false, false, true);
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
        continueBtn.setMaxWidth(Double.MAX_VALUE);
        standardConfigBtn.setMaxWidth(Double.MAX_VALUE);
        exitBtn.setMaxWidth(Double.MAX_VALUE);
        this.setPadding(new Insets(10));
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
        this.getChildren().addAll(vBox, board, listOfUnplacedUnits);
        HBox.setHgrow(board, Priority.ALWAYS);
    }

    protected ListView<Unit> getListOfUnplacedUnits() {
        return listOfUnplacedUnits;
    }

    protected GridPane getBoard() {
        return board;
    }
}
