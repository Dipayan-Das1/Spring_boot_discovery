package spring.edu.ms.app.vehicleregistrationapp.remote;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;
import spring.edu.ms.app.vehicleregistrationapp.domain.VehicleRegistrationEntity;

@Slf4j
@Component
public class PersonRemoteService {
	
	@Autowired
	private PersonServiceClient personServiceClient;
	
	@HystrixCommand(fallbackMethod = "missingPersonFlow")
	public void setPersonUUID(String ssn,VehicleRegistrationEntity entity) {
		Map<String, String> personDetails = personServiceClient.getPerson(ssn, "ssn");

		personDetails.forEach((key, val) -> {
			log.info(key + " -> " + val);
		});

		String personuuid = personDetails.get("uuid");
		entity.setOwnerUUID(personuuid);
	}
	
	
	public void missingPersonFlow(String ssn,VehicleRegistrationEntity entity) {
		entity.setIsCompleted(false);
		log.info("Missing person with SSN {}. Ensure person details are uploaded ",entity.getSsn());
	}


}
