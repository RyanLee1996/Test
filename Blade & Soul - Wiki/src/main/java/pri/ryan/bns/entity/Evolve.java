package pri.ryan.bns.entity;

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
public class Evolve implements Serializable {
    private static final long serialVersionUID = 4L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long currentGearId;
    @Enumerated(EnumType.ORDINAL)
    private EvolveType evolveType;
    private Long offeringGearId;
    private Long goalGearId;
    private Integer money;
    private Date updateTime;
}
