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
import models.Game;
import models.Player;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *  bepaaldt wat er gebeurd op de MatchHistoryView
 *  @author Oscar Wellner
 */
public class MatchHistoryController {

    private VBox vbContent;
    private HBox hbRow1;
    private Label lbHeader;
    private TableView tbMatchHistory;
    private TableColumn<Game, String> colPlayerName1;
    private TableColumn<Game, String> colPlayerName2;
    private TableColumn<Game, String> colDateOfMatch;
    private TableColumn<Game, String> colWinner;
    private HBox hbRow3;
    private TextField tfAddPlayer1;
    private TextField tfAddPlayer2;
    private TextField tfAddWinner;
    private Button btAddMatch;
    private HBox hbRow4;
    private TextField tfMatchDay;
    private TextField tfMatchMonth;
    private TextField tfMatchYear;
    private CheckBox cBisDraw;
    private Label lbMessage;

    private WriteData writeData = new WriteData();
    private ReadData readData = new ReadData();
    private ArrayList<Player> players = new ArrayList<>();
    private ObservableList<Game> matches = FXCollections.observableArrayList();
    private ArrayList<Game> data = new ArrayList<>();
    private Game game;

    public void initialize() {
        lbHeader.setText("Match History");
        lbHeader.setFont(Font.font("Arial Bold", FontWeight.BOLD, 16));
        lbHeader.setPadding(new Insets(8.0, 8.0, 2.0, 2.0));

        colPlayerName1.setText("Player 1");
        colPlayerName2.setText("Player 2");
        colDateOfMatch.setText("Match Date");
        colWinner.setText("Winner");

        hbRow3.setPadding(new Insets(8.0, 8.0, 2.0, 8.0));
        hbRow3.setSpacing(4.0);

        tfAddPlayer1.setPromptText("Player 1");
        tfAddPlayer1.setPadding(new Insets(4.0, 8.0, 4.0, 8.0));
        tfAddPlayer2.setPromptText("Player 2");
        tfAddPlayer2.setPadding(new Insets(4.0, 8.0, 4.0, 8.0));

        tfAddWinner.setPromptText("Winner of the match");
        tfAddWinner.setPadding(new Insets(4.0, 8.0, 4.0, 8.0));
        btAddMatch.setText("Add Match");
        btAddMatch.setPadding(new Insets(4.0, 8.0, 4.0, 8.0));
        btAddMatch.setStyle(
                "-fx-background-color: #478ce6;" +
                        "-fx-text-fill: white;"
        );

        hbRow4.setPadding(new Insets(2.0, 8.0, 8.0, 8.0));
        hbRow4.setSpacing(4.0);

        tfMatchDay.setPromptText("Day");
        tfMatchDay.setPadding(new Insets(4.0, -100.0, 4.0, 10.0));
        tfMatchMonth.setPromptText("Month");
        tfMatchMonth.setPadding(new Insets(4.0, -80.0, 4.0, 10.0));
        tfMatchYear.setPromptText("Year");
        tfMatchYear.setPadding(new Insets(4.0, -50.0, 4.0, 10.0));
        cBisDraw.setText("Draw");
        cBisDraw.setPadding(new Insets(4.0, 8.0, 8.0, 2.0));
        lbMessage.setPadding(new Insets(4.0, 8.0, 4.0, 8.0));
        lbMessage.setStyle(
                "-fx-font-weight: bold;" +
                        "-fx-text-fill: red;"
        );
        getMatchHistory();
        populateMatchHistoryTable();

        btAddMatch.setOnAction(t -> {
            System.out.println("\nAdding match manually");

            checkInput();
        });
    }

    public void setLbHeader(Label pageTitle) {
        this.lbHeader = pageTitle;
    }

    public void setTbMatchHistory(TableView tbMatchHistory) {
        this.tbMatchHistory = tbMatchHistory;
    }

    public void setColPlayerName1(TableColumn<Game, String> colPlayerName1) {
        this.colPlayerName1 = colPlayerName1;
    }

    public void setColPlayerName2(TableColumn<Game, String> colPlayerName2) {
        this.colPlayerName2 = colPlayerName2;
    }

