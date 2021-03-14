package spring.edu.ms.app.personservice.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PersonEventListener implements ApplicationListener<PersonEvent>{

	@Override
	public void onApplicationEvent(PersonEvent event) {
		log.info("received event of type "+event.getEventType()+" and person detail is "+event.getData().getSsn());
	}

}
