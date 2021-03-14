package spring.edu.ms.app.vehicleregistrationapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import spring.edu.ms.app.vehicleregistrationapp.domain.StateRegistrationEntity;

@Repository
public interface StateRegistrationRepository extends CrudRepository<StateRegistrationEntity, Long> {
	StateRegistrationEntity findByState(String state);
}
