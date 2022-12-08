package travel.service;

import travel.domain.Destination;

import java.util.List;

public interface DestinationService {
    List<Destination> getDestinations();

    Destination getDestinationById(long destinationId);

    void createDestination(Destination destination);
}
