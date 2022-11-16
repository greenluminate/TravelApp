package travel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitListModel {
    private long tripId;

    private String role;
    private String destinationName;
    private String country;
    private String startDate;
    private String endDate;

    private List<VisitModel> visitModels;
    private List<AttractionModel> possibleAttractions;
}
