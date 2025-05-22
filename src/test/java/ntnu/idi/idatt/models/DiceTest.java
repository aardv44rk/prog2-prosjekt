package ntnu.idi.idatt.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import ntnu.idi.idatt.exceptions.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Dice class.
 */
class DiceTest {

  List<Die> diceList;
  Dice dice;

  
  @BeforeEach
  void setUp() {
    diceList = List.of(new Die(6), new Die(6));
    dice = new Dice(diceList);
  }

  @Test
  void testDiceCreation() { 
    assertEquals(diceList, dice.getDice(), "Dice should match the provided list");
  }

  @Test
  void testRoll() {
    dice.roll();
    for (Die die : dice.getDice()) {
      assertTrue((die.getValue() >= 1 ) || (die.getValue() <= 6), "Die value should be between 1 and 6");
    }
  }

  @Test
  void testGetValue() {
    int expectedValue = diceList.stream().mapToInt(Die::getValue).sum();
    assertEquals(expectedValue, dice.getValue(), "Total value should match the sum of die values");
  }

  @Test
  void testGetValues() {
    int[] expectedValues = diceList.stream().mapToInt(Die::getValue).toArray();
    assertEquals(expectedValues.length, dice.getValues().length, "Length of values should match");
    for (int i = 0; i < expectedValues.length; i++) {
      assertEquals(expectedValues[i], dice.getValues()[i], "Value at index " + i + " should match");
    }
  } 

  @Test
  void testInvalidDiceCreation() {
    InvalidInputException e = assertThrows(InvalidInputException.class, () -> new Dice(null));
    assertEquals("Dice list cannot be null or empty", e.getMessage(), "Exception message should match");
  }
  
  @Test
  void testInvalidDiceCreationEmpty() {
    InvalidInputException e = assertThrows(InvalidInputException.class, () -> new Dice(List.of()));
    assertEquals("Dice list cannot be null or empty", e.getMessage(), "Exception message should match");
  }

  @Test
  void testValidDice() {
    assertTrue(dice.isValidDice(diceList), "Dice should be valid");
  }
}
