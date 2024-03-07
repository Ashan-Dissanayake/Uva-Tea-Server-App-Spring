package lk.uva.uvateafactory.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private Collection<Productionproduct> productionproducts;

    @Basic
    @Column(name = "unitprice")
    private BigDecimal unitprice;

    @Basic
    @Column(name = "qtyonhand")
    private BigDecimal qtyonhand;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private Collection<Orderrproduct> orderrproducts;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private Collection<Invoiceproduct> invoiceproducts;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Collection<Productionproduct> getProductionproducts() {
        return productionproducts;
    }

    public void setProductionproducts(Collection<Productionproduct> productionproducts) {
        this.productionproducts = productionproducts;
    }

    public BigDecimal getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(BigDecimal unitprice) {
        this.unitprice = unitprice;
    }

    public BigDecimal getQtyonhand() {
        return qtyonhand;
    }

    public void setQtyonhand(BigDecimal qtyonhand) {
        this.qtyonhand = qtyonhand;
    }

    public Collection<Orderrproduct> getOrderrproducts() {
        return orderrproducts;
    }

    public void setOrderrproducts(Collection<Orderrproduct> orderrproducts) {
        this.orderrproducts = orderrproducts;
    }

    public Collection<Invoiceproduct> getInvoiceproducts() {
        return invoiceproducts;
    }

    public void setInvoiceproducts(Collection<Invoiceproduct> invoiceproducts) {
        this.invoiceproducts = invoiceproducts;
    }
}
