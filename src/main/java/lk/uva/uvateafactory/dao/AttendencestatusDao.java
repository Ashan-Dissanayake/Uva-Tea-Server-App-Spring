package lk.uva.uvateafactory.dao;

import lk.uva.uvateafactory.entity.Attendence;
import lk.uva.uvateafactory.entity.Attendstatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendencestatusDao extends JpaRepository<Attendstatus,Integer> {


}
