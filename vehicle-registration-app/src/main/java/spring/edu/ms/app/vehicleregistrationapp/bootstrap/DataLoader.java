package spring.edu.ms.app.vehicleregistrationapp.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.edu.ms.app.vehicleregistrationapp.domain.StateRegistrationEntity;
import spring.edu.ms.app.vehicleregistrationapp.repository.StateRegistrationRepository;
import spring.edu.ms.app.vehicleregistrationapp.util.Constants;

@Service
public class DataLoader implements CommandLineRunner {

	@Autowired
	private StateRegistrationRepository stateRegRepository;
	
	@Override
	@Transactional(readOnly = false)
	public void run(String... args) throws Exception {
		Constants.stateCodes.forEach((key,val) -> {
			StateRegistrationEntity reg = stateRegRepository.findByState(key);
			if(reg == null)
			{
				reg = new StateRegistrationEntity();
				reg.setState(key);
				reg.setStateShortCode(val);
				reg.setNextRegistrationId(String.format("%s-%03d-%04d", val,1,1));
				stateRegRepository.save(reg);
			}
		});
		
	}
	
	

}
