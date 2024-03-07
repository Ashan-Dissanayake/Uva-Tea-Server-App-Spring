package lk.uva.uvateafactory.dao;

import lk.uva.uvateafactory.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ModuleDao extends JpaRepository<Module,Integer> {
}
