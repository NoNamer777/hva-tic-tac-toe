package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompetitionTest {
    Competition competition = new Competition("Grand TicTacToe");

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getName() {
        String expected = "Grand TicTacToe";
        String actual = competition.getName();

        assertEquals(expected, actual);
    }
}