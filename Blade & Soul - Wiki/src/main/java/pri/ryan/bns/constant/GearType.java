package pri.ryan.bns.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GearType {
    WEAPON(1,"武器");
    private final Integer key;
    private final String value;
}
