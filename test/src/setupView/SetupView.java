package setupView;

import javafx.geometry.Insets;
import javafx.scene.Node;
import unit.Unit;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class SetupView extends BorderPane {

    private GridPane board;
    private ListView<Unit> listOfUnplacedUnits;

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
    }

    private void layoutNodes() {
        board.setPadding(new Insets(10));
        listOfUnplacedUnits.setEditable(true);
        for (Node btn : this.board.getChildren()) {
            ((Button)btn).setMinSize(100,100);
        }
        this.setCenter(board);
        this.setRight(listOfUnplacedUnits);
    }

    protected ListView<Unit> getListOfUnplacedUnits() {
        return listOfUnplacedUnits;
    }

    protected GridPane getBoard() {
        return board;
    }
}
