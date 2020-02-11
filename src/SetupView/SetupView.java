package SetupView;

import unit.Unit;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class SetupView extends BorderPane {

    private GridPane board;
    private ListView<Unit> listOfUnplacedUnits;
    private TextArea setupInformation;
    private VBox vBox;

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
                Button btn = new Button("");
                btn.setMinSize(100,100);
                board.add(btn,i,j);
            }
        }
        listOfUnplacedUnits = new ListView<>();
        listOfUnplacedUnits.setEditable(true);
        setupInformation = new TextArea();
        setupInformation.setWrapText(true);
        setupInformation.appendText("This is the setup phase.");
        setupInformation.appendText("\nYou will place all your units on the bottom of the board.");
        setupInformation.appendText("\n");
    }

    private void layoutNodes() {
        this.setCenter(board);
        this.setRight(listOfUnplacedUnits);
        this.setLeft(setupInformation);
    }

    protected ListView<Unit> getListOfUnplacedUnits() {
        return listOfUnplacedUnits;
    }

    protected GridPane getBoard() {
        return board;
    }
}
