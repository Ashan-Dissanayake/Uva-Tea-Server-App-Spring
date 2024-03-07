package lk.uva.uvateafactory.dao;

import lk.uva.uvateafactory.entity.Fuel;
import lk.uva.uvateafactory.entity.Fueltype;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FueltypeDao extends JpaRepository<Fueltype,Integer> {



}
