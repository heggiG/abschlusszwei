package cardgame.core;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import cardgame.card.DrawableCard;

/**
 * Class that implements a card stack of drawable cards.
 * @author Florian Heck
 * @version 1.0
 */
public class CardStack {

    private Deque<DrawableCard> cardStack;
    
    /**
     * Standard constructor to initiate the cardstack
     */
    public CardStack() {
        cardStack = new LinkedList<>();
    }
    
    /**
     * Sets up the cardstack with an input stack
     * @param setup The setup stack
     */
    public CardStack(final DrawableCard[] setup) {
        newGame(setup);
    }
    
    /**
     * Creates a new game from a random cardstack
     */
    public void newGame() {
        cardStack = new LinkedList<>(CardUtility.getNewStack());
    }
    
    /**
     * Starts a game from a given cardstack
     * @param input The cardstack to initialize from
     */
    public void newGame(final DrawableCard[] input) {
        List<DrawableCard> list = Arrays.asList(input);
        cardStack = new LinkedList<DrawableCard>(list);
    }
    
    /**
     * 
     * @return the top card of the stack
     */
    public DrawableCard draw() {
        return cardStack.pollFirst();
    }
    
    /**
     * 
     * @return whether there are cards left on the stack
     */
    public boolean cardsLeft() {
        return !cardStack.isEmpty();
    }
}
