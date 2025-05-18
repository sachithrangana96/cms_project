package com.customer_management.customer_pt.entity;

import lombok.Builder;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "date_of_birth", nullable = false)
    private Date dateOfBirth;

    @Column(nullable = false,unique = true)
    private String nic;


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<MobileNumber> mobileNumbers;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Address> address;


    @ManyToMany
    @JoinTable(
            name="customer_family_member",
            joinColumns = @JoinColumn(name="customer_id"),
            inverseJoinColumns = @JoinColumn(name = "family_member_id")
    )
    private List<Customer> familyMembers;


    public Customer() {
    }

    public Customer(Long id, String name, Date dateOfBirth, String nic, List<MobileNumber> mobileNumber, List<Address> address, List<Customer> familyMembers) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.nic = nic;
        this.address = address;
        this.familyMembers = familyMembers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }



    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public List<Customer> getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(List<Customer> familyMembers) {
        this.familyMembers = familyMembers;
    }

    public List<MobileNumber> getMobileNumbers() {
        return mobileNumbers;
    }

    public void setMobileNumbers(List<MobileNumber> mobileNumbers) {
        this.mobileNumbers = mobileNumbers;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}






