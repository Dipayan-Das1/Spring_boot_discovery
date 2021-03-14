package spring.edu.ms.app.vehicleregistrationapp.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import spring.edu.ms.app.vehicleregistrationapp.domain.VehicleRegistrationEntity;

@Repository
public interface VehicleRegistrationRepository extends PagingAndSortingRepository<VehicleRegistrationEntity, Long> {
	List<VehicleRegistrationEntity> findByOwnerUUID(String uuid);
	List<VehicleRegistrationEntity> findBySsn(String ssn);
	VehicleRegistrationEntity findByRegistrationId(String registrationId);
	VehicleRegistrationEntity findByChassisNumber(String registrationId);
}
