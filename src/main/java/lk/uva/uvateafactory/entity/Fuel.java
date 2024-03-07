package lk.uva.uvateafactory.entity;

import lk.uva.uvateafactory.util.RegexPattern;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

@Entity
public class Fuel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "date")
    private Date date;
    @Basic
    @Column(name = "qty")
    private BigDecimal qty;
    @Basic
    @Column(name = "cost")
    private BigDecimal cost;
    @Basic
    @Column(name = "meterreading")
    @RegexPattern(reg = "^\\d{1,6}$",msg = "Invalid Odometer Number")
    private Integer meterreading;
    @Basic
    @Column(name = "time")
    private Time time;
    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Vehicle vehicle;
    @ManyToOne
    @JoinColumn(name = "fueltype_id", referencedColumnName = "id", nullable = false)
    private Fueltype fueltype;
    @ManyToOne
    @JoinColumn(name = "driveronduty_id", referencedColumnName = "id")
    private Employee driveronduty;
    @ManyToOne
    @JoinColumn(name = "fuelstation_id", referencedColumnName = "id", nullable = false)
    private Fuelstation fuelstation;

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

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Integer getMeterreading() {
        return meterreading;
    }

    public void setMeterreading(Integer meterreading) {
        this.meterreading = meterreading;
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
        Fuel fuel = (Fuel) o;
        return Objects.equals(id, fuel.id) && Objects.equals(date, fuel.date) && Objects.equals(qty, fuel.qty) && Objects.equals(cost, fuel.cost) && Objects.equals(meterreading, fuel.meterreading) && Objects.equals(time, fuel.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, qty, cost, meterreading, time);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Fueltype getFueltype() {
        return fueltype;
    }

    public void setFueltype(Fueltype fueltype) {
        this.fueltype = fueltype;
    }

    public Employee getDriveronduty() {
        return driveronduty;
    }

    public void setDriveronduty(Employee driveronduty) {
        this.driveronduty = driveronduty;
    }

    public Fuelstation getFuelstation() {
        return fuelstation;
    }

    public void setFuelstation(Fuelstation fuelstation) {
        this.fuelstation = fuelstation;
    }
}
