package deeplook.server.domain.user.repository;

import deeplook.server.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByOid(String oid);
    Optional<User> findById(Long id);
}
