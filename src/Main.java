import model.common.Position;
import model.fileManager.UnitFileManager;
import model.game.Game;
import model.game.GameSetup;
import model.unit.UnitManager;
import model.unitPositionPreset.PlayerUnitPreset;
import view.gameView.GamePresenter;
import view.gameView.GameView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.setupView.SetupPresenter;
import view.setupView.SetupView;
import model.unit.Rank;
import model.unit.Unit;
import model.unit.UnitColor;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
        /*List<Unit> unitlist = new UnitManager().getUnits();
        unitlist.get(1).captured();
        UnitFileManager manager = new UnitFileManager();
        manager.write("units", unitlist);
        unitlist = manager.read("units", unitlist);
        for (Unit unit : unitlist) {
            System.out.println(unit.isCaptured());
        }*/
    }

    @Override
    public void start(Stage stage) throws Exception {


        Map<Position, Unit> initialUnitPositions = new HashMap<>();
        initialUnitPositions.put(new Position(7,6), new Unit(Rank.General, UnitColor.BLUE));
        initialUnitPositions.put(new Position(6,6), new Unit(Rank.Captain, UnitColor.RED));
        initialUnitPositions.put(new Position(6,5), new Unit(Rank.Captain, UnitColor.RED));


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
