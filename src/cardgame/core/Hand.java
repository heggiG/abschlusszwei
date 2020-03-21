package cardgame.core;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import cardgame.card.BuildingCard;
import cardgame.card.BuildingType;
import cardgame.card.DrawableCard;
import cardgame.exceptions.GameException;

/**
 * Class that implements your "hand" of drawn and built cards
 * 
 * @author Florian Heck
 * @version 1.1
 */
public class Hand {

    private Deque<BuildingCard> buildings;
    private Deque<DrawableCard> drawnCards;

    /**
     * Constructor that instances the Deque interfaces
     */
    public Hand() {
        buildings = new LinkedList<>();
        drawnCards = new LinkedList<>();
    }

    /**
     * 
     * @return the last built card
     */
    public BuildingCard getLastBuilt() {
        return buildings.getLast();
    }

    /**
     * Adds a drawn card to the hand
     * 
     * @param dc The drawable card to add to your hand
     * @return The gamestate that follows this card
     * @throws GameException if null is given as parameter
     */
    public GameState addDrawnCard(DrawableCard dc) throws GameException {
        if (dc == null) {
            throw new GameException("no cards left");
        }
        if (dc.getCategory() == DrawableCard.Category.RESOURCE) {
            drawnCards.add(dc);
        }
        return dc.getFollowingState();
    }

    /**
     * 
     * @return an iterator of drawable cards
     */
    public Iterator<DrawableCard> listResource() {
        if (drawnCards.isEmpty()) {
            return null;
        }
        return drawnCards.iterator();
    }

    /**
     * 
     * @return an iterator of built cards
     */
    public Iterator<BuildingCard> listBuildings() {
        if (buildings.isEmpty()) {
            return null;
        }
        return buildings.descendingIterator();
    }

    /**
     * 
     * @return returns the highest bonus from built tools
     */
    public int getBonus() {
        return buildings.stream().mapToInt(built -> built.getBonus()).max().orElse(0);
    }

    /**
     * Builds a building card, if have the resources
     * @param toBuild The card to build
     * @return true, if the card has been built, false if it can't be
     * @throws GameException if you need to build a fireplace first
     */
    public boolean build(BuildingCard toBuild) throws GameException {
        if (toBuild.isWinningCard() && !buildings.contains(BuildingType.FIREPLACE)) {
            throw new GameException("need to build a fireplace first");
        }
        if (buildable().contains(toBuild)) {
            for (DrawableCard dc : toBuild.getCost()) {
                drawnCards.removeLastOccurrence(dc);
            }
            buildings.add(toBuild);
            return true;
        }
        return false;
    }

    /**
     * 
     * @return a list of buildable cards
     */
    public List<BuildingCard> buildable() {
        List<BuildingCard> ret = new ArrayList<>();
        for (BuildingCard toBuild : BuildingType.values()) {
            boolean buildable = false;
            Deque<DrawableCard> toCheck = new LinkedList<>(drawnCards); // copy the hands cards
            for (DrawableCard dc : toBuild.getCost()) {
                if (toCheck.contains(dc)) {
                    toCheck.removeFirstOccurrence(dc);
                    buildable = true;
                } else {
                    buildable = false;
                    break;
                }
            }
            if (buildable) {
                if (toBuild.isWinningCard() && !buildings.contains(BuildingType.FIREPLACE)) {
                    continue;
                } else if (!buildings.contains(toBuild)) { // cards can only be built once
                    ret.add(toBuild);
                }
            }
        }
        return ret;
    }

    /**
     * Removes all cards if an encounter has been lost, or a catastrophe occurs
     */
    public void loseCards() {
        if (containsShack()) {
            Iterator<DrawableCard> iter = drawnCards.descendingIterator();
            int i = 0;
            while (iter.hasNext()) {
                iter.next();
                if (i >= BuildingCard.SHACK_SAVINGS) {
                    iter.remove();
                }
                i++;
            }
        } else {
            drawnCards.clear();
        }
    }

    /**
     * 
     * @return whether a shack has been built
     */
    public boolean containsShack() {
        return buildings.stream().anyMatch(card -> card.isShack());
    }

    /**
     * Removes the fireplace from the built cards, like in
     */
    public void removeFirePlace() {
        buildings.remove(BuildingType.FIREPLACE);
    }
}