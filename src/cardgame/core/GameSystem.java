package cardgame.core;

import java.util.Iterator;
import cardgame.card.BuildingCard;
import cardgame.card.DrawableCard;
import cardgame.ui.GameState;

public class GameSystem {

    private GameState currentState;
    private DrawableCard lastDrawn;
    private Hand hand;
    private CardStack cardStack;
    
    public GameSystem() {
        hand = new Hand();
        cardStack = new CardStack();
    }
    
    public void draw() {
        lastDrawn = cardStack.draw();
        currentState = hand.addDrawnCard(lastDrawn);
    }
    
    public void encounter(int thrown) {
        
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
    
    public Iterator<BuildingCard> listBuildings() {
        return hand.listBuildings();
    }
}
