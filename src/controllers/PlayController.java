package controllers;

import data.ReadData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import models.Player;
import views.CreateNewPlayerView;
import views.TicTacToeView;

/**
 * Zorgt voor de setup en het uitvoeren het spel
 *
 * @author Miguel
 */
public class PlayController {

    private BorderPane borderPane;
    private Player player1;
    private Player player2;
    private ComboBox p1ComboBox;
    private ComboBox p2ComboBox;
    private Button playButton;

    private ObservableList players;

    /**
     * initialiseerd de play controller
     */
    public void initialize() {
        CreateNewPlayerController createNewPlayerController = new CreateNewPlayerController();
        CreateNewPlayerView createNewPlayerView = new CreateNewPlayerView(createNewPlayerController);

        players = FXCollections.observableList(ReadData.readDataFile("Player_Archive.dat"));

        p1ComboBox.setItems(players);
        p1ComboBox.setValue("Choose");
        p1ComboBox.setOnAction(t -> {
            onComboBoxChange();
        });

        p2ComboBox.setItems(players);
        p2ComboBox.setValue("Choose");
        p2ComboBox.setOnAction(t -> {
            onComboBoxChange();
        });

        playButton.setOnAction(t -> {
            System.out.println("play game");
            TicTacToeController ticTacToeController = new TicTacToeController(player1, player2);
            TicTacToeView ticTacToeView = new TicTacToeView(ticTacToeController);

            borderPane = ticTacToeView.changeBorderPane(borderPane);
        });
    }

    /**
     * veranderd de visibility als de players een waarde hebben die niet hetzelfde zijn
     */
    private void onComboBoxChange() {
        if (!p1ComboBox.getValue().toString().equals("Choose")) {
            player1 = (Player) p1ComboBox.getValue();
        }

        if (!p2ComboBox.getValue().toString().equals("Choose")) {
            player2 = (Player) p2ComboBox.getValue();
        }

        playButton.setDisable(!(player1 != null && player2 != null && !player1.equals(player2)));
    }

    /**
     * setter voor p1 combobox
     * @param p1ComboBox
     */
    public void setP1ComboBox(ComboBox p1ComboBox) {
        this.p1ComboBox = p1ComboBox;
    }

    /**
     * setter voor p2 combobox
     * @param p2ComboBox
     */
    public void setP2ComboBox(ComboBox p2ComboBox) {
        this.p2ComboBox = p2ComboBox;
    }

    /**
     * setter voor play button
     * @param playButton
     */
    public void setPlayButton(Button playButton) {
        this.playButton = playButton;
    }

    /**
     * setter voor borderpane
     * @param borderPane
     */
    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }
}
