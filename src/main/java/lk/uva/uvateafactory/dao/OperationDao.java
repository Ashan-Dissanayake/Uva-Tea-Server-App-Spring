package lk.uva.uvateafactory.dao;

import lk.uva.uvateafactory.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationDao extends JpaRepository<Operation,Integer> {
}
