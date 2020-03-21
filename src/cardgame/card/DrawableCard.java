package cardgame.card;

import cardgame.core.GameState;

/**
 * Interface to implement for all needed drawable card methods
 * 
 * @author Florian Heck
 * @version 1.0
 */
public interface DrawableCard {

    /**
     * Enumeration that contains the different categories of the cards
     * @author Florian Heck
     * @version 1.0
     */
    enum Category {
        
        /**
         * The resource category, from which you build stuff
         */
        RESOURCE,
        
        /**
         * The animal category, which triggers an encounter
         */
        ANIMAL,
        
        /**
         * The catastrophe category, which makes you lose cards
         */
        CATASTROPHE
    }

    /**
     * 
     * @return the cards type (wood, metal...)
     */
    String getType();

    /**
     * 
     * @return the cards group (resource, animal or catastrophe)
     */
    Category getCategory();

    /**
     * 
     * @return the size that needs to be thrown if this is an encounter
     */
    int getDieSize();

    /**
     * 
     * @return the eyecount needed to survive (unequal greater than)
     */
    int getEyeCount();

    /**
     * 
     * @return the games state that follows a drawn card
     */
    GameState getFollowingState();
}
