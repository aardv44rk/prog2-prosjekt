package ntnu.idi.idatt.games.snakesandladders;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Tests for the SnakesAndLaddersLadder class.
 */
class SnakesAndLaddersLadderTest {

    @Test
    void testConstructorAndGetters() {
        int startId = 5;
        int endId = 15;
        SnakesAndLaddersLadder ladder = new SnakesAndLaddersLadder(startId, endId);

        assertEquals(startId, ladder.startTileId(), "startTileId should match constructor argument.");
        assertEquals(endId, ladder.endTileId(), "endTileId should match constructor argument.");
    }

    @Test
    void testIsAscendingWhenEndIsGreaterThanStart() {
        SnakesAndLaddersLadder ladder = new SnakesAndLaddersLadder(5, 15); // Ascending
        assertTrue(ladder.isAscending(), "isAscending should be true when endTileId > startTileId.");
    }

    @Test
    void testIsAscendingWhenStartIsGreaterThanEnd() {
        SnakesAndLaddersLadder snake = new SnakesAndLaddersLadder(15, 5); // Descending (snake)
        assertFalse(snake.isAscending(), "isAscending should be false when startTileId > endTileId.");
    }

    @Test
    void testIsAscendingWhenStartEqualsEnd() {
        SnakesAndLaddersLadder same = new SnakesAndLaddersLadder(10, 10); // No change
        assertFalse(same.isAscending(), "isAscending should be false when startTileId == endTileId (conventionally not ascending).");
    }

    @Test
    void testRecordToString() {
        SnakesAndLaddersLadder ladder = new SnakesAndLaddersLadder(1, 10);
        String expectedToString = "SnakesAndLaddersLadder[startTileId=1, endTileId=10]";
        assertEquals(expectedToString, ladder.toString(), "toString output should match the record's default format.");
    }
}