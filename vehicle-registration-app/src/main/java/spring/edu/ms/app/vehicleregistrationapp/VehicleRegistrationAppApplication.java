package spring.edu.ms.app.vehicleregistrationapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableCircuitBreaker
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class VehicleRegistrationAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleRegistrationAppApplication.class, args);
	}

}
