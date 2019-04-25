package pri.ryan.bns.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import pri.ryan.bns.constant.MaterialType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Material implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private MaterialType materialType;
    @ApiModelProperty(value = "是否可以交易")
    @Column(nullable = false)
    private boolean binding;
    @ApiModelProperty(value = "点券价格")
    private Integer ticketPrice;
    private String description;
}
