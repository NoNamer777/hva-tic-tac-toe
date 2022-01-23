package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player player;

    @BeforeEach
    void setUp() {
        player = new Player("NoNamer777");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getWins() {
        int EXPECTED_WINS = 0;
        int actualWins = player.getWins();

        assertEquals(EXPECTED_WINS, actualWins);
    }

    @Test
    void getLosses() {
        int EXPECTED_LOSSES = 0;
        int actualLosses = player.getLosses();

        assertEquals(EXPECTED_LOSSES, actualLosses);
    }

    @Test
    void getDraws() {
        int EXPECTED_Draws = 0;
        int actualDraws = player.getDraws();

        assertEquals(EXPECTED_Draws, actualDraws);
    }

    @Test
    void getPoints() {
        int EXPECTED_POINTS = 0;
        int actualPoints = player.getPoints();

        assertEquals(EXPECTED_POINTS, actualPoints);
    }

    @Test
    void getName() {
        //Testing both the constructor with and without a name input
        String EXPECTED_NAME = "NoNamer777";
        String actualName = player.getName();

        assertEquals(EXPECTED_NAME, actualName);
    }

    @Test
    void addWins() {
        int EXPECTED_WINS = 1;
        player.addWins();
        int actualWins = player.getWins();

        assertEquals(EXPECTED_WINS, actualWins);
    }

    @Test
    void addLosses() {
        int EXPECTED_LOSSES = 1;
        player.addLosses();
        int actualLosses = player.getLosses();

        assertEquals(EXPECTED_LOSSES, actualLosses);
    }

    @Test
    void addDraws() {
        int EXPECTED_DRAWS = 1;
        player.addDraws();
        int actualDraws = player.getDraws();

        assertEquals(EXPECTED_DRAWS, actualDraws);
    }

    @Test
    void setName() {
        String EXPECTED_CUSTOM_NAME = "Menno777";
        player.setName("Menno777");
        String actualCustomName = player.getName();

        assertEquals(EXPECTED_CUSTOM_NAME, actualCustomName);
    }
}