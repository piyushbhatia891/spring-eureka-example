package org.customer.service.impl;

import java.util.Optional;

import org.customer.model.CustomerModel;
import org.customer.repository.CustomerRepository;
import org.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	@Override
	public CustomerModel createNewCustomer(CustomerModel customerModel) {
		return customerRepository.save(customerModel);
	}

	@Override
	public void deleteCustomer(String custId) {
		customerRepository.deleteById(custId);
		
	}

	@Override
	public CustomerModel updateCustomer(CustomerModel customerModel) {
		return customerRepository.save(customerModel);
	}

	@Override
	public Optional<CustomerModel> getCustomer(String custId) {
		return customerRepository.findById(custId);
		
	}

}
