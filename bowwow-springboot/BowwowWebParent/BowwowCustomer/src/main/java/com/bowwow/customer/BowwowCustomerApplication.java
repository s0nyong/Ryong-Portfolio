package com.bowwow.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.bowwow.common.entity", "com.bowwow.customer.user"})
public class BowwowCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BowwowCustomerApplication.class, args);
	}

}
