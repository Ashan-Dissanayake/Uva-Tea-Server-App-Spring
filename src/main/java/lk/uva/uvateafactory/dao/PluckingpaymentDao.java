package lk.uva.uvateafactory.dao;

import lk.uva.uvateafactory.entity.Pluckingpayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PluckingpaymentDao extends JpaRepository<Pluckingpayment,Integer> {

     @Query("select pp from Pluckingpayment pp where pp.id = :id")
     Pluckingpayment findByMyId(@Param("id") Integer id);




}
