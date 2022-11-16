package travel.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitModel {

    @NotNull(message = "Attraction field must not be blank!")
    private long attractionId;

    private long tripId;

    private String attractionName;

    @NotNull(message = "Visit date field Must not be null!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate visitDate;

    private LocalDate minDate;

    private LocalDate maxDate;
}
