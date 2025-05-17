package com.customer_management.customer_pt.repository;

import com.customer_management.customer_pt.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
