package cardgame.core;

/**
 * Enumeration that contains all possible drawable cards
 * 
 * @author Florian Heck
 * @version 1.0
 */
public enum CardType implements DrawableCard {

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
    SPIDER("animal", "spider"),

    /**
     * The spider animal card
     */
    SNAKE("animal", "snake"),

    /**
     * The tiger animal card
     */
    TIGER("animal", "tiger"),

    /**
     * The thunderstorm catastrophe card
     */
    THUNDERSTORM("catastrophe", "thunderstorm");

    private String group;
    private String type;

    private CardType(String group, String type) {
        this.group = group;
        this.type = type;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getGroup() {
        return this.group;
    }

    @Override
    public String toString() {
        return group + ": " + type;
    }

}