    public void setColDateOfMatch(TableColumn<Game, String> colDateOfMatch) {
        this.colDateOfMatch = colDateOfMatch;
    }

    public void setColWinner(TableColumn<Game, String> colWinner) {
        this.colWinner = colWinner;
    }

    public void setTfAddPlayer1(TextField tfAddPlayer1) {
        this.tfAddPlayer1 = tfAddPlayer1;
    }

    public void setTfAddPlayer2(TextField tfAddPlayer2) {
        this.tfAddPlayer2 = tfAddPlayer2;
    }

    public void setTfMatchDay(TextField tfMatchDay) {
        this.tfMatchDay = tfMatchDay;
    }

    public void setTfMatchMonth(TextField tfMatchMonth) {
        this.tfMatchMonth = tfMatchMonth;
    }

    public void setTfMatchYear(TextField tfMatchYear) {
        this.tfMatchYear = tfMatchYear;
    }

    public void setTfAddWinner(TextField tfAddWinner) {
        this.tfAddWinner = tfAddWinner;
    }

    public void setCbIsDraw(CheckBox cBisDraw) {
        this.cBisDraw = cBisDraw;
    }

    public void setBtAddMatch(Button btAddMatch) {
        this.btAddMatch = btAddMatch;
    }

    public void setLbMessage(Label lbMessage) {
        this.lbMessage = lbMessage;
    }

    public void setHbRow1(HBox hbRow1) {
        this.hbRow1 = hbRow1;
    }

    public void setHbRow3(HBox hbRow3) {
        this.hbRow3 = hbRow3;
    }

    public void setHbRow4(HBox hbRow4) {
        this.hbRow4 = hbRow4;
    }

    public void setVbContent(VBox content) {
        this.vbContent = content;
    }

