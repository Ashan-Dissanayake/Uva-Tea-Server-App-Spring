package lk.uva.uvateafactory.dao;

import lk.uva.uvateafactory.entity.Porder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PorderDao extends JpaRepository<Porder,Integer> {

    @Query("select por from Porder por where por.id = :id")
    Porder findByMyId(@Param("id") Integer id);
}
