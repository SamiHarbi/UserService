package rosol.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rosol.userservice.model.AppUser;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface to communicate the Service with the corresponding database using Spring Data JPA
 */
@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    List<AppUser> findByActiveTrue();
    Optional<AppUser> findByIdAndActiveTrue(Long id);
}
