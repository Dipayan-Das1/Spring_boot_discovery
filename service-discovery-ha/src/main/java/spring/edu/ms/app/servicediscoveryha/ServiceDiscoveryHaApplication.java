package spring.edu.ms.app.servicediscoveryha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ServiceDiscoveryHaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceDiscoveryHaApplication.class, args);
	}

}
