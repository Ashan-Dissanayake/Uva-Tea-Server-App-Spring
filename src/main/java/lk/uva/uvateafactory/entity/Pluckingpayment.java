package lk.uva.uvateafactory.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Pluckingpayment {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "startdate")
    private Date startdate;
    @Basic
    @Column(name = "enddate")
    private Date enddate;
    @Basic
    @Column(name = "dopayment")
    private Date dopayment;
    @Basic
    @Column(name = "bonusqty")
    private Integer bonusqty;
    @Basic
    @Column(name = "bonusperkilo")
    private BigDecimal bonusperkilo;
    @Basic
    @Column(name = "bonuspayment")
    private BigDecimal bonuspayment;
    @Basic
    @Column(name = "basicpayment")
    private BigDecimal basicpayment;
    @Basic
    @Column(name = "totalpayment")
    private BigDecimal totalpayment;

    @ManyToOne
    @JoinColumn(name = "plucker_id", referencedColumnName = "id", nullable = false)
    private Employee plucker;

    @ManyToOne
    @JoinColumn(name = "issuer_id", referencedColumnName = "id", nullable = false)
    private Employee issuer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Date getDopayment() {
        return dopayment;
    }

    public void setDopayment(Date dopayment) {
        this.dopayment = dopayment;
    }

    public Integer getBonusqty() {
        return bonusqty;
    }

    public void setBonusqty(Integer bonusqty) {
        this.bonusqty = bonusqty;
    }

    public BigDecimal getBonusperkilo() {
        return bonusperkilo;
    }

    public void setBonusperkilo(BigDecimal bonusperkilo) {
        this.bonusperkilo = bonusperkilo;
    }

    public BigDecimal getBonuspayment() {
        return bonuspayment;
    }

    public void setBonuspayment(BigDecimal bonuspayment) {
        this.bonuspayment = bonuspayment;
    }

    public BigDecimal getBasicpayment() {
        return basicpayment;
    }

    public void setBasicpayment(BigDecimal basicpayment) {
        this.basicpayment = basicpayment;
    }

    public BigDecimal getTotalpayment() {
        return totalpayment;
    }

    public void setTotalpayment(BigDecimal totalpayment) {
        this.totalpayment = totalpayment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pluckingpayment that = (Pluckingpayment) o;
        return Objects.equals(id, that.id) && Objects.equals(startdate, that.startdate) && Objects.equals(enddate, that.enddate) && Objects.equals(dopayment, that.dopayment) && Objects.equals(bonusqty, that.bonusqty) && Objects.equals(bonusperkilo, that.bonusperkilo) && Objects.equals(bonuspayment, that.bonuspayment) && Objects.equals(basicpayment, that.basicpayment) && Objects.equals(totalpayment, that.totalpayment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startdate, enddate, dopayment, bonusqty, bonusperkilo, bonuspayment, basicpayment, totalpayment);
    }

    public Employee getPlucker() {
        return plucker;
    }

    public void setPlucker(Employee plucker) {
        this.plucker = plucker;
    }

    public Employee getIssuer() {
        return issuer;
    }

    public void setIssuer(Employee issuer) {
        this.issuer = issuer;
    }
}
