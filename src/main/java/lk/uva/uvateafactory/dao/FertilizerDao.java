package lk.uva.uvateafactory.dao;

import lk.uva.uvateafactory.entity.Fertilizer;
import lk.uva.uvateafactory.entity.Plucking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FertilizerDao extends JpaRepository<Fertilizer,Integer> {

    @Query("select f from Fertilizer f where f.id=:id")
    Fertilizer findByMyId(@Param("id") Integer id);


}
