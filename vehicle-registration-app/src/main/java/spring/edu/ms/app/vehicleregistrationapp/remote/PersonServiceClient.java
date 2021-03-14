package spring.edu.ms.app.vehicleregistrationapp.remote;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@FeignClient("person-service")
public interface PersonServiceClient {
	@GetMapping(path = "/person-service/v1/person/{identifier}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Map<String,String> getPerson(@PathVariable String identifier,@RequestParam(name = "qualifiertype",defaultValue = "uuid") String qualifierType);
}
