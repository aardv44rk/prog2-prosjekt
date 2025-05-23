package ntnu.idi.idatt.games.thievesandrobbers;

import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.Piece;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;

/**
 * Test class for MoneyAction.
 */
@ExtendWith(MockitoExtension.class)
class MoneyActionTest {

  @Mock
  private Board mockBoard; // Board is a parameter to perform, so it's good to have a mock

  @Test
  void testPerformAddsMoneyToThievesAndRobbersPiece() {
    int moneyAmount = 50;
    MoneyAction moneyAction = new MoneyAction(moneyAmount);
    ThievesAndRobbersPiece mockTarPiece = mock(ThievesAndRobbersPiece.class);

    moneyAction.perform(mockTarPiece, mockBoard);

    verify(mockTarPiece).addMoney(moneyAmount);
  }

  @Test
  void testPerformWithNonThievesAndRobbersPieceThrowsClassCastException() {
    int moneyAmount = 25;
    MoneyAction moneyAction = new MoneyAction(moneyAmount);
    Piece mockGenericPiece = mock(Piece.class); 

    assertThrows(ClassCastException.class, () -> {
      moneyAction.perform(mockGenericPiece, mockBoard);
    });
  }

  @Test
  void testGetMoneyReturnsCorrectAmount() {
    int expectedMoney = 100;
    MoneyAction moneyAction = new MoneyAction(expectedMoney);

    assertEquals(expectedMoney, moneyAction.getMoney());
  }

  @Test
  void testConstructorWithNegativeMoney() {
    int negativeMoney = -20;
    MoneyAction moneyAction = new MoneyAction(negativeMoney);
    assertEquals(negativeMoney, moneyAction.getMoney(), "Constructor should accept negative money values.");
  }

  @Test
  void testConstructorWithZeroMoney() {
    int zeroMoney = 0;
    MoneyAction moneyAction = new MoneyAction(zeroMoney);
    assertEquals(zeroMoney, moneyAction.getMoney(), "Constructor should accept zero money value.");
  }
}