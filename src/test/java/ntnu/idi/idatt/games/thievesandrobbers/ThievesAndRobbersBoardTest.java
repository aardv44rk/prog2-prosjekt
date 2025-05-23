package ntnu.idi.idatt.games.thievesandrobbers;

import ntnu.idi.idatt.models.Tile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ThievesAndRobbersBoardTest {

    private static final int MIN_MONEY_ON_RANDOM_TILE = -20;
    private static final int MAX_MONEY_ON_RANDOM_TILE = 39; // Random.nextInt is exclusive for upper bound

    private void assertBoardProperties(ThievesAndRobbersBoard board, int width, int height) {
        assertEquals(width, board.getWidth(), "Board width should match.");
        assertEquals(height, board.getHeight(), "Board height should match.");

        int expectedNumTiles = width * 2 + (height - 2) * 2;
        assertNotNull(board.getTiles(), "Tiles map should not be null.");
        assertEquals(expectedNumTiles, board.getTiles().size(), "Correct number of tiles.");

        Tile tileZero = board.getTile(0);
        assertNotNull(tileZero, "Tile 0 must exist.");
        assertEquals(0, tileZero.getTileId());
        assertTrue(tileZero.getTileAction() instanceof MoneyAction, "Tile 0 action type.");
        assertEquals(100, ((MoneyAction) tileZero.getTileAction()).getMoney(), "Tile 0 money.");

        for (int i = 1; i < expectedNumTiles; i++) {
            Tile tile = board.getTile(i);
            assertNotNull(tile, "Tile " + i + " must exist.");
            assertEquals(i, tile.getTileId());
            assertTrue(tile.getTileAction() instanceof MoneyAction, "Tile " + i + " action type.");
            int money = ((MoneyAction) tile.getTileAction()).getMoney();
            assertTrue(money >= MIN_MONEY_ON_RANDOM_TILE && money <= MAX_MONEY_ON_RANDOM_TILE,
                    "Money on tile " + i + " out of range. Was: " + money);
        }
    }

    private void assertMoneyListProperties(ThievesAndRobbersBoard board, int width, int height) {
        int expectedNumTiles = width * 2 + (height - 2) * 2;
        int[] moneyList = board.getMoneyList();

        assertNotNull(moneyList, "Money list should not be null.");
        assertEquals(expectedNumTiles, moneyList.length, "Money list length.");

        boolean foundTileZeroMoney = false;
        int randomMoneyValuesCount = 0;
        for (int money : moneyList) {
            if (money == 100) {
                foundTileZeroMoney = true;
            } else {
                assertTrue(money >= MIN_MONEY_ON_RANDOM_TILE && money <= MAX_MONEY_ON_RANDOM_TILE,
                        "Money value " + money + " from list out of range.");
                randomMoneyValuesCount++;
            }
        }
        assertTrue(foundTileZeroMoney, "Tile 0 money (100) should be in the list.");
        assertEquals(expectedNumTiles - 1, randomMoneyValuesCount, "Correct count of random money values.");
    }

    @Test
    void testBoardInitSquare() {
        int width = 4;
        int height = 4;
        ThievesAndRobbersBoard board = new ThievesAndRobbersBoard(width, height);
        assertBoardProperties(board, width, height);
    }

    @Test
    void testBoardInitRectangular() {
        int width = 5;
        int height = 3;
        ThievesAndRobbersBoard board = new ThievesAndRobbersBoard(width, height);
        assertBoardProperties(board, width, height);
    }

    @Test
    void testBoardInitMinDimensions() {
        // Test with minimal dimensions where the formula (height - 2) * 2 becomes 0
        int width = 2;
        int height = 2;
        ThievesAndRobbersBoard board = new ThievesAndRobbersBoard(width, height);
        assertBoardProperties(board, width, height); // The helper handles the tile count logic
    }

    @Test
    void testGetMoneyListSquareBoard() {
        int width = 4;
        int height = 4;
        ThievesAndRobbersBoard board = new ThievesAndRobbersBoard(width, height);
        assertMoneyListProperties(board, width, height);
    }

    @Test
    void testGetMoneyListRectangularBoard() {
        int width = 5;
        int height = 3;
        ThievesAndRobbersBoard board = new ThievesAndRobbersBoard(width, height);
        assertMoneyListProperties(board, width, height);
    }

    @Test
    void testGetMoneyListMinDimensionsBoard() {
        int width = 2;
        int height = 2;
        ThievesAndRobbersBoard board = new ThievesAndRobbersBoard(width, height);
        assertMoneyListProperties(board, width, height);
    }
}