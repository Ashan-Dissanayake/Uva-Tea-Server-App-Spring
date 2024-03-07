package lk.uva.uvateafactory.report.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TeaCropSummary {

    @Id
    private Integer id;
    private String area;
    private Long currenttotal;
    private Long prevtotal;
    private Long difference;
    private String status;

    public TeaCropSummary() {}

    public Long getDifference() {
        return difference;
    }

    public void setDifference(Long difference) {
        this.difference = difference;
    }

    public TeaCropSummary(String area, Long prev, Long current) {
        this.area = area;
        this.prevtotal = prev;
        this.currenttotal = current;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Long getCurrenttotal() {
        return currenttotal;
    }

    public void setCurrenttotal(Long currenttotal) {
        this.currenttotal = currenttotal;
    }

    public Long getPrevtotal() {
        return prevtotal;
    }

    public void setPrevtotal(Long prevtotal) {
        this.prevtotal = prevtotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
