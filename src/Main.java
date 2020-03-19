import model.common.Position;
import model.fileManager.MediaPlayer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.game.GameResult;
import model.game.GameStatus;
import view.gameResultView.GameResultPresenter;
import view.gameResultView.GameResultView;
import view.mainMenu.MainMenuPresenter;
import view.mainMenu.MainMenuView;
import model.unit.Rank;
import model.unit.Unit;
import model.unit.UnitColor;
import java.util.HashMap;
import java.util.Map;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {


        Map<Position, Unit> initialUnitPositions = new HashMap<>();
        initialUnitPositions.put(new Position(7,6), new Unit(Rank.General, UnitColor.BLUE));
        initialUnitPositions.put(new Position(6,6), new Unit(Rank.Flag, UnitColor.RED));
        initialUnitPositions.put(new Position(6,6), new Unit(Rank.Flag, UnitColor.RED));


        MainMenuView mainMenuView = new MainMenuView();
        MainMenuPresenter mainMenuPresenter = new MainMenuPresenter(mainMenuView);
        MediaPlayer.playMusic();

        GameResult result = new GameResult(GameStatus.BLUE_NO_MOVES);
        GameResultView gameResultView = new GameResultView();
        GameResultPresenter gameResultPresenter = new GameResultPresenter(result, gameResultView);

       // Scene scene = new Scene(mainMenuPresenter.getView(),1200,800);
        Scene scene = new Scene(gameResultView,1200,750);
        stage.setTitle("Stratego");
        stage.setScene(scene);
        stage.show();
    }
}
