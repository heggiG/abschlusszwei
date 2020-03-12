package cardgame.core;

import java.util.Iterator;
import cardgame.card.DrawableCard;
import cardgame.ui.GameState;

public class GameSystem {

    private GameState currentState;
    private Hand hand;
    private CardStack cardStack;
    
    public GameSystem() {
        hand = new Hand();
        cardStack = new CardStack();
    }
    
    public void draw() {
        
    }
    
    public void newGame() {
        hand = new Hand();
        cardStack = new CardStack();
    }
    
    public GameState getCurrentState() {
        return currentState;
    }
    
    public Iterator<DrawableCard> listResources() {
        return hand.listResource();
    }
}
