package cardgame.card;

/**
 * Enum that implements all buildable cards
 * 
 * @author Florian Heck
 * @version 0.9
 */
public enum BuildingType implements BuildingCard {

    /**
     * The axe card
     */
    AXE("axe", 2, new DrawableType[] {DrawableType.METAL, DrawableType.METAL, DrawableType.METAL}),

    /**
     * The ballon card
     */
    BALLON("ballon",
        new DrawableType[] {DrawableType.WOOD, DrawableType.PLASTIC, DrawableType.PLASTIC, DrawableType.PLASTIC,
            DrawableType.PLASTIC, DrawableType.PLASTIC, DrawableType.PLASTIC}),

    /**
     * The club card
     */
    CLUB("club", 1, new DrawableType[] {DrawableType.WOOD, DrawableType.WOOD, DrawableType.WOOD}),

    /**
     * The fireplace card
     */
    FIREPLACE("fireplace",
        new DrawableType[] {DrawableType.WOOD, DrawableType.WOOD, DrawableType.WOOD, DrawableType.METAL}),

    /**
     * The hangglider card
     */
    HANGGLIDER("hangglider",
        new DrawableType[] {DrawableType.WOOD, DrawableType.WOOD, DrawableType.METAL, DrawableType.METAL,
            DrawableType.PLASTIC, DrawableType.PLASTIC, DrawableType.PLASTIC, DrawableType.PLASTIC}),

    /**
     * The sailingraft card
     */
    SAILINGRAFT("sailingraft",
        new DrawableType[] {DrawableType.WOOD, DrawableType.WOOD, DrawableType.WOOD, DrawableType.WOOD,
            DrawableType.METAL, DrawableType.METAL, DrawableType.PLASTIC, DrawableType.PLASTIC}),

    /**
     * The shack card
     */
    SHACK("shack",
        new DrawableType[] {DrawableType.WOOD, DrawableType.WOOD, DrawableType.METAL, DrawableType.PLASTIC,
            DrawableType.PLASTIC }),

    /**
     * The steamboat card
     */
    STEAMBOAT("steamboat", new DrawableType[] {DrawableType.METAL, DrawableType.METAL, DrawableType.METAL,
        DrawableType.METAL, DrawableType.METAL, DrawableType.METAL, DrawableType.PLASTIC});

    private final DrawableCard[] cost;
    private final String type;
    private final int bonus;

    /**
     * Constructor that sets the cards type string and an array containing all cards
     * needed to build this card
     * 
     * @param type The cards type
     * @param cost An array of cards needed to build this card
     */
    private BuildingType(String type, int bonus, DrawableCard[] cost) {
        this.cost = cost;
        this.type = type;
        this.bonus = bonus;
    }

    /**
     * Constructor for cards without attack bonus
     * 
     * @param type The cards type
     * @param cost The cards cost
     */
    private BuildingType(String type, DrawableCard[] cost) {
        this.cost = cost;
        this.type = type;
        this.bonus = 0;
    }

    @Override
    public int getBonus() {
        return bonus;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public DrawableCard[] getCost() {
        return this.cost;
    }

    @Override
    public boolean isShack() {
        if (this == BuildingType.SHACK) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isWinningCard() {
        if (this == BuildingType.STEAMBOAT || this == BuildingType.BALLON) {
            return true;
        }
        return false;
    }

    @Override
    public boolean triggersEndeavor() {
        if (this == BuildingType.HANGGLIDER || this == BuildingType.SAILINGRAFT) {
            return true;
        }
        return false;
    }

}
