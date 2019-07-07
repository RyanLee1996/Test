package pri.ryan.bns.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class GearDTO {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Integer gearTypeEnum;
    @NotNull
    private boolean seal;
    @NotNull
    private LocalDateTime updateTime;
}
