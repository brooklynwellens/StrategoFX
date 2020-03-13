package view.SettingsView;

import com.sun.tools.javac.Main;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.game.GameSetup;
import view.mainMenu.MainMenuPresenter;
import view.mainMenu.MainMenuView;

public class SettingsPresenter {
    private GameSetup model;
    private SettingsView view;
    private MainMenuView mainMenuView = new MainMenuView();


    public SettingsPresenter(GameSetup model, SettingsView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
        updateView();
    }

    private void setScreenStandard() {
        BorderPane.setMargin(view, new Insets(((double) (720 - 575) / 2), 0, 0, 160));
        view.setScaleX(1);
        view.setScaleY(1);
    }

    private void updateView() {
    }

    private void addEventHandlers() {
        view.getBtnFullscreen().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stage stage = (Stage) view.getScene().getWindow();
                if (!model.isFullscreen() && stage.isFullScreen()) {
                    stage.setFullScreen(false);
                    setScreenStandard();
                } else if (model.isFullscreen() && !stage.isFullScreen()) {
                    stage.setFullScreen(true);
                    BorderPane.setMargin(view, new Insets(((double) (1280 - 720) / 2), 0, 0, 515));
                    view.setScaleX(1.2);
                    view.setScaleY(1.2);
                } else {
                    setScreenStandard();
                }

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
