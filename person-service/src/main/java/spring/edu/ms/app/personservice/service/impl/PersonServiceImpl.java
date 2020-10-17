package spring.edu.ms.app.personservice.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import spring.edu.ms.app.personservice.domain.Person;
import spring.edu.ms.app.personservice.dto.PersonDto;
import spring.edu.ms.app.personservice.exception.PersonNotFoundException;
import spring.edu.ms.app.personservice.repository.PersonRepository;
import spring.edu.ms.app.personservice.service.PersonService;

@Service
@Slf4j
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Override
	@Transactional(readOnly = false)
	public PersonDto createPerson(PersonDto model) {
		log.info("Create person invoked");
		Person person = new Person();
		model.setUuid(UUID.randomUUID().toString());
		BeanUtils.copyProperties(model, person);
		personRepository.save(person);
		BeanUtils.copyProperties(person, model);
		return model;
	}

	@Override
	@Transactional(readOnly = false)
	public PersonDto updatePerson(PersonDto model) {
		log.info("Update person invoked");
		Person person = getPersonBuUUID(model.getUuid());
		BeanUtils.copyProperties(model, person);
		personRepository.save(person);
		return model;
	}

	@Override
	@Transactional(readOnly = true)
	public PersonDto getPerson(String uuid) {
		log.info("Get person invoked");
		Person person = getPersonBuUUID(uuid);
		PersonDto model = new PersonDto();
		BeanUtils.copyProperties(person, model);
		return model;
	}

	@Override
	@Transactional(readOnly = false)
	public void deletePerson(String uuid) {
		log.info("Get person invoked");
		Person person = getPersonBuUUID(uuid);
		personRepository.delete(person);
	}

	private Person getPersonBuUUID(String uuid) {
		Person person = personRepository.findByUuid(uuid);
		if (person == null) {
			throw PersonNotFoundException.personNotfoundByUUID(uuid);
		}
		return person;
	}

	@Override
	public List<PersonDto> getPersons(int size, int page) {
		PageRequest pageRequest = PageRequest.of(page, size);
		Page<Person> pageResponse = personRepository.findAll(pageRequest);
		List<PersonDto> persons = new LinkedList<>();
		if (pageResponse != null) {
			persons = pageResponse.stream().map(person -> {
				PersonDto personDto = new PersonDto();
				BeanUtils.copyProperties(person, personDto);
				return personDto;
			}).collect(Collectors.toList());
		}

		return persons;
	}

}
