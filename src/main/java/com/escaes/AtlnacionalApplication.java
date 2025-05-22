package com.escaes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class AtlnacionalApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.configure().ignoreIfMissing()
			.filename(".env")
			.load();

				dotenv.entries().forEach(entry ->
				System.setProperty(entry.getKey(), entry.getValue())
			);
		SpringApplication.run(AtlnacionalApplication.class, args);

		
	}

}
