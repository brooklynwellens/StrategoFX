package gameView;

import common.Position;
import game.Game;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import unit.Unit;
import unit.UnitColor;

import java.util.List;

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
                        model.computerMove();
                    }
                    updateView();
                }
            });
        }
    }

    private void updateView() {
        List<Unit> visibleUnits = model.getVisibleUnits();
        for (Node btn : view.getBoard().getChildren()) {
            int x = GridPane.getColumnIndex(btn);
            int y = GridPane.getRowIndex(btn);
            Position position = new Position(x, y);
            Unit selectedUnit = model.getUnitOnTile(position);
            if (selectedUnit == null) {
                ((Button) btn).setGraphic(null);
            } else {
                if (selectedUnit.isColor(UnitColor.RED) && !visibleUnits.contains(selectedUnit)) {
                    ((Button) btn).setGraphic(new ImageView(new Image("red_back.png", 50, 50, false, false)));
                } else {
                    String imagePath = (selectedUnit.getColor() + "_" + selectedUnit.getRank()).toLowerCase() + ".png";
                    ((Button) btn).setGraphic(new ImageView(new Image(imagePath, 50, 50, false, false)));
                }
            }
        }
    }
}
