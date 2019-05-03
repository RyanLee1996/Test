package pri.ryan.bns.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import pri.ryan.bns.constant.GearType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "装备实体类")
public class Gear implements Serializable {
    private static final long serialVersionUID = 3L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private GearType gearType;
    @ApiModelProperty(value = "是否可以封印")
    @Column(nullable = false)
    private boolean seal;
    @Column(nullable = false)
    private Date updateTime;
}
