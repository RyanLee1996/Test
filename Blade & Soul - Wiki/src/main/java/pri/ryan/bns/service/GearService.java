package pri.ryan.bns.service;

import pri.ryan.bns.dto.GearDTO;
import pri.ryan.bns.entity.Gear;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface GearService {
    Gear save(@NotNull GearDTO gear);
    void removeById(@NotNull Long id);
    Gear update(@NotNull GearDTO gear);
    List<GearDTO> listByName(String Name);
}
