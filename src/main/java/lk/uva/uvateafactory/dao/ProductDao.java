package lk.uva.uvateafactory.dao;

import lk.uva.uvateafactory.entity.Product;
import lk.uva.uvateafactory.entity.Productionproduct;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductDao extends JpaRepository<Product,Integer> {

}
