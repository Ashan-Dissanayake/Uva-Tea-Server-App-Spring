package lk.uva.uvateafactory.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Orderrproduct {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "qty")
    private BigDecimal qty;
    @Basic
    @Column(name = "linetotal")
    private BigDecimal linetotal;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "orderr_id", referencedColumnName = "id", nullable = false)
    private Orderr orderr;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;

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

    public BigDecimal getLinetotal() {
        return linetotal;
    }

    public void setLinetotal(BigDecimal linetotal) {
        this.linetotal = linetotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orderrproduct that = (Orderrproduct) o;
        return Objects.equals(id, that.id) && Objects.equals(qty, that.qty) && Objects.equals(linetotal, that.linetotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, qty, linetotal);
    }

    public Orderr getOrderr() {
        return orderr;
    }

    public void setOrderr(Orderr orderr) {
        this.orderr = orderr;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
