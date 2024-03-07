package lk.uva.uvateafactory.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lk.uva.uvateafactory.util.RegexPattern;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Employee {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "number")
    @Pattern(regexp = "^\\d{4}$", message = "Invalid Number")
    private String number;

    @Basic
    @Column(name = "fullname")
    @Pattern(regexp = "^([A-Z][a-z]*[.]?[\\s]?)*([A-Z][a-z]*)$", message = "Invalid Fullname")
    private String fullname;

    @Basic
    @Column(name = "callingname")
    @Pattern(regexp = "^([A-Z][a-z]+)$", message = "Invalid callingname" )
    private String callingname;

    @Basic
    @Column(name = "photo")
    private byte[] photo;

    @Basic
    @Column(name = "dobirth")
    @RegexPattern(reg = "^\\d{2}-\\d{2}-\\d{2}$", msg = "Invalid Date Format")
    private Date dobirth;

    @Basic
    @Column(name = "nic")
    @Pattern(regexp = "^(([\\d]{9}[vVxX])|([\\d]{12}))$", message = "Invalid NIC")
    private String nic;

    @Basic
    @Column(name = "address")
    @Pattern(regexp = "^([\\w\\/\\-,\\s]{2,})$", message = "Invalid Address" )
    private String address;

    @Basic
    @Column(name = "mobile")
    @Pattern(regexp = "^0\\d{9}$", message = "Invalid Mobilephone Number")
    private String mobile;

    @Basic
    @Column(name = "land")
    @Pattern(regexp = "^(|\\d{10})$", message = "Invalid Landphone Number")
    private String land;

    @Basic
    @Column(name = "doassignment")
    private Date doassignment;

    @Basic
    @Column(name = "description")
    @Pattern(regexp = "^.*$", message = "Invalid Description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "gender_id", referencedColumnName = "id", nullable = false)
    private Gender gender;
    @ManyToOne
    @JoinColumn(name = "designation_id", referencedColumnName = "id", nullable = false)
    private Designation designation;
    @ManyToOne
    @JoinColumn(name = "employeestatus_id", referencedColumnName = "id", nullable = false)
    private Employeestatus employeestatus;

    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    private Collection<User> users;

    @JsonIgnore
    @OneToMany(mappedBy = "empsupervisor")
    private Collection<Area> areassupervisors;

    @JsonIgnore
    @OneToMany(mappedBy = "empfieldofficer")
    private Collection<Area> areasfieldsupervisors;

    @JsonIgnore
    @OneToMany(mappedBy = "empplucker")
    private Collection<Plucking> pluckingsplucker;

    @JsonIgnore
    @OneToMany(mappedBy = "empkankani")
    private Collection<Plucking> pluckingskankani;

    @JsonIgnore
    @OneToMany(mappedBy = "kankani")
    private Collection<Fertilizerdistribution> fertilizerdistributions;

    @JsonIgnore
    @OneToMany(mappedBy = "driver")
    private Collection<Transport> transports;

    @JsonIgnore
    @OneToMany(mappedBy = "driveronduty")
    private Collection<Fuel> fuels;

    @JsonIgnore
    @OneToMany(mappedBy = "approver")
    private Collection<Activity> activities;

    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    private Collection<Attendence> attendences;

    @JsonIgnore
    @OneToMany(mappedBy = "teamaker")
    private Collection<Productionorder> productionorders;

    @JsonIgnore
    @OneToMany(mappedBy = "plucker")
    private Collection<Pluckingpayment> pluckingpaymentplucker;;

    @JsonIgnore
    @OneToMany(mappedBy = "issuer")
    private Collection<Pluckingpayment> pluckingpaymentsissuer;

    @JsonIgnore
    @OneToMany(mappedBy = "manager")
    private Collection<Invoice> invoices;

    public Employee() {}

    public Employee(int id,String callingname){
        this.id = id;
        this.callingname = callingname;
    }

    public Employee(Integer id,String callingname, Designation designation) {
        this.id = id;
        this.callingname = callingname;
        this.designation = designation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getCallingname() {
        return callingname;
    }

    public void setCallingname(String callingname) {
        this.callingname = callingname;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Date getDobirth() {
        return dobirth;
    }

    public void setDobirth(Date dobirth) {
        this.dobirth = dobirth;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public Date getDoassignment() {
        return doassignment;
    }

    public void setDoassignment(Date doassignment) {
        this.doassignment = doassignment;
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
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(number, employee.number) && Objects.equals(fullname, employee.fullname) && Objects.equals(callingname, employee.callingname) && Arrays.equals(photo, employee.photo) && Objects.equals(dobirth, employee.dobirth) && Objects.equals(nic, employee.nic) && Objects.equals(address, employee.address) && Objects.equals(mobile, employee.mobile) && Objects.equals(land, employee.land) && Objects.equals(doassignment, employee.doassignment) && Objects.equals(description, employee.description);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, number, fullname, callingname, dobirth, nic, address, mobile, land, doassignment, description);
        result = 31 * result + Arrays.hashCode(photo);
        return result;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Designation getDesignation() {
        return designation;
    }

    public void setDesignation(Designation designation) {
        this.designation = designation;
    }

    public Employeestatus getEmployeestatus() {
        return employeestatus;
    }

    public void setEmployeestatus(Employeestatus employeestatus) {
        this.employeestatus = employeestatus;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public Collection<Area> getAreassupervisors() {
        return areassupervisors;
    }

    public void setAreassupervisors(Collection<Area> areassupervisors) {
        this.areassupervisors = areassupervisors;
    }

    public Collection<Area> getAreasfieldsupervisors() {
        return areasfieldsupervisors;
    }

    public void setAreasfieldsupervisors(Collection<Area> areasfieldsupervisors) {
        this.areasfieldsupervisors = areasfieldsupervisors;
    }

    public Collection<Plucking> getPluckingsplucker() {
        return pluckingsplucker;
    }

    public void setPluckingsplucker(Collection<Plucking> pluckingsplucker) {
        this.pluckingsplucker = pluckingsplucker;
    }

    public Collection<Plucking> getPluckingskankani() {
        return pluckingskankani;
    }

    public void setPluckingskankani(Collection<Plucking> pluckingskankani) {
        this.pluckingskankani = pluckingskankani;
    }

    public Collection<Fertilizerdistribution> getFertilizerdistributions() {
        return fertilizerdistributions;
    }

    public void setFertilizerdistributions(Collection<Fertilizerdistribution> fertilizerdistributions) {
        this.fertilizerdistributions = fertilizerdistributions;
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

    public Collection<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Collection<Activity> activities) {
        this.activities = activities;
    }

    public Collection<Attendence> getAttendences() {
        return attendences;
    }

    public void setAttendences(Collection<Attendence> attendences) {
        this.attendences = attendences;
    }

    public Collection<Productionorder> getProductionorders() {
        return productionorders;
    }

    public void setProductionorders(Collection<Productionorder> productionorders) {
        this.productionorders = productionorders;
    }

    public Collection<Pluckingpayment> getPluckingpaymentplucker() {
        return pluckingpaymentplucker;
    }

    public void setPluckingpaymentplucker(Collection<Pluckingpayment> pluckingpaymentplucker) {
        this.pluckingpaymentplucker = pluckingpaymentplucker;
    }

    public Collection<Pluckingpayment> getPluckingpaymentsissuer() {
        return pluckingpaymentsissuer;
    }

    public void setPluckingpaymentsissuer(Collection<Pluckingpayment> pluckingpaymentsissuer) {
        this.pluckingpaymentsissuer = pluckingpaymentsissuer;
    }

    public Collection<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Collection<Invoice> invoices) {
        this.invoices = invoices;
    }
}
