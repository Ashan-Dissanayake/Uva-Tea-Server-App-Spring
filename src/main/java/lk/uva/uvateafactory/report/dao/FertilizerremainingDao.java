package lk.uva.uvateafactory.report.dao;

import lk.uva.uvateafactory.report.entity.Fertilizerremaining;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FertilizerremainingDao extends JpaRepository<Fertilizerremaining,Integer> {

    @Query(value = "SELECT NEW Fertilizerremaining(f.id,f.name,SUM(f.quantity)) FROM Fertilizer f GROUP BY f.name")
    List<Fertilizerremaining> remainFertilizer();
}
