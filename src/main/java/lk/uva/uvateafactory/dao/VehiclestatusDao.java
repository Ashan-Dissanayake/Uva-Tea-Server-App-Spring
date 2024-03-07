package lk.uva.uvateafactory.dao;

import lk.uva.uvateafactory.entity.Vehicle;
import lk.uva.uvateafactory.entity.Vehiclestatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiclestatusDao extends JpaRepository<Vehiclestatus,Integer> {

}
