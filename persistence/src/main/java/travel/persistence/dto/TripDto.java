package travel.persistence.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripDto {
    private long id;
    private long userId;
    private long destinationId;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<VisitDto> visits;
}
