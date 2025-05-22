package ntnu.idi.idatt.exceptions;

public class WriteException extends FileHandlingException {
    /**
     * Constructs a {@code WriteException} with no detail message.
     */
    public WriteException() {
        super();
    }

    /**
     * Constructs a {@code WriteException} with the specified detail message.
     *
     * @param message the detail message.
     */
    public WriteException(String message) {
        super(message);
    }

    /**
     * Constructs a {@code WriteException} with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method). (A {@code null} value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     */
    public WriteException(String message, Throwable cause) {
        super(message, cause);
    }
  
}
