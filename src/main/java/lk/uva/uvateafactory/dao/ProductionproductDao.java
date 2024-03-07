package lk.uva.uvateafactory.dao;

import lk.uva.uvateafactory.entity.Production;
import lk.uva.uvateafactory.entity.Productionproduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ProductionproductDao extends JpaRepository<Productionproduct,Integer> {

}
