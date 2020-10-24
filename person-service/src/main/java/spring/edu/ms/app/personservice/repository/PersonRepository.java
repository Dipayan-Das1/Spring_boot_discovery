package spring.edu.ms.app.personservice.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import spring.edu.ms.app.personservice.domain.Person;

@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person,Long> {
	Person findByLastName(String lastName);
	Person findByUuid(String uuid);
	Person findBySsn(String ssn);
}
