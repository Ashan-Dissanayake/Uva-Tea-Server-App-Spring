package lk.uva.uvateafactory.dao;

import lk.uva.uvateafactory.entity.Orderr;
import lk.uva.uvateafactory.entity.Orderstatus;
import lk.uva.uvateafactory.entity.Porderstatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderrstatusDao extends JpaRepository<Orderstatus,Integer> {
}
