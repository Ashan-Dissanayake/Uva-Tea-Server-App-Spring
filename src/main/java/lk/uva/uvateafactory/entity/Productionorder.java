package lk.uva.uvateafactory.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lk.uva.uvateafactory.util.RegexPattern;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Productionorder {
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

    @Basic
    @Column(name = "quantity")
    @RegexPattern(reg = "^\\d{1,5}$", msg = "Invalid Quantity")
    private Integer quantity;

    @Basic
    @Column(name = "humidity")
    private BigDecimal humidity;

    @Basic
    @Column(name = "description")
    @Pattern(regexp = "^.*$", message = "Invalid Description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    private Area area;
    @ManyToOne
    @JoinColumn(name = "productionorderstatus_id", referencedColumnName = "id", nullable = false)
    private Productionorderstatus productionorderstatus;
    @ManyToOne
    @JoinColumn(name = "teamaker_id", referencedColumnName = "id")
    private Employee teamaker;


    @JsonIgnore
    @OneToMany(mappedBy = "productionorder")
    private Collection<Production> productions;

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getHumidity() {
        return humidity;
    }

    public void setHumidity(BigDecimal humidity) {
        this.humidity = humidity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Productionorder that = (Productionorder) o;
        return Objects.equals(id, that.id) && Objects.equals(date, that.date) && Objects.equals(time, that.time) && Objects.equals(quantity, that.quantity) && Objects.equals(humidity, that.humidity) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, time, quantity, humidity, description);
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Productionorderstatus getProductionorderstatus() {
        return productionorderstatus;
    }

    public void setProductionorderstatus(Productionorderstatus productionorderstatus) {
        this.productionorderstatus = productionorderstatus;
    }

    public Employee getTeamaker() {
        return teamaker;
    }

    public void setTeamaker(Employee teamaker) {
        this.teamaker = teamaker;
    }

    public Collection<Production> getProductions() {
        return productions;
    }

    public void setProductions(Collection<Production> productions) {
        this.productions = productions;
    }
}
