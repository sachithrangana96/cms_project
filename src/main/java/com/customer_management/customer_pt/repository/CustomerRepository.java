package com.customer_management.customer_pt.repository;

import com.customer_management.customer_pt.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
