package cardgame.core;

public enum CardType implements DrawableCard {

    WOOD("resource", "wood"),
    
    METAL("resource", "metal"),
    
    PLASTIC("resource", "plastic"),
    
    SPIDER("animal", "spider"),
    
    SNAKE("animal", "snake"),
    
    TIGER("animal", "tiger"),
    
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
