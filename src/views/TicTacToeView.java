package views;

import controllers.TicTacToeController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class TicTacToeView {
    private static final String TITLE = "Play TicTacToe";

    private VBox content;
    private GridPane gridPane;
    private Label matchLabel;
    private Label turnLabel;

    private TicTacToeController controller;

    public TicTacToeView(TicTacToeController controller) {
        this.controller = controller;

        createRoot();
        setupController();
    }

    private void createRoot() {
        content = new VBox();
        content.setStyle("-fx-background-color: white");
        content.setMaxWidth(Double.MAX_VALUE);
        content.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));

        matchLabel = new Label();
        turnLabel = new Label();

        Border border = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
        gridPane = new GridPane();
        gridPane.setBorder(border);
        gridPane.setMaxSize(300,300);

        content.setAlignment(Pos.CENTER);
        content.getChildren().addAll(matchLabel, turnLabel, gridPane);
    }

    private void setupController() {
        controller.setMatchLabel(matchLabel);
        controller.setTurnLabel(turnLabel);
        controller.setGridPane(gridPane);
        controller.initialize();
    }

    public BorderPane changeBorderPane(BorderPane borderPane) {
        borderPane.setCenter(content);

        return borderPane;
    }
}
