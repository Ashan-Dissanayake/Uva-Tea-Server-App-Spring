package lk.uva.uvateafactory.dao;

import lk.uva.uvateafactory.entity.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransportDao extends JpaRepository<Transport,Integer> {

    @Query("select t from Transport t where t.id = :id")
    Transport findByMyId(@Param("id") Integer id);


}
