import common.Position;
import game.Game;
import game.GameSetup;
import gameView.GamePresenter;
import gameView.GameView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import setupView.SetupPresenter;
import setupView.SetupView;
import unit.Rank;
import unit.Unit;
import unit.UnitColor;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Map<Unit, Position> initialUnitPositions = new HashMap<>();
        initialUnitPositions.put(new Unit(Rank.General, UnitColor.BLUE), new Position(9,9));
        initialUnitPositions.put(new Unit(Rank.Marshal, UnitColor.BLUE), new Position(8,9));
        initialUnitPositions.put(new Unit(Rank.General, UnitColor.RED), new Position(0,0));


        SetupView view2 = new SetupView();
        GameSetup setup = new GameSetup();
        SetupPresenter presenter2 = new SetupPresenter(view2, setup);
        GameView view = new GameView();
        Game model = new Game(initialUnitPositions);
        GamePresenter presenter = new GamePresenter(view, model);

        Scene scene = new Scene(presenter.getView());
        stage.setTitle("Stratego");
        stage.setScene(scene);
        stage.show();
    }
}
