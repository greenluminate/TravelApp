package travel.persistence.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisitDto {
    private long id;
    private long attractionId;
    private LocalDate visitDate;
}
