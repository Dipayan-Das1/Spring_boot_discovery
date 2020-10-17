package spring.edu.ms.app.personservice.service;

import java.util.List;

import spring.edu.ms.app.personservice.dto.PersonDto;

public interface PersonService {

	PersonDto createPerson(PersonDto model);

	PersonDto updatePerson(PersonDto model);

	PersonDto getPerson(String uuid);
	
	List<PersonDto> getPersons(int size,int page);

	void deletePerson(String uuid);

}
