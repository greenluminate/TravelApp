package travel.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import travel.domain.Destination;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {
}
