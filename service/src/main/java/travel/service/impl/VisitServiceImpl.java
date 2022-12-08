package travel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.domain.Visit;
import travel.persistence.VisitRepository;
import travel.persistence.dto.VisitDto;
import travel.service.VisitService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitServiceImpl implements VisitService {
    @Autowired
    private VisitRepository visitRepository;

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

    @Override
    public void createVisit(Visit visit) {
        List<Visit> visits = visitRepository.findAll();
        if (visits.size() == 0) {
            visit.setId(1);
        } else {
            visit.setId(visits.get(visits.size() - 1).getId() + 1);
        }

        visitRepository.save(visit);
    }
}
