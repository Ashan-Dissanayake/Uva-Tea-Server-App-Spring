package lk.uva.uvateafactory.report.dao;


import lk.uva.uvateafactory.report.entity.Productionsummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductionsummaryDao extends JpaRepository<Productionsummary,Integer> {

//    @Query(value = "SELECT NEW Productionsummary (a.code,SUM(CASE WHEN YEAR(fd.date) LIKE :date2 THEN fd.quantitydis ELSE 0 END) ) FROM Fertilizerdistribution fd inner join Area a on a.id=fd.area.id WHERE YEAR(fd.date) =:date1 OR YEAR(fd.date) =:date2 GROUP BY fd.area.id")
//    List<Productionsummary> getproductionsummary(@Param("date1") Integer date1, @Param("date2") Integer date2);

//    @Query(value = "SELECT NEW Productionsummary (p.date,pro.name,cast(SUM(pp.quantity) as integer ) ) FROM Productionproduct pp inner join Production p on pp.production.id=p.id inner join Product pro on pp.product.id = pro.id  group by pp.product.id")
//    List<Productionsummary> getproductionsummary();


}
