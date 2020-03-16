package view.settingsView;

import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class SettingsView extends GridPane {
    private Label lblSettings;
    private Button btnFullscreen;
    private Button btnSound;
    private Button btnback;

    public SettingsView(){
        initialiseNodes();
        layoutNodes();
    }

    private void layoutNodes() {
        getStylesheets().add("/stylesheets/css.css");
        add(btnFullscreen, 3,2,2,1);
        add(btnSound, 3,3,3,1);
        add(btnback,3,4,4,1);
        setAlignment(Pos.CENTER);
        setBackground(new Background(new BackgroundImage(new Image("stratego.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                new BackgroundPosition(Side.LEFT,0,false,Side.BOTTOM,0,false),
                new BackgroundSize(BackgroundSize.AUTO,BackgroundSize.AUTO,true,true,true,true))));
    }

    private void initialiseNodes() {
        btnback = new Button("Back");
        btnFullscreen = new Button("Full-screen");
        btnSound = new Button("Sound on/off");
        lblSettings = new Label("Settings");
        lblSettings.setId("settingsLbl");


        //css id's aangeven
        btnback.setId("settingsBtn");
        btnFullscreen.setId("settingsBtn");
        btnSound.setId("settingsBtn");
    }

    public Button getBtnFullscreen() {
        return btnFullscreen;
    }

    public Button getBtnSound() {
        return btnSound;
    }

    public Button getBtnback() {
        return btnback;
    }
}

