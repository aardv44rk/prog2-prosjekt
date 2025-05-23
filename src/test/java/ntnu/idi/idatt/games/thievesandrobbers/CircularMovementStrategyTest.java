package ntnu.idi.idatt.games.thievesandrobbers;

import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.Piece;
import ntnu.idi.idatt.models.Tile;
import ntnu.idi.idatt.models.TileAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;

/**
 * Test class for CircularMovementStrategy.
 */
@ExtendWith(MockitoExtension.class)
class CircularMovementStrategyTest {

  @Mock
  private Piece mockPiece;
  @Mock
  private Board mockBoard;
  @Mock
  private Tile mockStartTile;
  @Mock
  private Tile mockTargetTile;
  @Mock
  private Tile mockTileZero;
  @Mock
  private TileAction mockTileZeroAction;
  @Mock
  private TileAction mockTargetTileAction; // Action for the target tile

  private CircularMovementStrategy strategy;
  private final int boardSize = 10;

  @BeforeEach
  void setUp() {
    strategy = new CircularMovementStrategy();
    when(mockPiece.getCurrentTile()).thenReturn(mockStartTile);

    // Setup a map of tiles for the board to use for .size()
    Map<Integer, Tile> tilesMap = new HashMap<>();
    for (int i = 0; i < boardSize; i++) {
      tilesMap.put(i, mock(Tile.class)); // Just need placeholders for size
    }
    when(mockBoard.getTiles()).thenReturn(tilesMap);

    // Specific setup for tile 0 and its action
    lenient().when(mockBoard.getTile(0)).thenReturn(mockTileZero);
    lenient().when(mockTileZero.getTileAction()).thenReturn(mockTileZeroAction);
  }

  @Test
  void testMoveNoWrap() {
    when(mockStartTile.getTileId()).thenReturn(1);
    int steps = 3;
    int targetPos = (1 + steps) % boardSize; // 4
    when(mockBoard.getTile(targetPos)).thenReturn(mockTargetTile);

    strategy.move(mockPiece, steps, mockBoard);

    verify(mockPiece).setCurrentTile(mockTargetTile);
    verify(mockTargetTile).land(mockPiece, mockBoard);
    verify(mockTileZeroAction, never()).perform(mockPiece, mockBoard);
  }

  @Test
  void testMoveWithWrapAndTileZeroAction() {
    when(mockStartTile.getTileId()).thenReturn(8); // e.g., on a 10-tile board
    int steps = 5; // 8 + 5 = 13. 13 % 10 = 3. targetPos (3) < currentPos (8)
    int targetPos = (8 + steps) % boardSize; // 3
    when(mockBoard.getTile(targetPos)).thenReturn(mockTargetTile);

    strategy.move(mockPiece, steps, mockBoard);

    verify(mockPiece).setCurrentTile(mockTargetTile);
    verify(mockTileZeroAction).perform(mockPiece, mockBoard); // Tile 0 action should be performed
    verify(mockTargetTile).land(mockPiece, mockBoard);
  }

  @Test
  void testMoveZeroSteps() {
    when(mockStartTile.getTileId()).thenReturn(2);
    int steps = 0;
    int targetPos = (2 + steps) % boardSize; // 2
    when(mockBoard.getTile(targetPos)).thenReturn(mockStartTile); // Lands on itself

    strategy.move(mockPiece, steps, mockBoard);

    verify(mockPiece).setCurrentTile(mockStartTile);
    verify(mockStartTile).land(mockPiece, mockBoard);
    verify(mockTileZeroAction, never()).perform(mockPiece, mockBoard);
  }

  @Test
  void testMoveFullCycleNoTileZeroAction() {
    when(mockStartTile.getTileId()).thenReturn(2);
    int steps = boardSize;
    int targetPos = (2 + steps) % boardSize; // 2
    when(mockBoard.getTile(targetPos)).thenReturn(mockStartTile); // Lands on itself

    strategy.move(mockPiece, steps, mockBoard);

    verify(mockPiece).setCurrentTile(mockStartTile);
    verify(mockStartTile).land(mockPiece, mockBoard);
    // The condition `targetPos < currentPos` (2 < 2) is false, so tile 0 action
    // shouldn't trigger
    verify(mockTileZeroAction, never()).perform(mockPiece, mockBoard);
  }
}