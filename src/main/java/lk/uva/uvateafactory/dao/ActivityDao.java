package lk.uva.uvateafactory.dao;

import lk.uva.uvateafactory.entity.Activity;
import lk.uva.uvateafactory.entity.Activitytype;
import lk.uva.uvateafactory.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActivityDao extends JpaRepository<Activity,Integer> {

    @Query("select ac from Activity ac where ac.id = :id")
    Activity findByMyId(@Param("id") Integer id);

    @Query(value = "SELECT a FROM Activity a WHERE DATE(a.date)>=CURDATE() order by a.date")
    List<Activity> upComingEvents();
}
