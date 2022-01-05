package security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import security.demo.repository.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findOneByUsername(String username);
}
