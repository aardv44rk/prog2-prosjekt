package ntnu.idi.idatt.games.snakesandladders;

import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.Piece;
import ntnu.idi.idatt.models.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.any;

/**
 * Tests for the LadderAction class.
 */
@ExtendWith(MockitoExtension.class)
class LadderActionTest {

    @Mock
    private Piece mockPiece;
    @Mock
    private Board mockBoard;
    @Mock
    private Tile mockDestinationTile;
    @Mock
    private Tile mockCurrentTile;

    private LadderAction ladderAction;
    private final int destinationTileId = 15;
    private final int currentTileId = 5;

    @BeforeEach
    void setUp() {
        ladderAction = new LadderAction(destinationTileId);
    }

    @Test
    void testConstructorAndGetDestinationTileId() {
        assertEquals(destinationTileId, ladderAction.getDestinationTileId(), "getDestinationTileId should return the initialized ID.");
    }

    @Test
    void testPerformMovesPieceToDestination() {
        when(mockPiece.getCurrentTile()).thenReturn(mockCurrentTile);
        when(mockCurrentTile.getTileId()).thenReturn(currentTileId - 1); 
        when(mockBoard.getTile(destinationTileId - 1)).thenReturn(mockDestinationTile);

        ladderAction.perform(mockPiece, mockBoard);

        verify(mockBoard).getTile(destinationTileId - 1);
        int expectedSteps = destinationTileId - (currentTileId -1) ;
        verify(mockPiece).move(expectedSteps, mockBoard);
    }

    @Test
    void testPerformWhenDestinationTileIsNull() {
        when(mockBoard.getTile(destinationTileId - 1)).thenReturn(null);

        ladderAction.perform(mockPiece, mockBoard);

        verify(mockBoard).getTile(destinationTileId - 1);
        verify(mockPiece, never()).move(anyInt(), any(Board.class));
    }
}