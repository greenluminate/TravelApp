package travel.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "destination")
public class Destination {
    @Id
    private long id;
    private String name;
    private String country;
    @OneToMany(targetEntity = Attraction.class, mappedBy = "destination", fetch = FetchType.EAGER)
    private List<Attraction> attractions;
    @OneToMany(targetEntity = Trip.class, mappedBy = "destination", fetch = FetchType.EAGER)
    private List<Trip> trips;

    @Override
    public String toString() {
        return "Destination{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Destination that = (Destination) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Attraction> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<Attraction> attractions) {
        this.attractions = attractions;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public Destination(String name, String country) {
        this.name = name;
        this.country = country;
        this.attractions = new ArrayList<>();
        this.trips = new ArrayList<>();
    }

    public Destination() {

    }
}
