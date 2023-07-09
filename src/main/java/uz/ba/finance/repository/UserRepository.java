package uz.ba.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ba.finance.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long > {

    Boolean existsAllByUsername(String username);

    Optional<User> findByUsername(String username);
}
