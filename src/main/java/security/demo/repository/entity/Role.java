package security.demo.repository.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "`ROLE`")
@Getter
@Setter
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue
    @Column(name = "", nullable = false)
    private Integer id;

    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_id"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_id"))
    private List<User> userList;
}
