package cardgame.ui;

/**
 * Enumeration that models the games given gamestates, when to draw cards or when to attack...
 *
 * @author Florian Heck
 * @version 1.0
 */
public enum GameState {

    SCAVENGE(new Commands[] {Commands.BUILD, Commands.DRAW}),

    ENCOUNTER(new Commands[] {Commands.ROLLDX}),

    ENDEAVOR(new Commands[] {Commands.ROLLDX}),

    END(new Commands[] {Commands.START});

    // TODO maybe give the input command to see if they match?
    private Commands[] expectedInput;
    
    private GameState(Commands[] expectedInput) {
        this.expectedInput = expectedInput;
    }

    public Commands[] getExpectedInput() {
        return expectedInput;
    }
    
    public boolean isExpected(Commands input) {
        if (input == Commands.LISTRESOURCES || input == Commands.LISTBUILDINGS) {
            return true; //splitted for readability
        } else if (input == Commands.BUILDABLE || input == Commands.RESET) {
            return true;
        } else {
            for (Commands comm : expectedInput) {
                if (input == comm) {
                    return true;
                }
            }
            return false;
        }
    }
    
}