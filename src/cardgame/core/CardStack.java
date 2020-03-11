package cardgame.core;

import java.util.Deque;
import java.util.LinkedList;
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
     * Resets the cardstack
     */
    public void newGame() {
        cardStack = new LinkedList<>(CardUtility.getNewStack());
    }
    
    /**
     * 
     * @return the top card of the stack
     */
    public DrawableCard draw() {
        return cardStack.pollFirst();
    }
}
