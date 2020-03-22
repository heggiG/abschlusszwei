package cardgame.exceptions;

/**
 * Excpetion that indicates an unexpected input has been made
 * 
 * @author Florian Heck
 * @version 1.0
 */
public class InputException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Standard exception constructor
     */
    public InputException() {
        super();
    }
    
    /**
     * Exception constructor with detail message
     * @param s Detail message
     */
    public InputException(final String s) {
        super(s);
    }
}
