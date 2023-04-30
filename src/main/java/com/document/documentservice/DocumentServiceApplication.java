package com.document.documentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DocumentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocumentServiceApplication.class, args);

		Dictionary dictionary = new Dictionary();
		dictionary.initializeDictionary();
	}

}
