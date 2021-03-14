package spring.edu.ms.app.vehicleregistrationapp.util;

import java.util.HashMap;
import java.util.Map;

public class Constants {
public static final Map<String,String> stateCodes = new HashMap<>();

static {
	stateCodes.put("West bengal".toUpperCase(), "WB");
	stateCodes.put("Karnataka".toUpperCase(), "KA");
	stateCodes.put("ASSAM".toUpperCase(), "AS");
}

}
