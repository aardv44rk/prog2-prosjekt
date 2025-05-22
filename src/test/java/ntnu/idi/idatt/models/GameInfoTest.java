package ntnu.idi.idatt.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ntnu.idi.idatt.exceptions.InvalidInputException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

class GameInfoTest {

    private String testName;
    private String testRules;
    private int testPlayerMin;
    private int testPlayerMax;
    private Supplier<List<Board>> mockBoardOptionsSupplier;
    private GameInfo gameInfo;
    private List<Board> mockBoardList;

    @BeforeEach
    @SuppressWarnings("unchecked") // For mock generic types
    void setUp() {
        testName = "Test Game";
        testRules = "Test Rules";
        testPlayerMin = 2;
        testPlayerMax = 4;

        mockBoardOptionsSupplier = mock(Supplier.class);
        
        mockBoardList = new ArrayList<>();
        mockBoardList.add(mock(Board.class));

        // Configure mocks
        when(mockBoardOptionsSupplier.get()).thenReturn(mockBoardList);

        gameInfo = new GameInfo(testName, testRules, testPlayerMin, testPlayerMax,
                                mockBoardOptionsSupplier);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals(testName, gameInfo.getName(), "Name should match constructor argument");
        assertEquals(testRules, gameInfo.getRules(), "Rules should match constructor argument");
        assertEquals(testPlayerMin, gameInfo.getPlayerMin(), "PlayerMin should match constructor argument");
        assertEquals(testPlayerMax, gameInfo.getPlayerMax(), "PlayerMax should match constructor argument");
    }


    @Test
    void testGetBoardOptions() {
        List<Board> boardOptions = gameInfo.getBoardOptions();

        verify(mockBoardOptionsSupplier, times(1)).get();
        assertEquals(mockBoardList, boardOptions, "getBoardOptions should return the list from the supplier");
        assertEquals(1, boardOptions.size(), "Board options list should contain one mock board");
    }

    @Test
    void testIsValidatingGameInfo() {
        assertTrue(gameInfo.isValidGameInfo(testName, testRules, testPlayerMin, testPlayerMax,
                                                    mockBoardOptionsSupplier),
                     "isValidGameInfo should return true for valid parameters");
        assertFalse(gameInfo.isValidGameInfo("", testRules, testPlayerMin, testPlayerMax,
                                                    mockBoardOptionsSupplier),
                     "isValidGameInfo should return false for invalid parameters");
        assertFalse(gameInfo.isValidGameInfo(testName, "", testPlayerMin, testPlayerMax,
                                                    mockBoardOptionsSupplier),
                    "isValidGameInfo should return false for invalid parameters");
        assertFalse(gameInfo.isValidGameInfo(testName, testRules, -1, testPlayerMax,
                                                    mockBoardOptionsSupplier),
                    "isValidGameInfo should return false for invalid parameters");
        assertFalse(gameInfo.isValidGameInfo(testName, testRules, testPlayerMin, -1,
                                                    mockBoardOptionsSupplier),
                    "isValidGameInfo should return false for invalid parameters");
        assertFalse(gameInfo.isValidGameInfo(testName, testRules, testPlayerMin, testPlayerMax,
                                                    null),
                    "isValidGameInfo should return false for invalid parameters");
    }

    @Test
    void testInvalidGameInfoParametersThrowsException() {
        // Test with invalid parameters
        String invalidName = "";
        String invalidRules = "";
        int invalidPlayerMin = -1;
        int invalidPlayerMax = -1;
        Supplier<List<Board>> invalidBoardOptionsSupplier = null;

        InvalidInputException e = assertThrows(InvalidInputException.class, () -> {
            new GameInfo(invalidName, invalidRules, invalidPlayerMin, invalidPlayerMax,
                         invalidBoardOptionsSupplier);
        });

        assertEquals("Invalid game info parameters", e.getMessage(), "Should throw exception for invalid parameters");
    }
}