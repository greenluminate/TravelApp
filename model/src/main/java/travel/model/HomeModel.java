package travel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeModel {
    private String fullName;
    private String role;

    private int numOfDest;
    private int numOfAttr;
    private int numOfUsers;
    private int numOfMyVisits;
    private int numOfMyReviews;
    private int numOfAllReviews;
}
