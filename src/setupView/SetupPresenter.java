package setupView;

import game.GameSetup;
import unit.Unit;
import common.Position;
import game.Game;
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
                            model.setUnitPosition(unitToPlace, x, y);
                            ((Button) btn).setText(model.getPlacedUnit().getRank().name());
                            updateView();
                            if (model.isSetupDone()) {
                                //transition to Game phase
                            }
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
