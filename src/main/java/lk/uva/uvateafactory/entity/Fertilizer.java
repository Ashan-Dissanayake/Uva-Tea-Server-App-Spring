package lk.uva.uvateafactory.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Fertilizer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    @Pattern(regexp = "^[A-Z].*$", message = "Invalid Fertilizer Name")
    private String name;
    @Basic
    @Column(name = "quantity")
    private BigDecimal quantity;
    @Basic
    @Column(name = "unitprice")
    private BigDecimal unitprice;
    @Basic
    @Column(name = "rop")
    private BigDecimal rop;
    @Basic
    @Column(name = "dointroduced")
    private Date dointroduced;

    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id", nullable = false)
    private Fertilzerbrand fertilzerbrand;

    @ManyToOne
    @JoinColumn(name = "fertilizertype_id", referencedColumnName = "id", nullable = false)
    private Fertilizertype fertilizertype;

    @ManyToOne
    @JoinColumn(name = "fertilizerstatus_id", referencedColumnName = "id", nullable = false)
    private Fertilizerstatus fertilizerstatus;

    @JsonIgnore
    @OneToMany(mappedBy = "fertilizer")
    private Collection<Fertilizerdistribution> fertilizerdistributions;

    @JsonIgnore
    @OneToMany(mappedBy = "fertilizer")
    private Collection<Supplierfertilizer> supplierfertilizers;

    @JsonIgnore
    @OneToMany(mappedBy = "fertilizer")
    private Collection<Porderfertilizer> porderfertilizers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(BigDecimal unitprice) {
        this.unitprice = unitprice;
    }

    public BigDecimal getRop() {
        return rop;
    }

    public void setRop(BigDecimal rop) {
        this.rop = rop;
    }

    public Date getDointroduced() {
        return dointroduced;
    }

    public void setDointroduced(Date dointroduced) {
        this.dointroduced = dointroduced;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fertilizer that = (Fertilizer) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(quantity, that.quantity) && Objects.equals(unitprice, that.unitprice) && Objects.equals(rop, that.rop) && Objects.equals(dointroduced, that.dointroduced);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantity, unitprice, rop, dointroduced);
    }

    public Fertilzerbrand getFertilzerbrand() {
        return fertilzerbrand;
    }

    public void setFertilzerbrand(Fertilzerbrand fertilzerbrand) {
        this.fertilzerbrand = fertilzerbrand;
    }

    public Fertilizertype getFertilizertype() {
        return fertilizertype;
    }

    public void setFertilizertype(Fertilizertype fertilizertype) {
        this.fertilizertype = fertilizertype;
    }

    public Fertilizerstatus getFertilizerstatus() {
        return fertilizerstatus;
    }

    public void setFertilizerstatus(Fertilizerstatus fertilizerstatus) {
        this.fertilizerstatus = fertilizerstatus;
    }

    public Collection<Fertilizerdistribution> getFertilizerdistributions() {
        return fertilizerdistributions;
    }

    public void setFertilizerdistributions(Collection<Fertilizerdistribution> fertilizerdistributions) {
        this.fertilizerdistributions = fertilizerdistributions;
    }

    public Collection<Supplierfertilizer> getSupplierfertilizers() {
        return supplierfertilizers;
    }

    public void setSupplierfertilizers(Collection<Supplierfertilizer> supplierfertilizers) {
        this.supplierfertilizers = supplierfertilizers;
    }

    public Collection<Porderfertilizer> getPorderfertilizers() {
        return porderfertilizers;
    }

    public void setPorderfertilizers(Collection<Porderfertilizer> porderfertilizers) {
        this.porderfertilizers = porderfertilizers;
    }
}