    /**
     * Haalt alle matches op uit de Match_History.dat die al gebeurd zijn
     */
    public void getMatchHistory() {
        data = readData.readDataFile("Match_History.dat");
        matches.clear();

        //check if file is read correctly
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                Game game = data.get(i);

                game.setPlayer1Property();
                game.setPlayer2Property();
                game.setWinnerProperty();
                game.setDateOfMatchProperty();
                matches.add(game);
            }
        } else {
            writeData.writeToDisk(new ArrayList<Player>(), "Match_History.dat");
            data = readData.readDataFile("Match_History.dat");
        }
    }

    /**
     * Vult de tabel met de opgehaalde gegevens
     */
    private void populateMatchHistoryTable() {
        colPlayerName1.setCellValueFactory(
                new PropertyValueFactory<>("player1")
        );
        colPlayerName2.setCellValueFactory(
                new PropertyValueFactory<>("player2")
        );
        colWinner.setCellValueFactory(
                new PropertyValueFactory<>("winner")
        );
        colDateOfMatch.setCellValueFactory(
                new PropertyValueFactory<>("dateOfMatch")
        );

        tbMatchHistory.setItems(matches);
    }

    /**
     * Haalt alle Players op uit de Player_Archive.dat
     */
    private void getPlayerArchive() {
        readData = new ReadData();
        ArrayList<Player> data = readData.readDataFile("Player_Archive.dat");

        //check if file is read correctly
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
     * Maakt de input velden leeg en reset hun style
     */
    public void resetInputFields() {
        for (Node child : hbRow3.getChildren()) {
            if (child.getTypeSelector().equals("TextField")) {
                ((TextField) child).setText(null);
                child.setStyle(null);
            }
        }
        for (Node child : hbRow4.getChildren()) {
            if (child.getTypeSelector().equals("TextField")) {
                ((TextField) child).setText(null);
                child.setStyle(null);
            }
        }

        lbMessage.setStyle("-fx-text-fill: red");
        lbMessage.setText(null);
    }

    /**
     *  checks de input waarden op de correcte invoer
     */
    private void checkInput() {
        lbMessage.setText(null);

        String player1Value = tfAddPlayer1.getText();
        String player2Value = tfAddPlayer2.getText();
        String winnerValue = tfAddWinner.getText();
        boolean playerArchiveCheck1;
        boolean playerArchiveCheck2;

        String matchDayValue = tfMatchDay.getText();
        String dayPat = "([0-3]\\d)";
        String matchMonthValue = tfMatchMonth.getText();
        String monthPat = "([0-1]\\d)";
        String matchYearValue = tfMatchYear.getText();
        String yearPat = "(\\d\\d\\d\\d)";
        LocalDate ldMatchDate;

        getPlayerArchive();

        //controleert de input velden op de 4de rij van de view
        for (Node child : hbRow4.getChildren()) {
            if (child.getTypeSelector().equals("TextField")) {
                //controleert of er een input waarde is gegeven
                if (((TextField) child).getText() == null || ((TextField) child).getText().isEmpty()) {
                    if (child.equals(tfMatchDay)) {
                        System.out.println("\tDAY of the match isn't'provided");
                        if (lbMessage.getText() == null) {
                            lbMessage.setText("DAY of the match isn't'provided");
                        }
                        child.setStyle("-fx-border-color: red;");
                    } else if (child.equals(tfMatchMonth)) {
                        System.out.println("\tMONTH of the match isn't'provided");
                        if (lbMessage.getText() == null) {
                            lbMessage.setText("MONTH of the match isn't'provided");
                        }
                        child.setStyle("-fx-border-color: red;");
                    } else if (child.equals(tfMatchYear)) {
                        System.out.println("\tYEAR of the match isn't'provided");
                        if (lbMessage.getText() == null) {
                            lbMessage.setText("YEAR of the match isn't'provided");
                        }
                        child.setStyle("-fx-border-color: red;");
                    } else {
                        child.setStyle(
                                "-fx-border-color: green;" +
                                        "-fx-text-fill: green;" +
                                        "-fx-font-weight: bold;"
                        );
                    }
                } else {
                    child.setStyle(null);
                    if (child.equals(tfMatchDay)) {
                        //controleert of de gegeven waarde voldoet aan de gevraagde format voor dat item
                        if (!matchDayValue.matches(dayPat)) {
                            System.out.println("\tSomething's wrong with the formatting of the DAY of the match");
                            if (lbMessage.getText() == null) {
                                lbMessage.setText("Something's wrong with the formatting of the DAY of the match");
                            }
                            child.setStyle("-fx-border-color: red;");
                        //controleert of er er niet te veel dagen zijn opgegeven
                        } else if (!(Integer.parseInt(matchDayValue) <= 31)) {
                            System.out.println("\tTo many DAYS provided");
                            if (lbMessage.getText() == null) {
                                lbMessage.setText("To many DAYS provided");
                            }
                            child.setStyle("-fx-border-color: red;");
                        } else {
                            child.setStyle(
                                    "-fx-border-color: green;" +
                                            "-fx-text-fill: green;" +
                                            "-fx-font-weight: bold;"
                            );
                        }
                    } else if (child.equals(tfMatchMonth)) {
                        if (!matchMonthValue.matches(monthPat)) {
                            System.out.println("\tSomething's wrong with the formatting of the MONTH of the match");
                            if (lbMessage.getText() == null) {
                                lbMessage.setText("Something's wrong with the formatting of the MONTH of the match");
                            }
                            child.setStyle("-fx-border-color: red;");
                        } else if (!(Integer.parseInt(matchMonthValue) <= 12)) {
                            System.out.println("\tTo many MONTHS provided!");
                            if (lbMessage.getText() == null) {
                                lbMessage.setText("To many MONTHS provided");
                            }
                            child.setStyle("-fx-border-color: red;");
                        } else {
                            child.setStyle(
                                    "-fx-border-color: green;" +
                                            "-fx-text-fill: green;" +
                                            "-fx-font-weight: bold;"
                            );
                        }
                    } else if (child.equals(tfMatchYear)) {
                        if (!matchYearValue.matches(yearPat)) {
                            System.out.println("\tSomething's wrong with the formatting of the YEAR of the match");
                            if (lbMessage.getText() == null) {
                                lbMessage.setText("Something's wrong with the formatting of the YEAR of the match");
                            }
                            child.setStyle("-fx-border-color: red;");
                        } else {
                            child.setStyle(
                                    "-fx-border-color: green;" +
                                            "-fx-text-fill: green;" +
                                            "-fx-font-weight: bold;"
                            );
                        }
                    }
                }
            }
        }

        try {
            ldMatchDate = LocalDate.parse(matchDayValue + "-" + matchMonthValue + "-" + matchYearValue,
                    DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (DateTimeParseException ex) {
            ldMatchDate = null;
        }

        playerArchiveCheck1 = checkPlayerArchive(players, player1Value);
        playerArchiveCheck2 = checkPlayerArchive(players, player2Value);

        try {
            //controleert of alle velden voldoen aan een aantal voorwaarden
            if ((player1Value != null && !player1Value.isEmpty()) && (player2Value != null && !player2Value.isEmpty())
                    && (winnerValue != null && (winnerValue.equals(player1Value) || winnerValue.equals(player2Value))
                    || (winnerValue == null || winnerValue.isEmpty()) && cBisDraw.isSelected())
                    && (!player1Value.equals(player2Value))
                    && (ldMatchDate != null)
                    && (playerArchiveCheck1 && playerArchiveCheck2)) {
                //todo confirm message doesn't show up?
                lbMessage.setText("New match entry created");
                lbMessage.setStyle(
                        "-fx-text-fill: green;" +
                        " -fx-font-weight: bold;"
                );
                System.out.println("\tAll data input is correct, creating a new row and writing data to a file");

                //controleert of de match geeindigd is in een gelijkspel of niet
                if (!cBisDraw.isSelected()) {
                    createMatch(player1Value, player2Value, winnerValue, ldMatchDate);
                } else {
                    Player player = new Player("dummy"); //creates a fake player, needs to be held out of the PlayerStatistics table
                    createMatch(player1Value, player2Value, player.getName(), ldMatchDate);
                }

                resetInputFields();
            } else {
                //controleert de input velden op de 3de rij van de view
                for (Node child : hbRow3.getChildren()) {
                    if (child.getTypeSelector().equals("TextField")
                            && (((TextField) child).getText() == null || ((TextField) child).getText().isEmpty())) {
                        child.setStyle("-fx-border-color: red;");
                    } else if (child.getTypeSelector().equals("TextField")
                            && (((TextField) child).getText().equals(player1Value) || ((TextField) child).getText().equals(player2Value))) {
                        //controleert of de opgegeven spelers bekend zijn in de Player_Archive.dat
                        if (!playerArchiveCheck1 && ((TextField) child).getText().equals(player1Value)) {
                            child.setStyle("-fx-border-color: red;");
                            System.out.println("\tPlayer doesn't exist in the player archive");
                            lbMessage.setText("Player doesn't exist in the player archive");
                            break;
                        } else if (!playerArchiveCheck2 && ((TextField) child).getText().equals(player2Value)) {
                            child.setStyle("-fx-border-color: red;");
                            System.out.println("\tPlayer doesn't exist in the player archive");
                            lbMessage.setText("Player doesn't exist in the player archive");
                            break;
                        //controleert of speler 1 niet gelijk is aan speler 2
                        } else if (((player1Value != null && !player1Value.isEmpty()) && (player2Value != null && !player2Value.isEmpty()))
                                && player1Value.equals(player2Value)) {
                            child.setStyle("-fx-border-color: red;");
                            System.out.println("\tPlayer 1 can't be the same player as player 2");
                            lbMessage.setText("Player 1 can't be the same player as player 2");
                        } else {
                            child.setStyle(
                                    "-fx-border-color: green;" +
                                    "-fx-text-fill: green;" +
                                    "-fx-font-weight: bold"
                            );
                        }
                    } else if (child.getTypeSelector().equals("TextField")
                            && (((TextField) child).getText() != null || !((TextField) child).getText().isEmpty())) {
                        child.setStyle(
                                "-fx-border-color: green;" +
                                        "-fx-text-fill: green;" +
                                        "-fx-font-weight: bold"
                        );
                    }
                    if (child.equals(tfAddWinner)) {
                        try {
                            //controleert of de winner van de match gelijk is aan speler 1 of speler 2
                            if ((!winnerValue.equals(player1Value) && !winnerValue.equals(player2Value) && !cBisDraw.isSelected())
                                    && !winnerValue.isEmpty()) {
                                System.out.println("\tThe Winner of the match isn't one of the participants");
                                if (lbMessage.getText() == null) {
                                    lbMessage.setText("The Winner of the match isn't one of the participants");
                                }
                                child.setStyle("-fx-border-color: red;");
                            } else {
                                //controleert of er aangegeven is of het gelijkspel is of niet
                                if (cBisDraw.isSelected() && winnerValue.isEmpty()) {
                                    System.out.println("\tIt's a draw");
                                    child.setStyle(
                                            "-fx-border-color: green;" +
                                            "-fx-text-fill: green;" +
                                            "-fx-font-weight: bold"
                                    );
                                    //controleert of er een winnaar is opgegeven wanneer het niet gelijkspel is
                                } else if (!cBisDraw.isSelected() && winnerValue.isEmpty()) {
                                    System.out.println("\tThe Winner of the match isn't provided");
                                    if (lbMessage.getText() == null) {
                                        lbMessage.setText("The Winner of the match isn't provided");
                                    }
                                    child.setStyle("-fx-border-color: red;");
                                }
                            }
                        } catch (Exception nullPointer) {
                            if (!cBisDraw.isSelected()) {
                                System.out.println("\tThe Winner of the match isn't provided");
                                if (lbMessage.getText() == null) {
                                    lbMessage.setText("The Winner of the match isn't provided");
                                }
                                child.setStyle("-fx-border-color: red;");
                            } else {
                                System.out.println("\tIt's a draw");
                                child.setStyle(
                                        "-fx-border-color: green;" +
                                        "-fx-text-fill: green;" +
                                        "-fx-font-weight: bold"
                                );
                            }
                        }
                    }
                }
                System.out.println("\tSome data is missing");
                if (lbMessage.getText() == null) {
                    lbMessage.setText("Some data is missing");
                }
            }
        } catch (NullPointerException ex) {
            System.out.println("\tSome data is missing");
            if (lbMessage.getText() == null) {
                lbMessage.setText("Some data is missing");
                lbMessage.setStyle("-fx-text-fill: red;");
            }
            tfAddPlayer1.setStyle("-fx-border-color: red;");
            tfAddPlayer2.setStyle("-fx-border-color: red;");
            tfAddWinner.setStyle("-fx-border-color: red;");
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
     * creeert een nieuwe match
     * @param player1 de naam van speler 1
     * @param player2 de naam van speler 2
     * @param winner de naam van de winnaar
     * @param ldMatchDate de datum wanneer de match heeft plaats gevonden
     */
    private void createMatch(String player1, String player2, String winner, LocalDate ldMatchDate) {
        //Creeert 2 dummy players zodat ze later overschreven kunnen worden door de al bekende players
        Player playerObject1 = new Player("dummy");
        Player playerObject2 = new Player("dummy");

        //overschrijft de dummy players met de al bekende players
        for (Player player : players) {
            if (player.getName().equals(player1)) {
                playerObject1 = player;
            } else if (player.getName().equals(player2)) {
                playerObject2 = player;
            }
        }

        game = new Game(playerObject1, playerObject2);

        //zet de eigenschappen van de nieuwe match en updates de wins / losses / draws van de spelers die meedoen
        if (game.getPlayer1().getName().equals(winner)) {
            game.setWinner(game.getPlayer1());
            game.getPlayer1().addWins();
            game.getPlayer2().addLosses();
        } else if (winner.equals("dummy")) {
            game.getPlayer1().addDraws();
            game.getPlayer2().addDraws();
        } else {
            game.setWinner(game.getPlayer2());
            game.getPlayer2().addWins();
            game.getPlayer1().addLosses();
        }
        game.setDateOfMatch(ldMatchDate);

        //maakt de properties van de matches leeg zodat ze weg geschreven kunnen worden
        for(int i = 0; i < data.size(); i++) {
            data.get(i).player1Property = null;
            data.get(i).player2Property = null;
            data.get(i).winnerProperty = null;
            data.get(i).dateOfMatchProperty = null;
        }

        data.add(game);

        //Updates de bestanden met de nieuwe data
        writeData.writeToDisk(data, "Match_History.dat");
        writeData.writeToDisk(players, "Player_Archive.dat");
        getMatchHistory();
        resetInputFields();
    }
}