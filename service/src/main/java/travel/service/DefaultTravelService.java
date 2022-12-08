package travel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.domain.Destination;
import travel.persistence.DestinationRepository;
import travel.persistence.ReviewRepository;
import travel.persistence.TripRepository;
import travel.persistence.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultTravelService implements TravelService {
    @Autowired
    DestinationRepository destinationRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    TripRepository tripRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @Override
    public Statistics getStatistics() {
        List<Destination> destinations = destinationRepository.findAll();
        int attractionCount = destinations.stream().mapToInt(d -> d.getAttractions().size()).sum();
        int userVisitCount = tripRepository.findAll().stream()
                .filter(t -> t.getUser().getId() == userService.getLoggedInUser().getId())
                .collect(Collectors.toList()).stream()
                .mapToInt(t -> t.getVisits().size()).sum();
        int userReviewCount = (int) reviewRepository.findAll().stream()
                .filter(r -> r.getUser().getId() == userService.getLoggedInUser().getId()).count();
        return new Statistics(
                destinations.size(),
                attractionCount,
                userRepository.findAll().size(),
                reviewRepository.findAll().size(),
                userVisitCount,
                userReviewCount
        );
    }
}
