package ntnu.idi.idatt.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test implementation of the Board class for testing purposes.
 */
class TestBoard extends Board {
    public TestBoard() {
        this.tiles = new HashMap<>();
        initializeBoard();
    }

    @Override
    protected void initializeBoard() {
        for (int i = 0; i < 10; i++) {
            tiles.put(i, new Tile(i));
        }
    }
}

/**
 * Tests for the Board abstract class.
 */
class BoardTest {

    

    private Board board;

    @BeforeEach
    void setUp() {
        board = new TestBoard();
    }

    @Test
    void getTileReturnsTile() {
        Tile tile = board.getTile(5);
        assertNotNull(tile, "Tile should not be null");
        assertEquals(5, tile.getTileId(), "Tile ID should match");
    }

    @Test
    void getTileNonExistingReturnsNull() {
        Tile tile = board.getTile(20);
        assertNull(tile, "Non-existing tile should return null");
    }

    @Test
    void getTilesReturnsAllTiles() {
        Map<Integer, Tile> tiles = board.getTiles();
        assertNotNull(tiles, "Tiles map should not be null");
        assertEquals(10, tiles.size(), "There should be 10 tiles");
        
        for (int i = 0; i < 10; i++) {
            assertTrue(tiles.containsKey(i), "Tiles map should contain key " + i);
            assertNotNull(tiles.get(i), "Tile with ID " + i + " should not be null");
            assertEquals(i, tiles.get(i).getTileId(), "Tile ID should match");
        }
    }

    @Test
    void getTilesReturnsMutableMap() {
        Map<Integer, Tile> originalTiles = board.getTiles();
        int originalSize = originalTiles.size();
        originalTiles.put(99, new Tile(99));
        assertEquals(originalSize + 1, board.getTiles().size());
        assertNotNull(board.getTile(99));
    }

    @Test
    void boardInitializationCreatesCorrectStructure() {
        Board newBoard = new TestBoard();
        assertEquals(10, newBoard.getTiles().size(), "Board should have 10 tiles");
        for (int i = 0; i < 10; i++) {
            Tile tile = newBoard.getTile(i);
            assertNotNull(tile, "Tile " + i + " should not be null");
            assertEquals(i, tile.getTileId(), "Tile ID should match");
        }
    }
}