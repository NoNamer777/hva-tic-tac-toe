package views;

import controllers.AboutController;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

/**
 * UI van het "over"-scherm
 *
 * @author HvA HBO-ICT
 */
public class AboutView {

    private static final String TITLE = "About Competition";
    private static final String EXPLANATION = "Play TicTacToe\n";
    private TextArea message;

    private AboutController controller;

    /**
     * Maakt een instantie van de UI van het hoofdscherm
     *
     * @param controller Een instantie van de controller die nodig is om het hoofdscherm aan te sturen
     */
    public AboutView(AboutController controller) {
        this.controller = controller;
        createRoot();
        setupController();
    }

    private void createRoot() {
        message = new TextArea();
        message.setStyle("-fx-background-color: bisque");
        message.setMaxWidth(Double.MAX_VALUE);
        message.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
        message.setEditable(false);
        message.setText(EXPLANATION);
    }

    public BorderPane changeBorderPane(BorderPane borderPane) {
        borderPane.setCenter(message);
        return borderPane;
    }

    public void setupController() {
        controller.setMessage(message);
        controller.initialize();
    }
}
