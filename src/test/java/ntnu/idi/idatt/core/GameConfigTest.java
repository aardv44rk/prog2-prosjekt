package ntnu.idi.idatt.core;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import ntnu.idi.idatt.games.snakesandladders.SnakesAndLaddersBoardFactory;
import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.GameConfig;
import ntnu.idi.idatt.models.Piece;
import ntnu.idi.idatt.models.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ntnu.idi.idatt.games.snakesandladders.LinearMovementStrategy;
import ntnu.idi.idatt.games.snakesandladders.SnakesAndLaddersBoard;

public class GameConfigTest {

  private GameConfig gameConfig;
  private List<Player> players;
  private Board board;
  private final String CONFIG_TEST_FILE = "test_config.json";
  private final String PLAYER_LIST_TEST_FILE = "test_players.json";

  @BeforeEach
  public void setUp() {
    // Create a test board
    board = SnakesAndLaddersBoardFactory.createStandardBoard();

    // Create test players with pieces
    players = new ArrayList<>();

    // Player 1 with one piece
    List<Piece> pieces1 = new ArrayList<>();
    Player player1 = new Player("Alice", pieces1);
    Piece piece1 = new Piece(board.getTile(5), player1, new LinearMovementStrategy());
    pieces1.add(piece1);
    players.add(player1);

    // Player 2 with one piece
    List<Piece> pieces2 = new ArrayList<>();
    Player player2 = new Player("Bob", pieces2);
    Piece piece2 = new Piece(board.getTile(10), player2, new LinearMovementStrategy());
    pieces2.add(piece2);
    players.add(player2);

    // Create game config with current player index 0 (Alice's turn)
    gameConfig = new GameConfig(players, board, 0);
  }

  @AfterEach
  public void tearDown() {
    // Delete test files after tests
    try {
      Files.deleteIfExists(Paths.get(CONFIG_TEST_FILE));
      Files.deleteIfExists(Paths.get(PLAYER_LIST_TEST_FILE));
    } catch (IOException e) {
      System.err.println("Error cleaning up test files: " + e.getMessage());
    }
  }

  @Test
  public void testSaveAndLoadConfig() {
    try {
      // Save the game configuration
      gameConfig.saveConfig(CONFIG_TEST_FILE);

      // Check that the file exists
      assertTrue(Files.exists(Paths.get(CONFIG_TEST_FILE)), "Config file should be created");

      // Load the configuration
      GameConfig loadedConfig = gameConfig.loadConfig(CONFIG_TEST_FILE);

      // Verify the loaded configuration
      assertNotNull(loadedConfig, "Loaded config should not be null");
      assertEquals(gameConfig.getCurrentPlayerIndex(), loadedConfig.getCurrentPlayerIndex(),
          "Current player index should match");
      assertEquals(gameConfig.getPlayers().size(), loadedConfig.getPlayers().size(),
          "Player count should match");
      assertEquals("Alice", loadedConfig.getPlayers().get(0).getName(),
          "First player name should be Alice");
      assertEquals("Bob", loadedConfig.getPlayers().get(1).getName(),
          "Second player name should be Bob");

      // Check pieces are loaded correctly
      assertEquals(1, loadedConfig.getPlayers().get(0).getPieces().size(),
          "First player should have 1 piece");
      assertEquals(5,
          loadedConfig.getPlayers().get(0).getPieces().get(0).getCurrentTile().getTileId(),
          "First player's piece should be on tile 5");
    } catch (IOException e) {
      fail("Exception should not be thrown: " + e.getMessage());
    }
  }

  @Test
  public void testSaveAndLoadPlayerList() {
    try {
      // Save player list
      gameConfig.savePlayerList(PLAYER_LIST_TEST_FILE);

      // Check that the file exists
      assertTrue(Files.exists(Paths.get(PLAYER_LIST_TEST_FILE)),
          "Player list file should be created");

      // Load player list
      List<Player> loadedPlayers = gameConfig.loadPlayerList(PLAYER_LIST_TEST_FILE);

      // Verify the loaded players
      assertNotNull(loadedPlayers, "Loaded players should not be null");
      assertEquals(2, loadedPlayers.size(), "Should load 2 players");
      assertEquals("Alice", loadedPlayers.get(0).getName(), "First player should be Alice");
      assertEquals("Bob", loadedPlayers.get(1).getName(), "Second player should be Bob");

      // Loaded players should have empty piece lists
      assertEquals(0, loadedPlayers.get(0).getPieces().size(),
          "Loaded players should have empty piece lists");
    } catch (IOException e) {
      fail("Exception should not be thrown: " + e.getMessage());
    }
  }

  @Test
  public void testNonExistentFile() {
    try {
      // Try to load from a non-existent file
      List<Player> loadedPlayers = gameConfig.loadPlayerList("non_existent_file.json");
      fail("Should throw an IOException when file doesn't exist");
    } catch (IOException e) {
      // Expected exception
      assertTrue(e.getMessage().contains("File not found"),
          "Exception message should indicate file not found");
    }
  }
}