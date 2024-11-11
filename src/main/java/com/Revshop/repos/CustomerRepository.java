package com.Revshop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Revshop.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>{

}
