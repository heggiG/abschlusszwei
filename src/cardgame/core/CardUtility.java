package cardgame.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import cardgame.card.*;

/**
 * Utility class that provide some set up functionality
 * 
 * @author Florian Heck
 * @version 1.0
 */

public class CardUtility { 

    /**
     * The amount of resource cards in the deck
     */
    public static final int RESOURCE_AMOUNT = 16;
    
    /**
     * The amount of each animal in the deck
     */
    public static final int ANIMAL_AMOUNT = 6;
    
    /**
     * The amount of each catastrophe in the deck
     */
    public static final int CATASTROPHE_AMOUNT = 1;

    /**
     * Private utility class constructor
     */
    private CardUtility() {
    }

    /**
     * 
     * @return a shuffled card deck
     */
    public static List<DrawableCard> getNewStack() {
        List<DrawableCard> stack = new ArrayList<>();
        for (int i = 0; i < RESOURCE_AMOUNT; i++) {
            stack.add(DrawableType.METAL);
            stack.add(DrawableType.WOOD);
            stack.add(DrawableType.PLASTIC);
            if (i < ANIMAL_AMOUNT) {
                stack.add(DrawableType.SNAKE);
                stack.add(DrawableType.SPIDER);
                stack.add(DrawableType.TIGER);
            }
            if (i < CATASTROPHE_AMOUNT) {
                stack.add(DrawableType.THUNDERSTORM);
            }
        }
        Collections.shuffle(stack);
        return stack;
    }

}
