package spring.edu.ms.app.personservice.event;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import lombok.Setter;
import spring.edu.ms.app.personservice.domain.Person;

@Getter
@Setter
public class PersonEvent extends ApplicationEvent{

	private String eventType;
	private Person data;
	
	public PersonEvent(Object source,String eventType,Person data ) {
		super(source);
		this.eventType = eventType;
		this.data = data;
	}

}
