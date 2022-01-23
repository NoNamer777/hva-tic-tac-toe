package models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

/**
 * Defines the Player and the values that he / she can have.
 * @author Oscar Weller
 */
public class Player implements Serializable {
    public SimpleStringProperty nameProperty;
    public SimpleIntegerProperty winsProperty;
    public SimpleIntegerProperty drawsProperty;
    public SimpleIntegerProperty lossesProperty;
    public SimpleIntegerProperty pointsProperty;

    private String name;
    private int wins;
    private int losses;
    private int draws;
    private int points;
    private static int AMOUNT_POINTS_WINNING = 3;

    /**
     * Constructs the player when a user creates a player account. Also initializes the name, and the amount of wins, losses,
     * draws and points the player has.
     * @param name the name of the player that the user has given.
     */
    public Player(String name) {
        this.name = name;
        this.wins = 0;
        this.losses = 0;
        this.draws = 0;
        this.points = 0;
    }

    /**
     * sets the amount of losses of a player.
     */
    public void addLosses() {
        this.losses += 1;
    }

    /**
     * Sets the amount of wins of a player.
     */
    public void addWins() {
        this.wins += 1;
        this.points += AMOUNT_POINTS_WINNING;
    }

    /**
     * sets the amount of draws of a player.
     */
    public void addDraws() {
        this.draws += 1;
        this.points += 1;
    }

    /**
     * Sets the name of a player.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * returns the name of the player.
     * @return the name of the player.
     */
    public String getName() {
        return this.name;
    }

    /**
     * returns the amount of wins the player has.
     * @return the amount of wins.
     */
    public int getWins() {
        return this.wins;
    }

    /**
     * returns the amount of losses the player has.
     * @return the amount of losses.
     */
    public int getLosses() { return this.losses; }

    /**
     * returns the amount of draws the player has played.
     * @return the amount of draws.
     */
    public int getDraws() { return this.draws; }

    /**
     * returns the amount of points a player has.
     * @return the amount of points.
     */
    public int getPoints() { return this.points; }

    /**
     * sets the pointsProperty necessary to get the name in the table
     * @param name the name to be set in the table
     */
    public void setNameProperty(String name) {
        this.nameProperty = new SimpleStringProperty(name);
    }

    /**
     * sets the pointsProperty necessary to get the wins in the table
     * @param wins the wins to be set in the table
     */
    public void setWinsProperty(int wins) {
        this.winsProperty = new SimpleIntegerProperty(wins);
    }

    /**
     * sets the pointsProperty necessary to get the draws in the table
     * @param draws the draws to be set in the table
     */
    public void setDrawsProperty(int draws) {
        this.drawsProperty = new SimpleIntegerProperty(draws);
    }

    /**
     * sets the pointsProperty necessary to get the losses in the table
     * @param losses the losses to be set in the table
     */
    public void setLossesProperty(int losses) {
        this.lossesProperty = new SimpleIntegerProperty(losses);
    }

    /**
     * sets the pointsProperty necessary to get the points in the table
     * @param points the points to be set in the table
     */
    public void setPointsProperty(int points) {
        this.pointsProperty = new SimpleIntegerProperty(points);
    }

    /**
     * returns a String containing the name
     * @return a String containing the name
     */
    @Override
    public String toString() {
        return this.getName();
    }
}
