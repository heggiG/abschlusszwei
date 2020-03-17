package cardgame.card;

/**
 * Interface to implement for the building cards
 * 
 * @author Florian Heck
 * @version 1.0
 */
public interface BuildingCard {

    /**
     * The amount of cards saved by a shack
     */
    public static final int SHACK_SAVINGS = 5;
    
    /**
     * 
     * @return a string that is the type of buildable card
     */
    String getType();

    /**
     * 
     * @return an array containing an amount of drawn cards that are required to
     *         build a buildable card
     */
    DrawableCard[] getCost();
    
    /**
     * 
     * @return the bonus this card gives in case of an encounter, if any
     */
    int getBonus();
    
    /**
     * 
     * @return wether this card is a hut or nut
     */
    boolean isShack();
    
    /**
     * 
     * @return whether this card will win the game
     */
    boolean isWinningCard();
    
    /**
     * 
     * @return whether this card triggers an endeavor
     */
    boolean triggersEndeavor();

}
