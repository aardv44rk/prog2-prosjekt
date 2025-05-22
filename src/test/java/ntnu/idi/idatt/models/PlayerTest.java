package ntnu.idi.idatt.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ntnu.idi.idatt.exceptions.InvalidInputException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

class PlayerTest {

    private String validPlayerName;
    private List<Piece> mockPieces;
    private Piece mockPiece1;
    private Piece mockPiece2;

    @BeforeEach
    void setUp() {
        validPlayerName = "TestPlayer";
        mockPiece1 = mock(Piece.class);
        mockPiece2 = mock(Piece.class);
        mockPieces = new ArrayList<>();
        mockPieces.add(mockPiece1);
        mockPieces.add(mockPiece2);
    }

    @Test
    void testConstructorInitialization() {
        Player player = new Player(validPlayerName, mockPieces);
        assertNotNull(player, "Player object should be created.");
        assertEquals(validPlayerName, player.getName(), "Player name should be initialized correctly.");
        assertEquals(mockPieces, player.getPieces(), "Player pieces should be initialized correctly.");
        assertEquals(2, player.getPieces().size(), "Player should have the correct number of pieces.");
    }

    @Test
    void testConstructorWithNullName() {
        InvalidInputException e = assertThrows(InvalidInputException.class, () -> {
            new Player(null, mockPieces);
        });
        assertEquals("Invalid player parameters", e.getMessage(), "Exception message for null name should match.");
    }

    @Test
    void testConstructorWithBlankName() {
        InvalidInputException e = assertThrows(InvalidInputException.class, () -> {
            new Player("   ", mockPieces);
        });
        assertEquals("Invalid player parameters", e.getMessage(), "Exception message for blank name should match.");
    }

    @Test
    void testConstructorWithEmptyName() {
        InvalidInputException e = assertThrows(InvalidInputException.class, () -> {
            new Player("", mockPieces);
        });
        assertEquals("Invalid player parameters", e.getMessage(), "Exception message for empty name should match.");
    }

    @Test
    void testConstructorWithNullPiecesList() {
        InvalidInputException e = assertThrows(InvalidInputException.class, () -> {
            new Player(validPlayerName, null);
        });
       assertEquals("Invalid player parameters", e.getMessage(), "Exception message for null pieces list should match.");
    }

    @Test
    void testGetName() {
        Player player = new Player(validPlayerName, mockPieces);
        assertEquals(validPlayerName, player.getName(), "getName should return the correct player name.");
    }

    @Test
    void testGetPieces() {
        Player player = new Player(validPlayerName, mockPieces);
        List<Piece> retrievedPieces = player.getPieces();
        assertEquals(mockPieces, retrievedPieces, "getPieces should return the correct list of pieces.");
        assertEquals(2, retrievedPieces.size(), "The list of pieces should contain the correct number of pieces.");
        assertTrue(retrievedPieces.contains(mockPiece1), "The list should contain mockPiece1.");
        assertTrue(retrievedPieces.contains(mockPiece2), "The list should contain mockPiece2.");
    }

    @Test
    void testIsValidPlayer() {
        Player player = new Player(validPlayerName, mockPieces); // Need an instance to call non-static method
        assertTrue(player.isValidPlayer("ValidName", mockPieces), "isValidPlayer should return true for a valid name.");
        assertFalse(player.isValidPlayer(null, mockPieces), "isValidPlayer should return false for a null name.");
        assertFalse(player.isValidPlayer("", mockPieces), "isValidPlayer should return false for an empty name.");
        assertFalse(player.isValidPlayer("   ", mockPieces), "isValidPlayer should return false for a blank name (spaces only).");
    }
}
