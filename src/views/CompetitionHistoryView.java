package views;

import controllers.CompetitionHistoryController;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Competition;

import java.time.LocalDate;

/**
 * UI for the view where an overview of passed competitions is presented
 * @author Oscar Wellner
 */
public class CompetitionHistoryView {

    private final CompetitionHistoryController controller;
    private HBox hbRow1;
    private Label lbHeader;
    private TableView tbCompetitions;
    private TableColumn<Competition, String> colCompetitionName;
    private TableColumn<Competition, String> colPlayer1;
    private TableColumn<Competition, String> colPlayer2;
    private TableColumn<Competition, String> colAmountMatches;
    private TableColumn<LocalDate, String> colDateCompetition;
    private TableColumn<Competition, String> colWinner;
    private HBox hbRow3;
    private TextField tfCompetitionName;
    private TextField tfAmountMatches;
    private TextField tfPlayer1;
    private TextField tfPlayer2;
    private TextField tfWinner;
    private HBox hbRow4;
    private TextField tfCompetitionDay;
    private TextField tfCompetitionMonth;
    private TextField tfCompetitionYear;
    private HBox hbRow5;
    private Button btAddCompetition;
    private Label lbMessage;
    private VBox vbContent;

    /**
     * Creates an instance of this UI
     * @param controller the controller that controls what happens on this view
     */
    public CompetitionHistoryView(CompetitionHistoryController controller) {
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

        hbRow1.getChildren().addAll(lbHeader);

        tbCompetitions = new TableView<>();
        colCompetitionName = new TableColumn<>();
        colPlayer1 = new TableColumn<>();
        colPlayer2 = new TableColumn<>();
        colAmountMatches = new TableColumn<>();
        colDateCompetition = new TableColumn<>();
        colWinner = new TableColumn<>();

        tbCompetitions.getColumns().addAll(colCompetitionName, colPlayer1, colPlayer2, colAmountMatches, colDateCompetition, colWinner);

        hbRow3 = new HBox();
        tfCompetitionName = new TextField();
        tfPlayer1 = new TextField();
        tfPlayer2 = new TextField();
        tfAmountMatches = new TextField();

        hbRow3.getChildren().addAll(tfCompetitionName, tfAmountMatches, tfPlayer1, tfPlayer2);

        hbRow4 = new HBox();
        tfWinner = new TextField();
        tfCompetitionDay = new TextField();
        tfCompetitionMonth = new TextField();
        tfCompetitionYear = new TextField();

        hbRow4.getChildren().addAll(tfWinner, tfCompetitionDay, tfCompetitionMonth, tfCompetitionYear);

        hbRow5 = new HBox();
        btAddCompetition = new Button();
        lbMessage = new Label();

        hbRow5.getChildren().addAll(btAddCompetition, lbMessage);

        vbContent = new VBox();
        vbContent.getChildren().addAll(hbRow1, tbCompetitions, hbRow3, hbRow4, hbRow5);
    }

    /**
     * Passes multiple elements to the controller so that those elements can be interacted with
     */
    private void setupController() {
        controller.setLbHeader(lbHeader);
        controller.setColCompetitionName(colCompetitionName);
        controller.setColPlayer1(colPlayer1);
        controller.setColPlayer2(colPlayer2);
        controller.setColAmountMatches(colAmountMatches);
        controller.setColDateCompetition(colDateCompetition);
        controller.setColWinner(colWinner);
        controller.setTfCompetitionName(tfCompetitionName);
        controller.setTfPlayer1(tfPlayer1);
        controller.setTfPlayer2(tfPlayer2);
        controller.setTfAmountMatches(tfAmountMatches);
        controller.setTfWinner(tfWinner);
        controller.setTfCompetitionDay(tfCompetitionDay);
        controller.setTfCompetitionMonth(tfCompetitionMonth);
        controller.setTfCompetitionYear(tfCompetitionYear);
        controller.setBtAddCompetition(btAddCompetition);
        controller.setLbMessage(lbMessage);

        controller.setHbRow1(hbRow1);
        controller.setTbCompetitions(tbCompetitions);
        controller.setHbRow3(hbRow3);
        controller.setHbRow4(hbRow4);
        controller.setHbRow5(hbRow5);
        controller.setVbContent(vbContent);

        controller.initialize();
    }

    /**
     * changes the border pane in the mainView to the current border pane
     * @param borderPane the border pane of the mainView
     * @return the changed border pane with the content of this UI
     */
    public BorderPane changeBorderPane(BorderPane borderPane) {
        controller.clearInputFields();
        borderPane.setCenter(vbContent);

        return borderPane;
    }
}
