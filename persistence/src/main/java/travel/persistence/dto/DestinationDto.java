package travel.persistence.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DestinationDto {
    private long id;
    private String name;
    private String country;
    private List<AttractionDto> attractions;
}
