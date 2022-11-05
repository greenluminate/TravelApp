package travel.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import travel.domain.Visit;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
}
