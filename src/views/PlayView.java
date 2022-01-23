package views;


import controllers.PlayController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class PlayView {
    private static final String TITLE = "Play a game";
    private static final int WIDTH = 300;
    private static final int HEIGHT = 600;

    private GridPane gridPane;
    private BorderPane borderPane;

    private PlayController controller;
    private Stage stage;

    private Label p1Label;
    private Label p2Label;
    private ComboBox p1ComboBox;
    private ComboBox p2ComboBox;
    private Button playButton;

    public PlayView(PlayController controller) {
        this.controller = controller;

        stage = new Stage();
        stage.setTitle(TITLE);

        Parent root = createRoot();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setResizable(false);

        setupController();
    }

    private Parent createRoot() {
        p1Label = new Label("Player 1 (X): ");
        p1ComboBox = new ComboBox();

        p2Label = new Label("Player 2 (O): ");
        p2ComboBox = new ComboBox();

        playButton = new Button("Play");
        playButton.setDisable(true);

        gridPane = new GridPane();
        gridPane.setVgap(4);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(5, 5, 5, 5));
        gridPane.add(new Label(""), 0, 0);
        gridPane.add(p1Label, 0, 1);
        gridPane.add(p1ComboBox, 1, 1);
        gridPane.add(p2Label, 0, 2);
        gridPane.add(p2ComboBox, 1, 2);
        gridPane.add(playButton, 0, 3);

        borderPane = new BorderPane();
        borderPane.setCenter(gridPane);

        return borderPane;
    }

    public void setupController() {
        controller.setP1ComboBox(p1ComboBox);
        controller.setP2ComboBox(p2ComboBox);
        controller.setPlayButton(playButton);
        controller.setBorderPane(borderPane);

        controller.initialize();
    }

    public BorderPane setBorderPane(BorderPane borderPane) {
        borderPane.setCenter(gridPane);

        return borderPane;
    }

    public void show() {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primaryScreenBounds.getWidth() - WIDTH) / 2f);
        stage.setY((primaryScreenBounds.getHeight() - HEIGHT) / 2f);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.show();
    }
}
