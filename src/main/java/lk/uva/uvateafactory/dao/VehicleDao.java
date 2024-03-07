package lk.uva.uvateafactory.dao;

import lk.uva.uvateafactory.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VehicleDao extends JpaRepository<Vehicle,Integer> {

    Vehicle findByNumber(String number);

     @Query("select v from Vehicle v where v.id = :id")
     Vehicle findByMyId(@Param("id") Integer id);


}
