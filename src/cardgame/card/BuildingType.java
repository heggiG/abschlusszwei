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
    AXE("axe", new DrawableType[] { DrawableType.METAL, DrawableType.METAL, DrawableType.METAL }),

    /**
     * The club card
     */
    CLUB("club", new DrawableType[] { DrawableType.WOOD, DrawableType.WOOD, DrawableType.WOOD }),

    /**
     * The shack card
     */
    SHACK("shack", new DrawableType[] { DrawableType.WOOD, DrawableType.WOOD, DrawableType.METAL, DrawableType.PLASTIC, DrawableType.PLASTIC }),

    /**
     * The fireplace card
     */
    FIREPLACE("fireplace", new DrawableType[] { DrawableType.WOOD, DrawableType.WOOD, DrawableType.WOOD, DrawableType.METAL }),

    /**
     * The sailingraft card
     */
    SAILINGRAFT("sailingraft",
            new DrawableType[] { DrawableType.WOOD, DrawableType.WOOD, DrawableType.WOOD, DrawableType.WOOD, DrawableType.METAL, DrawableType.METAL,
                    DrawableType.PLASTIC, DrawableType.PLASTIC }),

    /**
     * The hangglider card
     */
    HANGGLIDER("hangglider",
            new DrawableType[] { DrawableType.WOOD, DrawableType.WOOD, DrawableType.METAL, DrawableType.METAL, DrawableType.PLASTIC,
                    DrawableType.PLASTIC, DrawableType.PLASTIC, DrawableType.PLASTIC }),

    /**
     * The steamboat card
     */
    STEAMBOAT("steamboat",
            new DrawableType[] { DrawableType.METAL, DrawableType.METAL, DrawableType.METAL, DrawableType.METAL, DrawableType.METAL,
                    DrawableType.METAL, DrawableType.PLASTIC }),

    /**
     * The ballon card
     */
    BALLON("ballon", new DrawableType[] { DrawableType.METAL, DrawableType.PLASTIC, DrawableType.PLASTIC, DrawableType.PLASTIC,
            DrawableType.PLASTIC, DrawableType.PLASTIC, DrawableType.PLASTIC });

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

    private BuildingType(String type, DrawableCard[] cost) {
        this.cost = cost;
        this.type = type;
        this.bonus = 0;
    }
    
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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(type);
        sb.append(": ");
        for (int i = 0; i < type.length(); i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(cost[i].getType());
        }
        return sb.toString();
    }
}
