package lk.uva.uvateafactory.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Fertilizerdistribution {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "quantitydis")
    private BigDecimal quantitydis;
    @Basic
    @Column(name = "date")
    private Date date;
    @ManyToOne
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    private Area area;
    @ManyToOne
    @JoinColumn(name = "fertilizer_id", referencedColumnName = "id")
    private Fertilizer fertilizer;
    @ManyToOne
    @JoinColumn(name = "ferdistributionstate_id", referencedColumnName = "id", nullable = false)
    private Ferdistributionstate ferdistributionstate;
    @ManyToOne
    @JoinColumn(name = "empkankani_id", referencedColumnName = "id")
    private Employee kankani;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getQuantitydis() {
        return quantitydis;
    }

    public void setQuantitydis(BigDecimal quantitydis) {
        this.quantitydis = quantitydis;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fertilizerdistribution that = (Fertilizerdistribution) o;
        return Objects.equals(id, that.id) && Objects.equals(quantitydis, that.quantitydis) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantitydis, date);
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Fertilizer getFertilizer() {
        return fertilizer;
    }

    public void setFertilizer(Fertilizer fertilizer) {
        this.fertilizer = fertilizer;
    }

    public Ferdistributionstate getFerdistributionstate() {
        return ferdistributionstate;
    }

    public void setFerdistributionstate(Ferdistributionstate ferdistributionstate) {
        this.ferdistributionstate = ferdistributionstate;
    }

    public Employee getKankani() {
        return kankani;
    }

    public void setKankani(Employee kankani) {
        this.kankani = kankani;
    }
}
