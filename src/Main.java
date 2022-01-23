import controllers.MainController;
import models.Player;
import models.TicTacToe;
import views.MainView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Startpunt van de applicatie
 *
 * @author HvA HBO-ICT
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        MainController mainController = new MainController();
        MainView mainView = new MainView(mainController);
        mainView.show();



        TicTacToe game = new TicTacToe();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
