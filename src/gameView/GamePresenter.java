package gameView;

import common.Position;
import game.Game;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import unit.Unit;
import unit.UnitColor;

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
                    Position position = new Position(x, y);
                    if (!model.isUnitSelected()) {
                        model.selectUnit(position);
                    } else {
                        model.processMove(position);
                        updateView();
                        model.computerMove();
                        updateView();
                    }
                    updateView();
                }
            });
        }
    }

    private void updateView() {
        for (Node rect : view.getBoard().getChildren()) {
            int x = GridPane.getColumnIndex(rect);
            int y = GridPane.getRowIndex(rect);
            String text = "";
            Position position = new Position(x, y);
            Unit selectedUnit = model.getUnitOnTile(position);
            if (selectedUnit != null) {
                text = selectedUnit.getRank().name();
                if (selectedUnit.isColor(UnitColor.BLUE)) {
                    ((Button) rect).setTextFill(Color.BLUE);
                } else
                    ((Button) rect).setTextFill(Color.RED);
            }
            ((Button) rect).setText(text);
        }
    }
}
