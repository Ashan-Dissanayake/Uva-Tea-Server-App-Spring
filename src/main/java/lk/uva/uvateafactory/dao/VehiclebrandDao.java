package lk.uva.uvateafactory.dao;

import lk.uva.uvateafactory.entity.Vehiclebrand;
import lk.uva.uvateafactory.entity.Vehicletype;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiclebrandDao extends JpaRepository<Vehiclebrand,Integer> {

}
