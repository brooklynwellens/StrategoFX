package view.settingsView;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.game.GameSetup;
import view.mainMenu.MainMenuPresenter;
import view.mainMenu.MainMenuView;

public class SettingsPresenter {
    private GameSetup model;
    private view.settingsView.SettingsView view;
    private MainMenuView mainMenuView = new MainMenuView();
    private boolean omkeren;

    public SettingsPresenter(GameSetup model, view.settingsView.SettingsView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
        updateView();
    }

    private void updateView() {
    }

    private void addEventHandlers() {
        view.getBtnFullscreen().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stage stage = (Stage) view.getScene().getWindow();
                stage.setFullScreen(true);

            }
        });

        view.getBtnSound().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!omkeren) {
                    MainMenuView mainMenuView = new MainMenuView();
                    mainMenuView.getMusic().play();
                    mainMenuView.getMusic().setCycleCount(100);

                }else{
                    mainMenuView.getMusic().stop();
                }
                    omkeren = !omkeren;
            }
        });

        view.getBtnback().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MainMenuPresenter presenter = new MainMenuPresenter(model, mainMenuView);
                view.getBtnback().getScene().setRoot(mainMenuView);
            }
        });
    }
}
