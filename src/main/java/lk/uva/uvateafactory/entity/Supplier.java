package lk.uva.uvateafactory.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Supplier {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "name")
    @Pattern(regexp = "^([A-Z][a-z]*[.]?[\\s]?)*([A-Z][a-z]*)$", message = "Invalid SupplierName")
    private String name;

    @Basic
    @Column(name = "contactperson")
    @Pattern(regexp = "^([A-Z][a-z]*[.]?[\\s]?)*([A-Z][a-z]*)$", message = "Invalid ContactPersonName")
    private String contactperson;

    @Basic
    @Column(name = "email")
    @Pattern(regexp ="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$" ,message = "Invalid Email")
    private String email;

    @Basic
    @Column(name = "creditlimit")
    private BigDecimal creditlimit;

    @Basic
    @Column(name = "address")
    @Pattern(regexp = "^([\\w\\/\\-,\\s]{2,})$", message = "Invalid Address" )
    private String address;

    @Basic
    @Column(name = "officetp")
    @Pattern(regexp = "^0\\d{9}$", message = "Invalid OfficePhone Number")
    private String officetp;

    @Basic
    @Column(name = "contactpersontp")
    @Pattern(regexp = "^0\\d{9}$", message = "Invalid ContactPerson Number")
    private String contactpersontp;

    @ManyToOne
    @JoinColumn(name = "supplierstatus_id", referencedColumnName = "id", nullable = false)
    private Supplierstatus supplierstatus;


    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Supplierfertilizer> supplierfertilizers;

    @Basic
    @Pattern(regexp = "^S-\\d{3}$", message = "Invalid Code" )
    @Column(name = "code")
    private String code;

    @JsonIgnore
    @OneToMany(mappedBy = "supplier")
    private Collection<Porder> porders;

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

    public String getContactperson() {
        return contactperson;
    }

    public void setContactperson(String contactperson) {
        this.contactperson = contactperson;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getCreditlimit() {
        return creditlimit;
    }

    public void setCreditlimit(BigDecimal creditlimit) {
        this.creditlimit = creditlimit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOfficetp() {
        return officetp;
    }

    public void setOfficetp(String officetp) {
        this.officetp = officetp;
    }

    public String getContactpersontp() {
        return contactpersontp;
    }

    public void setContactpersontp(String contactpersontp) {
        this.contactpersontp = contactpersontp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return Objects.equals(id, supplier.id) && Objects.equals(name, supplier.name) && Objects.equals(contactperson, supplier.contactperson) && Objects.equals(email, supplier.email) && Objects.equals(creditlimit, supplier.creditlimit) && Objects.equals(address, supplier.address) && Objects.equals(officetp, supplier.officetp) && Objects.equals(contactpersontp, supplier.contactpersontp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, contactperson, email, creditlimit, address, officetp, contactpersontp);
    }

    public Supplierstatus getSupplierstatus() {
        return supplierstatus;
    }

    public void setSupplierstatus(Supplierstatus supplierstatus) {
        this.supplierstatus = supplierstatus;
    }

    public Collection<Supplierfertilizer> getSupplierfertilizers() {
        return supplierfertilizers;
    }

    public void setSupplierfertilizers(Collection<Supplierfertilizer> supplierfertilizers) {
        this.supplierfertilizers = supplierfertilizers;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Collection<Porder> getPorders() {
        return porders;
    }

    public void setPorders(Collection<Porder> porders) {
        this.porders = porders;
    }
}
