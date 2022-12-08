package travel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.domain.Trip;
import travel.persistence.TripRepository;
import travel.persistence.UserRepository;
import travel.persistence.dto.TripDto;
import travel.persistence.dto.VisitDto;
import travel.service.TripService;
import travel.service.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl implements TripService {
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Override
    public Trip findTripById(long id) {
        return tripRepository.findById(id).orElse(null);
    }

    @Override
    public List<TripDto> findAllTrips() {
        return tripRepository.findAll().stream()
                .map(this::convertTripToDto).collect(Collectors.toList());
    }

    @Override
    public List<TripDto> findUserTrips(long userId) {
        return tripRepository.findAll().stream()
                .filter(t -> t.getUser().getId() == userId).collect(Collectors.toList()).stream()
                .map(this::convertTripToDto).collect(Collectors.toList());
    }

    private TripDto convertTripToDto(Trip trip) {
        TripDto tripDTO = new TripDto();
        tripDTO.setId(trip.getId());
        tripDTO.setEndDate(trip.getEndDate());
        tripDTO.setStartDate(trip.getStartDate());
        tripDTO.setUserId(trip.getUser().getId());
        tripDTO.setDestinationId(trip.getDestination().getId());
        tripDTO.setVisits(trip.getVisits().stream().map(v ->
        {
            VisitDto visitDTO = new VisitDto();
            visitDTO.setId(v.getId());
            visitDTO.setVisitDate(v.getVisitDate());
            visitDTO.setAttractionId(v.getAttraction().getId());
            return visitDTO;
        }).collect(Collectors.toUnmodifiableList()));
        return tripDTO;
    }

    @Override
    public List<Trip> getTrips(LocalDate startDate, LocalDate endDate) {
        return tripRepository.findAll()
                .stream().filter(t ->
                        t.getStartDate().compareTo(startDate) >= 0
                                && t.getEndDate().compareTo(endDate) <= 0)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public void createTrip(Trip trip) {
        if (trip.getDestination() == null)
            throw new IllegalArgumentException("Trip must has a destination but it is null!");

        trip.setId(tripRepository.findAll().get(tripRepository.findAll().size() - 1).getId() + 1);
        trip.setUser(userService.getLoggedInUser());
        tripRepository.save(trip);
    }
}
