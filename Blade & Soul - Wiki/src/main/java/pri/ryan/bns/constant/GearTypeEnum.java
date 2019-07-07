package pri.ryan.bns.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GearTypeEnum {
    WEAPON(1,"武器"),
    ENERGY_STONE(2,"元气石"),
    POWER_STONE(3,"真气石"),
    NECKLACE(4,"项链"),
    EARRING(5,"耳环"),
    RING(6,"戒指"),
    BRACELET(7,"手镯"),
    BELT(8,"腰带"),
    GLOVE(9,"手套"),
    SOUL_STONE(10,"魂"),
    MIND_STONE(11,"灵"),
    GUARD_STONE(12,"守护石"),
    STAR_STONE(13,"星");
    private final Integer key;
    private final String value;
}
