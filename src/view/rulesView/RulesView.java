package view.rulesView;

import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class RulesView extends GridPane {
    private Label titelLbl;
    private Button backBtn;
    private Label rulesLbl;

    public RulesView(){
        initialiseNodes();
        layoutNodes();
    }

    private void layoutNodes() {
        getStylesheets().add("/stylesheets/css.css");
        add(titelLbl, 3, 1, 2, 1);
        add(rulesLbl, 3,2,2,1);
        add(backBtn, 3,3,3,1);
        setAlignment(Pos.CENTER);
        setBackground(new Background(new BackgroundImage(new Image("stratego.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                new BackgroundPosition(Side.LEFT,0,false,Side.BOTTOM,0,false),
                new BackgroundSize(BackgroundSize.AUTO,BackgroundSize.AUTO,true,true,true,true))));
    }

    private void initialiseNodes() {
        titelLbl = new Label("Stratego Game rules");
        Path bestandPath = Paths.get("resources" + File.separator + "files" + File.separator + "rules.txt");
        try {
            Scanner fileScanner = new Scanner(bestandPath);
            StringBuilder builder = new StringBuilder();
            while (fileScanner.hasNext()){
                builder.append(fileScanner.nextLine());
                builder.append("\n");
            }
            rulesLbl = new Label(builder.toString());
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        backBtn = new Button("Back");
        backBtn.setId("main");
        rulesLbl.setId("rules");
        titelLbl.setId("titel");
    }

    public Button getBackBtn() {
        return backBtn;
    }
}
