package cardgame.core;

import java.util.Iterator;
import java.util.List;
import cardgame.card.BuildingCard;
import cardgame.card.DrawableCard;
import cardgame.exceptions.GameException;

/**
 * Class that implements the game system
 * 
 * @author Florian Heck
 * @version 1.0
 */
public class GameSystem {

    private GameState currentState;
    private DrawableCard lastDrawn;
    private Hand hand;
    private CardStack cardStack;

    /**
     * Constructor to set up the hand and cardstack and set the current state
     */
    public GameSystem() {
        hand = new Hand();
        cardStack = new CardStack();
        currentState = GameState.NOT_STARTED;
    }

    /**
     * Draws a card from the cardstack
     * @return The drawn card
     * @throws GameException if the game is in the wrong state
     */
    public DrawableCard draw() throws GameException {
        if (currentState != GameState.SCAVENGE) {
            throw new GameException("wrongs game state for command");
        }
        lastDrawn = cardStack.draw();
        currentState = hand.addDrawnCard(lastDrawn);
        if (lastDrawn.getCategory() == DrawableCard.Category.CATASTROPHE) {
            hand.loseCards();
        }
        if (!cardsLeft() && !canBuildWinningCard()) {
            currentState = GameState.END;
        }
        return lastDrawn;
    }

    /**
     * Method to call when a die needs to be thrown in an encounter or endeavor
     * @param dieSize The size of the thrown die
     * @param thrown The amount thrown
     * @return True if the encounter / endeavor has succeeded, false if not
     * @throws GameException
     */
    public boolean dieThrow(int dieSize, int thrown) throws GameException {
        if (currentState != GameState.ENCOUNTER && currentState != GameState.ENDEAVOR) {
            throw new GameException("not in an encounter or endeavor");
        }
        if (currentState == GameState.ENCOUNTER) {
            if (dieSize != lastDrawn.getDieSize()) {
                throw new GameException("wrong die size, expected: " + lastDrawn.getDieSize());
            }
            currentState = GameState.SCAVENGE;
            if (lastDrawn.getEyeCount() >= thrown + hand.getBonus()) {
                hand.loseCards();
                return false;
            } else {
                return true;
            }
        } else {
            if (dieSize != BuildingCard.DIE_SIZE_ENDEAVOR) {
                throw new GameException("wrong die size for endeavor, expected " + BuildingCard.DIE_SIZE_ENDEAVOR);
            }
            if (thrown < BuildingCard.EYE_COUNT_ENDEAVOR) {
                return false;
            } else {
                currentState = GameState.END;
                return true;
            }
        }
    }

    /**
     * Starts a new game
     * 
     * @param input An array of drawable cards to setup the card stack
     */
    public void newGame(DrawableCard[] input) {
        hand = new Hand();
        cardStack = new CardStack(input);
        currentState = GameState.SCAVENGE;
    }

    /**
     * 
     * @return the current gamestate
     */
    public GameState getCurrentState() {
        return currentState;
    }

    /**
     * 
     * @return The iterator of drawn cards
     */
    public Iterator<DrawableCard> listResources() {
        return hand.listResource();
    }

    /**
     * 
     * @return The iterator of built cards
     */
    public Iterator<BuildingCard> listBuildings() {
        return hand.listBuildings();
    }

    /**
     * Resets the game //TODO reset to input
     */
    public void reset() {
        hand = new Hand();
        cardStack = new CardStack();
    }

    /**
     * 
     * @return a list of buildable cards
     */
    public List<BuildingCard> buildable() {
        return hand.buildable();
    }

    /**
     * Tries to build a given buildingcard
     * 
     * @param toBuild The card to build
     * @return whether the card has been built or not
     * @throws GameException if a fireplace needs to be built first
     */
    public boolean build(BuildingCard toBuild) throws GameException {
        if (hand.build(toBuild)) {
            if (toBuild.isWinningCard()) {
                currentState = GameState.END;
                return true;
            } else if (toBuild.triggersEndeavor()) {
                currentState = GameState.ENDEAVOR;
                return true;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    
    /**
     * 
     * @return true if cards are left on the stack, false if not
     */
    public boolean cardsLeft() {
        return cardStack.cardsLeft();
    }
    
    public boolean canBuildWinningCard() {
        return hand.buildable().stream().anyMatch(card -> card.isWinningCard() || card.triggersEndeavor());
    }
}
