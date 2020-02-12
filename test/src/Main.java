import game.Game;
import gameView.GamePresenter;
import gameView.GameView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import setupView.SetupPresenter;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Game model = new Game();
        GameView view = new GameView();
        GamePresenter presenter = new GamePresenter(view, model);
        Scene scene = new Scene(presenter.getView());
        stage.setTitle("Stratego");
        stage.setScene(scene);
        stage.show();
    }
}
