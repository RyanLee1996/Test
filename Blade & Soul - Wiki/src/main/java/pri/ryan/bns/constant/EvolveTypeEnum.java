package pri.ryan.bns.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EvolveTypeEnum {
    CHIEF(1,"确定成长"),
    NORMAL(2,"概率成长"),
    SEAL(3,"概率厄运");
    private final Integer key;
    private final String value;
}
