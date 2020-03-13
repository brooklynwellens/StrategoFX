
import model.common.Position;
import model.fileManager.UnitFileManager;
import model.game.Game;
import model.game.GameSetup;
import model.unit.UnitManager;
import view.gameView.GamePresenter;
import view.gameView.GameView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.mainMenu.MainMenuPresenter;
import view.mainMenu.MainMenuView;
import view.setupView.SetupPresenter;
import view.setupView.SetupView;
import model.unit.Rank;
import model.unit.Unit;
import model.unit.UnitColor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
        List<Unit> unitlist = new UnitManager().getUnits();
        unitlist.get(1).captured();
        UnitFileManager manager = new UnitFileManager();
        manager.write("units", unitlist);
        unitlist = manager.read("units", unitlist);
        for (Unit unit : unitlist) {
            System.out.println(unit.isCaptured());
        }
    }

    @Override
    public void start(Stage stage) throws Exception {

        Map<Position, Unit> initialUnitPositions = new HashMap<>();
        initialUnitPositions.put(new Position(7,6), new Unit(Rank.General, UnitColor.BLUE));
        initialUnitPositions.put(new Position(6,6), new Unit(Rank.Captain, UnitColor.RED));
        initialUnitPositions.put(new Position(6,5), new Unit(Rank.Captain, UnitColor.RED));

        GameView view = new GameView();
        Game model = new Game(initialUnitPositions);
        GamePresenter presenter1 = new GamePresenter(view, model);
        MainMenuView mainMenuView = new MainMenuView();
        GameSetup setup = new GameSetup();
        SetupView setupView = new SetupView();
        SetupPresenter presenter2 = new SetupPresenter(setupView, setup);
        GameSetup setupModel = new GameSetup();
        MainMenuPresenter presenter3 = new MainMenuPresenter(setupModel, mainMenuView);
        mainMenuView.setPrefSize(1200,800);

        Scene scene = new Scene(presenter3.getView());
        stage.setTitle("Stratego");
        stage.setScene(scene);
        stage.show();
    }
}