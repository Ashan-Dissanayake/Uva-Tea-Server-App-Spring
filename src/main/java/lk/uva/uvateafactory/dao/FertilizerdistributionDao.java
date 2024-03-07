package lk.uva.uvateafactory.dao;

import lk.uva.uvateafactory.entity.Employee;
import lk.uva.uvateafactory.entity.Fertilizerdistribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FertilizerdistributionDao extends JpaRepository<Fertilizerdistribution,Integer> {

    @Query("select ftd from Fertilizerdistribution ftd where ftd.id = :id")
    Fertilizerdistribution findByMyId(@Param("id") Integer id);
}
