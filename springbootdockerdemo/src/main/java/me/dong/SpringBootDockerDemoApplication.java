package me.dong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootDockerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDockerDemoApplication.class, args);
	}

	@RequestMapping("/")
    public String home(){
        return "Hello Spring Boot Docker";
    }


}
