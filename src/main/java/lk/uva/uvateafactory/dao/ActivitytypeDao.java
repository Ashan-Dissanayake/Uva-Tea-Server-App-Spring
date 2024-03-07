package lk.uva.uvateafactory.dao;

import lk.uva.uvateafactory.entity.Activitytype;
import lk.uva.uvateafactory.entity.Designation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivitytypeDao extends JpaRepository<Activitytype,Integer> {
}
