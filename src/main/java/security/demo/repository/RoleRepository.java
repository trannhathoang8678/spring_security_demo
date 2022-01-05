package security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import security.demo.repository.entity.Role;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    @Query(nativeQuery = true, value = "SELECT DISTINCT `name` FROM `ROLE`,`USER_ROLE` WHERE `USER_id` = ?1 AND `ROLE`.`id`= `ROLE_id`")
    List<String> getRolesByUserId(Integer userId);
}
