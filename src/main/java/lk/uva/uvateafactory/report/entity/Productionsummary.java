package lk.uva.uvateafactory.report.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Productionsummary {

    @Id
    private Integer id;
    private Date date;
    private String productname;
    private Long total;

    public Productionsummary() {}

    public Productionsummary(Date date, String productname, Long total) {
        this.date = date;
        this.productname = productname;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
