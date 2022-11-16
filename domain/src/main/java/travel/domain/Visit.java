package travel.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "visit")
public class Visit {
    @Id
    private long id;
    @Column(name = "visit_date")
    private LocalDate visitDate;
    @ManyToOne(targetEntity = Attraction.class)
    private Attraction attraction;

    @ManyToOne(targetEntity = Trip.class)
    private Trip trip;

    public Visit(LocalDate visitDate, Attraction attraction, Trip trip) {
        this.visitDate = visitDate;
        this.attraction = attraction;
        this.trip = trip;
    }

    public Visit() {

    }

    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", visitDate=" + visitDate +
                ", attraction=" + attraction +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return id == visit.id && Objects.equals(visitDate, visit.visitDate) && Objects.equals(attraction, visit.attraction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, visitDate, attraction);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public Attraction getAttraction() {
        return attraction;
    }

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
