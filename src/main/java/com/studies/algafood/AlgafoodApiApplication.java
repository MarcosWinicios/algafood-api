package com.studies.algafood;

import com.studies.algafood.infrastructure.repository.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class AlgafoodApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgafoodApiApplication.class, args);
		/*
		ApplicationContext applicationContext =  SpringApplication.run(AlgafoodApiApplication.class, args);

		String[] allBeansNames = applicationContext.getBeanDefinitionNames();
		Arrays.stream(allBeansNames).forEach(System.out::println);

		System.out.println(allBeansNames.length);
		System.out.println(applicationContext.getBeanDefinitionCount());
		*/

	}

}
