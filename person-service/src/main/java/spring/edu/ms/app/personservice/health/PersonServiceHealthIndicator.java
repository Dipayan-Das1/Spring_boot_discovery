package spring.edu.ms.app.personservice.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class PersonServiceHealthIndicator implements HealthIndicator{

	@Autowired
	private ServiceProperties serviceProperties;
	
	@Override
	public Health health() {
		System.out.println("Inside health");
		
		return Health.up().withDetail("info", "Person service is up and running").build();
	}

}
