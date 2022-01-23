package controllers;

import data.ReadData;
import data.WriteData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import models.Player;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * bepaaldt wat er gebeurd wanneer de CreateNewPlayerView wordt aangeroepen
 * @author Oscar Wellner
 */
public class CreateNewPlayerController {
    private static String DEFAULT_PLAYER_NAME = "player";
    private static int DEAFULT_PLAYER_NUMBER = 0;

    private Label lbHeader;
    private TextField tfPlayerName;
    private Label lbMessage;
    private Button btCreatePlayer;
    private Label dummy1;
    private Label dummy2;

    private HBox hbRow2;

    private ReadData readData;
    private WriteData writeData;
    private ArrayList<Player> players;

    public void initialize() {
        lbHeader.setText("Create a new Player");
        lbHeader.setFont(Font.font("Arial Bold", FontWeight.BOLD, 16));
        lbHeader.setPadding(new Insets(8.0, 8.0, 0.0, 16.0));

        hbRow2.setSpacing(8.0);
        hbRow2.setPadding(new Insets(8.0));

        lbMessage.setTranslateX(16.0);

        btCreatePlayer.setText("Create Player");
        btCreatePlayer.setPadding(new Insets(8.0));
        btCreatePlayer.setTranslateX(16.0);
        btCreatePlayer.setTranslateY(24.0);

        btCreatePlayer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Creating new player");

                checkPlayerExistence();
            }
        });
    }

    public void setLbHeader(Label lbHeader) {
        this.lbHeader = lbHeader;
    }

    public void setDummy1(Label dummy1) {
        this.dummy1 = dummy1;
    }

    public void setTfPlayerName(TextField tfPlayerName) {
        this.tfPlayerName = tfPlayerName;
    }

    public void setDummy2(Label dummy2) {
        this.dummy2 = dummy2;
    }

    public void setLbMessage(Label lbMessage) {
        this.lbMessage = lbMessage;
    }

    public void setBtCreatePlayer(Button btCreatePlayer) {
        this.btCreatePlayer = btCreatePlayer;
    }

    public void setHbRow2(HBox hbRow2) {
        this.hbRow2 = hbRow2;
    }

    /**
     * reset de input waarden en de message label naar de standaard waarden
     */
    public void resetView() {
        lbMessage.setText("Keep Player name 'DEFAULT'\nto get a default Player name.");
        lbMessage.setStyle(null);
        tfPlayerName.setText("DEFAULT");
        tfPlayerName.deselect();
        tfPlayerName.setStyle(null);
    }

    /**
     * controleert de Player_Archive.dat of de ingevoerde naam overeenkomt met de naam van een al gemaakte speler
     */
    private void checkPlayerExistence() {
        String nameToCheck = tfPlayerName.getText();

        readData = new ReadData();
        writeData = new WriteData();
        players = new ArrayList<>();

        players = readData.readDataFile("Player_Archive.dat");
        boolean playerNonExistent = false;

        //controleert of er een waarde gegeven is
        if(nameToCheck != null && !nameToCheck.isEmpty()) {
            //controleert of er uberhaupt spelers bestaan
            if (players.toArray().length != 0) {
                //controleert alle spelers of hun naam wel of niet matched met de opgegeven naam voor de nieuwe speler
                for (Player player : players) {
                    if(!player.getName().equals(nameToCheck) && (players.indexOf(player) == (players.toArray().length - 1))) {
                        System.out.println("\t" + nameToCheck + " DOESN'T exist!");

                        playerNonExistent = true;
                        break;
                    } else if(player.getName().equals(nameToCheck)) {
                        System.out.println("\t" + nameToCheck + " DOES exist!");

                        lbMessage.setText("Player already exists");
                        lbMessage.setStyle("-fx-text-fill: red; -fx-font-weight: bold");

                        tfPlayerName.setStyle("-fx-border-color: red");
                        break;
                    }
                }
                //als de naam van de speler niet overeenkomt met een andere speler wordt er een niewe speler gemaakt
                if (playerNonExistent) {
                    addPlayerToArchive(players, nameToCheck);

                    lbMessage.setText("New Player Created");
                    lbMessage.setStyle("-fx-text-fill: green; -fx-font-weight: bold");
                }
            } else {
                addPlayerToArchive(players, nameToCheck);

                lbMessage.setText("New Player Created");
                lbMessage.setStyle("-fx-text-fill: green; -fx-font-weight: bold");
            }
        } else {
            System.out.println("\tPlayer name is missing");
            lbMessage.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            lbMessage.setText("Player name is required");

            tfPlayerName.setStyle("-fx-border-color: red;");
        }
    }

    /**
     * Maakt de nieuwe speler aan
     * @param players de lijst met al bekende spelers
     * @param newPlayerName de naam van de nieuwe speler
     */
    private void addPlayerToArchive(ArrayList<Player> players, String newPlayerName) {
        Player newPlayer;

        //controleert of er een standaard naam gegenereerd moet worden
        if (newPlayerName.equals("DEFAULT")) {
            ArrayList<String> playerNames = new ArrayList<>();
            ArrayList<Integer> defaultNumbersUsed = new ArrayList<>();
            for(Player player : players) {
                if (player.getName().matches("player\\d\\d\\d\\d")) {
                    playerNames.add(player.getName());
                }
            }
            //haalt het hoogste nummer op van de speler die als laatst is gemaakt met een standaard naam
            for(String name : playerNames) {
                name = name.replace("player", "");
                for (char number : name.toCharArray()) {
                    if (number == 0) {
                        name = name.replaceFirst(Character.toString(number), "");
                    } else {
                        break;
                    }
                }
                defaultNumbersUsed.add(Integer.parseInt(name));
            }

            defaultNumbersUsed.sort(Integer::compareTo);
            if (defaultNumbersUsed.size() != 0) {
                DEAFULT_PLAYER_NUMBER = defaultNumbersUsed.get((defaultNumbersUsed.size() - 1)) + 1;
            }

            newPlayer = new Player(String.format("%6s%04d", DEFAULT_PLAYER_NAME, DEAFULT_PLAYER_NUMBER));
        } else {
            newPlayer = new Player(newPlayerName);
        }

        players.add(newPlayer);

        writeData.writeToDisk(players, "Player_Archive.dat");

        Stage stage = (Stage) lbHeader.getScene().getWindow();
        stage.close();
    }
}
