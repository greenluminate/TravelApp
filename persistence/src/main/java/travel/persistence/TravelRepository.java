package travel.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import travel.domain.Destination;
import travel.domain.Review;
import travel.domain.Trip;
import travel.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TravelRepository {
    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private AttractionRepository attractionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public List<Destination> getDestinations() {
        return destinationRepository.findAll();
    }

    public List<Trip> getTrips() {
        return tripRepository.findAll();
    }

    public List<Review> getReviews() {
        return  reviewRepository.findAll();
    }
}