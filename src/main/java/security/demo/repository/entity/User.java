package security.demo.repository.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "`USER`")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Access(AccessType.PROPERTY)
    private Integer id;
    @Column
    private String username;
    @Column(name = "encrypted_password")
    private String encryptedPassword;
    @Column
    private String role;
    @Column
    private String email;
    @Column(name = "is_disable", insertable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean enable;
    @ManyToMany
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_id"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_id"))
    Set<Role> roles;

    public User(String username, String email, String encryptedPassword) {
        this.username = username;
        this.encryptedPassword = encryptedPassword;
        this.email = email;
    }
}
