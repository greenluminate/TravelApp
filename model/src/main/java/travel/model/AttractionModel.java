package travel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import travel.domain.Category;
//import travel.validation.ValueOfEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttractionModel {
    private long id;
    private long destinationId;

    @Size(min = 3, max = 100, message = "Name length must be between 3 and 100 chars (inclusive).")
    @NotBlank(message = "Name field must not be blank!")
    private String name;

    @Size(min = 2, max = 200, message = "Description length must be between 2 and 200.")
    @NotBlank(message = "Description field must not be blank!")
    private String description;

    //@ValueOfEnum(enumClass = Category.class, message = "Invalid attraction category")
    @NotBlank(message = "Category field must not be blank!")
    private String category;

    private int numOfReviews;

    private double averageRating;
}
