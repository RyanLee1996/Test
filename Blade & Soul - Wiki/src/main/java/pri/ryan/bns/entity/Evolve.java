package pri.ryan.bns.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import pri.ryan.bns.constant.EvolveType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "升级路线实体类")
public class Evolve implements Serializable {
    private static final long serialVersionUID = 4L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long currentGearId;
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private EvolveType evolveType;
    @ApiModelProperty(value = "祭品装备编号")
    private Long offeringGearId;
    @ApiModelProperty(value = "目标装备编号")
    @Column(nullable = false)
    private Long goalGearId;
    @ApiModelProperty(value = "所需铜币")
    @Column(nullable = false)
    private Integer money;
    @Column(nullable = false)
    private Date updateTime;
}
