package security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import security.demo.repository.entity.ERole;
import security.demo.repository.entity.Role;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(ERole roleMember);
}
