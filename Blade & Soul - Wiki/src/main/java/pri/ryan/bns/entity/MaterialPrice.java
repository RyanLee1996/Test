package pri.ryan.bns.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.sql.Time;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "材料价格实体类")
public class MaterialPrice implements Serializable {
    private static final long serialVersionUID = 2L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long materialId;
    @ApiModelProperty(value = "铜币价格")
    @Column(nullable = false)
    private Integer price;
    @Column(nullable = false)
    private Time createTime;
}
