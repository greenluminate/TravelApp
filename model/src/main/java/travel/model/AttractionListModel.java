package travel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttractionListModel {
    private long destinationId;

    private String name;
    private String country;
    private String role;

    private List<AttractionModel> attractionModels;

}
