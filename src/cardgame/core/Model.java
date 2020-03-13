package cardgame.core;

import java.util.Iterator;
import cardgame.card.DrawableCard;
import edu.kit.informatik.Terminal;

public class Model {

    private GameSystem gs;

    public void buildable() {
        gs = new GameSystem();
    }

    /**    
     * Prints the resources currently hold in the hand
     */
    public void listResources() {
        Iterator<DrawableCard> iter = gs.listResources();
        if (iter == null) {
            Terminal.printLine("EMPTY");
        } else {
            while (iter.hasNext()) {
                Terminal.printLine(iter.next());
            }
        }
    }
    
    public void draw() {
        
    }
}