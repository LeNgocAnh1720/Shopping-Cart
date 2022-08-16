package poly.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
//@ComponentScan({"com.beta.replyservice", "com.beta.ruleService"})
@SpringBootApplication
public class Java6AsmApplication {

	public static void main(String[] args) {
		SpringApplication.run(Java6AsmApplication.class, args);
	}

}
