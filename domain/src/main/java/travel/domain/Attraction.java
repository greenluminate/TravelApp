package travel.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "attraction")
public class Attraction {
    @Id
    private long id;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne(targetEntity = Destination.class)
    private Destination destination;
    @OneToMany(targetEntity = Visit.class, mappedBy = "attraction", fetch = FetchType.EAGER)
    private List<Visit> visits;
    @OneToMany(targetEntity = Review.class, mappedBy = "attraction", fetch = FetchType.EAGER)
    private List<Review> reviews;

    @Override
    public String toString() {
        return "Attraction{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attraction that = (Attraction) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(description, that.description) && category == that.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, category);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Attraction(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.reviews = new ArrayList<>();
        this.visits = new ArrayList<>();
    }

    public Attraction() {

    }
}
