package travel.model;

import travel.domain.Category;

import travel.validator.EnumValue;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttractionModel {

    private long id;

    private long destinationId;

    @Size(min = 2, max = 100, message = "Name length must be between 2 and 100 chars (inclusive).")
    @NotBlank(message = "Name field must not be blank!")
    private String name;

    @Size(min = 2, max = 200, message = "Description length must be between 2 and 200.")
    @NotBlank(message = "Description field must not be blank!")
    private String description;

    @EnumValue(enumClass = Category.class, message = "Invalid attraction category")
    @NotBlank(message = "Category field must not be blank!")
    private String category;

    private int numOfReviews;

    private double averageRating;
}
