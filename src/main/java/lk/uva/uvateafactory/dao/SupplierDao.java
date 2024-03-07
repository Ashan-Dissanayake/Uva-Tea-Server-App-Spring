package lk.uva.uvateafactory.dao;

import lk.uva.uvateafactory.entity.Employee;
import lk.uva.uvateafactory.entity.Supplier;
import lk.uva.uvateafactory.entity.Supplierstatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SupplierDao extends JpaRepository<Supplier,Integer> {

    Supplier findByName(String name);

    @Query("select e from Supplier e where e.code = :code")
    Supplier findByCode(@Param("code")String code);

    @Query("select s from Supplier s where s.id = :id")
    Supplier findByMyId(@Param("id") Integer id);


}
