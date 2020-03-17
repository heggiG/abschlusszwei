package cardgame.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import cardgame.card.*;
import cardgame.exceptions.InputException;

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
     * Total card amount
     */
    public static final int CARD_AMOUNT = 64;
    
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

    /**
     * Returns an array of drawable cards from the start command
     * @param input The list of cards as a string
     * @return The array of drawable cards
     * @throws InputException If the input dosent match the tasks parameters
     */
    public static DrawableCard[] getStartInput(String input) throws InputException {
        DrawableCard[] ret = new DrawableCard[CARD_AMOUNT];
        if (input.split(",").length != CARD_AMOUNT) {
            throw new InputException("Wrong amount of cards");
        }
        for (int i = 0; i < CARD_AMOUNT; i++) {
            switch (input.split(",")[i]) {
            case "wood":
                ret[i] = DrawableType.WOOD;
                break;
                
            case "metal":
                ret[i] = DrawableType.METAL;
                break;
                
            case "plastic":
                ret[i] = DrawableType.PLASTIC;
                break;
                
            case "snake":
                ret[i] = DrawableType.SNAKE;
                break;
                
            case "spider":
                ret[i] = DrawableType.SPIDER;
                break;
                
            case "tiger":
                ret[i] = DrawableType.TIGER;
                break;
                
            case "thunderstorm":
                ret[i] = DrawableType.THUNDERSTORM;
                break;
                
                default:
                    throw new InputException(i + " is an unknown card");
            }       
        }
        return ret;
    }
    
    public static BuildingCard getFromString(String input) throws InputException {
        switch (input) {
            case "axe":
                return BuildingType.AXE;
                
            case "club":
                return BuildingType.CLUB;
                
            case "shack":
                return BuildingType.SHACK;
                
            case "fireplace":
                return BuildingType.FIREPLACE;
                
            case "sailingraft":
                return BuildingType.SAILINGRAFT;
                
            case "hangglider":
                return BuildingType.HANGGLIDER;
                
            case "steamboat":
                return BuildingType.STEAMBOAT;
                
            case "ballon":
                return BuildingType.BALLON;
            
            default:
                throw new InputException("input is not a buildable card");
        }
    }
}
