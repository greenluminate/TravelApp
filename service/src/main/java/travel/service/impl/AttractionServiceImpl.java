package travel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.domain.Attraction;
import travel.domain.Destination;
import travel.persistence.AttractionRepository;
import travel.persistence.DestinationRepository;
import travel.persistence.dto.AttractionDto;
import travel.service.AttractionService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttractionServiceImpl implements AttractionService {
    @Autowired
    private AttractionRepository attractionRepository;
    @Autowired
    private DestinationRepository destinationRepository;

    @Override
    public Attraction findAttractionById(long id) {
        return attractionRepository.findById(id).orElse(null);
    }

    @Override
    public List<AttractionDto> findAllAttractions() {
        List<Attraction> attractions = attractionRepository.findAll();

        return attractions.stream().map(this::convertAttractionToDto).collect(Collectors.toList());
    }

    private AttractionDto convertAttractionToDto(Attraction attraction) {
        AttractionDto attrDTO = new AttractionDto();
        attrDTO.setId(attraction.getId());
        attrDTO.setName(attraction.getName());
        attrDTO.setDescription(attraction.getDescription());
        attrDTO.setCategory(attraction.getCategory());
        return attrDTO;
    }

    @Override
    public void createAttraction(long destinationId, Attraction attraction) {
        Destination destination = destinationRepository.findById(destinationId).orElse(null);
        attraction.setDestination(destination);

        List<Attraction> attractions = attractionRepository.findAll();
        attraction.setId(attractions.get(attractions.size() - 1).getId() + 1);

        if (destination != null) {
            destination.getAttractions().add(attraction);
        }

        attractionRepository.save(attraction);
    }

}
