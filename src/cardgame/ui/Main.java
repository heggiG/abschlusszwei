package cardgame.ui;

import java.util.regex.Pattern;

import cardgame.core.GameSystem;
import cardgame.exceptions.InputException;
import edu.kit.informatik.Terminal;

/**
 * The cardgames Main class
 * 
 * @author Florian Heck
 * @version 1.0
 */
public final class Main {

    private static final String GENERIC_COMM = "start|draw|build|rollD[468]|list-resources|list-buildings|reset"
        + "|build\\?|quit";
    
    /**
     * Private main class constructor
     */
    private Main() {

    }

    /**
     * Main method
     * 
     * @param args Commandline arguments
     */
    public static void main(String[] args) {
        GameSystem gameSystem = new GameSystem();
        Commands command = null;
        do {
            try {
                String input = Terminal.readLine();
                command = Commands.runFitting(gameSystem, input);
                if (command == null && Pattern.matches(GENERIC_COMM + "(\\s\\S)*", input)) {
                    Terminal.printError("wrong parameters for given command");
                } else if (command == null) {
                    Terminal.printError("unknown command");
                }
            } catch (InputException e) {
                Terminal.printError(e.getMessage());
            }
        } while (command == null || command.isRunning());
    }

}
