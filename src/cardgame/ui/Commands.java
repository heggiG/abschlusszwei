package cardgame.ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import cardgame.core.GameSystem;
import cardgame.exceptions.InputException;
import edu.kit.informatik.Terminal;

/**
 * Enum that handles the user input
 * 
 * @author Florian Heck
 * @version 1.0
 */
public enum Commands {

    /**
     * The start command
     */
    START("start") {
        @Override
        public void run(Matcher match, GameSystem gs) {
            
        }
    }, 
    
    /**
     * The draw command
     */
    DRAW("draw") {
        @Override
        public void run(Matcher match, GameSystem gs) throws InputException {
            if (!gs.getCurrentState().isExpected(this)) {
                throw new InputException("unexpected command for current game state");
            }
        }
    }, 
    
    /**
     * The list-resources command
     */
    LISTRESOURCES("list-resources") {
        @Override
        public void run(Matcher match, GameSystem gs) {
           
        }
    }, 
    
    /**
     * The build command
     */
    BUILD("") {
        @Override
        public void run(Matcher match, GameSystem gs) throws InputException {
            if (!gs.getCurrentState().isExpected(this)) {
                throw new InputException("unexpected command for current game state");
            }
        }
    },
    
    /**
     * The list-buildings command
     */
    LISTBUILDINGS("list-buildings") {
        @Override
        public void run(Matcher match, GameSystem gs) {
            
        }
    },
    
    /**
     * The build? command
     */
    BUILDABLE("build\\?") {
        @Override
        public void run(Matcher match, GameSystem gs) {
            
        }
    },
    
    /**
     * The rolldx command
     */
    ROLLDX("rollD[468] ([+-]?\\d+)") {
        @Override
        public void run(Matcher match, GameSystem gs) throws InputException {
            if (!gs.getCurrentState().isExpected(this)) {
                throw new InputException("unexpected command for current game state");
            }
            int thrown = Integer.parseInt(match.group(1));
        }  
    },
    
    /**
     * The reset command
     */
    RESET("reset") {
        @Override
        public void run(Matcher match, GameSystem gs) {
            
        }
    }, 
    
    /**
     * The quit command
     */
    QUIT("quit") {
        @Override
        public void run(Matcher match, GameSystem gs) {
            quit();
        }
    };

    private Pattern pattern;
    private boolean running;
    
    private Commands(String regex) {
        pattern = Pattern.compile(regex);
        running = true;
    }

    /**
     * Finds the fitting command and executes it
     * @param gamesys The gamesystem to operate on
     * @param input the user input
     * @return The found command
     * @throws InputException If the input dosen't fit the expected one
     */
    public static Commands runFitting(GameSystem gamesys, String input) throws InputException {
        for (Commands comm : Commands.values()) {
            Matcher match = comm.pattern.matcher(input);
            if (match.matches()) {
                comm.run(match, gamesys);
                return comm;
            }
        }
        return null;
    }
    
    /**
     * Executes the command
     * @param match The regex Matcher
     * @param gs The gamesystem to operate on
     * @throws InputException if the input dosen't match the expected
     */
    public abstract void run(Matcher match, GameSystem gs) throws InputException;
    
    /**
     * 
     * @return Whether the interaction is still running
     */
    public boolean isRunning() {
        return running;
    }
    
    /**
     * Quits the user interaction
     */
    protected void quit() {
        running = false;
    }
}
