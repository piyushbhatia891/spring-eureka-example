package org.customer.repository;

import org.customer.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, String> {
	@Query(value = "SELECT * from customer WHERE userName=:name", nativeQuery = true)
    CustomerModel findByUserName(@Param("name") String name);
	

}
