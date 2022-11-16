package travel.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import travel.domain.*;

import java.util.List;

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
        return reviewRepository.findAll();
    }

    public void createVisit(Visit visit) {
        if (visitRepository.findAll().size() == 0)
            visit.setId(1);
        else {
            List<Visit> visits = visitRepository.findAll();
            visit.setId(visits.get(visits.size() - 1).getId() + 1);
        }

        visitRepository.save(visit);
    }

    public void createDestination(Destination destination) {
        if (getDestinations().size() == 0)
            destination.setId(1);
        else {
            var destinations = getDestinations();
            destination.setId(destinations.get(destinations.size() - 1).getId() + 1);
        }

        destinationRepository.save(destination);
    }
}
