package cardgame.exceptions;

/**
 * Exception that indicates an error in the game has occured
 * @author Florian Heck
 * @version 1.0
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
    public GameException(final String s) {
        super(s);
    }   
}