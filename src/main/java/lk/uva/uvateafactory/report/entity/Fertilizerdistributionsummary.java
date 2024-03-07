package lk.uva.uvateafactory.report.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Fertilizerdistributionsummary {

    private Integer id;
    private String area;
    private BigDecimal prevyear;
    private BigDecimal currentyear;

    public Fertilizerdistributionsummary() {  }

    public Fertilizerdistributionsummary(String area, BigDecimal prevyear, BigDecimal currentyear) {
        this.area = area;
        this.prevyear = prevyear;
        this.currentyear = currentyear;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public BigDecimal getPrevyear() {
        return prevyear;
    }

    public void setPrevyear(BigDecimal prevyear) {
        this.prevyear = prevyear;
    }

    public BigDecimal getCurrentyear() {
        return currentyear;
    }

    public void setCurrentyear(BigDecimal currentyear) {
        this.currentyear = currentyear;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    public Integer getId() {
        return id;
    }

}
