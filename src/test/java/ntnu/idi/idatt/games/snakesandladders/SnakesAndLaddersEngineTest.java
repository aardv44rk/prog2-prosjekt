package ntnu.idi.idatt.games.snakesandladders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ntnu.idi.idatt.exceptions.InvalidInputException;
import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.Dice;
import ntnu.idi.idatt.models.Piece;
import ntnu.idi.idatt.models.Player;
import ntnu.idi.idatt.models.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Tests for the SnakesAndLaddersEngine class.
 */
@ExtendWith(MockitoExtension.class)
class SnakesAndLaddersEngineTest {

    @Mock
    private Board mockBoard;
    @Mock
    private Dice mockDice;

    private List<Player> players;
    private Player player1;
    private Player player2;
    private SnakesAndLaddersEngine engine;
    private Map<Integer, Tile> mockTilesMap;
    private final int boardSize = 20; 

    @BeforeEach
    void setUp() {
        // Initialize players
        List<Piece> p1Pieces = new ArrayList<>();
        player1 = new Player("Player1", p1Pieces);
        List<Piece> p2Pieces = new ArrayList<>();
        player2 = new Player("Player2", p2Pieces);
        players = new ArrayList<>(List.of(player1, player2));

        // Mock board and tiles
        mockTilesMap = new HashMap<>();
        for (int i = 0; i < boardSize; i++) {
            Tile tile = mock(Tile.class);
            lenient().when(tile.getTileId()).thenReturn(i);
            mockTilesMap.put(i, tile);
            lenient().when(mockBoard.getTile(i)).thenReturn(tile);
        }
        lenient().when(mockBoard.getTiles()).thenReturn(mockTilesMap); // Used by engine.checkWinCondition() via board.getTiles().size()

        engine = new SnakesAndLaddersEngine(players, mockBoard, 0, mockDice);
    }

    @Test
    void testConstructorInitialization() {
        assertNotNull(engine.getBoard(), "Board should be initialized.");
        assertEquals(players, engine.getPlayers(), "Players list should be initialized.");
        assertEquals(player1, engine.getCurrentPlayer(), "Current player should be the first player.");
        assertEquals(mockDice, engine.getDice(), "Dice should be initialized.");
        assertFalse(engine.isGameOver(), "Game should not be over initially.");
    }

    @Test
    void testConstructorWithInvalidParametersThrowsException() {
        InvalidInputException exception = assertThrows(InvalidInputException.class, () -> {
            new SnakesAndLaddersEngine(players, mockBoard, 0, null); // Null dice
        });
        assertEquals("Dice cannot be null", exception.getMessage());

        InvalidInputException exception2 = assertThrows(InvalidInputException.class, () -> {
            new SnakesAndLaddersEngine(null, mockBoard, 0, mockDice); // Null players
        });
        assertEquals("Invalid game engine parameters", exception2.getMessage());
    }

    @Test
    void testInitPieces() {
        engine.initPieces();
        for (Player player : players) {
            assertEquals(1, player.getPieces().size(), "Each player should have 1 piece after init.");
            Piece piece = player.getPieces().getFirst();
            assertNotNull(piece, "Piece should not be null.");
            assertEquals(mockBoard.getTile(0), piece.getCurrentTile(), "Piece should start on tile 0.");
            assertTrue(piece.getMovementStrategy() instanceof LinearMovementStrategy, "Piece should have LinearMovementStrategy.");
        }
    }

    @Test
    void testHandleTurnNoWin() {
        engine.initPieces(); // Initializes pieces 
        Piece p1Piece = player1.getPieces().getFirst();

        // we mock the dice to return 3
        when(mockDice.getValue()).thenReturn(3);

        engine.handleTurn();

        verify(mockDice, times(1)).roll();

        assertEquals(mockBoard.getTile(3), p1Piece.getCurrentTile(), "Piece should have moved to tile 3.");
        verify(mockBoard.getTile(3), times(1)).land(p1Piece, mockBoard); // Verify landing logic

        engine.nextPlayer(); // Move to the next player
        
        assertFalse(engine.isGameOver(), "Game should not be over.");
        assertEquals(player2, engine.getCurrentPlayer(), "Should be next player's turn.");
    }

    @Test
    void testHandleTurnWithWin() {
        engine.initPieces();
        Piece p1Piece = player1.getPieces().getFirst();
        Tile initialTile = mockBoard.getTile(boardSize - 2); // Piece starts near the end
        p1Piece.setCurrentTile(initialTile);
        when(mockDice.getValue()).thenReturn(1); 

        engine.handleTurn();

        verify(mockDice, times(1)).roll();
        assertEquals(mockBoard.getTile(boardSize - 1), p1Piece.getCurrentTile(), "Piece should be on the winning tile.");
        verify(mockBoard.getTile(boardSize - 1), times(1)).land(p1Piece, mockBoard);

        assertTrue(engine.isGameOver(), "Game should be over.");
        assertEquals(player1, engine.getCurrentPlayer(), "Winner should be the current player (player1).");
    }


    @Test
    void testCheckWinConditionPlayerWins() {
        engine.initPieces();
        Piece piece = player1.getPieces().getFirst();
        piece.setCurrentTile(mockBoard.getTile(boardSize - 1));

        Player winner = engine.checkWinCondition();
        assertEquals(player1, winner, "Player1 should be the winner.");
    }

    @Test
    void testCheckWinConditionNoWinner() {
        engine.initPieces();
        Piece piece = player1.getPieces().getFirst();
        // Piece on a non-winning tile
        piece.setCurrentTile(mockBoard.getTile(0));

        Player winner = engine.checkWinCondition();
        assertNull(winner, "There should be no winner.");
    }
}