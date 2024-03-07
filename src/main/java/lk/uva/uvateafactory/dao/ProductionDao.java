package lk.uva.uvateafactory.dao;

import lk.uva.uvateafactory.entity.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ProductionDao extends JpaRepository<Production,Integer> {

     @Query("select p from Production p where p.id = :id")
     Production findByMyId(@Param("id") Integer id);

}
