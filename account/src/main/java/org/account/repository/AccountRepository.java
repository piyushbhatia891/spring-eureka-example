package org.account.repository;

import org.account.model.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import feign.Param;

@Repository
public interface AccountRepository extends JpaRepository<AccountModel, String>{

	@Query(value = "SELECT * from account WHERE customerName=:name", nativeQuery = true)
    AccountModel findByCustomerName(@Param("name") String name);
}
