package com.bowwow.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.bowwow.common.entity", "com.bowwow.admin.user"})
public class BowwowAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(BowwowAdminApplication.class, args);
	}

}
