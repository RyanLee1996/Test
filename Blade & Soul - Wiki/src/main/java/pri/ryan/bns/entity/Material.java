package pri.ryan.bns.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import pri.ryan.bns.constant.MaterialType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class Material implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer materialType;
    private boolean binding;
    private Integer ticketPrice;
    private String description;

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType.getKey();
    }

    public Material(String name, MaterialType materialType, boolean binding, Integer ticketPrice, String description) {
        this.name = name;
        this.materialType = materialType.getKey();
        this.binding = binding;
        this.ticketPrice = ticketPrice;
        this.description = description;
    }
}
