package lk.uva.uvateafactory.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lk.uva.uvateafactory.util.RegexPattern;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Vehicle {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "doattach")
    private Date doattach;

    @Basic
    @Column(name = "number")
    @RegexPattern(reg = "^(([A-Z]{2,3}-\\d{4})|(\\d{2,3}-\\d{4}))$", msg = "Invalid Vehicle Number")
    private String number;

    @Basic
    @Column(name = "yom")
    @RegexPattern(reg = "^\\d{4}$", msg = "Invalid Manufacture Year")
    private Integer yom;

    @Basic
    @Column(name = "lastmeterreading")
    @RegexPattern(reg = "^\\d{1,6}$", msg = "Invalid Mileage")
    private Integer lastmeterreading;

    @Basic
    @Column(name = "capacity")
    @RegexPattern(reg = "^\\d{1,4}$", msg = "Invalid Capacity")
    private Integer capacity;

    @Basic
    @Column(name = "description")
    @Pattern(regexp = "^.*$", message = "Invalid Description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "vehiclemodel_id", referencedColumnName = "id", nullable = false)
    private Vehiclemodel vehiclemodel;
    @ManyToOne
    @JoinColumn(name = "vehicletype_id", referencedColumnName = "id", nullable = false)
    private Vehicletype vehicletype;
    @ManyToOne
    @JoinColumn(name = "vehiclestatus_id", referencedColumnName = "id", nullable = false)
    private Vehiclestatus vehiclestatus;

    @JsonIgnore
    @OneToMany(mappedBy = "vehicle")
    private Collection<Transport> transports;

    @JsonIgnore
    @OneToMany(mappedBy = "vehicle")
    private Collection<Fuel> fuels;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDoattach() {
        return doattach;
    }

    public void setDoattach(Date doattach) {
        this.doattach = doattach;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getYom() {
        return yom;
    }

    public void setYom(Integer yom) {
        this.yom = yom;
    }

    public Integer getLastmeterreading() {
        return lastmeterreading;
    }

    public void setLastmeterreading(Integer lastmeterreading) {
        this.lastmeterreading = lastmeterreading;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
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
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(id, vehicle.id) && Objects.equals(doattach, vehicle.doattach) && Objects.equals(number, vehicle.number) && Objects.equals(yom, vehicle.yom) && Objects.equals(lastmeterreading, vehicle.lastmeterreading) && Objects.equals(capacity, vehicle.capacity) && Objects.equals(description, vehicle.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, doattach, number, yom, lastmeterreading, capacity, description);
    }

    public Vehiclemodel getVehiclemodel() {
        return vehiclemodel;
    }

    public void setVehiclemodel(Vehiclemodel vehiclemodel) {
        this.vehiclemodel = vehiclemodel;
    }

    public Vehicletype getVehicletype() {
        return vehicletype;
    }

    public void setVehicletype(Vehicletype vehicletype) {
        this.vehicletype = vehicletype;
    }

    public Vehiclestatus getVehiclestatus() {
        return vehiclestatus;
    }

    public void setVehiclestatus(Vehiclestatus vehiclestatus) {
        this.vehiclestatus = vehiclestatus;
    }

    public Collection<Transport> getTransports() {
        return transports;
    }

    public void setTransports(Collection<Transport> transports) {
        this.transports = transports;
    }

    public Collection<Fuel> getFuels() {
        return fuels;
    }

    public void setFuels(Collection<Fuel> fuels) {
        this.fuels = fuels;
    }
}
