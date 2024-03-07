package lk.uva.uvateafactory.report.dao;

import lk.uva.uvateafactory.report.entity.AreaTeaQuantity;
import lk.uva.uvateafactory.report.entity.CountByArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface AreaTeaQuantityDao extends JpaRepository<AreaTeaQuantity,Integer> {

    @Query(value = " SELECT NEW AreaTeaQuantity( a.code,p.date,SUM(p.qty) ) FROM Plucking p,Area a WHERE a.id = p.area.id AND p.date=:passingdate GROUP BY a.code ")
    List<AreaTeaQuantity> teaQuantity(@Param("passingdate") Date passingdate);

}
