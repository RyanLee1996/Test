package pri.ryan.bns.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import pri.ryan.bns.constant.EvolveTypeEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "升级路线实体类")
public class Evolve implements Serializable {

    private static final long serialVersionUID = 2212002470691913050L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long currentGearId;
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private EvolveTypeEnum evolveTypeEnum;
    @ApiModelProperty(value = "祭品装备编号")
    private Long offeringGearId;
    @ApiModelProperty(value = "目标装备编号")
    @Column(nullable = false)
    private Long goalGearId;
    @ApiModelProperty(value = "所需铜币")
    @Column(nullable = false)
    private Integer fee;
    @Column(nullable = false)
    private LocalDateTime creatTime;
    @Column(nullable = false)
    private LocalDateTime updateTime;
}
