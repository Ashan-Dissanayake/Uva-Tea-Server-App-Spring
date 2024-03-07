package lk.uva.uvateafactory.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Distributor {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "telephone")
    private String telephone;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "contactperson")
    private String contactperson;
    @Basic
    @Column(name = "contactpersontp")
    private String contactpersontp;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "creditlimit")
    private BigDecimal creditlimit;
    @ManyToOne
    @JoinColumn(name = "distributorstatus_id", referencedColumnName = "id", nullable = false)
    private Distributorstatus distributorstatus;
    @ManyToOne
    @JoinColumn(name = "distributortype_id", referencedColumnName = "id", nullable = false)
    private Distributortype distributortype;

    @JsonIgnore
    @OneToMany(mappedBy = "distributor")
    private Collection<Orderr> orderrs;

    public Distributor() {}

    public Distributor(int id, String name) {
        this.id = id;
        this.name = name;
    }

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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactperson() {
        return contactperson;
    }

    public void setContactperson(String contactperson) {
        this.contactperson = contactperson;
    }

    public String getContactpersontp() {
        return contactpersontp;
    }

    public void setContactpersontp(String contactpersontp) {
        this.contactpersontp = contactpersontp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCreditlimit() {
        return creditlimit;
    }

    public void setCreditlimit(BigDecimal creditlimit) {
        this.creditlimit = creditlimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distributor that = (Distributor) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(telephone, that.telephone) && Objects.equals(email, that.email) && Objects.equals(address, that.address) && Objects.equals(contactperson, that.contactperson) && Objects.equals(contactpersontp, that.contactpersontp) && Objects.equals(description, that.description) && Objects.equals(creditlimit, that.creditlimit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, telephone, email, address, contactperson, contactpersontp, description, creditlimit);
    }

    public Distributorstatus getDistributorstatus() {
        return distributorstatus;
    }

    public void setDistributorstatus(Distributorstatus distributorstatus) {
        this.distributorstatus = distributorstatus;
    }

    public Distributortype getDistributortype() {
        return distributortype;
    }

    public void setDistributortype(Distributortype distributortype) {
        this.distributortype = distributortype;
    }

    public Collection<Orderr> getOrderrs() {
        return orderrs;
    }

    public void setOrderrs(Collection<Orderr> orderrs) {
        this.orderrs = orderrs;
    }
}
