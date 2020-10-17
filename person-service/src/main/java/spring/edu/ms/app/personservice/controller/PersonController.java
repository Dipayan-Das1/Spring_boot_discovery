package spring.edu.ms.app.personservice.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import spring.edu.ms.app.personservice.dto.MessageDto;
import spring.edu.ms.app.personservice.dto.PersonDto;
import spring.edu.ms.app.personservice.service.PersonService;

@Slf4j
@RestController
@RequestMapping(path = "/v1/person")
public class PersonController {

	@Autowired
	private PersonService personService;

	@GetMapping(path = "/{uuid}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public PersonDto getPerson(@PathVariable String uuid) {
		log.info(String.format("Get person with %s",uuid));
		return personService.getPerson(uuid);
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<PersonDto> getPersons(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		log.info(String.format("Get persons with page %d and size %d",page,size));
		if(size < 0)
			throw new IllegalArgumentException("Page size cannot be negative");
		if(page < 0)
			throw new IllegalArgumentException("Page number cannot be negative");
		return personService.getPersons(size, page);
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(HttpStatus.CREATED)
	public MessageDto createPerson(@RequestBody PersonDto request) {
		log.info("Create person");
		request = personService.createPerson(request);
		return new MessageDto(String.format("Person with uuid %s created successfully", request.getUuid()));
	}

	@PutMapping(path = "/{uuid}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public PersonDto updatePerson(@PathVariable String uuid, @RequestBody PersonDto request) {
		log.info("Update person");
		request.setUuid(uuid);
		request = personService.updatePerson(request);
		return request;
	}

	@DeleteMapping(path = "/{uuid}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public MessageDto deletePerson(@PathVariable String uuid) {
		log.info("Delete person");
		personService.getPerson(uuid);
		return new MessageDto(String.format("Person with uuid %s deleted successfully", uuid));
	}

}
