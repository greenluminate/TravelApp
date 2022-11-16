package travel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewListModel {
    private String role;
    private String name;
    private String category;
    private String description;

    private List<ReviewModel> reviewModels;
}
