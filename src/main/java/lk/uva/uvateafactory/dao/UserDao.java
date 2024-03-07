package lk.uva.uvateafactory.dao;


import lk.uva.uvateafactory.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<User,Integer> {
    User findByUsername(String username);
}
