package ntnu.idi.idatt.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class PieceTest {

    private Tile mockTile;
    private Player mockPlayer;
    private MovementStrategy mockMovementStrategy;
    private Board mockBoard;
    private Piece piece;

    @BeforeEach
    void setUp() {
        mockTile = mock(Tile.class);
        // Assuming Player constructor takes (String name, List<Piece> pieces)
        // If Player is simple, you might not need to mock it, but for consistency:
        mockPlayer = mock(Player.class); 
        mockMovementStrategy = mock(MovementStrategy.class);
        mockBoard = mock(Board.class);

        piece = new Piece(mockTile, mockPlayer, mockMovementStrategy);
    }

    @Test
    void testConstructorInitialization() {
        assertEquals(mockTile, piece.getCurrentTile(), "Current tile should be initialized by constructor.");
        assertEquals(mockPlayer, piece.getOwner(), "Owner should be initialized by constructor.");
        assertEquals(mockMovementStrategy, piece.getMovementStrategy(), "Movement strategy should be initialized by constructor.");
    }

    @Test
    void testGetCurrentTile() {
        assertEquals(mockTile, piece.getCurrentTile());
    }

    @Test
    void testGetOwner() {
        assertEquals(mockPlayer, piece.getOwner());
    }

    @Test
    void testGetMovementStrategy() {
        assertEquals(mockMovementStrategy, piece.getMovementStrategy());
    }

    @Test
    void testSetCurrentTile() {
        Tile newMockTile = mock(Tile.class);
        piece.setCurrentTile(newMockTile);
        assertEquals(newMockTile, piece.getCurrentTile(), "setCurrentTile should update the current tile.");
    }

    @Test
    void testSetMovementStrategy() {
        MovementStrategy newMockStrategy = mock(MovementStrategy.class);
        piece.setMovementStrategy(newMockStrategy);
        assertEquals(newMockStrategy, piece.getMovementStrategy(), "setMovementStrategy should update the strategy.");
    }

    @Test
    void testMove_whenStrategyIsSet() {
        int steps = 5;
        piece.move(steps, mockBoard);
        // Verify that the strategy's move method was called once with the correct parameters
        verify(mockMovementStrategy, times(1)).move(piece, steps, mockBoard);
    }

    @Test
    void testMove_whenStrategyIsNull() {
        piece.setMovementStrategy(null); // Set strategy to null
        int steps = 5;
        
        // Assert that an IllegalStateException is thrown
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            piece.move(steps, mockBoard);
        });
        
        // Optionally, assert the exception message
        assertEquals("MovementStrategy not set for piece.", exception.getMessage());
    }
}