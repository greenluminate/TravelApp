package travel.persistence.dto;


import java.time.LocalDate;
import java.util.List;


public class TripDto {
    private long id;
    private long userId;
    private long destinationId;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<VisitDto> visits;
}
