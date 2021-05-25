package utils;

/**
 *
 * @author Viktoriia Sergeeva
 */
public class DateHaveAlreadyExistException extends IllegalArgumentException {

    /**
     * Vlastní vyjímka. Vygeneruje se, když datum existuje
     *
     * @param message
     */
    public DateHaveAlreadyExistException(String message) {
        super(message);
    }
}
