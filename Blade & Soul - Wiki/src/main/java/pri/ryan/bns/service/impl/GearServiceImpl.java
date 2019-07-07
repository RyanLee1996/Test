package pri.ryan.bns.service.impl;

import pri.ryan.bns.dto.GearDTO;
import pri.ryan.bns.entity.Gear;
import pri.ryan.bns.service.GearService;

import javax.validation.constraints.NotNull;
import java.util.List;

public class GearServiceImpl implements GearService {

    @Override
    public Gear save(@NotNull GearDTO gear) {

        return null;
    }

    @Override
    public void removeById(@NotNull Long id) {

    }

    @Override
    public Gear update(@NotNull GearDTO gear) {
        return null;
    }

    @Override
    public List<GearDTO> listByName(String Name) {
        return null;
    }
}
