package view.mainMenu;

import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;


public class MainMenuView extends GridPane {
    private Button btnStartNew;
    private Button btnPlay;
    private Button btnSettings;
    private Button btnRules;
    private Button btnQuit;
    private AudioClip music;

    public MainMenuView() {
        initialiseNodes();
        layoutNodes();
    }

    private void layoutNodes() {
        this.add(btnStartNew, 2, 1, 1, 1);
        this.add(btnPlay, 2, 2, 1, 1);
        this.add(btnSettings, 2, 3, 1, 1);
        this.add(btnRules, 2, 4, 1, 1);
        this.add(btnQuit, 2, 5, 1, 1);
        this.setAlignment(Pos.CENTER);
        this.setVgap(10);
        this.getStylesheets().add("stratego.css");
        Image image = new Image("stratego.png");
        BackgroundSize backgroundSize = new BackgroundSize(1.0,1.0, true, true, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        this.setBackground(background);
    }

    private void initialiseNodes() {
        btnStartNew = new Button("Start new game");
        btnStartNew.setId("main");
        btnPlay = new Button("Continue game");
        btnPlay.setId("main");
        btnSettings = new Button("Settings");
        btnSettings.setId("main");
        btnRules = new Button("Rules");
        btnRules.setId("main");
        btnQuit = new Button("Quit");
        btnQuit.setId("main");
        music = new AudioClip(this.getClass().getResource("/files/videoplayback.m4a").toString());

    }

    public Button getBtnQuit() {
        return btnQuit;
    }

    public Button getBtnStartNew() {
        return btnStartNew;
    }

    public Button getBtnPlay() {
        return btnPlay;
    }

    public Button getBtnSettings() {
        return btnSettings;
    }

    public Button getBtnRules() {
        return btnRules;
    }
}
