package cardgame.card;

import cardgame.ui.GameState;

/**
 * Interface to implement for all needed drawable card methods
 * @author Florian Heck
 * @version 1.0
 */
public interface DrawableCard {
    
    /**
     * TODO maybe unnecessary?
     * @return the cards type (wood, metal...)
     */
    String getType();
    
    /**
     * TODO maybe unnecessary?
     * @return the cards group (resource, animal or catastrophe)
     */
    String getCategory();
    
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
