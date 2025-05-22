package ntnu.idi.idatt.games.snakesandladders;

import ntnu.idi.idatt.exceptions.InvalidInputException;
import ntnu.idi.idatt.models.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for the SnakesAndLaddersBoard class.
 */
class SnakesAndLaddersBoardTest {

  private SnakesAndLaddersBoard board;
  private final int rows = 8;
  private final int columns = 7;
  private final int totalTiles = rows * columns;
  private List<SnakesAndLaddersLadder> testLadders;

  @BeforeEach
  void setUp() {
    testLadders = new ArrayList<>();
    testLadders.add(new SnakesAndLaddersLadder(1, 10));
    testLadders.add(new SnakesAndLaddersLadder(20, 5));
    board = new SnakesAndLaddersBoard(rows, columns, testLadders);
  }

  @Test
  void testConstructorInitializesBoardCorrectly() {
    assertNotNull(board, "Board should be created.");
    assertEquals(rows, board.getRows(), "Number of rows should match constructor argument.");
    assertEquals(columns, board.getColumns(), "Number of columns should match constructor argument.");
    assertEquals(totalTiles, board.getTiles().size(), "Total number of tiles should be rows * columns.");
    assertEquals(testLadders, board.getLadders(), "Ladders list should match constructor argument.");
  }

  @Test
  void testInitializeBoardCreatesAllTiles() {
    for (int i = 0; i < totalTiles; i++) {
      Tile tile = board.getTile(i);
      assertNotNull(tile, "Tile with 0-based ID " + i + " should exist.");
      assertEquals(i, tile.getTileId(), "Tile ID should be " + i + " (0-based).");
    }
  }

  @Test
  void testGetRows() {
    assertEquals(rows, board.getRows(), "getRows should return the correct number of rows.");
  }

  @Test
  void testGetColumns() {
    assertEquals(columns, board.getColumns(), "getColumns should return the correct number of columns.");
  }

  @Test
  void testGetLadders() {
    List<SnakesAndLaddersLadder> retrievedLadders = board.getLadders();
    assertNotNull(retrievedLadders, "Ladders list should not be null.");
    assertEquals(testLadders.size(), retrievedLadders.size(), "Number of ladders should match.");
    assertTrue(retrievedLadders.containsAll(testLadders), "Retrieved ladders should contain all initial ladders.");
  }

  @Test
  void testInvalidArgumentsThrowException() {
    // Test invalid board size
    InvalidInputException e = assertThrows(InvalidInputException.class,
        () -> new SnakesAndLaddersBoard(0, 0, testLadders),
        "Should throw exception for invalid board size.");
    InvalidInputException e2 = assertThrows(InvalidInputException.class,
        () -> new SnakesAndLaddersBoard(-1, -1, testLadders),
        "Should throw exception for negative board size.");

    assertEquals("Invalid board parameters", e.getMessage(), "Exception message should match.");
    assertEquals("Invalid board parameters", e2.getMessage(), "Exception message should match.");
  }
}