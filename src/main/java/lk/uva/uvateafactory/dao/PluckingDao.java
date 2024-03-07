package lk.uva.uvateafactory.dao;

import lk.uva.uvateafactory.entity.Plucking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;

public interface PluckingDao extends JpaRepository<Plucking,Integer> {

    @Query("select p from Plucking p where p.id = :id")
    Plucking findByMyId(@Param("id") Integer id);

//    @Query(value = "SELECT NEW Plucking (p.empplucker,cast(SUM(p.bonus) as integer ) ) from Plucking p where p.empplucker.id=:id and Date(p.date) between '2023-06-10' and '2023-07-10'")
//    Plucking getPluckerPaymntBonus(@Param("id") Integer pluckerId);

    @Query(value = "SELECT NEW Plucking (p.empplucker,cast(SUM(p.bonus) as integer ) ) from Plucking p where p.empplucker.id=:id and Date(p.date) between :dostart and :doend")
    Plucking getPluckerPaymntBonus(@Param("id") Integer pluckerId, @Param("dostart") Date dostart,  @Param("doend") Date doend);

}
