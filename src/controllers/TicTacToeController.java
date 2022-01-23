package controllers;

import data.ReadData;
import data.WriteData;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import models.Game;
import models.Player;
import models.TicTacToe;

import java.util.ArrayList;

/**
 * Bepaalt wat er gebeurd op de tictactoeview
 *
 * @author Miguel Korn
 */
public class TicTacToeController {
    private GridPane gridPane;
    private TicTacToe ticTacToe;
    private Game game;
    private Label matchLabel;
    private Label turnLabel;
    private Player[] players = new Player[2];

    public TicTacToeController(Player player1, Player player2) {
        ticTacToe = new TicTacToe();
        players[0] = player1;
        players[1] = player2;
    }

    /**
     * initialiseerd de tictactoe controller
     */
    public void initialize() {
        turnLabel.setText("Turn: " + ticTacToe.getTurn());
        gridPane.setPrefSize(600, 600);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button();
                button.setId("tile" + i + "_" + j);

                button.setPrefSize(200, 200);
                button.setStyle("-fx-font: 24 arial;");

                GridPane.setHgrow(button, Priority.ALWAYS);
                GridPane.setVgrow(button, Priority.ALWAYS);

                gridPane.add(button, i, j);
            }
        }

        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button) {
                ((Button) node).setOnAction(t -> {
                    int i = GridPane.getRowIndex(node);
                    int j = GridPane.getColumnIndex(node);

                    System.out.println("Clicked: " + i + ", " + j);

                    if (ticTacToe.changeTile(i, j)) {
                        ((Button) node).setText(Character.toString(ticTacToe.grid[i][j].getState()));
                        turnLabel.setText("Turn: " + ticTacToe.getTurn());
                        game = new Game(players[0], players[1]);

                        if (ticTacToe.isGameOver()) {
                            disableButtons();
                            char winner = ticTacToe.getWinner();
                            if (winner == 'X') {
                                turnLabel.setText("Winner: " + players[0].getName());
                                game.setWinner(players[0]);
                            } else if (winner == 'O') {
                                turnLabel.setText("Winner: " + players[1].getName());
                                game.setWinner(players[1]);
                            } else {
                                turnLabel.setText("Winner: draw!");
                            }

                            ArrayList<Game> games = ReadData.readDataFile("Match_History.dat");
                            games.add(game);
                            WriteData.writeToDisk(games, "Match_History.dat");


                        }
                    }
                });
            }
        }
    }

    /**
     * disabled alle buttons in de gridpane
     */
    private void disableButtons() {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button) {
                node.setDisable(true);
            }
        }
    }

    /**
     * setter voor de gridpane
     * @param gridPane
     */
    public void setGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    /**
     * setter voor de matchlabel
     * @param matchLabel
     */
    public void setMatchLabel(Label matchLabel) {
        this.matchLabel = matchLabel;
    }

    /**
     * setter voor de turnlabel
     * @param turnLabel
     */
    public void setTurnLabel(Label turnLabel) {
        this.turnLabel = turnLabel;
    }
}
