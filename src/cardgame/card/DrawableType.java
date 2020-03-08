package cardgame.card;

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
    WOOD("resource", "wood"),

    /**
     * The metal resource card
     */
    METAL("resource", "metal"),

    /**
     * The plastic resource card
     */
    PLASTIC("resource", "plastic"),

    /**
     * The spider animal card
     */
    SPIDER("animal", "spider", 4, 2),

    /**
     * The spider animal card
     */
    SNAKE("animal", "snake", 6, 4),

    /**
     * The tiger animal card
     */
    TIGER("animal", "tiger", 8, 6),

    /**
     * The thunderstorm catastrophe card
     */
    THUNDERSTORM("catastrophe", "thunderstorm");

    private final String category;
    private final String type;
    private final int dieSize;
    private final int eyeCount;

    /**
     * Constructor that sets the cards category and the cards type
     * 
     * @param category The cards category (resource, animal or catastrophe)
     * @param type     The cards type (instance)
     */
    private DrawableType(String category, String type) {
        this.category = category;
        this.type = type;
        this.dieSize = 0;
        this.eyeCount = 0;
    }

    /**
     * Sets the category and type and also sets a die size and eyeCount if the card
     * is an encounter like an animal
     * 
     * @param category The cards category
     * @param type     The cards type
     * @param dieSize  The die size rolled in an encounter
     * @param eyeCount The eye count needed to survive
     */
    private DrawableType(String category, String type, int dieSize, int eyeCount) {
        this.category = category;
        this.type = type;
        this.dieSize = dieSize;
        this.eyeCount = eyeCount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCategory() {
        return this.category;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDieSize() {
        return dieSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getEyeCount() {
        return eyeCount;
    }
    
    @Override
    public String toString() {
        return category + ": " + type;
    }

}
