package org.api.gateway.feign;

import java.util.Optional;

import org.api.gateway.model.AdminResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@FeignClient(name = "customer-service")
@RequestMapping(name = "/api")
public interface AdminInterface {

	@RequestMapping(value = "/get-user/{username}")
    Optional<AdminResponseDTO> getUser(@PathVariable("username") String username);
}
