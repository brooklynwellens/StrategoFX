package view.setupView;

import model.game.GameSetup;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.unit.Unit;
import model.common.Position;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.unit.UnitColor;

import java.util.Optional;


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
                        model.setUnitPosition(unitToPlace, new Position(x, y));
                        String imagePath = (model.getPlacedUnit().getColor() + "_" + model.getPlacedUnit().getRank()).toLowerCase() + ".png";
                        ((Button) btn).setGraphic(new ImageView(new Image(imagePath, 50, 50, false, false)));
                        updateView();
                    }
                });
            }
        }
        Button standardConfigButton = view.getStandardConfigBtn();
        standardConfigButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Overwrite confirmation");
                alert.setHeaderText("Your currently placed units will be overwritten");
                alert.setContentText("Are you sure you want to continue?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    model.usePlayerUnitPreset();
                }
                updateBoard();
                updateView();
            }
        });
    }

    public void updateView() {
        ListView<Unit> units = view.getListOfUnplacedUnits();
        ObservableList<Unit> obsList = FXCollections.observableArrayList(model.getUnplacedUnits());
        units.setItems(obsList);
        units.setCellFactory(param -> new ListCell<Unit>() {
            private ImageView imageView = new ImageView();

            @Override
            public void updateItem(Unit unit, boolean empty) {
                super.updateItem(unit, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    String imagePath = (unit.getColor() + "_" + unit.getRank()).toLowerCase() + ".png";
                    imageView.setImage(new Image(imagePath, 50, 50, false, false));
                    setText(unit.getRank().name());
                    setGraphic(imageView);
                }
            }
        });
    }

    public void updateBoard() {
        for (Node btn : view.getBoard().getChildren()) {
            int x = GridPane.getColumnIndex(btn);
            int y = GridPane.getRowIndex(btn);
            Position position = new Position(x, y);
            Unit selectedUnit = model.getUnitAtPosition(position);
            if (selectedUnit == null) {
                ((Button) btn).setGraphic(null);
            } else if (selectedUnit.isColor(UnitColor.BLUE)) {
                String imagePath = (selectedUnit.getColor() + "_" + selectedUnit.getRank()).toLowerCase() + ".png";
                ((Button) btn).setGraphic(new ImageView(new Image(imagePath, 50, 50, false, false)));
            }
        }
    }

    public SetupView getView() {
        return view;
    }
}
