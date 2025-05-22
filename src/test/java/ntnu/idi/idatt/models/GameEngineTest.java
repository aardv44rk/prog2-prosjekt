package ntnu.idi.idatt.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;
import ntnu.idi.idatt.exceptions.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the GameEngine class.
 */
class TestGameEngine extends GameEngine {
    public TestGameEngine(List<Player> players, Board board, int currentPlayerIndex) {
        super(players, board, currentPlayerIndex);
    }

    @Override
    public Player checkWinCondition() {
        // No-op for testing base class
        return null;
    }
}

class GameEngineTest {

    private Board mockBoard;
    private List<Player> players;
    private GameEngine gameEngine;

    @BeforeEach
    void setUp() {
        mockBoard = mock(Board.class); // Using Mockito to mock the Board interface
        players = new ArrayList<>();
        players.add(new Player("Player 1", new ArrayList<>()));
        players.add(new Player("Player 2", new ArrayList<>()));
        players.add(new Player("Player 3", new ArrayList<>()));
        
        // Start with Player 1 (index 0)
        gameEngine = new TestGameEngine(players, mockBoard, 0);
    }

    @Test
    void testConstructorInitialization() {
        assertNotNull(gameEngine.getBoard(), "Board should be initialized");
        assertEquals(mockBoard, gameEngine.getBoard(), "Board should be the one passed in constructor");
        assertEquals(players, gameEngine.getPlayers(), "Players list should be the one passed in constructor");
        assertEquals(0, gameEngine.currentPlayerIndex, "Current player index should be initialized");
        assertFalse(gameEngine.isGameOver(), "Game should not be over initially");
    }

    @Test
    void testGetCurrentPlayer() {
        assertEquals(players.get(0), gameEngine.getCurrentPlayer(), "Should return the player at currentPlayerIndex");
        gameEngine.currentPlayerIndex = 1;
        assertEquals(players.get(1), gameEngine.getCurrentPlayer(), "Should return updated current player");
    }

    @Test
    void testNextPlayer() {
        assertEquals(players.get(0), gameEngine.getCurrentPlayer(), "Initial player should be Player 1");
        
        gameEngine.nextPlayer();
        assertEquals(players.get(1), gameEngine.getCurrentPlayer(), "After nextPlayer, should be Player 2");
        
        gameEngine.nextPlayer();
        assertEquals(players.get(2), gameEngine.getCurrentPlayer(), "After nextPlayer, should be Player 3");
        
        gameEngine.nextPlayer(); // Wrap around
        assertEquals(players.get(0), gameEngine.getCurrentPlayer(), "After nextPlayer, should wrap around to Player 1");
    }
    
    @Test
    void testGetLastPlayer() {
        // Current player is Player 1 (index 0)
        assertEquals(players.get(2), gameEngine.getLastPlayer(), "Last player should be Player 3 when current is Player 1");

        gameEngine.nextPlayer(); // Current player is Player 2 (index 1)
        assertEquals(players.get(0), gameEngine.getLastPlayer(), "Last player should be Player 1 when current is Player 2");

        gameEngine.nextPlayer(); // Current player is Player 3 (index 2)
        assertEquals(players.get(1), gameEngine.getLastPlayer(), "Last player should be Player 2 when current is Player 3");
    }


    @Test
    void testEndGameAndIsGameOver() {
        assertFalse(gameEngine.isGameOver(), "Game should not be over initially");
        gameEngine.endGame();
        assertTrue(gameEngine.isGameOver(), "Game should be over after endGame() is called");
    }

    @Test
    void testGetBoard() {
        assertEquals(mockBoard, gameEngine.getBoard(), "getBoard should return the initialized board");
    }

    @Test
    void testGetPlayers() {
        assertEquals(players, gameEngine.getPlayers(), "getPlayers should return the initialized list of players");
        assertEquals(3, gameEngine.getPlayers().size(), "Should be 3 players in the list");
    }

    @Test
    void testValidateConstructorParameters() {
        // Valid parameters
        assertTrue(gameEngine.isValidGameEngine(players, mockBoard, 0), "Should validate successfully with valid parameters");

        // Invalid parameters
        assertFalse(gameEngine.isValidGameEngine(null, mockBoard, 0), "Should not validate with null players");
        assertFalse(gameEngine.isValidGameEngine(new ArrayList<>(), mockBoard, 0), "Should not validate with empty players list");
        assertFalse(gameEngine.isValidGameEngine(players, null, 0), "Should not validate with null board");
    @Test
    void testInvalidGameEngineParametersThrowsException() {
        // Test with invalid parameters
        List<Player> invalidPlayers = new ArrayList<>();
        Board invalidBoard = null;
        int invalidCurrentPlayerIndex = -1;

        // Check if the constructor throws an exception for invalid parameters
        InvalidInputException e = assertThrows(InvalidInputException.class, () -> {
            new TestGameEngine(invalidPlayers, invalidBoard, invalidCurrentPlayerIndex);
        });

        assertEquals("Invalid game engine parameters", e.getMessage(), "Should throw exception for invalid parameters");
    }
}
