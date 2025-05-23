package ntnu.idi.idatt.games.thievesandrobbers;

import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.Dice;
import ntnu.idi.idatt.models.Piece;
import ntnu.idi.idatt.models.Player;
import ntnu.idi.idatt.models.Tile;
import ntnu.idi.idatt.models.TileAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ThievesAndRobbersEngineTest {

    @Mock
    private Board mockBoard;
    @Mock
    private Dice mockDice;
    @Mock
    private Tile mockTile0;
    @Mock
    private Tile mockTargetTile;
    @Mock
    private TileAction mockTile0Action; // For CircularMovementStrategy if it wraps

    private Player player1;
    private Player player2;
    private List<Player> players;
    private ThievesAndRobbersEngine engine;
    private final int boardSizeForStrategy = 10; // For CircularMovementStrategy

    @BeforeEach
    void setUp() {
        player1 = new Player("Player One", new ArrayList<>());
        player2 = new Player("Player Two", new ArrayList<>());
        players = new ArrayList<>(List.of(player1, player2));

        when(mockBoard.getTile(0)).thenReturn(mockTile0);

        Map<Integer, Tile> tilesMapForSize = new HashMap<>();
        for (int i = 0; i < boardSizeForStrategy; i++) {
            tilesMapForSize.put(i, mock(Tile.class)); // Dummy tiles for size calculation
        }
        lenient().when(mockBoard.getTiles()).thenReturn(tilesMapForSize); // Lenient as not all tests use this
        lenient().when(mockTile0.getTileAction()).thenReturn(mockTile0Action);


        engine = new ThievesAndRobbersEngine(players, mockBoard, 0, mockDice);
    }

    @Test
    void testInitPieces() {
        engine.initPieces();

        for (Player p : players) {
            assertEquals(1, p.getPieces().size(), "Each player should have one piece.");
            Piece piece = p.getPieces().getFirst();
            assertTrue(piece instanceof ThievesAndRobbersPiece, "Piece should be ThievesAndRobbersPiece.");
            assertEquals(mockTile0, piece.getCurrentTile(), "Piece should start on tile 0.");
            assertTrue(piece.getMovementStrategy() instanceof CircularMovementStrategy, "Piece should have CircularMovementStrategy.");
            assertEquals(0, ((ThievesAndRobbersPiece) piece).getMoney(), "Piece should start with 0 money.");
        }
    }

    @Test
    void testCheckWinNoWinner() {
        engine.initPieces(); // Pieces will have 0 money
        assertNull(engine.checkWinCondition(), "Should be no winner if no one has enough money.");
        assertFalse(engine.isGameOver(), "Game should not be over if only checkWinCondition is called.");
    }

    @Test
    void testCheckWinPlayer1Wins() {
        engine.initPieces();
        ThievesAndRobbersPiece p1Piece = (ThievesAndRobbersPiece) player1.getPieces().getFirst();
        p1Piece.addMoney(1000); // Player 1 gets enough money

        assertEquals(player1, engine.checkWinCondition(), "Player 1 should be the winner.");
        assertFalse(engine.isGameOver(), "checkWinCondition itself should not set gameOver.");
    }

    @Test
    void testCheckWinPlayer2WinsOverAmount() {
        engine.initPieces();
        ThievesAndRobbersPiece p2Piece = (ThievesAndRobbersPiece) player2.getPieces().getFirst();
        p2Piece.addMoney(1500); // Player 2 gets more than enough money

        assertEquals(player2, engine.checkWinCondition(), "Player 2 should be the winner.");
    }

    @Test
    void testHandleTurnPlayer1WinsGameEnds() {
        engine.initPieces();
        ThievesAndRobbersPiece p1Piece = (ThievesAndRobbersPiece) player1.getPieces().getFirst();

        when(mockDice.getValue()).thenReturn(1);

        // Setup the target tile and its action to grant winning money
        MoneyAction winningMoneyAction = new MoneyAction(1000); // Real action
        when(mockBoard.getTile(1)).thenReturn(mockTargetTile); // Target tile for player 1's piece
        doAnswer(invocation -> {
            Piece pieceBeingLandedOn = invocation.getArgument(0);
            Board boardLandedOn = invocation.getArgument(1);
            winningMoneyAction.perform(pieceBeingLandedOn, boardLandedOn); // This will update the piece's money
            return null;
        }).when(mockTargetTile).land(p1Piece, mockBoard);


        engine.handleTurn(); // Player 1 takes a turn, moves, lands, gets money, wins

        verify(mockDice).roll();
        assertTrue(engine.isGameOver(), "Game should be over as Player 1 won.");
        assertEquals(player1, engine.getCurrentPlayer(), "Winner (Player 1) should remain the current player at game end.");
    }
}