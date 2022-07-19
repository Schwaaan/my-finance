package com.myfinance.myfinance;

import com.myfinance.myfinance.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class MyFinanceApplication implements CommandLineRunner {

	@Autowired
	private AccountRepository accountRepository;


	public static void main(String[] args) {
		SpringApplication.run(MyFinanceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
