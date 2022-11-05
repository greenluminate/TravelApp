package travel.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    private long id;
    @Column(name = "full_name")
    private String fullName;
    @OneToMany(targetEntity = Trip.class, mappedBy = "user", fetch = FetchType.EAGER)
    private List<Trip> trips;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(targetEntity = Review.class, mappedBy = "user", fetch = FetchType.EAGER)
    private List<Review> reviews;

    @Embedded
    private Credentials credentials;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", role=" + role +
                ", credentials=" + credentials +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(fullName, user.fullName) && role == user.role && Objects.equals(credentials, user.credentials);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, role, credentials);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public User(String fullName, List<Trip> trips, Role role, List<Review> reviews, Credentials credentials) {
        this.fullName = fullName;
        this.trips = trips;
        this.role = role;
        this.reviews = reviews;
        this.credentials = credentials;
    }

    public User() {
    }
}
