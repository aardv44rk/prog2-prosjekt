package ntnu.idi.idatt.exceptions;

import java.io.IOException;

/**
 * Exception related to file handling operations.
 * This exception is thrown when there are issues with file operations,
 * such as reading from or writing to a file.
 */
public class FileHandlingException extends IOException {
    /**
     * Constructs a {@code FileHandlingException} with no detail message.
     */
    public FileHandlingException() {
        super();
    }

    /**
     * Constructs a {@code FileHandlingException} with the specified detail message.
     *
     * @param message the detail message.
     */
    public FileHandlingException(String message) {
        super(message);
    }

    /**
     * Constructs a {@code FileHandlingException} with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method). (A {@code null} value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     */
    public FileHandlingException(String message, Throwable cause) {
        super(message, cause);
    }
  
}
