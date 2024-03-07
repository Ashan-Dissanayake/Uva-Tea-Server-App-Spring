package lk.uva.uvateafactory.dao;

import lk.uva.uvateafactory.entity.Vehiclebrand;
import lk.uva.uvateafactory.entity.Vehiclemodel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VehiclemodelDao extends JpaRepository<Vehiclemodel,Integer> {

    @Query("select vm from Vehiclemodel vm where vm.vehiclebrand.id=:id")
    List<Vehiclemodel> findByGivenId(@Param("id") Integer id);

}
