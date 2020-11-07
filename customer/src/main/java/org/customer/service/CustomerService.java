package org.customer.service;

import java.util.Optional;

import org.customer.model.CustomerModel;

public interface CustomerService {

	CustomerModel createNewCustomer(CustomerModel customerModel);
	void deleteCustomer(String custId);
	CustomerModel updateCustomer(CustomerModel customerModel);
	Optional<CustomerModel> getCustomer(String custId);
}
