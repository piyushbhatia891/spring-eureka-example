package org.customer.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.customer.model.CustomerModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	CustomerModel custModel;
	@Before
	public void setUp() {
		custModel=new CustomerModel();
		custModel.setUserId("12");
		custModel.setUserName("test");
		custModel.setUserPhone("12-34-56");
        custModel.setUserGender("male");
	}
	
	@After
	public void afterTest() {
		testEntityManager.remove(custModel);
		customerRepository.deleteAll();
	}
	
	@Test
    public void testfindById() {
		createNewCustomer();
        Optional<CustomerModel> customerModelInDb = customerRepository.findById("12");
        assertThat(customerModelInDb.get().getUserName()).isEqualTo(custModel.getUserName());
    }
	
	@Test
    public void testfindByCustomerName() {
		createNewCustomer();
        CustomerModel customerModelInDb = customerRepository.findByUserName("test");
        assertThat(customerModelInDb.getUserName()).isEqualTo(custModel.getUserName());
    }
	
	@Test
    public void testSaveCustomer() {
		createNewCustomer();
        assertNotNull(custModel);
    }
	

	private void createNewCustomer() {
		testEntityManager.persist(custModel);
	}
}
