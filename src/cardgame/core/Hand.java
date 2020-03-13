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
import cardgame.ui.GameState;

public class Hand {

    private Deque<BuildingCard> buildings;
    private Deque<DrawableCard> drawnCards;

    public Hand() {
        buildings = new LinkedList<>();
        drawnCards = new LinkedList<>();
    }

    public GameState addDrawnCard(DrawableCard dc) {
        drawnCards.add(dc);
        return dc.getFollowingState();
    }

    public Iterator<DrawableCard> listResource() {
        if (drawnCards.isEmpty()) {
            return null;
        }
        return drawnCards.descendingIterator();
    }
    
    /**
     * 
     * @return returns the highest bonus from built tools
     */
    public int getBonus() {
        return buildings.stream().mapToInt(t -> t.getBonus()).max().getAsInt();
    }

    /**
     * 
     * @param toBuild The card to build
     * @return true, if the card has been built, false if it can't be
     */
    public boolean build(BuildingCard toBuild) throws GameException {
        if ((toBuild == BuildingType.BALLON || toBuild == BuildingType.STEAMBOAT)
                && !buildings.contains(BuildingType.FIREPLACE)) {
            throw new GameException("need to build a fireplace first");
        }
        if (!buildable().contains(toBuild)) {
            return false;
        }
        for (DrawableCard dc : toBuild.getCost()) {
            drawnCards.removeFirstOccurrence(dc);
        }
        buildings.add(toBuild);
        return true;
    }

    /**
     * 
     * @return a list of buildable cards
     */
    public List<BuildingCard> buildable() {
        List<BuildingCard> ret = new ArrayList<>();
        for (BuildingCard toBuild : BuildingType.values()) {
            boolean buildable = false;
            Deque<DrawableCard> toCheck = new LinkedList<>(drawnCards); //copy the hands cards
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
                ret.add(toBuild);
            }
        }  
        return ret;
    }

    
    public Iterator<BuildingCard> listBuildings() {
        return buildings.descendingIterator();
    }
    
    /**
     * Removes all cards if an encounter has been lost, or a catastrophe occurs
     */
    public void loseCards() {
        if (buildings.contains(BuildingType.SHACK)) {
            Iterator<DrawableCard> iter = drawnCards.descendingIterator();
            int i = 0;
            while (iter.hasNext()) {
                iter.next();
                if (i >= 5) {
                    iter.remove();
                }
            }
        } else {
            drawnCards.clear();
        }
    }
}
