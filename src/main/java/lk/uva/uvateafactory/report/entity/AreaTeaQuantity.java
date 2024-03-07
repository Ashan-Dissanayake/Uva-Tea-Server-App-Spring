package lk.uva.uvateafactory.report.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class AreaTeaQuantity {

    private Integer id;
    private String code;
    private java.util.Date date;
    private Long teaTotal;
    private double percentage;

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public AreaTeaQuantity() {  }

    public AreaTeaQuantity(String code, Date date, Long teaTotal) {
        this.code = code;
        this.date = date;
        this.teaTotal = teaTotal;
    }

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getTeaTotal() {
        return teaTotal;
    }

    public void setTeaTotal(Long teaTotal) {
        this.teaTotal = teaTotal;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
