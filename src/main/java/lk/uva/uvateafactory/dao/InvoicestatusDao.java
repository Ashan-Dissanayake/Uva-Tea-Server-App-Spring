package lk.uva.uvateafactory.dao;

import lk.uva.uvateafactory.entity.Invoicestatus;
import lk.uva.uvateafactory.entity.Orderstatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoicestatusDao extends JpaRepository<Invoicestatus,Integer> {
}
