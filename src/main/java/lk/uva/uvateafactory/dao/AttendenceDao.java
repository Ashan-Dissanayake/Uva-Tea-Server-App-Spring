package lk.uva.uvateafactory.dao;

import lk.uva.uvateafactory.entity.Attendence;
import lk.uva.uvateafactory.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttendenceDao extends JpaRepository<Attendence,Integer> {

     @Query("select at from Attendence at where at.id = :id")
     Attendence findByMyId(@Param("id") Integer id);

}
