import SetupView.*;
import game.Game;
import gameView.GamePresenter;
import gameView.GameView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        game.printUnits();
    }
}
