package cardgame.core;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import cardgame.card.BuildingCard;
import cardgame.card.BuildingType;
import cardgame.card.DrawableCard;
import cardgame.exceptions.GameException;

public class Hand {

    private Set<BuildingCard> buildings;
    private Deque<DrawableCard> drawnCards;

    public Hand() {
        buildings = new HashSet<>();
        drawnCards = new LinkedList<>();
    }

    public void addDrawnCard(DrawableCard dc) {
        drawnCards.add(dc);
    }

    public Iterator<DrawableCard> listResource() {
        if (drawnCards.isEmpty()) {
            return null;
        }
        return drawnCards.descendingIterator();
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

}
