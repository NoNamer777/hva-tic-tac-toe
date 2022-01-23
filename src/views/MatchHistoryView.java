package views;

import controllers.MatchHistoryController;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Game;

/**
 * UI for the view where an overview of passed games is presented and where games can be added manually
 * @author Oscar Wellner
 */
public class MatchHistoryView {

    private VBox vbContent;
    private HBox hbRow1, hbRow3, hbRow4;
    private Label lbHeader;
    private TableView tbMatchHistory;
    private TableColumn<Game, String> colPlayerName1;
    private TableColumn<Game, String> colPlayerName2;
    private TableColumn<Game, String> colWinner;
    private TableColumn<Game, String> colDateOfMatch;
    private TextField tfAddPlayer1;
    private TextField tfAddPlayer2;
    private TextField tfAddWinner;
    private Button btAddMatch;
    private TextField tfMatchDay;
    private TextField tfMatchMonth;
    private TextField tfMatchYear;
    private CheckBox cBisDraw;
    private Label lbMessage;

    private MatchHistoryController controller;

    /**
     * Creates an instance of this UI
     * @param controller the controller that controls what happens on this view
     */
    public MatchHistoryView(MatchHistoryController controller) {
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

        hbRow1.getChildren().add(lbHeader);

        tbMatchHistory = new TableView();
        colPlayerName1 = new TableColumn<>();
        colPlayerName2 = new TableColumn<>();
        colDateOfMatch = new TableColumn<>();
        colWinner = new TableColumn<>();

        tbMatchHistory.getColumns().addAll(colPlayerName1, colPlayerName2, colDateOfMatch, colWinner);

        hbRow3 = new HBox();
        tfAddPlayer1 = new TextField();
        tfAddPlayer2 = new TextField();
        tfAddWinner = new TextField();
        btAddMatch = new Button();

        hbRow3.getChildren().addAll(
                tfAddPlayer1,
                tfAddPlayer2,
                tfAddWinner,
                btAddMatch
        );

        hbRow4 = new HBox();
        tfMatchDay = new TextField();
        tfMatchMonth = new TextField();
        tfMatchYear = new TextField();
        cBisDraw = new CheckBox();
        lbMessage = new Label();

        hbRow4.getChildren().addAll(
                tfMatchDay,
                tfMatchMonth,
                tfMatchYear,
                cBisDraw,
                lbMessage
        );

        vbContent = new VBox();
        vbContent.getChildren().addAll(hbRow1, tbMatchHistory, hbRow3, hbRow4);
    }

    /**
     * Passes multiple elements to the controller so that those elements can be interacted with
     */
    private void setupController() {
        controller.setLbHeader(lbHeader);
        controller.setColPlayerName1(colPlayerName1);
        controller.setColPlayerName2(colPlayerName2);
        controller.setColDateOfMatch(colDateOfMatch);
        controller.setColWinner(colWinner);
        controller.setTfAddPlayer1(tfAddPlayer1);
        controller.setTfAddPlayer2(tfAddPlayer2);
        controller.setTfAddWinner(tfAddWinner);
        controller.setBtAddMatch(btAddMatch);
        controller.setTfMatchDay(tfMatchDay);
        controller.setTfMatchMonth(tfMatchMonth);
        controller.setTfMatchYear(tfMatchYear);
        controller.setCbIsDraw(cBisDraw);
        controller.setLbMessage(lbMessage);

        controller.setHbRow1(hbRow1);
        controller.setTbMatchHistory(tbMatchHistory);
        controller.setHbRow3(hbRow3);
        controller.setHbRow4(hbRow4);
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

        return borderPane;
    }
}
