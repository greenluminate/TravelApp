package travel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.domain.Attraction;
import travel.persistence.AttractionRepository;
import travel.persistence.dto.AttractionDto;
import travel.service.AttractionService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttractionServiceImpl implements AttractionService {
    private AttractionRepository attractionRepository;

    public AttractionServiceImpl(AttractionRepository attractionRepository) {
        this.attractionRepository = attractionRepository;
    }

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
}
