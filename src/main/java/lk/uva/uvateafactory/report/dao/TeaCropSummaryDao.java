package lk.uva.uvateafactory.report.dao;

import lk.uva.uvateafactory.report.entity.TeaCropSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface TeaCropSummaryDao extends JpaRepository<lk.uva.uvateafactory.report.entity.TeaCropSummary,Integer> {

    @Query(value = "SELECT NEW TeaCropSummary( a.code,SUM(CASE WHEN p.date=:passingdate1 THEN p.qty ELSE 0 END),SUM(CASE WHEN p.date=:passingdate2 THEN p.qty ELSE 0 END)  )  FROM Plucking p inner join Area a on p.area.id=a.id where p.date=:passingdate1 or p.date=:passingdate2 group by p.area.id")
    List<TeaCropSummary> teacropsummary(@Param("passingdate1") Date passingdate1,@Param("passingdate2") Date passingdate2);
}
