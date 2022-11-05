package travel.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import travel.domain.Category;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttractionDto {
    private long id;
    private String name;
    private String description;
    private Category category;
}
