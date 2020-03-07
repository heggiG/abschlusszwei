package cardgame.ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cardgame.core.Model;

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
        public void execute(Matcher match, String input) {
            
        }
    }, 
    
    /**
     * The draw command
     */
    DRAW("draw") {
        @Override
        public void execute(Matcher match, String input) {
            
        }
    }, 
    
    /**
     * The list-resources command
     */
    LISTRESOURCES("list-resources") {
        @Override
        public void execute(Matcher match, String input) {
            
        }
    }, 
    
    /**
     * The build command
     */
    BUILD("") {
        @Override
        public void execute(Matcher match, String input) {
            
        }
    },
    
    /**
     * The list-buildings command
     */
    LISTBUILDINGS("list-buildings") {
        @Override
        public void execute(Matcher match, String input) {
            
        }
    },
    
    /**
     * The build? command
     */
    BUILDABLE("build\\?") {
        @Override
        public void execute(Matcher match, String input) {
            
        }
    },
    
    /**
     * The rolldx command
     */
    ROLLDX("rollD[468] \\d+") {
        @Override
        public void execute(Matcher match, String input) {
            
        }  
    },
    
    /**
     * The reset command
     */
    RESET("reset") {
        @Override
        public void execute(Matcher match, String input) {
            
        }
    }, 
    
    /**
     * The quit command
     */
    QUIT("quit") {
        @Override
        public void execute(Matcher match, String input) {
            quit();
        }
    };

    private Pattern pattern;
    private boolean running;
    
    private Commands(String regex) {
        pattern = Pattern.compile(regex);
        running = true;
    }

    public static Commands runFitting(Model model, String input) {
        for (Commands comm : Commands.values()) {
            Matcher match = comm.pattern.matcher(input);
            if (match.matches()) {
                comm.execute(match, input);
                return comm;
            }
        }
        return null;
    }
    
    /**
     * Executes the command
     * @param match The regex Matcher
     * @param command The user input
     */
    public abstract void execute(Matcher match, String command);
    
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
    public void quit() {
        running = false;
    }
}
