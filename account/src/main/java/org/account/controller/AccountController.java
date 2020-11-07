package org.account.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.account.model.AccountModel;
import org.account.model.CustomerResponseDTO;
import org.account.service.AccountService;
import org.account.service.impl.AccountServiceImpl;
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
@RequestMapping("/api/account")
public class AccountController {

	@Autowired
	private AccountService accountServiceImpl;
	
	
	@GetMapping("/{accountId}")
	public ResponseEntity<AccountModel> getAccountById(@PathVariable("accountId") String accountId) throws Exception {
		AccountModel custModel = accountServiceImpl.getAccount(accountId)
				.orElseThrow(() -> new Exception("Resource not found"));
		return ResponseEntity.ok().body(custModel);
	}
	
	@GetMapping("/{accountId}/user")
	public ResponseEntity<CustomerResponseDTO> getUserByAccountId(@PathVariable("accountId") String accountId) throws Exception {
		AccountModel accModel = accountServiceImpl.getAccount(accountId)
				.orElseThrow(() -> new Exception("Resource not found"));
		
		CustomerResponseDTO customerResponseDTO = accountServiceImpl.getUserByAccountId(accModel.getCustomerId())
				.orElseThrow(() -> new Exception("Resource not found"));
		return ResponseEntity.ok().body(customerResponseDTO);
	}

	@PostMapping("/add")
	public AccountModel addNewAccount(@Valid @RequestBody AccountModel accountModel) {
		return accountServiceImpl.createNewAccountAndUser(accountModel);
	}

	@DeleteMapping("/{accountId}")
	public Map<String, Boolean> deleteAccount(@PathVariable(value = "accountId") String accountId) throws Exception {
		accountServiceImpl.deleteAccount(accountId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@PutMapping("/{accountId}")
	public ResponseEntity<AccountModel> updateAccount(@PathVariable(value = "accountId") String accountId,
			@Valid @RequestBody AccountModel accModel) throws Exception {
		AccountModel accountModel = accountServiceImpl.getAccount(accountId)
				.orElseThrow(() -> new Exception("Employee not found for this id :: " + accountId));

		accountModel.setAccountType(accModel.getAccountType());
		accountModel.setBranch(accModel.getBranch());
		accountModel.setCustomerName(accModel.getCustomerName());
		return ResponseEntity.ok(accountModel);
	}

}
