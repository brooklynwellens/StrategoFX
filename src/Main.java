import game.Game;
import game.GameSetup;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import setupView.SetupPresenter;
import setupView.SetupView;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        GameSetup model = new GameSetup();
        SetupView view = new SetupView();
        SetupPresenter presenter = new SetupPresenter(view, model);
        Scene scene = new Scene(presenter.getView());
        stage.setTitle("Stratego");
        stage.setScene(scene);
        stage.show();
    }
}
