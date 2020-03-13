package view.rulesView;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.game.Game;
import model.game.GameSetup;
import view.mainMenu.MainMenuPresenter;
import view.mainMenu.MainMenuView;

public class RulesPresenter {
    private GameSetup model;
    private RulesView view;

    public RulesPresenter(GameSetup model, RulesView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
        updateView();
    }

    private void updateView() {

    }

    private void addEventHandlers() {
        view.getBackBtn().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                GameSetup setup = new GameSetup();
                MainMenuView mainMenuView = new MainMenuView();
                MainMenuPresenter presenter = new MainMenuPresenter(setup, mainMenuView);
                view.getBackBtn().getScene().setRoot(mainMenuView);
            }
        });

    }
}
