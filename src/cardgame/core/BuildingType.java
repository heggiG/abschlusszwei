package cardgame.core;

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
    AXE("axe", new CardType[] { CardType.METAL, CardType.METAL, CardType.METAL }),

    /**
     * The club card
     */
    CLUB("club", new CardType[] { CardType.WOOD, CardType.WOOD, CardType.WOOD }),

    /**
     * The shack card
     */
    SHACK("shack", new CardType[] { CardType.WOOD, CardType.WOOD, CardType.METAL, CardType.PLASTIC, CardType.PLASTIC }),

    /**
     * The fireplace card
     */
    FIREPLACE("fireplace", new CardType[] { CardType.WOOD, CardType.WOOD, CardType.WOOD, CardType.METAL }),

    /**
     * The sailingraft card
     */
    SAILINGRAFT("sailingraft",
            new CardType[] { CardType.WOOD, CardType.WOOD, CardType.WOOD, CardType.WOOD, CardType.METAL, CardType.METAL,
                    CardType.PLASTIC, CardType.PLASTIC }),

    /**
     * The hangglider card
     */
    HANGGLIDER("hangglider",
            new CardType[] { CardType.WOOD, CardType.WOOD, CardType.METAL, CardType.METAL, CardType.PLASTIC,
                    CardType.PLASTIC, CardType.PLASTIC, CardType.PLASTIC }),

    /**
     * The steamboat card
     */
    STEAMBOAT("steamboat",
            new CardType[] { CardType.METAL, CardType.METAL, CardType.METAL, CardType.METAL, CardType.METAL,
                    CardType.METAL, CardType.PLASTIC }),

    /**
     * The ballon card
     */
    BALLON("ballon", new CardType[] { CardType.METAL, CardType.PLASTIC, CardType.PLASTIC, CardType.PLASTIC,
            CardType.PLASTIC, CardType.PLASTIC, CardType.PLASTIC });

    private final DrawableCard[] cost;
    private final String type;

    /**
     * Constructor that sets the cards type string and an array containing all cards
     * needed to build this card
     * 
     * @param type The cards type
     * @param cost An array of cards needed to build this card
     */
    private BuildingType(String type, DrawableCard[] cost) {
        this.cost = cost;
        this.type = type;
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
