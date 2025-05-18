package com.customer_management.customer_pt.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "mobile_number")
@Builder
public class MobileNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    public MobileNumber() {
    }

    public MobileNumber(Long id, String number, Customer customer) {
        this.id = id;
        this.number = number;
        this.customer = customer;
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
