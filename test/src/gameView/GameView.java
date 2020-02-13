package gameView;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class GameView extends BorderPane {

    private GridPane board;

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
    }

    private void layoutNodes() {
        board.setPadding(new Insets(10));
        for (Node btn : this.board.getChildren()) {
            ((Button)btn).setMinSize(100,100);
        }
        this.setCenter(board);
    }

    protected GridPane getBoard() {
        return board;
    }
}
