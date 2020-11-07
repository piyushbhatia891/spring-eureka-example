package org.account.service;

import java.util.Optional;

import org.account.model.AccountModel;
import org.account.model.CustomerModel;
import org.account.model.CustomerResponseDTO;

public interface AccountService {

	AccountModel createNewAccountAndUser(AccountModel accountModel);
	void deleteAccount(String accountId);
	AccountModel updateAccount(AccountModel accountModel);
	Optional<AccountModel> getAccount(String accountId);
	
	Optional<CustomerResponseDTO> getUserByAccountId(String accountId);
}
