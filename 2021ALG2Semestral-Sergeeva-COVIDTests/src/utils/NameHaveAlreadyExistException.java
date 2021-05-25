package utils;

/**
 *
 * @author Viktoriia Sergeeva
 */
public class NameHaveAlreadyExistException extends IllegalArgumentException {
/**
 * Vlastní vyjímka. Vygeneruje se, když jméno existuje
 * @param message 
 */
    public NameHaveAlreadyExistException(String message) {
        super(message);
    }
}
