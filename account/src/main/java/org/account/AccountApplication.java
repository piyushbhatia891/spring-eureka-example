package org.account;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
//@EnableAsync
public class AccountApplication 
{
    public static void main( String[] args )
    {
        SpringApplication.run(AccountApplication.class, args);
    }
    
	/*
	 * @Bean public Executor asyncExecutor() { ThreadPoolTaskExecutor executor = new
	 * ThreadPoolTaskExecutor(); return executor; }
	 */
}
