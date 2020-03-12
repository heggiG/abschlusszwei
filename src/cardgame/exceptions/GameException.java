package cardgame.exceptions;

/**
 * Exception that indicates
 * @author Florian
 *
 */
public class GameException extends Exception {
    
    private static final long serialVersionUID = 1L;

    /**
     * Standard exception constructor
     */
    public GameException() {
        super();
    }
    
    /**
     * Exception constructor with detail message
     * @param s Detail message
     */
    public GameException(String s) {
        super(s);
    }
    
}
