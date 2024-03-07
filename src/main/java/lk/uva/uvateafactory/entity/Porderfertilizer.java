package lk.uva.uvateafactory.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Porderfertilizer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "qty")
    private BigDecimal qty;
    @Basic
    @Column(name = "linecost")
    private BigDecimal linecost;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "porder_id", referencedColumnName = "id", nullable = false)
    private Porder porder;

    @ManyToOne
    @JoinColumn(name = "fertilizer_id", referencedColumnName = "id", nullable = false)
    private Fertilizer fertilizer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getLinecost() {
        return linecost;
    }

    public void setLinecost(BigDecimal linecost) {
        this.linecost = linecost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Porderfertilizer that = (Porderfertilizer) o;
        return Objects.equals(id, that.id) && Objects.equals(qty, that.qty) && Objects.equals(linecost, that.linecost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, qty, linecost);
    }

    public Porder getPorder() {
        return porder;
    }

    public void setPorder(Porder porder) {
        this.porder = porder;
    }

    public Fertilizer getFertilizer() {
        return fertilizer;
    }

    public void setFertilizer(Fertilizer fertilizer) {
        this.fertilizer = fertilizer;
    }
}
