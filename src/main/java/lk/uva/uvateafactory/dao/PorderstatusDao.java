package lk.uva.uvateafactory.dao;

import lk.uva.uvateafactory.entity.Gender;
import lk.uva.uvateafactory.entity.Porderstatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PorderstatusDao extends JpaRepository<Porderstatus,Integer> {
}
