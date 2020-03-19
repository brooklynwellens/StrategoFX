package view.gameResultView;

import javafx.scene.control.Label;
import model.game.GameResult;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import model.game.GameSetup;
import model.unit.UnitColor;
import view.setupView.SetupPresenter;
import view.setupView.SetupView;
import java.util.Optional;

public class GameResultPresenter {
    private GameResult model;
    private GameResultView view;

    public GameResultPresenter(GameResult model, GameResultView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        view.getBtnStartNew().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Continue confirmation");
                alert.setHeaderText("The game will start.");
                alert.setContentText("Are you sure you want to continue?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    GameSetup model = new GameSetup();
                    SetupView setupView = new SetupView();
                    SetupPresenter presenter = new SetupPresenter(setupView, model);
                    view.getScene().setRoot(setupView);
                }
            }
        });
        view.getBtnExit().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.initOwner(view.getScene().getWindow());
                alert.setTitle("Exit");
                alert.setHeaderText("Are you sure you want to exit?");
                alert.getButtonTypes().addAll(ButtonType.YES,ButtonType.NO);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES){
                    System.exit(0);
                }else if (alert.getResult() == ButtonType.NO){
                    alert.close();
                }
            }
        });
    }

    private void updateView() {
        UnitColor winner = model.getWinner();
        Label lblResult = view.getLblResult();
        Label lblMessage = view.getLblMessage();
        if (winner == UnitColor.RED) {
            lblResult.setText("You lost");
            lblMessage.setText("The red team has won the game, try again");
        } else  {
            lblResult.setText("You won");
            lblMessage.setText("Congratulations soldier you won the battle, see you next time!");
        }
    }
}