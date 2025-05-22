package ntnu.idi.idatt.exceptions;

/**
 * Exception thrown for errors related to invalid setup or
 * configuration of game components or the application.
 */
public class ConfigurationException extends RuntimeException {

    /**
     * Constructs a {@code ConfigurationException} with no detail message.
     */
    public ConfigurationException() {
        super();
    }

    /**
     * Constructs a {@code ConfigurationException} with the specified detail message.
     *
     * @param message the detail message.
     */
    public ConfigurationException(String message) {
        super(message);
    }

    /**
     * Constructs a {@code ConfigurationException} with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method). (A {@code null} value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     */
    public ConfigurationException(String message, Throwable cause) {
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
    public ConfigurationException(Throwable cause) {
        super(cause);
    }
}