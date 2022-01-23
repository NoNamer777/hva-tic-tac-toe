package controllers;

import data.ReadData;
import data.WriteData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import models.Competition;
import models.Game;
import models.Player;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Controls what happens on the CompetitionHistoryView
 * @author Oscar Wellner
 */
public class CompetitionHistoryController {

    private VBox vbContent;
    private HBox hbRow1;
    private TableView tbCompetitions;
    private HBox hbRow3;
    private HBox hbRow4;
    private HBox hbRow5;
    private Label lbHeader;
    private TableColumn<Competition, String> colCompetitionName;
    private TableColumn<Competition, String> colPlayer1;
    private TableColumn<Competition, String> colPlayer2;
    private TableColumn<Competition, String> colAmountMatches;
    private TableColumn<LocalDate, String> colDateCompetition;
    private TableColumn<Competition, String> colWinner;
    private TextField tfCompetitionName;
    private TextField tfPlayer1;
    private TextField tfPlayer2;
    private TextField tfAmountMatches;
    private TextField tfWinner;
    private TextField tfCompetitionDay;
    private TextField tfCompetitionMonth;
    private TextField tfCompetitionYear;
    private Button btAddCompetition;
    private Label lbMessage;

    private ReadData readData = new ReadData();
    private WriteData writeData = new WriteData();

    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Competition> competitions = new ArrayList<>();
    private ArrayList<Competition> data = new ArrayList<>();
    private ObservableList<Competition> olCompetitions = FXCollections.observableArrayList();

    public void initialize() {
        lbHeader.setText("Competition History");
        lbHeader.setFont(Font.font("Arial Bold", FontWeight.BOLD, 16));
        lbHeader.setPadding(new Insets(8.0, 8.0, 2.0, 2.0));

        colCompetitionName.setText("Name");
        colCompetitionName.setMinWidth(80.0);
        colPlayer1.setText("Player 1");
        colPlayer1.setMinWidth(80.0);
        colPlayer2.setText("Player 2");
        colPlayer2.setMinWidth(80.0);
        colAmountMatches.setText("Amount of Matches");
        colAmountMatches.setMinWidth(130.0);
        colDateCompetition.setText("Date of Competition");
        colDateCompetition.setMinWidth(130.0);
        colWinner.setText("Winner");
        colWinner.setMinWidth(100.0);

        hbRow3.setPadding(new Insets(6.0));
        hbRow3.setSpacing(4.0);
        tfCompetitionName.setPromptText("Name of Competition");
        tfCompetitionName.setId("name");
        tfAmountMatches.setPromptText("Amount of Matches");
        tfAmountMatches.setId("amountOfMatches");
        tfPlayer1.setPromptText("Player 1");
        tfPlayer1.setId("player1");
        tfPlayer2.setPromptText("Player 2");
        tfPlayer2.setId("player2");

        hbRow4.setPadding(new Insets(0.0, 6.0, 6.0, 6.0));
        hbRow4.setSpacing(4.0);
        tfWinner.setPromptText("Winner");
        tfWinner.setId("winner");
        tfWinner.setPadding(new Insets(3.0, 8.0, 3.0, 6.0));
        tfCompetitionDay.setPromptText("Day");
        tfCompetitionDay.setId("dayOfCompetition");
        tfCompetitionDay.setPadding(new Insets(3.0, -102, 3.0, 6.0));
        tfCompetitionMonth.setPromptText("Month");
        tfCompetitionMonth.setId("monthOfCompetition");
        tfCompetitionMonth.setPadding(new Insets(3.0, -92, 3.0, 6.0));
        tfCompetitionYear.setPromptText("Year");
        tfCompetitionYear.setId("yearOfCompetition");
        tfCompetitionYear.setPadding(new Insets(3.0, -64, 3.0, 6.0));

        hbRow5.setPadding(new Insets(0.0, 6.0, 6.0, 6.0));
        hbRow5.setSpacing(4.0);
        btAddCompetition.setText("Create Competition");
        btAddCompetition.setStyle(
                "-fx-background-color: #478ce6;" +
                "-fx-text-fill: white;"
        );
        lbMessage.setStyle("-fx-text-fill: red; -fx-font-weight: bold");

        getCompetitions();
        populateTable();

        btAddCompetition.setOnAction(t -> {
            System.out.println("Creating new Competition");

            getPlayerArchive();
            checkInputFields();
            getCompetitions();
        });
    }

