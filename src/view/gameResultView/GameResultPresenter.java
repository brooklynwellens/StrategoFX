package view.gameResultView;

import model.game.GameResult;

public class GameResultPresenter {
    private GameResult model;
    private GameResultPresenter presenter;

    public GameResultPresenter(GameResult model, GameResultPresenter presenter) {
        this.model = model;
        this.presenter = presenter;
        addEventHandlers();
    }

    private void addEventHandlers() {

    }
}
