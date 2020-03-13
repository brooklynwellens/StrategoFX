package view.gameResultView;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.game.GameResult;
import model.game.GameStatus;
import model.unit.UnitColor;

public class GameResultView extends BorderPane {

    private VBox vBox;
    private Label lblMessage;
    private Label lblResult;
    private Button btnStartNew;
    private Button btnExit;

    public GameResultView() {
        initialiseNodes();
        layoutNodes();
    }

    private void layoutNodes() {
        this.getStylesheets().add("/stylesheets/css.css");
        vBox.getChildren().addAll(lblResult, lblMessage, btnStartNew, btnExit);
        setCenter(vBox);
    }

    private void initialiseNodes() {
        GameStatus status = GameStatus.RUNNING;
        GameResult gameResult = new GameResult(status);
        vBox= new VBox();
        if (gameResult.getWinner() == UnitColor.RED) {
            lblResult = new Label("You lost");
            lblMessage = new Label("The red team has won the game");
        }
        else if (gameResult.getWinner() == UnitColor.BLUE){
            lblResult = new Label("You won");
            lblMessage = new Label("Congratulations, you won!");
        }
        btnExit = new Button("Exit");
        btnStartNew = new Button("Start new game");
    }

    public Button getBtnStartNew() {
        return btnStartNew;
    }

    public Button getBtnExit() {
        return btnExit;
    }
}
