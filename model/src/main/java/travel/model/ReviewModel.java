package travel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewModel {
    private long id;
    private int rating;
    private String comment;
    private String reviewer;
}
