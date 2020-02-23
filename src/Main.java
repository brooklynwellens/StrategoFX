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

        Map<Position, Unit> initialUnitPositions = new HashMap<>();
        initialUnitPositions.put(new Position(9,9), new Unit(Rank.General, UnitColor.BLUE));
        initialUnitPositions.put(new Position(0,0), new Unit(Rank.General, UnitColor.RED));


        SetupView setupView = new SetupView();
        GameSetup setup = new GameSetup();
        SetupPresenter presenter2 = new SetupPresenter(setupView, setup);
        GameView view = new GameView();
        Game model = new Game(initialUnitPositions);
        GamePresenter presenter = new GamePresenter(view, model);

        Scene scene = new Scene(presenter2.getView());
        stage.setTitle("Stratego");
        stage.setScene(scene);
        stage.show();
    }
}
