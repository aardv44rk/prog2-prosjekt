package ntnu.idi.idatt.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TileTest {

    private Tile tile;
    private final int TILE_ID = 42;
    private TileAction mockTileAction;
    private Piece mockPiece;
    private Board mockBoard;

    @BeforeEach
    void setUp() {
        tile = new Tile(TILE_ID);
        mockTileAction = mock(TileAction.class);
        mockPiece = mock(Piece.class);
        mockBoard = mock(Board.class);
    }

    @Test
    void testConstructorInitialization() {
        assertEquals(TILE_ID, tile.getTileId(), "Tile ID should be initialized by constructor.");
        assertNull(tile.getTileAction(), "TileAction should be null initially.");
    }

    @Test
    void testGetTileId() {
        assertEquals(TILE_ID, tile.getTileId());
    }

    @Test
    void testGetTileAction() {
        assertNull(tile.getTileAction(), "Initially, action should be null.");
        tile.setTileAction(mockTileAction);
        assertEquals(mockTileAction, tile.getTileAction(), "Should return the set action.");
    }

    @Test
    void testSetTileAction() {
        tile.setTileAction(mockTileAction);
        assertEquals(mockTileAction, tile.getTileAction(), "setTileAction should update the action.");
    }

    @Test
    void testLand_whenActionIsSet() {
        tile.setTileAction(mockTileAction);
        tile.land(mockPiece, mockBoard);
        // Verify that the action's perform method was called once
        verify(mockTileAction, times(1)).perform(mockPiece, mockBoard);
    }

    @Test
    void testLand_whenActionIsNull() {
        tile.setTileAction(null); // Ensure action is null
        
        // Call land and assert that no exception is thrown (and no method on a null action is called)
        assertDoesNotThrow(() -> tile.land(mockPiece, mockBoard));
        
        // Verify that the mockTileAction (which is not set on the tile) was NOT called
        verify(mockTileAction, never()).perform(any(Piece.class), any(Board.class));
    }
    
    @Test
    void testToString() {
        assertEquals(String.valueOf(TILE_ID), tile.toString(), "toString should return the tileId as a String.");
    }
}