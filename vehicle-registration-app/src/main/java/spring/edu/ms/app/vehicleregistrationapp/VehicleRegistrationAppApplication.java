package spring.edu.ms.app.vehicleregistrationapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class VehicleRegistrationAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleRegistrationAppApplication.class, args);
	}

}
