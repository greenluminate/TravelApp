package travel.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("travel.domain")
@EnableJpaRepositories(basePackages = {"travel.persistence"})
@SpringBootApplication(scanBasePackages = {
        "travel.app", "travel.controller", "travel.model",
        "travel.persistence", "travel.security", "travel.service",
        "travel.transformer", "travel.validator"
})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
