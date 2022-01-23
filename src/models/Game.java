package models;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *  Model of the Game, determines the properties of a game (match)
 * @author Miguel Korn, Oscar Wellner
 */
public class Game implements Serializable {
    private LocalDate dateOfMatch;
    private Player winner;
    private Player player1;
    private Player player2;

    public SimpleStringProperty player1Property;
    public SimpleStringProperty player2Property;
    public SimpleStringProperty winnerProperty;
    public SimpleStringProperty dateOfMatchProperty;

    /**
     * Constructs the Game with player 1 and 2, and the sets the date of the match the current date
     * @param playerX player 1
     * @param playerO player 2
     */
    public Game(Player playerX, Player playerO) {
        this.player1 = playerX;
        this.player2 = playerO;
        this.dateOfMatch = LocalDate.now();
    }

    /**
     * Returns the date on which the game was played
     * @return the date of the match
     */
    public String getDateOfMatch() {
        return dateOfMatch.toString();
    }

    /**
     * Sets the date of the match on which the match was played
     * @param dateOfMatch The date of the match
     */
    public void setDateOfMatch(LocalDate dateOfMatch) {
        this.dateOfMatch = dateOfMatch;
    }

    /**
     * Sets the winner of the match
     * @param player The winner of the match
     */
    public void setWinner(Player player) {
        this.winner = player;
    }

    /**
     * Gets player 1 of the match, who also controls the 'X'
     * @return player 1
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     * Gets player 2 of the match, who also controls the 'O'
     * @return player 2
     */
    public Player getPlayer2() {
        return player2;
    }

    /**
     * Returns the winner of the match
     * @return the winner of the match
     */
    public Player getWinner() {
        return winner;
    }

    /**
     * Sets the player 1 property, necessary for the game to show up in the Match_History table
     */
    public void setPlayer1Property() {
        this.player1Property = new SimpleStringProperty(this.player1.getName());
    }

    /**
     * Sets the player 2 property, necessary for the game to show up in the Match_History table
     */
    public void setPlayer2Property() {
        this.player2Property = new SimpleStringProperty(this.player2.getName());
    }

    /**
     * Sets the winner property, necessary for the game to show up in the Match_History table
     */
    public void setWinnerProperty() {
        if (this.getWinner() != null) {
            this.winnerProperty = new SimpleStringProperty(this.getWinner().getName());
        } else {
            this.winnerProperty = new SimpleStringProperty("");
        }
    }

    /**
     * Sets the date of the match property, necessary for the game to show up in the Match_History table
     */
    public void setDateOfMatchProperty() {
        this.dateOfMatchProperty = new SimpleStringProperty(this.getDateOfMatch());
    }
}
