package travel.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "review")
public class Review {
    @Id
    private long id;
    private int rating;
    private String comment;
    @ManyToOne(targetEntity = User.class)
    private User user;
    @ManyToOne(targetEntity = Attraction.class)
    private Attraction attraction;

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return id == review.id && rating == review.rating && Objects.equals(comment, review.comment) && Objects.equals(user, review.user) && Objects.equals(attraction, review.attraction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rating, comment, user, attraction);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Attraction getAttraction() {
        return attraction;
    }

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }

    public Review(int rating, String comment, Attraction attraction, User user) {
        this.rating = rating;
        this.comment = comment;
        this.user = user;
        this.attraction = attraction;
    }

    public Review() {

    }
}
