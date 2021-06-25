package engine.repository;

import engine.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // TODO
    User findByUsername(String username);
}
