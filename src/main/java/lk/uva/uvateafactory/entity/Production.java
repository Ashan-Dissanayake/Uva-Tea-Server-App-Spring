package lk.uva.uvateafactory.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Production {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "date")
    private Date date;
    @Basic
    @Column(name = "time")
    private Time time;

    @ManyToOne
    @JoinColumn(name = "productionorder_id", referencedColumnName = "id", nullable = false)
    private Productionorder productionorder;


    @OneToMany(mappedBy = "production", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Productionproduct> productionproducts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Production that = (Production) o;
        return Objects.equals(id, that.id) && Objects.equals(date, that.date) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, time);
    }

    public Productionorder getProductionorder() {
        return productionorder;
    }

    public void setProductionorder(Productionorder productionorder) {
        this.productionorder = productionorder;
    }

    public Collection<Productionproduct> getProductionproducts() {
        return productionproducts;
    }

    public void setProductionproducts(Collection<Productionproduct> productionproducts) {
        this.productionproducts = productionproducts;
    }
}
