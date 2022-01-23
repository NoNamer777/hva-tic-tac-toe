package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeTest {
    private TicTacToe ticTacToe;

    @BeforeEach
    void setUp() {
        this.ticTacToe = new TicTacToe();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void changeTile() {
        boolean actual = ticTacToe.changeTile(0,0);
        assertTrue(actual);
    }

    @Test
    void isGameOver() {
        boolean actual = ticTacToe.isGameOver();
        assertFalse(actual);
    }
}