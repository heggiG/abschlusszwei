package cardgame.card;

/**
 * Interface to implement for the building cards
 * 
 * @author Florian Heck
 * @version 1.0
 */
public interface BuildingCard {

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

}
