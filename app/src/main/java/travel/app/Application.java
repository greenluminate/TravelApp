package travel.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import travel.persistence.InvalidReferenceException;
import travel.service.Statistics;
import travel.service.TravelService;
import travel.domain.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication(scanBasePackages = {"travel.app", "travel.view", "travel.service", "travel.persistence"})
@EntityScan("travel.domain")
@EnableJpaRepositories(basePackages = {"travel.persistence"})
public class Application {
    /*@Autowired
    private View view;*/
    @Autowired
    private TravelService travelService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private void runApp() throws InvalidReferenceException, IOException {

        User user = loginUser();
        if (user != null) {
        } else {
        }
    }

    private User loginUser() throws IOException {
        Credentials credentials = new Credentials();

        return travelService.authenticateUser(credentials);
    }

    private void showStatistics(User user) {
        Statistics statistics = travelService.getStatistics();

        if (user.getRole() != Role.ADMIN) {

        }
    }

    private void menuInteractions(User user) throws IOException {

    }

    private void userTripsInteractions(User user) throws IOException {

    }

    private void tripCreationInteractions(User user) throws IOException {

    }

    private void createAttractionInteractions(User user) throws IOException {

    }

    private void viewReviewsInteractions(User user) throws IOException {

    }

    private void viewDestinationsAndAttractions(User user) throws IOException {

    }
}
