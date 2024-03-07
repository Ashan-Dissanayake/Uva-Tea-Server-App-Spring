package lk.uva.uvateafactory.report.dao;

import lk.uva.uvateafactory.report.entity.Fertilizerdistributionsummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface FertilizerdistributionsummaryDao extends JpaRepository<Fertilizerdistributionsummary,Integer> {

//    @Query(value = "SELECT NEW Fertilizerdistributionsummary(a.code,SUM(CASE when fed.date'like'+pastdate then fed.quantitydis else 0 end ),SUM(CASE when fed.date like presentdate% then fed.quantitydis else 0 end ) ) FROM Area a inner join Fertilizerdistribution fed on a.id=fed.area.id WHERE fed.date like pastdate% or fed.date like presentdate% group by fed.area.id")
//    List<FertilizerdistributionsummaryDao> fertilizerdistributionsummary(@Param("pastdate") Date pastdate,@Param("presentdate") Date presentdate);

//    @Query(value = "SELECT fd.areaId, fd.quantitydis, fd.date, SUM(fd.quantitydis) as total, SUM(CASE WHEN fd.date LIKE '2022%' THEN fd.quantitydis ELSE 0 END) as prevyear, SUM(CASE WHEN fd.date LIKE '2023%' THEN fd.quantitydis ELSE 0 END) as currentyear FROM Fertilizerdistribution fd WHERE (fd.date LIKE '2022%' OR fd.date LIKE '2023%') GROUP BY fd.area.id")
//    List<FertilizerdistributionsummaryDao> fertilizerdistributionsummary(@Param("pastdate") Date pastdate,@Param("presentdate") Date presentdate);

//    @Query(value = "SELECT a.code,SUM(CASE WHEN fd.date LIKE '2022%' THEN fd.quantitydis ELSE 0 END),SUM(CASE WHEN fd.date LIKE '2023%' THEN fd.quantitydis ELSE 0 END) as currentyear FROM Fertilizerdistribution fd inner join Area a on a.id=fd.area.id WHERE (fd.date LIKE '2022%' OR fd.date LIKE '2023%') GROUP BY fd.area.id")
//    List<FertilizerdistributionsummaryDao> fertilizerdistributionsummary(@Param("pastdate") Date pastdate,@Param("presentdate") Date presentdate);

//    @Query(value = "SELECT NEW Fertilizerdistributionsummary(a.code,SUM(CASE WHEN YEAR(fd.date) LIKE '2022%' THEN fd.quantitydis ELSE 0 END),SUM(CASE WHEN YEAR(fd.date) LIKE '2023%' THEN fd.quantitydis ELSE 0 END)) FROM Fertilizerdistribution fd inner join Area a on a.id=fd.area.id WHERE YEAR(fd.date) = '2022' OR YEAR(fd.date) = '2023' GROUP BY fd.area.id")
//    List<Fertilizerdistributionsummary> fertilizerdistributionsummary(@Param("date1") String date1, @Param("date2") String date2);

//    @Query(value = "SELECT NEW Fertilizerdistributionsummary(a.code,SUM(CASE WHEN YEAR(fd.date) LIKE :date1 THEN fd.quantitydis ELSE 0 END),SUM(CASE WHEN YEAR(fd.date) LIKE :date2 THEN fd.quantitydis ELSE 0 END)) FROM Fertilizerdistribution fd inner join Area a on a.id=fd.area.id WHERE YEAR(fd.date) LIKE :date1 OR YEAR(fd.date) LIKE :date2 GROUP BY fd.area.id")
//    List<Fertilizerdistributionsummary> fertilizerdistributionsummary(@Param("date1") Date date1, @Param("date2") Date date2);

    @Query(value = "SELECT NEW Fertilizerdistributionsummary(a.code,SUM(CASE WHEN YEAR(fd.date) LIKE :date1 THEN fd.quantitydis ELSE 0 END),SUM(CASE WHEN YEAR(fd.date) LIKE :date2 THEN fd.quantitydis ELSE 0 END)) FROM Fertilizerdistribution fd inner join Area a on a.id=fd.area.id WHERE YEAR(fd.date) =:date1 OR YEAR(fd.date) =:date2 GROUP BY fd.area.id")
    List<Fertilizerdistributionsummary> getfertilizerdistributionsummary(@Param("date1") Integer date1, @Param("date2") Integer date2);


}
