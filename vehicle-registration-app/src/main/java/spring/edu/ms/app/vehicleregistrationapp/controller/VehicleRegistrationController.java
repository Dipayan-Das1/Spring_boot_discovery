package spring.edu.ms.app.vehicleregistrationapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import spring.edu.ms.app.vehicleregistrationapp.dto.MessageDto;
import spring.edu.ms.app.vehicleregistrationapp.dto.VehicleRegistrationDto;
import spring.edu.ms.app.vehicleregistrationapp.service.VehicleRegistrationService;

@RestController
@RequestMapping("/registration")
public class VehicleRegistrationController {
	
	@Autowired
	private VehicleRegistrationService vehicleRegistrationService;

	@PostMapping
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public MessageDto create(@RequestBody VehicleRegistrationDto vehicle)
	{
		MessageDto message = vehicleRegistrationService.createRegistration(vehicle);
		return message;
	}
	
	@GetMapping("/{registrationId}")
	@ResponseBody
	public VehicleRegistrationDto getByRegistrationId(@PathVariable("registrationId") String registrationId)
	{
		VehicleRegistrationDto vehicle = vehicleRegistrationService.getVehicleDetails(registrationId);
		return vehicle;
	}
	
}
