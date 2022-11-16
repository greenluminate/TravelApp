package travel.service.impl;

import org.springframework.stereotype.Service;
import travel.domain.Review;
import travel.domain.Visit;
import travel.persistence.VisitRepository;
import travel.persistence.dto.ReviewDto;
import travel.persistence.dto.VisitDto;
import travel.service.VisitService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitServiceImpl implements VisitService {
    private VisitRepository visitRepository;

    public VisitServiceImpl(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    public VisitServiceImpl() {
    }

    @Override
    public Visit findVisitById(long id) {
        return visitRepository.findById(id).orElse(null);
    }

    @Override
    public List<VisitDto> findAllVisits() {
        return visitRepository.findAll().stream()
                .map(this::convertVisitToDto).collect(Collectors.toList());
    }

    private VisitDto convertVisitToDto(Visit visit) {
        VisitDto visitDTO = new VisitDto();
        visitDTO.setId(visit.getId());
        visitDTO.setVisitDate(visit.getVisitDate());
        visitDTO.setAttractionId(visit.getAttraction().getId());
        return visitDTO;
    }
}
