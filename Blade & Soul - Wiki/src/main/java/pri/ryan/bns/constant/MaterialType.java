package pri.ryan.bns.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MaterialType {
    MYSTERIOUS_OBJECT(1,"神物"),
    MATERIAL_PIECES(2,"碎片");
    private final Integer key;
    private final String value;
}
