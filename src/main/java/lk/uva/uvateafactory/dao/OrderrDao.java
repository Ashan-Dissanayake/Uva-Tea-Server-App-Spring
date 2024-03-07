package lk.uva.uvateafactory.dao;

import lk.uva.uvateafactory.entity.Orderr;
import lk.uva.uvateafactory.entity.Porder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderrDao extends JpaRepository<Orderr,Integer> {

    @Query("select ord from Orderr ord where ord.id = :id")
    Orderr findByMyId(@Param("id") Integer id);
}
