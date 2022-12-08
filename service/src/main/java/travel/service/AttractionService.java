package travel.service;

import travel.domain.Attraction;
import travel.persistence.dto.AttractionDto;

import java.util.List;

public interface AttractionService {
    Attraction findAttractionById(long id);

    List<AttractionDto> findAllAttractions();

    void createAttraction(long destinationId, Attraction attraction);
}
