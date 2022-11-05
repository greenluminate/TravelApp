package travel.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import travel.domain.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
}
