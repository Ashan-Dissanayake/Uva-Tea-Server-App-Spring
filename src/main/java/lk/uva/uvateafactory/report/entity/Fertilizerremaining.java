package lk.uva.uvateafactory.report.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Fertilizerremaining {

    private Integer id;
    private String area;
    private BigDecimal total;


    public Fertilizerremaining(Integer id,String area, BigDecimal total) {
        this.id = id;
        this.area = area;
        this.total = total;
    }

    public Fertilizerremaining() {

    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    public Integer getId() {
        return id;
    }

}
