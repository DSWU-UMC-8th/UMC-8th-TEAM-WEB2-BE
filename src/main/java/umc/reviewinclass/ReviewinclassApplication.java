package umc.reviewinclass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ReviewinclassApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewinclassApplication.class, args);
	}

}
