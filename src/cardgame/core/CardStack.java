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
    
    public CardStack(DrawableCard[] setup) {
        newGame(setup);
    }
    
    /**
     * Resets the cardstack
     */
    public void newGame() {
        cardStack = new LinkedList<>(CardUtility.getNewStack());
    }
    
    public void newGame(DrawableCard[] input) {
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
}
