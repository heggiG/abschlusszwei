package cardgame.core;

import java.util.Iterator;
import java.util.List;
import cardgame.card.BuildingCard;
import cardgame.card.DrawableCard;
import cardgame.exceptions.GameException;
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
    
    public DrawableCard draw() throws GameException {
        if (currentState != GameState.SCAVENGE) {
            throw new GameException("wrong command, expected draw");
        }
        lastDrawn = cardStack.draw();
        currentState = hand.addDrawnCard(lastDrawn);
        return lastDrawn;
    }
    
    public boolean encounter(int dieSize, int thrown) throws GameException {
        if (currentState != GameState.ENCOUNTER) {
            throw new GameException("not in an encounter");
        }
        if (dieSize != lastDrawn.getDieSize()) {
            throw new GameException("wrong die size, expected: " + lastDrawn.getDieSize());
        }
        if (thrown <= lastDrawn.getEyeCount() - hand.getBonus()) {
            hand.loseCards();
            return false;
        } else {
            return true;
        }
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
    
    public void reset() {
        hand = new Hand();
        cardStack = new CardStack();
    }
    
    public List<BuildingCard> buildable() {
        return hand.buildable();
    }
}
