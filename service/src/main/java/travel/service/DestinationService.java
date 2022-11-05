package travel.service;

import travel.domain.Destination;
import travel.persistence.dto.DestinationDto;

import java.util.List;

public interface DestinationService {
    Destination findDestinationById(long id);

    List<DestinationDto> findAllDestinations();
}
