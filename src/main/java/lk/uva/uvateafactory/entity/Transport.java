package lk.uva.uvateafactory.entity;

import lk.uva.uvateafactory.util.RegexPattern;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

@Entity
public class Transport {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "date")
    private Date date;

    @Basic
    @Column(name = "startreading")
    @RegexPattern(reg = "^\\d{1,6}$",msg = "Invalid Odometer Starting Number")
    private Integer startreading;

    @Basic
    @Column(name = "endreading")
    @RegexPattern(reg = "^\\d{1,6}$",msg = "Invalid Odometer Finish Number")
    private Integer endreading;

    @Basic
    @Column(name = "description")
    @Pattern(regexp = "^.*$", message = "Invalid Description")
    private String description;

    @Basic
    @Column(name = "strattime")
    private Time strattime;
    @Basic
    @Column(name = "endtime")
    private Time endtime;
    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Vehicle vehicle;
    @ManyToOne
    @JoinColumn(name = "root_id", referencedColumnName = "id")
    private Root root;
    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    private Employee driver;
    @ManyToOne
    @JoinColumn(name = "transportstatus_id", referencedColumnName = "id", nullable = false)
    private Transportstatus transportstatus;
    @ManyToOne
    @JoinColumn(name = "transportpurpose_id", referencedColumnName = "id", nullable = false)
    private Transportpurpose transportpurpose;

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

    public Integer getStartreading() {
        return startreading;
    }

    public void setStartreading(Integer startreading) {
        this.startreading = startreading;
    }

    public Integer getEndreading() {
        return endreading;
    }

    public void setEndreading(Integer endreading) {
        this.endreading = endreading;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Time getStrattime() {
        return strattime;
    }

    public void setStrattime(Time strattime) {
        this.strattime = strattime;
    }

    public Time getEndtime() {
        return endtime;
    }

    public void setEndtime(Time endtime) {
        this.endtime = endtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transport transport = (Transport) o;
        return Objects.equals(id, transport.id) && Objects.equals(date, transport.date) && Objects.equals(startreading, transport.startreading) && Objects.equals(endreading, transport.endreading) && Objects.equals(description, transport.description) && Objects.equals(strattime, transport.strattime) && Objects.equals(endtime, transport.endtime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, startreading, endreading, description, strattime, endtime);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Root getRoot() {
        return root;
    }

    public void setRoot(Root root) {
        this.root = root;
    }

    public Employee getDriver() {
        return driver;
    }

    public void setDriver(Employee driver) {
        this.driver = driver;
    }

    public Transportstatus getTransportstatus() {
        return transportstatus;
    }

    public void setTransportstatus(Transportstatus transportstatus) {
        this.transportstatus = transportstatus;
    }

    public Transportpurpose getTransportpurpose() {
        return transportpurpose;
    }

    public void setTransportpurpose(Transportpurpose transportpurpose) {
        this.transportpurpose = transportpurpose;
    }
}
