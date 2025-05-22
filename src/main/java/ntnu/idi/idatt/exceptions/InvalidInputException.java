package ntnu.idi.idatt.exceptions;

/**
 * Exception thrown when invalid input is provided,
 * such as malformed user entries or incorrect data formats.
 */
public class InvalidInputException extends IllegalArgumentException {

  /**
   * Constructs an {@code InvalidInputException} with no detail message.
   */
  public InvalidInputException() {
    super();
  }

  /**
   * Constructs an {@code InvalidInputException} with the specified detail
   * message.
   *
   * @param message the detail message.
   */
  public InvalidInputException(String message) {
    super(message);
  }

  /**
   * Constructs an {@code InvalidInputException} with the specified detail message
   * and cause.
   *
   * @param message the detail message.
   * @param cause   the cause (which is saved for later retrieval by the
   *                {@link #getCause()} method). (A {@code null} value is
   *                permitted, and indicates that the cause is nonexistent or
   *                unknown.)
   */
  public InvalidInputException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new exception with the specified cause and a detail
   * message of {@code (cause==null ? null : cause.toString())} (which
   * typically contains the class and detail message of {@code cause}).
   *
   * @param cause the cause (which is saved for later retrieval by the
   *              {@link #getCause()} method). (A {@code null} value is
   *              permitted, and indicates that the cause is nonexistent or
   *              unknown.)
   */
  public InvalidInputException(Throwable cause) {
    super(cause);
  }
}