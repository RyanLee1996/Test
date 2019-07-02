package pri.ryan.bns.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pri.ryan.bns.entity.Gear;

public interface GearRepository extends JpaRepository<Gear,Long> {
}
