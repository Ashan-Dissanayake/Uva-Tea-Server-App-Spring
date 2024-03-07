package lk.uva.uvateafactory.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Porder {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "doplaced")
    private Date doplaced;
    @Basic
    @Column(name = "costexpected")
    private BigDecimal costexpected;
    @ManyToOne
    @JoinColumn(name = "porderstatus_id", referencedColumnName = "id", nullable = false)
    private Porderstatus porderstatus;

    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id", nullable = false)
    private Supplier supplier;

    @OneToMany(mappedBy = "porder", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Porderfertilizer> porderfertilizers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDoplaced() {
        return doplaced;
    }

    public void setDoplaced(Date doplaced) {
        this.doplaced = doplaced;
    }

    public BigDecimal getCostexpected() {
        return costexpected;
    }

    public void setCostexpected(BigDecimal costexpected) {
        this.costexpected = costexpected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Porder porder = (Porder) o;
        return Objects.equals(id, porder.id) && Objects.equals(doplaced, porder.doplaced) && Objects.equals(costexpected, porder.costexpected);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, doplaced, costexpected);
    }

    public Porderstatus getPorderstatus() {
        return porderstatus;
    }

    public void setPorderstatus(Porderstatus porderstatus) {
        this.porderstatus = porderstatus;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Collection<Porderfertilizer> getPorderfertilizers() {
        return porderfertilizers;
    }

    public void setPorderfertilizers(Collection<Porderfertilizer> porderfertilizers) {
        this.porderfertilizers = porderfertilizers;
    }
}
