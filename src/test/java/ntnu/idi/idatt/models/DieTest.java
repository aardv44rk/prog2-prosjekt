package ntnu.idi.idatt.models;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ntnu.idi.idatt.exceptions.InvalidInputException;

/**
 * Tests for the Die class. Only tests getSides method and constructor as the rest of the
 * methods are tested in the DiceTest class.
 */
class DieTest {
  Die die;
  @BeforeEach
  void setUp() {
    die = new Die(6);
  }

  @Test
  void testGetSides() {
    assertEquals(6, die.getSides(), "Die should have 6 sides");
  }

  @Test
  void testDieInvalidSides() {
    int sides = 0;
    InvalidInputException e = assertThrows(InvalidInputException.class, () -> new Die(sides));

    assertEquals("Number of sides must be a positive integer", e.getMessage(),
        "Exception message should match");
  }
}
