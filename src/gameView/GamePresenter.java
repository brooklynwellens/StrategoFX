package gameView;

import common.Position;
import game.Game;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GamePresenter {

    GameView view;
    Game model;

    public GamePresenter(GameView view, Game model) {
        this.view = view;
        this.model = model;
    }

    public GameView getView() {
        return view;
    }

    private void addEventHandlers() {
        for (Node btn : view.getBoard().getChildren()) {
            btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    int x = GridPane.getColumnIndex(btn);
                    int y = GridPane.getRowIndex(btn);
                    model.selectUnit(x, y);
                }
            });
        }
    }

    private void updateView() {
        for (Node rect : view.getBoard().getChildren()) {
            int x = GridPane.getColumnIndex(rect);
            int y = GridPane.getRowIndex(rect);
        }
    }
}
