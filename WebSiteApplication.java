package com.jonnyparko.web.webSite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebSiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebSiteApplication.class, args);
	}
	
//    @Bean
//    ApplicationRunner init(CarRepository repository) {
//        return args -> {
//            Stream.of("Ferrari", "Jaguar", "Porsche", "Lamborghini", "Bugatti",
//                      "AMC Gremlin", "Triumph Stag", "Ford Pinto", "Yugo GV", "Subaru WRX/STI").forEach(name -> {
//                Car car = new Car();
//                car.setName(name);
//                repository.save(car);
//                System.out.println(car.getName().toString() + " " + car.getId());
//            });
////            repository.findAll().forEach(System.out::println);
//        };
//    }

}

