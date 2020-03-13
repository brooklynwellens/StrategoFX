package view.gameResultView;

import model.game.GameResult;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import model.game.Game;
import model.game.GameSetup;
import view.setupView.SetupPresenter;
import view.setupView.SetupView;

import java.util.Set;

public class GameResultPresenter {
    private GameResult model;
    private GameResultView view;

    public GameResultPresenter(GameResult model, GameResultView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
    }

    private void addEventHandlers() {
        view.getBtnStartNew().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                GameSetup model = new GameSetup();
                SetupView  view = new SetupView();
                SetupPresenter presenter = new SetupPresenter(view, model);
                view.getScene().setRoot(view);
            }
        });
        view.getBtnExit().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Alert alert = new Alert(Alert.AlertType.NONE);
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
}
