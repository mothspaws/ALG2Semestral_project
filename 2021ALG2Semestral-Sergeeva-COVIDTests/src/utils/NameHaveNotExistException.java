package utils;

/**
 *
 * @author Viktoriia Sergeeva
 */
public class NameHaveNotExistException extends IllegalArgumentException {
/**
 * Vlastní vyjímka. Vygeneruje se, když jméno neexistuje
 * @param message 
 */
    public NameHaveNotExistException(String message) {
        super(message);
    }
}
