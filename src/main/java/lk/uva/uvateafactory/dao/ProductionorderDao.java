package lk.uva.uvateafactory.dao;

import lk.uva.uvateafactory.entity.Productionorder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductionorderDao extends JpaRepository<Productionorder,Integer> {

     @Query("select proor from Productionorder proor where proor.id = :id")
     Productionorder findByMyId(@Param("id") Integer id);

}
