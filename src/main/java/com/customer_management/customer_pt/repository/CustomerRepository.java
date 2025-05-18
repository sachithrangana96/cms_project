package com.customer_management.customer_pt.repository;

import com.customer_management.customer_pt.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query(value = "select * from customer where nic = :nic ", nativeQuery = true)
    Optional<Customer> findByNice(String nic);

}
