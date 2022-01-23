package models;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Model of a Competition, determines the properties of a Competition
 * @author Miguel Korn, Oscar Wellner
 */
public class Competition implements Serializable {
    private String name;
    private int amountOfMatches;
    private Player player1;
    private Player player2;
    private Player winner;
    private ArrayList<Game> matches;
    private LocalDate dateOfCompetition;

    public SimpleStringProperty nameProperty;
    public SimpleStringProperty winnerProperty;
    public SimpleStringProperty amountOfMatchesProperty;
    public SimpleStringProperty player1Property;
    public SimpleStringProperty player2Property;
    public SimpleStringProperty dateOfCompetitionProperty;

    /**
     * Constructs a new Competition with the name of the competition
     * @param name the name of the competition
     */
    public Competition(String name) {
        this.name = name;
        this.matches = new ArrayList<>();
        this.amountOfMatches = 0;
    }

    /**
     * Returns the name of the competition
     * @return the name of the competition
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the amount of matches that this competition consists out of
     * @return the amount of matches of this competition
     */
    public int getAmountOfMatches() {
        return this.amountOfMatches;
    }

    /**
     * Returns player 1 of this competition
     * @return player 1
     */
    public String getPlayer1() {
        return this.player1.getName();
    }

    /**
     * Returns player 2 of the competition
     * @return player 2
     */
    public String getPlayer2() {
        return this.player2.getName();
    }

    /**
     * Returns the winner of the competition
     * @return the winner of the competition
     */
    public String getWinner() {
        return this.winner.getName();
    }

    /**
     * Returns the date of the competition
     * @return the date of the competition
     */
    public String getDateOfCompetition() {
        return this.dateOfCompetition.toString();
    }

    /**
     * Sets player 1 of the competition
     * @param player player 1
     */
    public void setPlayer1(Player player) {
        this.player1 = player;
    }

    /**
     * Sets player 2 of the competition
     * @param player player 2
     */
    public void setPlayer2(Player player) {
        this.player2 = player;
    }

    /**
     * Sets the winner of the competition
     * @param winner the winner of the competition
     */
    public void setWinner(Player winner) {
        this.winner = winner;
    }

    /**
     * Sets the date on which this competition was held on
     * @param date the date on which this competition was held on
     */
    public void setDateOfCompetition(LocalDate date) {
        this.dateOfCompetition = date;
    }

    /**
     * Adds a match to the Competition and updates the number of matches in this competition
     * @param match the match that is going to be added to the competition
     */
    public void addMatch(Game match) {
        this.matches.add(match);
        this.amountOfMatches++;
    }

    /**
     * Sets the name property of the competition, necessary for the competition to be loaded in the Competition History table
     */
    public void setNameProperty() {
        this.nameProperty = new SimpleStringProperty(this.getName());
    }

    /**
     * Sets the amount of matches property of the competition, necessary for the competition to be loaded in the Competition History table
     */
    public void setAmountOfMatchesProperty() {
        this.amountOfMatchesProperty = new SimpleStringProperty(Integer.toString(this.getAmountOfMatches()));
    }

    /**
     * Sets the date property of the competition, necessary for the competition to be loaded in the Competition History table
     */
    public void setDateOfCompetitionProperty() {
        this.dateOfCompetitionProperty = new SimpleStringProperty(this.matches.get(0).getDateOfMatch());
    }

    /**
     * Sets the player 1 property of the competition, necessary for the competition to be loaded in the Competition History table
     */
    public void setPlayer1Property() {
        this.player1Property = new SimpleStringProperty(this.getPlayer1());
    }

    /**
     * Sets the player 2 property of the competition, necessary for the competition to be loaded in the Competition History table
     */
    public void setPlayer2Property() {
        this.player2Property = new SimpleStringProperty(this.getPlayer2());
    }

    /**
     * Sets the winner property of the competition, necessary for the competition to be loaded in the Competition History table
     */
    public void setWinnerProperty() {
        this.winnerProperty = new SimpleStringProperty(this.getWinner());
    }
}
