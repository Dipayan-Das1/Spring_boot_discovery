package spring.edu.ms.app.personservice.exception;

public class PersonNotFoundException extends RuntimeException{
	
	
	public PersonNotFoundException(String message)
	{
		super(message);
	}
	
	public static PersonNotFoundException personNotfoundByUUID(String uuid)
	{
		return new PersonNotFoundException(String.format("Person not forund for uuid %s",uuid));
	}
	
	public static PersonNotFoundException personNotfoundBySSN(String ssn)
	{
		return new PersonNotFoundException(String.format("Person not forund for ssn %s",ssn));
	}
}
