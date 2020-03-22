package cardgame.core;

import cardgame.ui.Commands;

/**
 * Enumeration that models the games given gamestates, when to draw cards or
 * when to attack and so on. Also gives the expected commands for any gamestate.
 *
 * @author Florian Heck
 * @version 1.0
 */
public enum GameState {

    /**
     * The scavenge gamestate, where a player needs to draw cards or build
     */
    SCAVENGE(new Commands[] {Commands.BUILD, Commands.DRAW, Commands.LISTRESOURCES, Commands.LISTBUILDINGS,
        Commands.BUILDABLE, Commands.RESET}), //all the commands expected for this gamestate

    /**
     * The encounter gamestate where a player needs to fend off animals
     */
    ENCOUNTER(new Commands[] {Commands.ROLLDX, Commands.LISTRESOURCES, Commands.LISTBUILDINGS, Commands.RESET}),

    /**
     * The endeavor gamestate where a player needs to roll the dice to win the game
     */
    ENDEAVOR(new Commands[] {Commands.ROLLDX, Commands.LISTRESOURCES, Commands.LISTBUILDINGS, Commands.RESET}),

    /**
     * The end gamestate, when the game is over
     */
    END(new Commands[] {Commands.START, Commands.LISTRESOURCES, Commands.LISTBUILDINGS, Commands.RESET}),
    
    /**
     * If the game hasn't been started yet
     */
    NOT_STARTED(new Commands[] {Commands.START});

    private Commands[] expectedInput;

    private GameState(final Commands[] expectedInput) {
        this.expectedInput = expectedInput;
    }

    /**
     * 
     * @return An array of commands that are expected in this gamestate
     */
    public Commands[] getExpectedInput() {
        return expectedInput;
    }

    /**
     * 
     * @param input The command that has been triggered
     * @return Whether the command is expected or not
     */
    public boolean isExpected(final Commands input) {
        if (input == Commands.QUIT) {
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