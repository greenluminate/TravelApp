package travel.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import travel.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
