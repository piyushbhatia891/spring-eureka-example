package org.customer.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.customer.model.CustomerModel;
import org.customer.repository.CustomerRepository;
import org.customer.service.impl.CustomerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {
	
	private static final String CUST_ID = "123";

	@InjectMocks
	private CustomerServiceImpl customerServiceImpl;
	
	@Mock
	private CustomerRepository customerRepository;
	
	CustomerModel custModel;
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		custModel=new CustomerModel();
		custModel.setUserId("12");
		custModel.setUserName("test");
		custModel.setUserPhone("12-34-56");
        custModel.setUserGender("male");
	}
	
	@Test
	public void createNewCustomer() {
		assertThat(customerServiceImpl.createNewCustomer(custModel))
		.isEqualTo(Optional.of(custModel));
		verify(customerRepository).save(custModel);
	}
	
	@Test
	public void getCustomer() {
		assertThat(customerServiceImpl.getCustomer(CUST_ID))
		.isEqualTo(Optional.of(custModel));
		verify(customerRepository).findById(CUST_ID);
	}
	
	@Test
	public void deleteCustomer() {
		doNothing().when(customerServiceImpl).deleteCustomer(CUST_ID);
		verify(customerRepository).deleteById(CUST_ID);
	}

}
