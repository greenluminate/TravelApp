package travel.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DestinationModel {

    private long id;

    // Longest place name: Taumatawhakatangihangakoauauotamateaturipukakapikimaungahoronukupokaiwhenuakitanatahu
    // => 85 character long
    // Shortest place names: https://www.geographyrealm.com/longest-shortest-geographical-names-world/
    // => 1 character long
    @Size(min = 1, max = 85, message = "Name length must be between 1 and 85.")
    @NotBlank(message = "Name field must not be blank!")
    private String name;

    // Longest country name: The United Kingdom of Great Britain and Northern Ireland => 56 character long
    // Shortest country names: Chad, Cuba, Fiji, Iran => 4 character long
    @Size(min = 4, max = 56, message = "Country length must be between 4 and 56.")
    @NotBlank(message = "Country field must not be blank!")
    private String country;

    private int numberOfAttractions;
}
