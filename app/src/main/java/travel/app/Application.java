package travel.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"travel.app", "travel.controller", "travel.model", "travel.persistence", "travel.security", "travel.service", "travel.validator"})
@EntityScan("travel.domain")
@EnableJpaRepositories(basePackages = {"travel.persistence"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
