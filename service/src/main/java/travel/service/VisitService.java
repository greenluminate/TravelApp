package travel.service;

import travel.domain.Visit;
import travel.persistence.dto.VisitDto;

import java.util.List;

public interface VisitService {
    Visit findVisitById(long id);

    List<VisitDto> findAllVisits();
}
