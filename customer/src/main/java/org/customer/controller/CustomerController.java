package org.customer.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.customer.model.CustomerModel;
import org.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/{custId}")
	public ResponseEntity<CustomerModel> getCustomerById(@PathVariable("custId") String custId) throws Exception {
		CustomerModel custModel = customerService.getCustomer(custId)
				.orElseThrow(() -> new Exception("Customer not found"));
		return ResponseEntity.ok().body(custModel);
	}

	@PostMapping("/add")
	public CustomerModel addNewCustomer(@Valid @RequestBody CustomerModel customerModel) {
		return customerService.createNewCustomer(customerModel);
	}

	@DeleteMapping("/{custId}")
	public Map<String, Boolean> deleteCustomer(@PathVariable(value = "custId") String custId) throws Exception {
		customerService.deleteCustomer(custId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@PutMapping("/{custId}")
	public ResponseEntity<CustomerModel> updateCustomer(@PathVariable(value = "custId") String custId,
			@Valid @RequestBody CustomerModel custModel) throws Exception {
		CustomerModel customerModel = customerService.getCustomer(custId)
				.orElseThrow(() -> new Exception("Customer not found for this id :: " + custId));

		customerModel.setUserName(custModel.getUserName());
		customerModel.setUserPhone(custModel.getUserPhone());
		customerModel.setUserGender(custModel.getUserGender());
		final CustomerModel updatedEmployee = customerService.updateCustomer(customerModel);
		return ResponseEntity.ok(updatedEmployee);
	}
}
