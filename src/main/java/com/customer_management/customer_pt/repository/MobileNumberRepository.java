package com.customer_management.customer_pt.repository;

import com.customer_management.customer_pt.entity.MobileNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MobileNumberRepository extends JpaRepository<MobileNumber,Long> {
}