    public void setVbContent(VBox vbContent) {
        this.vbContent = vbContent;
    }

    public void setHbRow1(HBox hbRow1) {
        this.hbRow1 = hbRow1;
    }

    public void setTbCompetitions(TableView tbCompetitions) {
        this.tbCompetitions = tbCompetitions;
    }

    public void setHbRow3(HBox hbRow3) {
        this.hbRow3 = hbRow3;
    }

    public void setHbRow4(HBox hbRow4) {
        this.hbRow4 = hbRow4;
    }

    public void setHbRow5(HBox hbRow5) {
        this.hbRow5 = hbRow5;
    }

    public void setLbHeader(Label lbHeader) {
        this.lbHeader = lbHeader;
    }

    public void setColCompetitionName(TableColumn<Competition, String> colCompetitionName) {
        this.colCompetitionName = colCompetitionName;
    }

    public void setColPlayer1(TableColumn<Competition, String> colPlayer1) {
        this.colPlayer1 = colPlayer1;
    }

    public void setColPlayer2(TableColumn<Competition, String> colPlayer2) {
        this.colPlayer2 = colPlayer2;
    }

    public void setColAmountMatches(TableColumn<Competition, String> colAmountMatches) {
        this.colAmountMatches = colAmountMatches;
    }

    public void setColDateCompetition(TableColumn<LocalDate, String> colDateCompetition) {
        this.colDateCompetition = colDateCompetition;
    }

    public void setColWinner(TableColumn<Competition, String> colWinner) {
        this.colWinner = colWinner;
    }

    public void setTfCompetitionName(TextField tfCompetitionName) {
        this.tfCompetitionName = tfCompetitionName;
    }

    public void setTfPlayer1(TextField tfPlayer1) {
        this.tfPlayer1 = tfPlayer1;
    }

    public void setTfPlayer2(TextField tfPlayer2) {
        this.tfPlayer2 = tfPlayer2;
    }

    public void setTfAmountMatches(TextField tfAmountMatches) {
        this.tfAmountMatches = tfAmountMatches;
    }

    public void setTfWinner(TextField tfWinner) {
        this.tfWinner = tfWinner;
    }

    public void setTfCompetitionDay(TextField tfCompetitionDay) {
        this.tfCompetitionDay = tfCompetitionDay;
    }

    public void setTfCompetitionMonth(TextField tfCompetitionMonth) {
        this.tfCompetitionMonth = tfCompetitionMonth;
    }

    public void setTfCompetitionYear(TextField tfCompetitionYear) {
        this.tfCompetitionYear = tfCompetitionYear;
    }

    public void setBtAddCompetition(Button btAddCompetition) {
        this.btAddCompetition = btAddCompetition;
    }

    public void setLbMessage(Label lbMessage) {
        this.lbMessage = lbMessage;
    }

    /**
     *  Haalt de data op van het bestand Competition_History.dat
     */
    private void getCompetitions() {
        //Maakt de verschillende lists leeg om "dubbele" items te voorkomen
        data.clear();
        competitions.clear();
        olCompetitions.clear();
        
        data = readData.readDataFile("Competition_History.dat");

        for (Competition competition : data) {
            competitions.add(competition);
        }

        //Als er geen competities gevonden zijn, wordt er een nieuw bestand gemaakt
        if (competitions.size() <= 0) {
            writeData.writeToDisk(competitions, "Competition_History.dat");
            competitions = readData.readDataFile("Competition_History.dat");
        } else {
            //zet de properties van de competitie zodat ze in de tabel gezet kunnen worden
            for(Competition competition : competitions) {
                competition.setNameProperty();
                competition.setAmountOfMatchesProperty();
                competition.setDateOfCompetitionProperty();
                competition.setPlayer1Property();
                competition.setPlayer2Property();
                competition.setWinnerProperty();

                olCompetitions.add(competition);
            }
        }
    }

