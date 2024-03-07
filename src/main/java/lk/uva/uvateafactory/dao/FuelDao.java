package lk.uva.uvateafactory.dao;

import lk.uva.uvateafactory.entity.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FuelDao extends JpaRepository<Fuel,Integer> {

    @Query("select f from Fuel f where f.id = :id")
    Fuel findByMyId(@Param("id") Integer id);


}
