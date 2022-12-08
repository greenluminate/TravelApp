package travel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripModel {

    private long id;

    private long destinationId;

    private String destinationName;

    private String country;

    @NotNull(message = "Start date field Must not be null!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String startDate;

    @NotNull(message = "End date field Must not be null!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String endDate;
}
