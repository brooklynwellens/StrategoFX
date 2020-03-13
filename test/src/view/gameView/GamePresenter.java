package view.gameView;

import model.game.Game;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
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
        addEventHandlers();
        updateView();
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
            String text = "";
            if (model.getUnitOnTile(x, y) != null) {
                text = model.getUnitOnTile(x, y).getRank().name();
            }
            ((Button) rect).setText(text);
        }
    }
}
