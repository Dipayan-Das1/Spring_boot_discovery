package spring.edu.ms.app.personservice.health;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "person.service", ignoreUnknownFields = false)
@Component
@Getter
@Setter
public class ServiceProperties {
	private String name = "Person Service";
	private String description = "Person Service Description";
}
