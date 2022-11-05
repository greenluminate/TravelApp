package travel.service;

import travel.domain.Trip;
import travel.persistence.dto.TripDto;

import java.util.List;

public interface TripService {
    Trip findTripById(long id);

    List<TripDto> findAllTrips();
}
