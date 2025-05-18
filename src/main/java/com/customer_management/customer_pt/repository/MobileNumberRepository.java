package com.customer_management.customer_pt.repository;

import com.customer_management.customer_pt.entity.MobileNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface MobileNumberRepository extends JpaRepository<MobileNumber,Long> {
}
