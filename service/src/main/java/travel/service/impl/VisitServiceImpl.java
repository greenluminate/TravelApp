package travel.service.impl;

import org.springframework.stereotype.Service;
import travel.domain.Visit;
import travel.persistence.VisitRepository;
import travel.persistence.dto.VisitDto;
import travel.service.VisitService;

import java.util.List;

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
        return null;
    }

    @Override
    public List<VisitDto> findAllVisits() {
        return null;
    }
}
