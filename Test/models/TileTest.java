package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {
    private Tile tile;

    @BeforeEach
    void setUp() {
        this.tile = new Tile();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getState() {
        char expected = ' ';
        char actual = tile.getState();
        assertEquals(expected, actual);
    }

    @Test
    void changeState() {
        boolean actual = tile.changeState('X');
        assertTrue(actual);
    }
}