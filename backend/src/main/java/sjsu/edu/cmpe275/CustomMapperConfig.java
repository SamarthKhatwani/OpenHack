package sjsu.edu.cmpe275;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

/**
 * Jackson ObjectMapper configuration class for json
 *
 * @author nirbhaykekre
 *
 */
@Component
public class CustomMapperConfig {

	/**
	 * registers Hibernate5Module in the object mapper, so that serialization of
	 * entity object won't invoke Lazy loaded entities
	 *
	 * @param mapper       instance of json ObjectMapper
	 */
	@Autowired
	public CustomMapperConfig(ObjectMapper mapper) {
		mapper.registerModule(new Hibernate5Module());
	}
}
