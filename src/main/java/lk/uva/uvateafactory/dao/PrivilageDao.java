package lk.uva.uvateafactory.dao;

import lk.uva.uvateafactory.entity.Privilage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PrivilageDao extends JpaRepository<Privilage,Integer> {

    Optional<Privilage> findById(Integer id);

    @Query("select e from Privilage e where e.id = :id")
    Privilage findByMyId(@Param("id") Integer id);

}
