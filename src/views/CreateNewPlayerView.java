package views;

import controllers.CreateNewPlayerController;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * UI for the view where a new player can be created manually
 * @author Oscar Wellner
 */
public class CreateNewPlayerView {

    private CreateNewPlayerController controller;
    private static final String TITLE = "Create New Player";
    private static final int WIDTH = 240;
    private static final int HEIGHT = 240;

    private Stage stage;
    private BorderPane borderPane;
    private HBox hbRow2;

    private Label lbHeader;
    private Label dummy1;
    private TextField tfPlayerName;
    private Label dummy2;
    private Label lbMessage;
    private Button btCreatePlayer;

    /**
     * Creates an instance of this UI
     * @param controller the controller that controls what happens on this view
     */
    public CreateNewPlayerView(CreateNewPlayerController controller) {
        this.controller = controller;

        stage = new Stage();
        stage.setTitle(TITLE);

        Parent root = createRoot();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setResizable(true);

        setupController();
    }

    /**
     * Determines what the base is for this view
     * @return a border pane that the base is for this view
     */
    private Parent createRoot() {
        borderPane = createBorderPane();

        return borderPane;
    }

    /**
     * Determines what elements are in the border pane
     * @return the border pane filled with various elements
     */
    private BorderPane createBorderPane() {
        hbRow2 = new HBox();

        lbHeader = new Label();
        dummy1 = new Label();
        tfPlayerName = new TextField();
        dummy2 = new Label();
        lbMessage = new Label();

        hbRow2.getChildren().addAll(dummy1, tfPlayerName, dummy2);

        btCreatePlayer = new Button();

        return new BorderPane(new VBox(lbHeader, hbRow2, lbMessage, btCreatePlayer));
    }

    /**
     * Passes multiple elements to the controller so that those elements can be interacted with
     */
    private void setupController() {
        controller.setLbHeader(lbHeader);
        controller.setDummy1(dummy1);
        controller.setTfPlayerName(tfPlayerName);
        controller.setDummy2(dummy2);
        controller.setLbMessage(lbMessage);
        controller.setBtCreatePlayer(btCreatePlayer);

        controller.setHbRow2(hbRow2);

        controller.initialize();
    }

    /**
     * Creates a new screen on top of the previous screen to show this UI
     */
    public void show() {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        stage.setX((primaryScreenBounds.getWidth() - WIDTH) / 2f);
        stage.setY((primaryScreenBounds.getHeight() - HEIGHT) / 2f);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);

        stage.show();
    }
}
