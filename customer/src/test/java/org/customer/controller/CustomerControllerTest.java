package org.customer.controller;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.customer.model.CustomerModel;
import org.customer.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomerControllerTest {
	
	@Mock
	private CustomerService customerService;
	
	@InjectMocks
	private CustomerController customerController;
	private static MockMvc mockMvc;
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType());
	CustomerModel custModel;
	String accountId="123";
	@Before
	public void createMock() {
		MockitoAnnotations.initMocks(this);
		mockMvc=MockMvcBuilders.standaloneSetup(customerController).build();
		custModel=new CustomerModel();
		custModel.setUserId("12");
		custModel.setUserName("test");
		custModel.setUserPhone("12-34-56");
        custModel.setUserGender("male");
	}
	
	
	@Test
	public void saveAccount() throws Exception {
		String URL = "/api" + "/account" + "/add";
		
        when(customerService.createNewCustomer(custModel))
        .thenReturn(custModel);

        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .contentType(APPLICATION_JSON_UTF8)
                .content(writeObjectToJson(custModel)))
                .andExpect(status().isOk());

        verify(customerService).createNewCustomer(custModel);
	}
	
	@Test
	public void deleteAccount() throws Exception {
		String URL = "/api" + "/account/" + accountId;
		
        doNothing().when(customerService).deleteCustomer(accountId);

        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .contentType(APPLICATION_JSON_UTF8)
                .content(writeObjectToJson(custModel)))
                .andExpect(status().isOk());

        verify(customerService).deleteCustomer(accountId);
	}
	
	@Test
	public void getAccount() throws Exception {
		String URL = "/api" + "/account/" + accountId;
		
		when(customerService.getCustomer(accountId))
        .thenReturn(java.util.Optional.ofNullable(custModel));

        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .contentType(APPLICATION_JSON_UTF8)
                .content(writeObjectToJson(custModel)))
                .andExpect(status().isOk());

        verify(customerService).deleteCustomer(accountId);
	}
	
	 private String writeObjectToJson(final Object obj) {
	        ObjectMapper objectMapper = new ObjectMapper();
	        try {
	            return objectMapper.writeValueAsString(obj);
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }

}
