package spring.edu.ms.app.vehicleregistrationapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VehicleRegistrationDto {
	public String ssn;
	public String ownerUUID;
	public String registrationId;
	public String vehicleType;
	public String vehicleWheelType;
	public String modelDetails;
	public String chassisNumber;
	public String registartionState;
	public String state;
}
