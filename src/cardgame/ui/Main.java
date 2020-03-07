package cardgame.ui;

import cardgame.core.Model;
import edu.kit.informatik.Terminal;

/**
 * The cardgames Main class
 * @author Florian Heck
 * @version 1.0
 */
public class Main {

    /**
     * Utility main class
     */
    private Main() {
        
    }
    
    /**
     * Main method
     * @param args Commandline arguments
     */
    public static void main(String[] args) {
        Model model = new Model();
        Commands command;
        
        do {
            command = Commands.runFitting(model, Terminal.readLine());
        } while(command == null || command.isRunning());
    }

}
