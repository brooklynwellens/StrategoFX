package view.boardView;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class BoardView extends GridPane {

    public BoardView() {
        initializeNodes();
        layoutNodes();
    }

    private void initializeNodes() {
        int paneRows = 10;
        for (int i = 0; i < paneRows; i++) {
            int paneColumns = 10;
            for (int j = 0; j < paneColumns; j++) {
                Button btn = new Button();
                this.add(btn,i,j);
            }
        }
    }

    private void layoutNodes() {
        this.getStylesheets().add("/stylesheets/css.css");
        Image image = new Image("grid.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(1.0,1.0, true, true, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        this.setBackground(background);
        this.setAlignment(Pos.CENTER);
        for (Node btn : this.getChildren()) {
            ((Button)btn).setMinSize(75,75);
            ((Button)btn).setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
            GridPane.setHgrow(btn, Priority.ALWAYS);
            GridPane.setVgrow(btn, Priority.ALWAYS);
            GridPane.setFillWidth(btn, true);
            GridPane.setFillHeight(btn, true);
        }
    }
}