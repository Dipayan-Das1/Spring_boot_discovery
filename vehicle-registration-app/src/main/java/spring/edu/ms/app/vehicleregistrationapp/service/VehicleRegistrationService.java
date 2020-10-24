package spring.edu.ms.app.vehicleregistrationapp.service;

import java.util.List;

import spring.edu.ms.app.vehicleregistrationapp.domain.VehicleRegistrationEntity;
import spring.edu.ms.app.vehicleregistrationapp.dto.MessageDto;
import spring.edu.ms.app.vehicleregistrationapp.dto.VehicleRegistrationDto;

public interface VehicleRegistrationService {
public VehicleRegistrationDto getVehicleDetails(String registrationId);
public List<VehicleRegistrationDto> getVehicleDetailsByOwnerUUID(String person);
public List<VehicleRegistrationDto> getVehicleDetailsBySSN(String ssn);
public void updateVehicleDetails(String registrationId,VehicleRegistrationDto updateDetails);
public MessageDto createRegistration(VehicleRegistrationDto updateDetails);
public void deleteRegistration(String registrationId);
}
