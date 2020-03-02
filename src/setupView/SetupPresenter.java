package setupView;

import game.GameSetup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unit.Unit;
import common.Position;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;


public class SetupPresenter {

    private SetupView view;
    private GameSetup model;

    public SetupPresenter(SetupView view, GameSetup model) {
        this.view = view;
        this.model = model;
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        for (Node btn : view.getBoard().getChildren()) {
            if (GridPane.getRowIndex(btn) > 5) {
                    btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            int x = GridPane.getColumnIndex(btn);
                            int y = GridPane.getRowIndex(btn);
                            Unit unitToPlace = view.getListOfUnplacedUnits().getSelectionModel().getSelectedItem();
                            model.setUnitPosition(unitToPlace, new Position(x,y));
                            String imagePath = (model.getPlacedUnit().getColor() + "_" + model.getPlacedUnit().getRank()).toLowerCase() + ".png";
                            ((Button) btn).setGraphic(new ImageView(new Image(imagePath, 60, 60, false, false)));
                            updateView();
                        }
                    });
            }
        }
    }

    public void updateView() {
        ListView<Unit> units = view.getListOfUnplacedUnits();
        ObservableList<Unit> obsList = FXCollections.observableArrayList(model.getUnplacedUnits());
        units.setItems(obsList);
    }

    public SetupView getView() {
        return view;
    }
}
