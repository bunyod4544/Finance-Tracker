package uz.ba.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.ba.finance.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}