    /**
     *  populeert de tabel met competities
     */
    private void populateTable() {
        //koppelt verschillende eigenschappen van een competitie aan de toegewezen colommen
        colCompetitionName.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        colAmountMatches.setCellValueFactory(
                new PropertyValueFactory<>("amountOfMatches")
        );
        colDateCompetition.setCellValueFactory(
                new PropertyValueFactory<>("dateOfCompetition")
        );
        colPlayer1.setCellValueFactory(
                new PropertyValueFactory<>("player1")
        );
        colPlayer2.setCellValueFactory(
                new PropertyValueFactory<>("player2")
        );
        colWinner.setCellValueFactory(
                new PropertyValueFactory<>("winner")
        );

        tbCompetitions.setItems(olCompetitions);
    }

    /**
     *  Maakt de input velden leeg en reset de style naar de standaard-waarden
     */
    public void clearInputFields() {
        tfCompetitionName.clear();
        tfCompetitionName.setStyle(null);
        tfPlayer1.clear();
        tfPlayer1.setStyle(null);
        tfPlayer2.clear();
        tfPlayer2.setStyle(null);
        tfAmountMatches.clear();
        tfAmountMatches.setStyle(null);
        tfWinner.clear();
        tfWinner.setStyle(null);
        tfCompetitionDay.clear();
        tfCompetitionDay.setStyle(null);
        tfCompetitionMonth.clear();
        tfCompetitionMonth.setStyle(null);
        tfCompetitionYear.clear();
        tfCompetitionYear.setStyle(null);
        lbMessage.setText(null);
        lbMessage.setStyle("-fx-text-fill: red; -fx-font-weight: bold");
    }

