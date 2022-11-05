package travel.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import travel.domain.Attraction;

@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Long> {
}
