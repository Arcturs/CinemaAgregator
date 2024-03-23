package ru.itmo.mega.cinema.agregator;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "Cinema Agregator",
				contact = @Contact(name = "Anastasiya Sashina")))
public class AgregatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgregatorApplication.class, args);
	}

}
