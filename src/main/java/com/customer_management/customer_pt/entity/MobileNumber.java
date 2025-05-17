package com.customer_management.customer_pt.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "mobile_number")
public class MobileNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String number;

    @ManyToOne
    @JoinColumn(name ="customer_id")
    private Customer customer;

    public MobileNumber() {
    }

    public MobileNumber(Long id, Customer customer, String number) {
        this.id = id;
        this.customer = customer;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MobileNumber that = (MobileNumber) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
