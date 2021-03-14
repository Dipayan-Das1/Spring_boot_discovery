package spring.edu.ms.app.vehicleregistrationapp.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;
import spring.edu.ms.app.vehicleregistrationapp.domain.StateRegistrationEntity;
import spring.edu.ms.app.vehicleregistrationapp.domain.VehicleRegistrationEntity;
import spring.edu.ms.app.vehicleregistrationapp.dto.MessageDto;
import spring.edu.ms.app.vehicleregistrationapp.dto.VehicleRegistrationDto;
import spring.edu.ms.app.vehicleregistrationapp.exception.RegistrationNotFoundException;
import spring.edu.ms.app.vehicleregistrationapp.exception.RegistrationProcessingException;
import spring.edu.ms.app.vehicleregistrationapp.remote.PersonRemoteService;
import spring.edu.ms.app.vehicleregistrationapp.repository.StateRegistrationRepository;
import spring.edu.ms.app.vehicleregistrationapp.repository.VehicleRegistrationRepository;
import spring.edu.ms.app.vehicleregistrationapp.service.VehicleRegistrationService;

@Slf4j
@Service
public class VehicleRegistrationServiceImpl implements VehicleRegistrationService {

	@Autowired
	private VehicleRegistrationRepository vehicleRegistrationRepo;

	@Autowired
	private StateRegistrationRepository stateRegRepository;
	
	@Autowired
	private PersonRemoteService personRemoteService;

	@Override
	public VehicleRegistrationDto getVehicleDetails(String registrationId) {
		VehicleRegistrationEntity registration = vehicleRegistrationRepo.findByRegistrationId(registrationId);
		if (registration == null) {
			throw RegistrationNotFoundException.byRegistrationId(registrationId);
		}
		VehicleRegistrationDto dto = new VehicleRegistrationDto();
		BeanUtils.copyProperties(registration, dto);
		return dto;
	}

	@Override
	public List<VehicleRegistrationDto> getVehicleDetailsByOwnerUUID(String person) {
		List<VehicleRegistrationEntity> registrations = vehicleRegistrationRepo.findByOwnerUUID(person);
		if (registrations == null) {
			throw RegistrationNotFoundException.byOwnerUUID(person);
		}
		List<VehicleRegistrationDto> result = registrations.stream().map(registration -> {
			VehicleRegistrationDto registrationDto = new VehicleRegistrationDto();
			BeanUtils.copyProperties(registration, registrationDto);
			return registrationDto;
		}).collect(Collectors.toList());
		return result;
	}

	@Override
	public List<VehicleRegistrationDto> getVehicleDetailsBySSN(String ssn) {
		List<VehicleRegistrationEntity> registrations = vehicleRegistrationRepo.findBySsn(ssn);
		if (registrations == null) {
			throw RegistrationNotFoundException.bySSN(ssn);
		}
		List<VehicleRegistrationDto> result = registrations.stream().map(registration -> {
			VehicleRegistrationDto registrationDto = new VehicleRegistrationDto();
			BeanUtils.copyProperties(registration, registrationDto);
			return registrationDto;
		}).collect(Collectors.toList());
		return result;
	}

	@Override
	public void updateVehicleDetails(String registrationId, VehicleRegistrationDto updateDetails) {
		VehicleRegistrationEntity registration = vehicleRegistrationRepo.findByRegistrationId(registrationId);
		if (registration == null) {
			throw RegistrationNotFoundException.byRegistrationId(registrationId);
		}

		registration.setModelDetails(updateDetails.getModelDetails());
		registration.setVehicleType(updateDetails.getVehicleType());
		registration.setVehicleWheelType(updateDetails.getVehicleWheelType());
		vehicleRegistrationRepo.save(registration);

	}

	@Override
	@Transactional(readOnly = false)
	public MessageDto createRegistration(VehicleRegistrationDto createDetails) {

		VehicleRegistrationEntity registration = vehicleRegistrationRepo
				.findByChassisNumber(createDetails.getChassisNumber());

		if (registration != null) {
			throw new RegistrationProcessingException(String.format(
					"Vehicle number with chassis number %s is already registered", createDetails.getChassisNumber()));
		}

		StateRegistrationEntity stateRegdetails = stateRegRepository.findByState(createDetails.getRegistartionState());
		if (stateRegdetails == null) {
			throw new RegistrationProcessingException(
					"Registration not possible for state " + createDetails.getRegistartionState());
		}

		registration = new VehicleRegistrationEntity();
		BeanUtils.copyProperties(createDetails, registration);
		registration.setRegistrationId(getRegistrationId(stateRegdetails));

		personRemoteService.setPersonUUID(createDetails.getSsn(), registration);
		vehicleRegistrationRepo.save(registration);

		stateRegRepository.save(stateRegdetails);

		BeanUtils.copyProperties(registration, createDetails);

		MessageDto message = new MessageDto("Registration created with id " + registration.getRegistrationId(),
				LocalDateTime.now());

		return message;
	}



	private String getRegistrationId(StateRegistrationEntity stateRegdetails) {
		String regId = stateRegdetails.getNextRegistrationId();

		String[] regComponents = regId.split("-");
		Integer serial = Integer.parseInt(regComponents[2]);
		Integer serial2 = Integer.parseInt(regComponents[1]);
		if (serial < 9999) {
			++serial;
		} else {
			serial = 1;
			++serial2;
		}

		stateRegdetails.setNextRegistrationId(String.format("%s-%03d-%04d", regComponents[0], serial2, serial));

		return regId;
	}

	@Override
	public void deleteRegistration(String registrationId) {
	}

}
