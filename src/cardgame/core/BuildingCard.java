package cardgame.core;

import java.util.Map;

public interface BuildingCard {

    public BuildingType getType();
    
    public Map<CardType, Integer> cost();
    
}
