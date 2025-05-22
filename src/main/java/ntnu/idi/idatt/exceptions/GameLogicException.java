package ntnu.idi.idatt.exceptions;

/**
 * Exception thrown for errors that occur during gameplay due to
 * violations of game rules or an inconsistent game state.
 */
public class GameLogicException extends RuntimeException {

  /**
   * Constructs a {@code GameLogicException} with no detail message.
   */
  public GameLogicException() {
    super();
  }

  /**
   * Constructs a {@code GameLogicException} with the specified detail message.
   *
   * @param message the detail message.
   */
  public GameLogicException(String message) {
    super(message);
  }

  /**
   * Constructs a {@code GameLogicException} with the specified detail message and
   * cause.
   *
   * @param message the detail message.
   * @param cause   the cause (which is saved for later retrieval by the
   *                {@link #getCause()} method). (A {@code null} value is
   *                permitted, and indicates that the cause is nonexistent or
   *                unknown.)
   */
  public GameLogicException(String message, Throwable cause) {
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
  public GameLogicException(Throwable cause) {
    super(cause);
  }
}