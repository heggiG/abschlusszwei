package cardgame.ui;

public enum GameState {

    SCAVENGE(new Commands[] {Commands.BUILD, Commands.DRAW, Commands.BUILD}),

    ENCOUNTER(new Commands[] {Commands.ROLLDX}),

    ENDEAVOR(new Commands[] {Commands.ROLLDX}),

    END(new Commands[] {});

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
        } else if (input == Commands.START || input == Commands.BUILDABLE || input == Commands.RESET) {
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