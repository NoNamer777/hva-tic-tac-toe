package views;

import controllers.PlayerStatsController;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Player;

/**
 * UI for the view where the leaderboard is presented
 * @author Oscar Wellner
 */
public class PlayerStatsView {

    private final PlayerStatsController controller;

    private VBox vbContent;
    private TableView<Player> tbPlayerStats;
    private HBox hbRow1;
    private HBox hbRow3;

    private Label lbHeader;
    private TableColumn<Player, String> colPlayerName;
    private TableColumn<Player, Integer> colPlayerWins;
    private TableColumn<Player, Integer> colPlayerDraws;
    private TableColumn<Player, Integer> colPlayerLosses;
    private TableColumn<Player, Integer> colPlayerPoints;
    private TextField tfSearchPlayer;
    private Label lbMessage;

    /**
     * Creates an instance of this UI
     * @param controller the controller that controls what happens on this view
     */
    public PlayerStatsView(PlayerStatsController controller) {
        this.controller = controller;

        createRoot();
        setupController();
    }

    /**
     * Determines what is on this view
     */
    private void createRoot() {
            hbRow1 = new HBox();
            lbHeader = new Label();

            tbPlayerStats = new TableView<>();
            colPlayerName = new TableColumn<>();
            colPlayerWins = new TableColumn<>();
            colPlayerDraws = new TableColumn<>();
            colPlayerLosses = new TableColumn<>();
            colPlayerPoints = new TableColumn<>();

            tbPlayerStats.getColumns().addAll(colPlayerName, colPlayerWins, colPlayerDraws, colPlayerLosses, colPlayerPoints);

            hbRow3 = new HBox();
            tfSearchPlayer = new TextField();
            lbMessage = new Label();

            hbRow3.getChildren().add(tfSearchPlayer);

            vbContent = new VBox();
            vbContent.getChildren().addAll(hbRow1, tbPlayerStats, hbRow3);
    }

    /**
     * Passes multiple elements to the controller so that those elements can be interacted with
     */
    private void setupController() {
        controller.setLbHeader(lbHeader);
        controller.setVbPlayerStats(tbPlayerStats);
        controller.setColPlayerName(colPlayerName);
        controller.setColPlayerWins(colPlayerWins);
        controller.setColPlayerDraws(colPlayerDraws);
        controller.setColPlayerLosses(colPlayerLosses);
        controller.setColPlayerPoints(colPlayerPoints);
        controller.setTfSearchPlayer(tfSearchPlayer);
        controller.setLbMessage(lbMessage);

        controller.setHbRow1(hbRow1);
        controller.setHbRow3(hbRow3);
        controller.setVbContent(vbContent);

        controller.initialize();
    }

    /**
     * changes the border pane in the mainView to the current border pane
     * @param borderPane the border pane of the mainView
     * @return the changed border pane with the content of this UI
     */
    public BorderPane changeBorderPane(BorderPane borderPane) {
        borderPane.setCenter(vbContent);
        controller.getPlayersArchive();

        return borderPane;
    }
}
