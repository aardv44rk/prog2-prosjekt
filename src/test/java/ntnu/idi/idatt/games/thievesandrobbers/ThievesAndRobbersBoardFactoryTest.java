package ntnu.idi.idatt.games.thievesandrobbers;

import ntnu.idi.idatt.models.Board;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ThievesAndRobbersBoardFactoryTest {

    @Test
    void testCreateSmallBoard() {
        Board board = ThievesAndRobbersBoardFactory.createSmallBoard();
        assertNotNull(board, "Small board should not be null.");
        assertTrue(board instanceof ThievesAndRobbersBoard, "Board should be an instance of ThievesAndRobbersBoard.");
        ThievesAndRobbersBoard tarBoard = (ThievesAndRobbersBoard) board;
        assertEquals(8, tarBoard.getWidth(), "Small board should have width 8.");
        assertEquals(6, tarBoard.getHeight(), "Small board should have height 6.");
    }

    @Test
    void testCreateStandardBoard() {
        Board board = ThievesAndRobbersBoardFactory.createStandardBoard();
        assertNotNull(board, "Standard board should not be null.");
        assertTrue(board instanceof ThievesAndRobbersBoard, "Board should be an instance of ThievesAndRobbersBoard.");
        ThievesAndRobbersBoard tarBoard = (ThievesAndRobbersBoard) board;
        assertEquals(8, tarBoard.getWidth(), "Standard board should have width 8.");
        assertEquals(8, tarBoard.getHeight(), "Standard board should have height 8.");
    }

    @Test
    void testCreateBigBoard() {
        Board board = ThievesAndRobbersBoardFactory.createBigBoard();
        assertNotNull(board, "Big board should not be null.");
        assertTrue(board instanceof ThievesAndRobbersBoard, "Board should be an instance of ThievesAndRobbersBoard.");
        ThievesAndRobbersBoard tarBoard = (ThievesAndRobbersBoard) board;
        assertEquals(10, tarBoard.getWidth(), "Big board should have width 10.");
        assertEquals(10, tarBoard.getHeight(), "Big board should have height 10.");
    }

    @Test
    void testGetBoards() {
        List<Board> boards = ThievesAndRobbersBoardFactory.getBoards();
        assertNotNull(boards, "List of boards should not be null.");
        assertEquals(3, boards.size(), "Should return 3 boards.");
        for (Board b : boards) {
            assertNotNull(b, "Board in list should not be null.");
            assertTrue(b instanceof ThievesAndRobbersBoard, "Each board should be an instance of ThievesAndRobbersBoard.");
        }
    }
}