package cardgame.ui;

import cardgame.core.GameSystem;
import cardgame.exceptions.InputException;
import edu.kit.informatik.Terminal;

/**
 * The cardgames Main class
 * 
 * @author Florian Heck
 * @version 1.0
 */
public class Main {

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
                command = Commands.runFitting(gameSystem, Terminal.readLine());
                if (command == null) {
                    Terminal.printError("unknown command");
                }
            } catch (InputException e) {
                Terminal.printError(e.getMessage());
            }
        } while (command == null || command.isRunning());
    }

}
