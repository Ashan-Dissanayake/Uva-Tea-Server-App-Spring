package lk.uva.uvateafactory.dao;

import lk.uva.uvateafactory.entity.Porder;
import lk.uva.uvateafactory.entity.Porderfertilizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PorderfertilizerDao extends JpaRepository<Porderfertilizer,Integer> {

}
