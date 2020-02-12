package gameView;

import common.Position;
import game.Game;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;

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

    }

    private void updateView() {
        for (Node rect : view.getBoard().getChildren()) {
            int x = GridPane.getColumnIndex(rect);
            int y = GridPane.getRowIndex(rect);
            if (model.getUnitAtPosition(new Position(x, y)) != null) {
                ((Rectangle) rect).setFill(Color.RED);
            }
        }
    }
}
