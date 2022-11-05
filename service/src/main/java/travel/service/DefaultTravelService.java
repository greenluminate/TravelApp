package travel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import travel.domain.*;
import travel.persistence.TravelRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DefaultTravelService implements TravelService {
    private User loggedInUser;

    @Autowired
    TravelRepository travelRepository;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultTravelService that = (DefaultTravelService) o;
        return Objects.equals(loggedInUser, that.loggedInUser) && Objects.equals(travelRepository, that.travelRepository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loggedInUser, travelRepository);
    }

    public DefaultTravelService(TravelRepository travelRepository) {
        this.travelRepository = travelRepository;
    }

    @Override
    public User authenticateUser(Credentials credentials) {
        User user = travelRepository.getUsers().stream().filter(u ->
                        u.getCredentials().equals(credentials))
                .findFirst().orElse(null);

        if (user == null)
            throw new AuthenticationException("User does not exits with the given credentials!");

        loggedInUser = user;

        return user;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    @Override
    public Statistics getStatistics() {
        List<Destination> destinations = travelRepository.getDestinations();
        int attractionCount = destinations.stream().mapToInt(d -> d.getAttractions().size()).sum();
        int userVisitCount = travelRepository.getTrips().stream()
                .filter(t -> t.getUser().getId() == loggedInUser.getId())
                .collect(Collectors.toList()).stream()
                .mapToInt(t -> t.getVisits().size()).sum();
        int userReviewCount = (int) travelRepository.getReviews().stream().filter(r -> r.getUser().getId() == loggedInUser.getId()).count();
        return new Statistics(
                destinations.size(),
                attractionCount,
                travelRepository.getUsers().size(),
                travelRepository.getReviews().size(),
                userVisitCount,
                userReviewCount
        );
    }

    @Override
    public List<Destination> getDestinations() {
        return travelRepository.getDestinations();
    }

    @Override
    public List<Review> getReviews(long attractionId) {
        return travelRepository.getReviews().stream().filter(r ->
                        r.getAttraction().getId() == attractionId)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public void createAttraction(long destinationId, Attraction attraction) {
        Destination destination = travelRepository.getDestinations()
                .stream().filter(d -> d.getId() == destinationId)
                .findFirst().orElse(null);

        List<Attraction> attractions = new ArrayList<>();
        travelRepository.getDestinations().forEach(d -> d.getAttractions().forEach(a -> {
            a.setDestination(d);
            a.setVisits(new ArrayList<>());
            a.setReviews(new ArrayList<>());
            attractions.add(a);
        }));

        attraction.setId(attractions.get(attractions.size() - 1).getId() + 1);
        if (destination != null) {
            destination.getAttractions().add(attraction);
        }

    }

    @Override
    public List<Trip> getTrips(LocalDate startDate, LocalDate endDate) {
        return travelRepository.getTrips()
                .stream().filter(t ->
                        t.getStartDate().compareTo(startDate) >= 0
                                && t.getEndDate().compareTo(endDate) <= 0)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public void createTrip(Trip trip) {
        if (trip.getDestination() == null)
            throw new IllegalArgumentException("Trip must has a destination but it is null!");

        trip.setId(travelRepository.getTrips().get(travelRepository.getTrips().size() - 1).getId() + 1);
        trip.setUser(loggedInUser);
        travelRepository.getTrips().add(trip);
    }
}
