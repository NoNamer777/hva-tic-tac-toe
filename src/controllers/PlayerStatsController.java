package controllers;

import data.ReadData;
import data.WriteData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import models.Player;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Bepaalt wat er gebeurd wanneer de PlayerStatsView wordt aangeroepen
 * @author Oscar Wellner
 */
public class PlayerStatsController {

    private VBox vbContent;
    private HBox hbRow1;
    private TableView<Player> tbPlayerStats;
    private HBox hbRow3;
    private Label lbHeader;
    private TableColumn<Player, String> colPlayerName;
    private TableColumn<Player, Integer> colPlayerWins;
    private TableColumn<Player, Integer> colPlayerDraws;
    private TableColumn<Player, Integer> colPlayerLosses;
    private TableColumn<Player, Integer> colPlayerPoints;
    private TextField tfSearchPlayer;
    private Label lbMessage;

    private ReadData readData = new ReadData();
    private WriteData writeData = new WriteData();
    private ObservableList<Player> players;
    private ArrayList<Player> data;

    public void initialize() {
        lbHeader.setText("Leaderboard");
        lbHeader.setFont(Font.font("Arial Bold", FontWeight.BOLD, 16));

        colPlayerName.setText("Name");
        colPlayerName.setMinWidth(100.0);
        colPlayerWins.setText("Matches Won");
        colPlayerWins.setMinWidth(100.0);
        colPlayerDraws.setText("Draws Played");
        colPlayerDraws.setMinWidth(100.0);
        colPlayerLosses.setText("Matches Lost");
        colPlayerLosses.setMinWidth(100.0);
        colPlayerPoints.setText("Total Points");
        colPlayerPoints.setMinWidth(100.0);

        hbRow3.setPadding(new Insets(8.0));
        hbRow3.setSpacing(4.0);

        tfSearchPlayer.setPromptText("Search Player");
        tfSearchPlayer.setPadding(new Insets(4.0, 0.0, 4.0, 2.0));

        getPlayersArchive();
        populateTable();
    }

    public void setLbHeader(Label lbHeader) {
        this.lbHeader = lbHeader;
    }

    public void setVbPlayerStats(TableView<Player> tbPlayerStats) {
        this.tbPlayerStats = tbPlayerStats;
    }

    public void setColPlayerName(TableColumn<Player, String> colPlayerName) {
        this.colPlayerName = colPlayerName;
    }

    public void setColPlayerWins(TableColumn<Player, Integer> colPlayerWins) {
        this.colPlayerWins = colPlayerWins;
    }

    public void setColPlayerDraws(TableColumn<Player, Integer> colPlayerDraws) {
        this.colPlayerDraws = colPlayerDraws;
    }

    public void setColPlayerLosses(TableColumn<Player, Integer> colPlayerLosses) {
        this.colPlayerLosses = colPlayerLosses;
    }

    public void setColPlayerPoints(TableColumn<Player, Integer> colPlayerPoints) {
        this.colPlayerPoints = colPlayerPoints;
    }

    public void setTfSearchPlayer(TextField tfSearchPlayer) {
        this.tfSearchPlayer = tfSearchPlayer;
    }

    public void setHbRow1(HBox hbRow1) {
        this.hbRow1 = hbRow1;
    }

    public void setHbRow3(HBox hbRow3) {
        this.hbRow3 = hbRow3;
    }

    public void setVbContent(VBox vbContent) {
        this.vbContent = vbContent;

    }

    public void setLbMessage(Label lbMessage) {
        this.lbMessage = lbMessage;
    }

    /**
     * haalt de lijst op uit Player_Archive.dat met de gemaakte spelers
     */
    public void getPlayersArchive() {
        players = FXCollections.observableArrayList();
        data = new ArrayList<>();
        data = readData.readDataFile("Player_Archive.dat");

        //check if file is read correctly, otherwise create a new file
        if(data.toArray().length != 0) {
            for (Player player : data) {
                Collections.addAll(players, player);

                //sets de property's van de player zodat het in de tabel gezet kan worden
                player.setNameProperty(player.getName());
                player.setWinsProperty(player.getWins());
                player.setDrawsProperty(player.getDraws());
                player.setLossesProperty(player.getLosses());
                player.setPointsProperty(player.getPoints());
            }
        } else {
            writeData.writeToDisk(new ArrayList<Player>(), "Player_Archive.dat");
            data = readData.readDataFile("Player_Archive.dat");
        }
    }

    /**
     * zet de opgehaalde lijst met spelers in de tabel
     */
    public void populateTable() {
        colPlayerName.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        colPlayerWins.setCellValueFactory(
                new PropertyValueFactory<>("wins")
        );
        colPlayerDraws.setCellValueFactory(
                new PropertyValueFactory<>("draws")
        );
        colPlayerLosses.setCellValueFactory(
                new PropertyValueFactory<>("losses")
        );
        colPlayerPoints.setCellValueFactory(
                new PropertyValueFactory<>("points")
        );

        //maakt een filterlijst voor de zoek functie
        FilteredList<Player> filteredData = new FilteredList<>(players, p -> true);

        tfSearchPlayer.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(player -> {
                //Als de nieuwe waarde leeg is of null worden alle spelers getoond
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                // geeft de speler terug die overeen komt met de zoek query
                return player.getName().toLowerCase().contains(lowerCaseFilter);
            });
        });

        SortedList<Player> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tbPlayerStats.comparatorProperty());

        tbPlayerStats.setItems(sortedData);
    }
}