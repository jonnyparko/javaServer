package com.jonnyparko.web.webSite;

import java.util.stream.Stream;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebSiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebSiteApplication.class, args);
	}
	
    @Bean
    ApplicationRunner init(CarRepository repository) {
        return args -> {
            Stream.of("Ferrari", "Jaguar", "Porsche", "Lamborghini", "Bugatti",
                      "AMC Gremlin", "Triumph Stag", "Ford Pinto", "Yugo GV").forEach(name -> {
                Car car = new Car();
                car.setName(name);
                repository.save(car);
                System.out.println(car.getName().toString() + " " + car.getId());
            });
//            repository.findAll().forEach(System.out::println);
        };
    }

}

