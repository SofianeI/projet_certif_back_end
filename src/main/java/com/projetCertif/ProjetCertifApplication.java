package com.projetCertif;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ProjetCertifApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetCertifApplication.class, args);
	}

}
