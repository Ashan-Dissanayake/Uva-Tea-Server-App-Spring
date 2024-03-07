package lk.uva.uvateafactory.dao;

import lk.uva.uvateafactory.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InvoiceDao extends JpaRepository<Invoice,Integer> {

    @Query("select invoi from Invoice invoi where invoi.id = :id")
    Invoice findByMyId(@Param("id") Integer id);
}
