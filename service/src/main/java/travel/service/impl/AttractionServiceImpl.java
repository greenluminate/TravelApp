package travel.service.impl;

import org.springframework.stereotype.Service;
import travel.domain.Attraction;
import travel.persistence.AttractionRepository;
import travel.persistence.dto.AttractionDto;
import travel.service.AttractionService;

import java.util.List;

@Service
public class AttractionServiceImpl implements AttractionService {
    private AttractionRepository attractionRepository;

    public AttractionServiceImpl(AttractionRepository attractionRepository) {
        this.attractionRepository = attractionRepository;
    }

    public AttractionServiceImpl() {
    }


    @Override
    public Attraction findAttractionById(long id) {
        return null;
    }

    @Override
    public List<AttractionDto> findAllAttractions() {
        return null;
    }
}
