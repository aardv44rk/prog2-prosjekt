package ntnu.idi.idatt.exceptions;

public class ReadException extends FileHandlingException {
    /**
     * Constructs a {@code ReadException} with no detail message.
     */
    public ReadException() {
        super();
    }

    /**
     * Constructs a {@code ReadException} with the specified detail message.
     *
     * @param message the detail message.
     */
    public ReadException(String message) {
        super(message);
    }

    /**
     * Constructs a {@code ReadException} with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method). (A {@code null} value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     */
    public ReadException(String message, Throwable cause) {
        super(message, cause);
    }
  
}
