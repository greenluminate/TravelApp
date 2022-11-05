package travel.service.impl;

import org.springframework.stereotype.Service;
import travel.domain.Review;
import travel.domain.Trip;
import travel.persistence.TripRepository;
import travel.persistence.dto.ReviewDto;
import travel.persistence.dto.TripDto;
import travel.persistence.dto.VisitDto;
import travel.service.TripService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl implements TripService {
    private TripRepository tripRepository;

    public TripServiceImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public TripServiceImpl() {
    }


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
}
