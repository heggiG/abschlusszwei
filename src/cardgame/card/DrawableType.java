package cardgame.card;

import cardgame.core.GameState;

/**
 * Enumeration that contains all possible drawable cards
 * 
 * @author Florian Heck
 * @version 1.0
 */
public enum DrawableType implements DrawableCard {

    /**
     * The wood resource card
     */
    WOOD(DrawableCard.Category.RESOURCE, "wood", GameState.SCAVENGE),

    /**
     * The metal resource card
     */
    METAL(DrawableCard.Category.RESOURCE, "metal", GameState.SCAVENGE),

    /**
     * The plastic resource card
     */
    PLASTIC(DrawableCard.Category.RESOURCE, "plastic", GameState.SCAVENGE),

    /**
     * The spider animal card
     */
    SPIDER(DrawableCard.Category.ANIMAL, "spider", 4, 2, GameState.ENCOUNTER),

    /**
     * The spider animal card
     */
    SNAKE(DrawableCard.Category.ANIMAL, "snake", 6, 4, GameState.ENCOUNTER),

    /**
     * The tiger animal card
     */
    TIGER(DrawableCard.Category.ANIMAL, "tiger", 8, 6, GameState.ENCOUNTER),

    /**
     * The thunderstorm catastrophe card
     */
    THUNDERSTORM(DrawableCard.Category.CATASTROPHE, "thunderstorm", GameState.SCAVENGE);
    
    private final DrawableCard.Category category;
    private final String type;
    private final int dieSize;
    private final int eyeCount;
    private final GameState following;

    /**
     * Constructor that sets the cards category and the cards type
     * 
     * @param category The cards category (resource, animal or catastrophe)
     * @param type     The cards type (instance)
     * @param following The gamestate that follows after this card is drawn
     */
    private DrawableType(DrawableCard.Category category, String type, GameState following) {
        this.category = category;
        this.type = type;
        this.dieSize = 0;
        this.eyeCount = 0;
        this.following = following;
    }

    /**
     * Sets the category and type and also sets a die size and eyeCount if the card
     * is an encounter like an animal
     * 
     * @param category The cards category
     * @param type     The cards type
     * @param dieSize  The die size rolled in an encounter
     * @param eyeCount The eye count needed to survive
     * @param following The gamestate that follows after
     */
    private DrawableType(DrawableCard.Category category, String type, int dieSize, int eyeCount, GameState following) {
        this.category = category;
        this.type = type;
        this.dieSize = dieSize;
        this.eyeCount = eyeCount;
        this.following = following;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public DrawableCard.Category getCategory() {
        return this.category;
    }

    @Override
    public int getDieSize() {
        return this.dieSize;
    }

    @Override
    public int getEyeCount() {
        return this.eyeCount;
    }
    
    @Override
    public GameState getFollowingState() {
        return this.following;
    }
    
    @Override
    public String toString() {
        return category + ": " + type;
    }

}