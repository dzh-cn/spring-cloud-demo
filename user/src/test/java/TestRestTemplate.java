import com.spring.demo.user.entity.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class TestRestTemplate {
	static RestTemplate restTemplate = new RestTemplate();
	static Logger logger = LoggerFactory.getLogger(TestRestTemplate.class);

	@Test
	public void testRest() {
		User user = restTemplate.getForObject("http://localhost:8081/user/detail/1", User.class);
		logger.info("user info: {}", user);
	}
}
