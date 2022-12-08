package travel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.domain.Destination;
import travel.persistence.DestinationRepository;
import travel.persistence.dto.AttractionDto;
import travel.persistence.dto.DestinationDto;
import travel.service.DestinationService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DestinationServiceImpl implements DestinationService {
    @Autowired
    private DestinationRepository destinationRepository;

    /*@Override
    public Destination findDestinationById(long id) {
        return destinationRepository.findById(id).orElse(null);
    }

    @Override
    public List<DestinationDto> findAllDestinations() {
        return destinationRepository.findAll().stream()
                .map(this::convertDestinationToDto).collect(Collectors.toList());
    }*/

    private DestinationDto convertDestinationToDto(Destination destination) {
        DestinationDto destDTO = new DestinationDto();
        destDTO.setId(destination.getId());
        destDTO.setName(destination.getName());
        destDTO.setAttractions(destination.getAttractions().stream().map(a -> {
            AttractionDto attrDTO = new AttractionDto();
            attrDTO.setId(a.getId());
            attrDTO.setName(a.getName());
            attrDTO.setDescription(a.getDescription());
            attrDTO.setCategory(a.getCategory());
            return attrDTO;
        }).collect(Collectors.toUnmodifiableList()));
        destDTO.setCountry(destination.getCountry());
        return destDTO;
    }

    @Override
    public List<Destination> getDestinations() {
        return destinationRepository.findAll();
    }

    @Override
    public Destination getDestinationById(long destinationId) {
        return destinationRepository.findById(destinationId).orElse(null);
    }

    @Override
    public void createDestination(Destination destination) {
        List<Destination> destinations = destinationRepository.findAll();
        if (getDestinations().size() == 0)
            destination.setId(1);
        else {
            destination.setId(destinations.get(destinations.size() - 1).getId() + 1);
        }

        destinationRepository.save(destination);
    }
}
