package org.account.service.impl;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.transaction.Transactional;

import org.account.feign.CustomerInterface;
import org.account.model.AccountModel;
import org.account.model.CustomerModel;
import org.account.model.CustomerResponseDTO;
import org.account.repository.AccountRepository;
import org.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	
	@Autowired
	private CustomerInterface customerInterface;

	@Override
	@Transactional
	public AccountModel createNewAccountAndUser(AccountModel accountModel) {
		CompletableFuture<AccountModel> accountFuture
		  = CompletableFuture.supplyAsync(() -> accountRepository.save(accountModel));
		AccountModel accModel=null;
		try {
			accModel = accountFuture.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CustomerModel cust=new CustomerModel();
		cust.setUserId(accModel.getAccountId());
		CompletableFuture<Void> userFuture
		  = accountFuture.thenAccept((acc) -> customerInterface.createCustomer(cust));
		return accModel;
	}

	@Override
	public void deleteAccount(String accountId) {
		accountRepository.deleteById(accountId);

	}

	@Override
	public AccountModel updateAccount(AccountModel accountModel) {
		return accountRepository.save(accountModel);
	}

	@Override
	public Optional<AccountModel> getAccount(String accountId) {
		return accountRepository.findById(accountId);
	}

	@Override
	public Optional<CustomerResponseDTO> getUserByAccountId(String custId) {
		return customerInterface.getCustomer(custId);
	}

}
