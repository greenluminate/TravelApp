package travel.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import travel.domain.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
