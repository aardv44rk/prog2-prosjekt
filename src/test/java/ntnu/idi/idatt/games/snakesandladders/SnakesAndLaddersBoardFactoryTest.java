package ntnu.idi.idatt.games.snakesandladders;

import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.Tile;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for the SnakesAndLaddersBoardFactory class.
 */
class SnakesAndLaddersBoardFactoryTest {

    @Test
    void testCreateSmallBoard() {
        Board board = SnakesAndLaddersBoardFactory.createSmallBoard();
        assertNotNull(board, "Small board should not be null.");
        assertTrue(board instanceof SnakesAndLaddersBoard, "Board should be an instance of SnakesAndLaddersBoard.");
        SnakesAndLaddersBoard snlBoard = (SnakesAndLaddersBoard) board;

        assertEquals(7, snlBoard.getRows(), "Small board should have 7 rows.");
        assertEquals(8, snlBoard.getColumns(), "Small board should have 8 columns.");
        assertEquals(56, snlBoard.getTiles().size(), "Small board should have 56 tiles.");

        Tile startTileForLadder1 = snlBoard.getTile(1);
        assertNotNull(startTileForLadder1.getTileAction(), "Tile 1 should have a LadderAction.");
        assertTrue(startTileForLadder1.getTileAction() instanceof LadderAction, "Action should be LadderAction.");
        LadderAction action1 = (LadderAction) startTileForLadder1.getTileAction();
        assertEquals(13, action1.getDestinationTileId(), "LadderAction destination should be 13 (1-based).");

        Tile startTileForSnake1 = snlBoard.getTile(20);
        assertNotNull(startTileForSnake1.getTileAction(), "Tile 20 should have a LadderAction (snake).");
        assertTrue(startTileForSnake1.getTileAction() instanceof LadderAction, "Action should be LadderAction.");
        LadderAction actionSnake1 = (LadderAction) startTileForSnake1.getTileAction();
        assertEquals(9, actionSnake1.getDestinationTileId(), "SnakeAction destination should be 9 (1-based).");
    }

    @Test
    void testCreateStandardBoard() {
        Board board = SnakesAndLaddersBoardFactory.createStandardBoard();
        assertNotNull(board, "Standard board should not be null.");
        assertTrue(board instanceof SnakesAndLaddersBoard, "Board should be an instance of SnakesAndLaddersBoard.");
        SnakesAndLaddersBoard snlBoard = (SnakesAndLaddersBoard) board;

        assertEquals(9, snlBoard.getRows(), "Standard board should have 9 rows.");
        assertEquals(10, snlBoard.getColumns(), "Standard board should have 10 columns.");
        assertEquals(90, snlBoard.getTiles().size(), "Standard board should have 90 tiles.");

        Tile startTileForLadder = snlBoard.getTile(5);
        assertNotNull(startTileForLadder.getTileAction(), "Tile 5 should have a LadderAction.");
        LadderAction action = (LadderAction) startTileForLadder.getTileAction();
        assertEquals(15, action.getDestinationTileId(), "LadderAction destination should be 15 (1-based).");
    }

    @Test
    void testCreateBigBoard() {
        Board board = SnakesAndLaddersBoardFactory.createBigBoard();
        assertNotNull(board, "Big board should not be null.");
        assertTrue(board instanceof SnakesAndLaddersBoard, "Board should be an instance of SnakesAndLaddersBoard.");
        SnakesAndLaddersBoard snlBoard = (SnakesAndLaddersBoard) board;

        assertEquals(10, snlBoard.getRows(), "Big board should have 10 rows.");
        assertEquals(10, snlBoard.getColumns(), "Big board should have 10 columns.");
        assertEquals(100, snlBoard.getTiles().size(), "Big board should have 100 tiles.");

        Tile startTileForLadder = snlBoard.getTile(4);
        assertNotNull(startTileForLadder.getTileAction(), "Tile 4 should have a LadderAction.");
        LadderAction action = (LadderAction) startTileForLadder.getTileAction();
        assertEquals(22, action.getDestinationTileId(), "LadderAction destination should be 22 (1-based).");
    }

    @Test
    void testGetBoards() {
        List<Board> boards = SnakesAndLaddersBoardFactory.getBoards();
        assertNotNull(boards, "List of boards should not be null.");
        assertEquals(3, boards.size(), "Should return 3 boards.");
        for (Board b : boards) {
            assertNotNull(b, "Board in list should not be null.");
            assertTrue(b instanceof SnakesAndLaddersBoard, "Each board should be an instance of SnakesAndLaddersBoard.");
        }
    }
}