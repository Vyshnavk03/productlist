package com.vyshnav.product;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.annotations.OpenAPI30;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Product Serivce Rest API documentation",
				description =  "Product service REST API",
				version =  "V1",
				contact = @Contact(
						name = "Vyshnav K Saseendran",
						email = "vyshnavksaseendran03@gmail.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Sharepoint URL product Service API",
				url = "example.com"
		)
)
@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {

		SpringApplication.run(ProductApplication.class, args);
	}

}
