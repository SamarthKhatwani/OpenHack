package sjsu.edu.cmpe275;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "sjsu.edu.cmpe275" })
public class OpenHackApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenHackApplication.class, args);
	}

}
