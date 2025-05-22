package ntnu.idi.idatt.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import ntnu.idi.idatt.exceptions.ConfigurationException;
import ntnu.idi.idatt.exceptions.InvalidInputException;
import ntnu.idi.idatt.exceptions.ReadException;
import ntnu.idi.idatt.games.snakesandladders.LinearMovementStrategy;
import ntnu.idi.idatt.games.snakesandladders.SnakesAndLaddersBoardFactory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ntnu.idi.idatt.games.snakesandladders.SnakesAndLaddersBoard;

class GameConfigTest {

  private GameConfig gameConfig;
  private GameConfig gameConfig2;
  private GameConfig gameConfig3;
  private List<Player> players;
  private Board smallBoard;
  private Board standardBoard;
  private Board bigBoard;
  private final String CONFIG_TEST_FILE = "test_config.json";
  private final String PLAYER_LIST_TEST_FILE = "test_players.json";

  @BeforeEach
  public void setUp() {
    // Create test boards
    smallBoard = SnakesAndLaddersBoardFactory.createSmallBoard();
    standardBoard = SnakesAndLaddersBoardFactory.createStandardBoard();
    bigBoard = SnakesAndLaddersBoardFactory.createBigBoard();

    // Create test players with pieces
    players = new ArrayList<>();

    // Player 1 with one piece
    List<Piece> pieces1 = new ArrayList<>();
    Player player1 = new Player("Alice", pieces1);
    Piece piece1 = new Piece(standardBoard.getTile(5), player1, new LinearMovementStrategy());
    pieces1.add(piece1);
    players.add(player1);

    // Player 2 with one piece
    List<Piece> pieces2 = new ArrayList<>();
    Player player2 = new Player("Bob", pieces2);
    Piece piece2 = new Piece(standardBoard.getTile(10), player2, new LinearMovementStrategy());
    pieces2.add(piece2);
    players.add(player2);

    // Create game config with current player index 0 (Alice's turn)
    gameConfig = new GameConfig(players, smallBoard, 0);
    gameConfig2 = new GameConfig(players, standardBoard, 0);
    gameConfig3 = new GameConfig(players, bigBoard, 0);
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

      System.out.println("Saving game configuration to: " + CONFIG_TEST_FILE);
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

      // Check board is loaded correctly
      assertTrue(loadedConfig.getBoard() instanceof SnakesAndLaddersBoard,
          "Loaded board should be of type SnakesAndLaddersBoard");
      SnakesAndLaddersBoard loadedBoard = (SnakesAndLaddersBoard) loadedConfig.getBoard();
      // Check board properties are correct
      assertEquals(7, loadedBoard.getRows(), "Loaded board should have 10 rows");
      assertEquals(8, loadedBoard.getColumns(), "Loaded board should have 10 columns");

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
  public void testSaveAndLoadConfig2() {
    try {

      System.out.println("Saving game configuration to: " + CONFIG_TEST_FILE);
      // Save the game configuration
      gameConfig2.saveConfig(CONFIG_TEST_FILE);

      // Check that the file exists
      assertTrue(Files.exists(Paths.get(CONFIG_TEST_FILE)), "Config file should be created");

      // Load the configuration
      GameConfig loadedConfig = gameConfig2.loadConfig(CONFIG_TEST_FILE);

      // Verify the loaded configuration
      assertNotNull(loadedConfig, "Loaded config should not be null");
      assertEquals(gameConfig2.getCurrentPlayerIndex(), loadedConfig.getCurrentPlayerIndex(),
          "Current player index should match");
      assertEquals(gameConfig2.getPlayers().size(), loadedConfig.getPlayers().size(),
          "Player count should match");
      assertEquals("Alice", loadedConfig.getPlayers().get(0).getName(),
          "First player name should be Alice");
      assertEquals("Bob", loadedConfig.getPlayers().get(1).getName(),
          "Second player name should be Bob");

      // Check board is loaded correctly
      assertTrue(loadedConfig.getBoard() instanceof SnakesAndLaddersBoard,
          "Loaded board should be of type SnakesAndLaddersBoard");
      SnakesAndLaddersBoard loadedBoard = (SnakesAndLaddersBoard) loadedConfig.getBoard();
      // Check board properties are correct
      assertEquals(9, loadedBoard.getRows(), "Loaded board should have 10 rows");
      assertEquals(10, loadedBoard.getColumns(), "Loaded board should have 10 columns");

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
  public void testSaveAndLoadConfig3() {
    try {

      System.out.println("Saving game configuration to: " + CONFIG_TEST_FILE);
      // Save the game configuration
      gameConfig3.saveConfig(CONFIG_TEST_FILE);

      // Check that the file exists
      assertTrue(Files.exists(Paths.get(CONFIG_TEST_FILE)), "Config file should be created");

      // Load the configuration
      GameConfig loadedConfig = gameConfig3.loadConfig(CONFIG_TEST_FILE);

      // Verify the loaded configuration
      assertNotNull(loadedConfig, "Loaded config should not be null");
      assertEquals(gameConfig3.getCurrentPlayerIndex(), loadedConfig.getCurrentPlayerIndex(),
          "Current player index should match");
      assertEquals(gameConfig3.getPlayers().size(), loadedConfig.getPlayers().size(),
          "Player count should match");
      assertEquals("Alice", loadedConfig.getPlayers().get(0).getName(),
          "First player name should be Alice");
      assertEquals("Bob", loadedConfig.getPlayers().get(1).getName(),
          "Second player name should be Bob");

      // Check board is loaded correctly
      assertTrue(loadedConfig.getBoard() instanceof SnakesAndLaddersBoard,
          "Loaded board should be of type SnakesAndLaddersBoard");
      SnakesAndLaddersBoard loadedBoard = (SnakesAndLaddersBoard) loadedConfig.getBoard();
      // Check board properties are correct
      assertEquals(10, loadedBoard.getRows(), "Loaded board should have 10 rows");
      assertEquals(10, loadedBoard.getColumns(), "Loaded board should have 10 columns");

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
    // Try to load from a non-existent file
    ReadException e = assertThrows(ReadException.class, 
      () -> gameConfig.loadPlayerList("non_existent_file.json"),
      "Should throw an InvalidInputException when file doesn't exist");

    assertEquals("Error reading player list from file: non_existent_file.json", e.getMessage(),
        "Exception message should indicate file not found");
  }

  @Test
  void testCreateInvalidGameConfig() {
    // Test with invalid player list
    List<Player> invalidPlayers = new ArrayList<>();
    assertThrows(ConfigurationException.class, () -> new GameConfig(invalidPlayers, smallBoard, 0),
        "Should throw ConfigurationException for empty player list");

    // Test with null board
    assertThrows(ConfigurationException.class,
        () -> new GameConfig(players, null, 0), "Should throw InvalidInputException for null board");

    // Test with negative current player index
    assertThrows(ConfigurationException.class,
        () -> new GameConfig(players, smallBoard, -1),
        "Should throw InvalidInputException for negative current player index");
  }

  @Test
  void testSaveConfigInvalidFilePath() {
    // Test with invalid file path
    assertThrows(InvalidInputException.class, () -> gameConfig.saveConfig(""),
        "Should throw InvalidInputException for empty file path");
  }

  @Test
  void testLoadConfigInvalidFilePath() {
    // Test with invalid file path
    assertThrows(InvalidInputException.class, () -> gameConfig.loadConfig(""),
        "Should throw InvalidInputException for empty file path");
  }

  @Test
  void testLoadPlayerListInvalidFilePath() {
    // Test with invalid file path
    assertThrows(InvalidInputException.class, () -> gameConfig.loadPlayerList(""),
        "Should throw InvalidInputException for empty file path");
  }

  @Test
  void testSavePlayerListInvalidFilePath() {
    // Test with invalid file path
    assertThrows(InvalidInputException.class, () -> gameConfig.savePlayerList(""),
        "Should throw InvalidInputException for empty file path");
  }

  @Test
  void testGetActionDestinationTileIdInvalid() {
    // Test with invalid tile ID
    TileAction invalidTileId = null;
    assertThrows(InvalidInputException.class,
        () -> gameConfig.getActionDestinationTileId(invalidTileId),
        "Should throw InvalidInputException for invalid tile ID");
  }
}