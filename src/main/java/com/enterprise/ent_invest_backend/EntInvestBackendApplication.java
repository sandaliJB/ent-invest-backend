package com.enterprise.ent_invest_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.authentication.ott.JdbcOneTimeTokenService;

@SpringBootApplication
public class EntInvestBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntInvestBackendApplication.class, args);
	}

}