package lk.uva.uvateafactory.dao;

import lk.uva.uvateafactory.entity.Invoiceproduct;
import lk.uva.uvateafactory.entity.Orderrproduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceproductDao extends JpaRepository<Invoiceproduct,Integer> {

}
