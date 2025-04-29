package org.rajesh.jpaspecification.repo;

import org.rajesh.jpaspecification.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
