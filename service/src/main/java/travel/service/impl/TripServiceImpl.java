package travel.service.impl;

import org.springframework.stereotype.Service;
import travel.domain.Trip;
import travel.persistence.TripRepository;
import travel.persistence.dto.TripDto;
import travel.service.TripService;

import java.util.List;

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
        return null;
    }

    @Override
    public List<TripDto> findAllTrips() {
        return null;
    }
}
