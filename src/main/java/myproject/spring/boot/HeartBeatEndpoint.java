package myproject.spring.boot;


import java.util.Map;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * Created by psingh on 3/10/2017.
 */
@Component ("heartbeatEndpoint")
@Endpoint(id="heartbeat")
public class HeartBeatEndpoint {

	public Map<String, Object> heartbeat() {
	   return Map.of("status", "UP");
	}   

}