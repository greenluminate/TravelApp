package travel.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "trip")
public class Trip {
    @Id
    private long id;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;
    @OneToMany(targetEntity = Visit.class, mappedBy = "trip", fetch = FetchType.EAGER)
    private List<Visit> visits;
    @ManyToOne(targetEntity = Destination.class)
    private Destination destination;
    @ManyToOne(targetEntity = User.class)
    private User user;

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", destination=" + destination +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return id == trip.id && Objects.equals(startDate, trip.startDate) && Objects.equals(endDate, trip.endDate) && Objects.equals(destination, trip.destination) && Objects.equals(user, trip.user);
        //Objects.equals(visits, trip.visits) &&
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate, destination, user);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Trip(LocalDate startDate, LocalDate endDate, User user, Destination destination) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.visits = new ArrayList<>();
        this.destination = destination;
        this.user = user;
    }

    public Trip() {

    }
}
