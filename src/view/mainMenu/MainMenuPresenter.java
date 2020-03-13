package view.mainMenu;

import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.fileManager.GameFileManager;
import model.game.Game;
import model.game.GameSetup;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import view.SettingsView.SettingsPresenter;
import view.SettingsView.SettingsView;
import view.gameView.GamePresenter;
import view.gameView.GameView;
import view.rulesView.RulesPresenter;
import view.rulesView.RulesView;
import view.setupView.SetupPresenter;
import view.setupView.SetupView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MainMenuPresenter {
    private GameSetup model;
    private MainMenuView view;
    private List<String> records = new ArrayList<String>();

    public MainMenuPresenter(GameSetup model,MainMenuView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
        updateView();
    }

    private void updateView() {
    }

    private void addEventHandlers() {
        view.getBtnQuit().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setTitle("Exit");
                alert.setHeaderText("Are you sure you want to exit?");
                alert.getButtonTypes().addAll(ButtonType.YES,ButtonType.NO);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES){
                    System.exit(0);
                }else if (alert.getResult() == ButtonType.NO){
                    alert.close();
                }
            }
        });

        view.getBtnRules().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                RulesView rulesView = new RulesView();
                RulesPresenter presenter = new RulesPresenter(model, rulesView);
                view.getScene().setRoot(rulesView);
            }
        });

        view.getBtnStartNew().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SetupView setupView = new SetupView();
                SetupPresenter setupPresenter = new SetupPresenter(setupView,model);
                view.getScene().setRoot(setupView);
            }
        });

        view.getBtnSettings().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                SettingsView settingsView = new SettingsView();
                SettingsPresenter presenter = new SettingsPresenter(model, settingsView);
                view.getScene().setRoot(settingsView);
            }
        });

        view.getBtnPlay().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                FileChooser fileChooser = new FileChooser();

                //Set extension filter for text files
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);

                Stage openStage = new Stage();

                //Show save file dialog
                File file = fileChooser.showOpenDialog(openStage);

                if (file != null) {
                    Game game = GameFileManager.load(file.getAbsolutePath());
                    GameView gameView = new GameView();
                    GamePresenter gamePresenter = new GamePresenter(gameView, game);
                    view.getScene().setRoot(gameView);
                    gameView.getScene().getWindow().sizeToScene();
                }
            }
        });
    }

    public Parent getView() {
        return view;
    }
}
