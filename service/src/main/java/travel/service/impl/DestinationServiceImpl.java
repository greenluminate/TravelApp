package travel.service.impl;

import org.springframework.stereotype.Service;
import travel.domain.Destination;
import travel.persistence.DestinationRepository;
import travel.persistence.dto.DestinationDto;
import travel.service.DestinationService;

import java.util.List;

@Service
public class DestinationServiceImpl implements DestinationService {
    private DestinationRepository destinationRepository;

    public DestinationServiceImpl(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public DestinationServiceImpl() {
    }


    @Override
    public Destination findDestinationById(long id) {
        return null;
    }

    @Override
    public List<DestinationDto> findAllDestinations() {
        return null;
    }
}