    /**
     *  Controleerdt de ingevoerde waarden van de input velden of ze juist zijn ingevoerd
     */
    private void checkInputFields() {
        final int AMOUNT_INPUTFIELDS = 8;
        boolean[] inputChecks = new boolean[AMOUNT_INPUTFIELDS];

        lbMessage.setText(null);

        //controleerdt alle input velden op de 3de rij van de view
        for (Node child : hbRow3.getChildren()) {
            switch (child.getId()) {
                case "name":
                    //controleerdt of er een waarde is gegeven
                    if (((TextField) child).getText() == null || ((TextField) child).getText().isEmpty()) {
                        inputChecks[0] = false;
                        System.out.println("\tName of the Competition is not provided");
                        child.setStyle("-fx-border-color: red;");
                        if (lbMessage.getText() == null) {
                            lbMessage.setText("Name of the Competition is not provided");
                        }
                    } else {
                        inputChecks[0] = true;
                        child.setStyle(
                                "-fx-border-color: green; " +
                                "-fx-text-fill: green; " +
                                "-fx-font-weight: bold"
                        );
                    }
                    break;
                case "amountOfMatches":
                    if (((TextField) child).getText() == null || ((TextField) child).getText().isEmpty()) {
                        inputChecks[1] = false;
                        System.out.println("\tAmount of matches in the competition is not set");
                        child.setStyle("-fx-border-color: red;");
                        if (lbMessage.getText() == null) {
                            lbMessage.setText("Amount of matches in the competition is not set");
                        }
                    //controleerdt of de ingevoerde waarde een getal is
                    } else if (!Character.isDigit(((TextField) child).getText().charAt(0))) {
                        inputChecks[1] = false;
                        System.out.println("\tInput isn't a number");
                        child.setStyle("-fx-border-color: red;");
                        if (lbMessage.getText() == null) {
                            lbMessage.setText("Input isn't a number");
                        }
                    } else {
                        inputChecks[1] = true;
                        child.setStyle(
                                "-fx-border-color: green; " +
                                "-fx-text-fill: green; " +
                                "-fx-font-weight: bold"
                        );
                    }
                    break;
                case "player1":
                    if (((TextField) child).getText() == null || ((TextField) child).getText().isEmpty()) {
                        inputChecks[2] = false;
                        System.out.println("\tPlayer 1 is not provided");
                        child.setStyle("-fx-border-color: red;");
                        if (lbMessage.getText() == null) {
                            lbMessage.setText("Player 1 is not provided");
                        }
                        //controleerdt of de ingevoerde naam van de speler bekend is in het Player_Archive.dat
                    } else if (!checkPlayerArchive(players, ((TextField) child).getText())) {
                        inputChecks[2] = false;
                        System.out.println("\tplayer doesn't exist in the player archive");
                        child.setStyle("-fx-border-color: red;");
                        if (lbMessage.getText() == null) {
                            lbMessage.setText("player doesn't exist in the player archive");
                        }
                    } else {
                        inputChecks[2] = true;
                        child.setStyle(
                                "-fx-border-color: green; " +
                                "-fx-text-fill: green; " +
                                "-fx-font-weight: bold"
                        );
                    }
                case "player2":
                    if (((TextField) child).getText() == null || ((TextField) child).getText().isEmpty()) {
                        inputChecks[3] = false;
                        System.out.println("\tPlayer 2 is not provided");
                        child.setStyle("-fx-border-color: red;");
                        if (lbMessage.getText() == null) {
                            lbMessage.setText("Player 2 is not provided");
                        }
                    } else if (!checkPlayerArchive(players, ((TextField) child).getText())) {
                        inputChecks[3] = false;
                        System.out.println("\tplayer doesn't exist in the player archive");
                        child.setStyle("-fx-border-color: red;");
                        if (lbMessage.getText() == null) {
                            lbMessage.setText("player doesn't exist in the player archive");
                        }
                        //controleerdt of speler 1 en 2 niet dezelfde speler zijn
                    } else if (tfPlayer1.getText().equals(tfPlayer2.getText())) {
                        inputChecks[3] = false;
                        System.out.println("\tPlayer 1 and player 2 can't be te same player");
                        child.setStyle("-fx-border-color: red;");
                        tfPlayer1.setStyle("-fx-border-color: red;");
                        if (lbMessage.getText() == null) {
                            lbMessage.setText("Player 1 and player 2 can't be te same player");
                        }
                    } else {
                        inputChecks[3] = true;
                        child.setStyle(
                                "-fx-border-color: green; " +
                                "-fx-text-fill: green; " +
                                "-fx-font-weight: bold"
                        );
                    }
                    break;
            }
        }

        //controleerdt alle input velden op de 4de rij van de view
        for (Node child : hbRow4.getChildren()) {
            switch (child.getId()) {
                case "winner":
                    if (((TextField) child).getText() == null || ((TextField) child).getText().isEmpty()) {
                        inputChecks[4] = false;
                        System.out.println("\twinner isn't provided");
                        child.setStyle("-fx-border-color: red;");
                        if (lbMessage.getText() == null) {
                            lbMessage.setText("Winner isn't provided");
                        }
                        //controleerdt of de winnaar van de competitie gelijk is aan speler 1 of speler 2
                    } else if (!(((TextField) child).getText().equals(tfPlayer1.getText()))
                            && !(((TextField) child).getText().equals(tfPlayer2.getText()))) {
                        inputChecks[4] = false;
                        System.out.println("\tWinner doesn't match with one of the players");
                        child.setStyle("-fx-border-color: red;");
                        if (lbMessage.getText() == null) {
                            lbMessage.setText("Winner doesn't match with one of the players");
                        }
                    } else {
                        inputChecks[4] = true;
                        child.setStyle(
                                "-fx-border-color: green; " +
                                        "-fx-text-fill: green; " +
                                        "-fx-font-weight: bold"
                        );
                    }
                    break;
                case "dayOfCompetition":
                    if (((TextField) child).getText() == null || ((TextField) child).getText().isEmpty()) {
                        inputChecks[5] = false;
                        System.out.println("\tDay of the competition isn provided");
                        child.setStyle("-fx-border-color: red;");
                        if (lbMessage.getText() == null) {
                            lbMessage.setText("Day of the competition isn provided");
                        }
                    } else if (!Character.isDigit(((TextField) child).getText().charAt(0))) {
                        inputChecks[5] = false;
                        System.out.println("\tInput isn't a number");
                        child.setStyle("-fx-border-color: red;");
                        if (lbMessage.getText() == null) {
                            lbMessage.setText("Input isn't a number");
                        }
                    //controleerdt of de ingevoerde dag voldoet aan een bepaalde format
                    } else if (!((TextField) child).getText().matches("[0-3]\\d")) {
                        inputChecks[5] = false;
                        System.out.println("\tDay format isn't correct, should be (dd)");
                        child.setStyle("-fx-border-color: red;");
                        if (lbMessage.getText() == null) {
                            lbMessage.setText("Day format isn't correct, should be (dd)");
                        }
                    } else {
                        inputChecks[5] = true;
                        child.setStyle(
                                "-fx-border-color: green; " +
                                        "-fx-text-fill: green; " +
                                        "-fx-font-weight: bold"
                        );
                    }
                    break;
                case "monthOfCompetition":
                    if (((TextField) child).getText() == null || ((TextField) child).getText().isEmpty()) {
                        inputChecks[6] = false;
                        System.out.println("\tMonth of the competition isn provided");
                        child.setStyle("-fx-border-color: red;");
                        if (lbMessage.getText() == null) {
                            lbMessage.setText("Month of the competition isn provided");
                        }
                    } else if (!Character.isDigit(((TextField) child).getText().charAt(0))) {
                        inputChecks[6] = false;
                        System.out.println("\tInput isn't a number");
                        child.setStyle("-fx-border-color: red;");
                        if (lbMessage.getText() == null) {
                            lbMessage.setText("Input isn't a number");
                        }
                    //controleerdt of de ingevoerde maand voldoet aan een bepaalde format
                    } else if (!((TextField) child).getText().matches("[0-1]\\d")) {
                        inputChecks[6] = false;
                        System.out.println("\tMonths format is not correct, should by (MM)");
                        child.setStyle("-fx-border-color: red;");
                        if (lbMessage.getText() == null) {
                            lbMessage.setText("Months format is not correct, should by (MM)");
                        }
                    } else {
                        inputChecks[6] = true;
                        child.setStyle(
                                "-fx-border-color: green; " +
                                        "-fx-text-fill: green; " +
                                        "-fx-font-weight: bold"
                        );
                    }
                    break;
                case "yearOfCompetition":
                    if (((TextField) child).getText() == null || ((TextField) child).getText().isEmpty()) {
                        inputChecks[7] = false;
                        System.out.println("\tYear of the competition isn provided");
                        child.setStyle("-fx-border-color: red;");
                        if (lbMessage.getText() == null) {
                            lbMessage.setText("Year of the competition isn provided");
                        }
                    } else if (!Character.isDigit(((TextField) child).getText().charAt(0))) {
                        inputChecks[7] = false;
                        System.out.println("\tInput isn't a number");
                        child.setStyle("-fx-border-color: red;");
                        if (lbMessage.getText() == null) {
                            lbMessage.setText("Input isn't a number");
                        }
                    //controleerdt of de ingevoerde jaar voldoet aan een bepaalde format
                    } else if (!((TextField) child).getText().matches("\\d\\d\\d\\d")) {
                        inputChecks[7] = false;
                        System.out.println("\tYear format isn't correct, should be (yyyy)");
                        child.setStyle("-fx-border-color: red;");
                        if (lbMessage.getText() == null) {
                            lbMessage.setText("Year format isn't correct, should be (yyyy)");
                        }
                    } else {
                        inputChecks[7] = true;
                        child.setStyle(
                                "-fx-border-color: green; " +
                                        "-fx-text-fill: green; " +
                                        "-fx-font-weight: bold"
                        );
                    }
                    break;
            }
        }
        createNewCompetition(inputChecks);
    }

