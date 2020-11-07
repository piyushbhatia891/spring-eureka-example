package org.account.feign;

import java.util.Optional;

import org.account.model.CustomerModel;
import org.account.model.CustomerResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@FeignClient(name = "customer-service")
@RequestMapping(name = "/api")
public interface CustomerInterface {

	@RequestMapping(value = "/customer/{custId}")
    Optional<CustomerResponseDTO> getCustomer(@PathVariable("custId") String custId);
	
	@RequestMapping(value = "/customer/add")
    Optional<CustomerResponseDTO> createCustomer(CustomerModel customerModel);
}
