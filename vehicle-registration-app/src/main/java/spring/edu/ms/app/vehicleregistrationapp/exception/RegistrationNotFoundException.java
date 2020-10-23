package spring.edu.ms.app.vehicleregistrationapp.exception;

public class RegistrationNotFoundException extends RuntimeException{
	
	public RegistrationNotFoundException(String message)
	{
		super(message);
	}
	
	public static RegistrationNotFoundException byRegistrationId(String registrationId)
	{
		return new RegistrationNotFoundException(String.format("No vehicle registration found by Id %s",registrationId));
	}
	
	public static RegistrationNotFoundException byOwnerUUID(String ownerUUID)
	{
		return new RegistrationNotFoundException(String.format("No vehicle registration found by owner UUID %s",ownerUUID));
	}
	
	public static RegistrationNotFoundException bySSN(String ssn)
	{
		return new RegistrationNotFoundException(String.format("No vehicle registration found by owner SSN %s",ssn));
	}

}
