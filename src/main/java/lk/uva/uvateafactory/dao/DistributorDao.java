package lk.uva.uvateafactory.dao;

import lk.uva.uvateafactory.entity.Distributor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DistributorDao extends JpaRepository<Distributor,Integer> {

     Distributor findByEmail(String email);

     @Query("select d from Distributor d where d.id = :id")
     Distributor findByMyId(@Param("id") Integer id);

     @Query("SELECT NEW Distributor (d.id,d.name) FROM Distributor d")
     List<Distributor> findAllNameID();



}
