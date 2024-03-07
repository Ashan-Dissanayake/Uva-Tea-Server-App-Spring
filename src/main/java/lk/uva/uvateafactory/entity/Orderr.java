package lk.uva.uvateafactory.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Orderr {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "doorder")
    private Date doorder;
    @Basic
    @Column(name = "doexpected")
    private Date doexpected;
    @Basic
    @Column(name = "expectedgrandtotal")
    private BigDecimal expectedgrandtotal;

    @ManyToOne
    @JoinColumn(name = "distributor_id", referencedColumnName = "id", nullable = false)
    private Distributor distributor;

    @ManyToOne
    @JoinColumn(name = "orderstatus_id", referencedColumnName = "id", nullable = false)
    private Orderstatus orderstatus;

    @OneToMany(mappedBy = "orderr", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Orderrproduct> orderrproducts;

    @JsonIgnore
    @OneToMany(mappedBy = "orderr")
    private Collection<Invoice> invoices;

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

    public Date getDoorder() {
        return doorder;
    }

    public void setDoorder(Date doorder) {
        this.doorder = doorder;
    }

    public Date getDoexpected() {
        return doexpected;
    }

    public void setDoexpected(Date doexpected) {
        this.doexpected = doexpected;
    }

    public BigDecimal getExpectedgrandtotal() {
        return expectedgrandtotal;
    }

    public void setExpectedgrandtotal(BigDecimal expectedgrandtotal) {
        this.expectedgrandtotal = expectedgrandtotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orderr orderr = (Orderr) o;
        return Objects.equals(id, orderr.id) && Objects.equals(name, orderr.name) && Objects.equals(doorder, orderr.doorder) && Objects.equals(doexpected, orderr.doexpected) && Objects.equals(expectedgrandtotal, orderr.expectedgrandtotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, doorder, doexpected, expectedgrandtotal);
    }

    public Distributor getDistributor() {
        return distributor;
    }

    public void setDistributor(Distributor distributor) {
        this.distributor = distributor;
    }

    public Orderstatus getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(Orderstatus orderstatus) {
        this.orderstatus = orderstatus;
    }

    public Collection<Orderrproduct> getOrderrproducts() {
        return orderrproducts;
    }

    public void setOrderrproducts(Collection<Orderrproduct> orderrproducts) {
        this.orderrproducts = orderrproducts;
    }

    public Collection<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Collection<Invoice> invoices) {
        this.invoices = invoices;
    }
}
