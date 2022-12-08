package travel.service;

import travel.domain.Trip;
import travel.persistence.dto.TripDto;

import java.time.LocalDate;
import java.util.List;

public interface TripService {
    Trip findTripById(long id);

    List<TripDto> findAllTrips();

    List<TripDto> findUserTrips(long userId);

    List<Trip> getTrips(LocalDate startDate, LocalDate endDate);

    void createTrip(Trip trip);
}