    /**
     *  Haalt alle al aangemaakte Players op uit de Player_Archive.dat
     */
    private void getPlayerArchive() {
        ArrayList<Player> data = readData.readDataFile("Player_Archive.dat");

        //check if file is read correctly.
        if (data.toArray().length != 0) {
            for (Player player : data) {
                Collections.addAll(players, player);
            }
        } else {
            System.out.println("\tNo Players in archive");
        }

        ObservableList<Player> olPlayers = FXCollections.observableArrayList();

        for (Player player : players) {
            Collections.addAll(olPlayers, player);
        }
    }

    /**
     * controleert of de playername bekend is binnen het player archive
     * @param players de lijst met de al bekende players
     * @param playerValue de naam die gecontroleerd moet worden
     * @return geeft terug of de playernaam wel of niet bekend is
     */
    private boolean checkPlayerArchive(ArrayList<Player> players, String playerValue) {
        for (Player player : players) {
            if (player.getName().equals(playerValue)) {
                return true;
            } else if (!player.getName().equals(playerValue)
                    && players.indexOf(player) == (players.size() - 1)) {
                return false;
            }
        }
        return false;
    }

    /**
     *  maakt een nieuwe compotitie aan
     * @param inputChecks een lijst met resultaten van de input checks.
     */
    private void createNewCompetition(boolean[] inputChecks) {
        //gaat alle resultaten af en maakt pas een nieuwe competitie wanneer alle checks goed zijn
        for (int i = 0; i < inputChecks.length; i++) {
            if (!inputChecks[i]) {
                System.out.println("\tCheck didn't make it, don't make a new competition");
                break;
            } else if (i == 7 && inputChecks[i]) {
                System.out.println("\tAll checks passed, creating new competition");

                Competition competition = new Competition(tfCompetitionName.getText());

                //creeert players met de naam 'dummy' zodat ze wel geconstrueerd kunnen worden
                Player player1 = new Player("dummy");
                Player player2 = new Player("dummy");
                Player winner = new Player("dummy");

                //maakt evenveel matches als er bij de opgegeven hoeveelheid matches stond
                for(int j = 0; j < Integer.parseInt(tfAmountMatches.getText()); j++) {

                    //corrigeer de hierboven gemaakte spelers naar de correcte opgegeven speler
                    for (Player player : players) {
                        if(player.getName().equals(tfPlayer1.getText())) {
                            player1 = player;
                            if (tfWinner.getText().equals(tfPlayer1.getText())) {
                                winner = player1;
                            }
                        } else if (player.getName().equals(tfPlayer2.getText())) {
                            player2 = player;
                            if (tfWinner.getText().equals(tfPlayer2.getText())) {
                                winner = player2;
                            }
                        }
                    }

                    Game match = new Game(player1, player2);
                    match.setDateOfMatch(
                            LocalDate.parse(tfCompetitionDay.getText() + "-"
                                    + tfCompetitionMonth.getText() + "-" + tfCompetitionYear.getText(),
                                    DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    competition.addMatch(match);

                    competition.setDateOfCompetition(LocalDate.parse(match.getDateOfMatch()));
                }
                competition.setWinner(winner);
                competition.setPlayer1(player1);
                competition.setPlayer2(player2);

                data.add(competition);

                //zet de properties op null zodat ze weggeschreven kunnen worden naar het Competition_History.dat bestand
                for (Competition comp : data) {
                    comp.nameProperty = null;
                    comp.amountOfMatchesProperty = null;
                    comp.dateOfCompetitionProperty = null;
                    comp.player1Property = null;
                    comp.player2Property = null;
                    comp.winnerProperty = null;
                }

                writeData.writeToDisk(data, "Competition_History.dat");
            }
        }
        clearInputFields();
        getCompetitions();
    }
}
