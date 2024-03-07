package lk.uva.uvateafactory.dao;

import lk.uva.uvateafactory.entity.Designation;
import lk.uva.uvateafactory.entity.Supplierstatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierstatusDao extends JpaRepository<Supplierstatus,Integer> {
}
