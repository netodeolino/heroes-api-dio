package com.br.heroesapidio;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDynamoDBRepositories
public class HeroesApiDIOApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeroesApiDIOApplication.class, args);
	}

}
