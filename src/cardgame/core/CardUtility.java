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
public final class CardUtility {

    /**
     * The amount of resource cards in the deck
     */
    public static final int RESOURCE_AMOUNT = 16;

    /**
     * The amount of each animal in the deck
     */
    public static final int ANIMAL_AMOUNT = 5;

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
     * Method not used for the tests, but usefull if you want to play the game for
     * real
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
     * 
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
            ret[i] = getDrawableFromString(input.split(",")[i]);
        }
        for (DrawableCard dc : DrawableType.values()) {
            int sum = 0; // amount of a single card
            for (DrawableCard toSum : ret) {
                if (dc.equals(toSum)) {
                    sum++;
                }
            } // check amount of each card
            if (dc.getCategory() == DrawableCard.Category.ANIMAL) {
                if (sum != ANIMAL_AMOUNT) {
                    throw new InputException("wrong amount of " + dc.getType());
                }
            } else if (dc.getCategory() == DrawableCard.Category.RESOURCE) {
                if (sum != RESOURCE_AMOUNT) {
                    throw new InputException("wrong amount of " + dc.getType());
                }
            } else {
                if (sum != CATASTROPHE_AMOUNT) {
                    throw new InputException("wrong amount of " + dc.getType());
                }
            }
        }
        return ret;
    }

    /**
     * Returns a building card from an input string
     * 
     * @param input The input string
     * @return The building card that fits the name, null if none are found
     * @throws InputException if the input is not any of the existing building cards
     */
    public static BuildingCard getFromString(String input) throws InputException {
        for (BuildingCard bc : BuildingType.values()) {
            if (bc.getType().equals(input)) {
                return bc;
            }
        }
        throw new InputException("none existing card");
    }

    private static DrawableCard getDrawableFromString(String input) {
        for (DrawableCard dc : DrawableType.values()) {
            if (dc.getType().equals(input)) {
                return dc;
            }
        }
        return null;
    }
}
