package ntnu.idi.idatt.utility;

import java.util.List;

/**
 * Utility class for validating arguments. Contains only static methods, so should not be instantiated.
 */
public class ArgumentValidator { // TODO Potentially add more validation methods in the future, remove unused ones

  private ArgumentValidator() {
    // Prevent instantiation
  }

  /**
   * Validates if the input is a valid integer.
   *
   * @param input the input string to validate
   * @return true if the input is a valid integer, false otherwise
   */
  public static boolean isValidInteger(int input) {
    if (input < 0) {
      return false;
    }
    
    return true;
  }

  /**
   * Validates if the input is a valid double.
   *
   * @param input the input string to validate
   * @return true if the input is a valid double, false otherwise
   */
  public static boolean isValidDouble(String input) {
    try {
      Double.parseDouble(input);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  /**
   * Validates if the input is a valid string.
   *
   * @param input the input string to validate
   * @return true if the input is not null && not blank, false otherwise
   */
  public static boolean isValidString(String string) {
    return string != null && !string.isBlank();
  }

  /**
   * Validates if the input is a valid object.
   *
   * @param obj the object to validate
   * @return true if the object is not null, false otherwise
   */
  public static boolean isValidObject(Object obj) {
    return obj != null;
  }

  /**
   * Validates if the input is a valid list.
   *
   * @param list the list to validate
   * @return true if the list is not null && not empty, false otherwise
   */
  public static boolean isValidList(List<?> list) {
    return list != null && !list.isEmpty();
  }
  
  /**
   * Validates if the input is a valid file path. Same implementation as isValidString,
   * but added for clarity.
   *
   * @param filePath the file path to validate
   * @return true if the file path is not null && not blank, false otherwise
   */
  public static boolean isValidFilePath(String filePath) {
    return filePath != null && !filePath.isBlank();
  }
}
