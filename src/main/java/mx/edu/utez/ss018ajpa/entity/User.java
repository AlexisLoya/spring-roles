package mx.edu.utez.ss018ajpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @Column(nullable = false, unique = true, columnDefinition = "varchar(25)")
    private String username;

    @Column(columnDefinition = "varchar(50)")
    private String name;

    @Column(columnDefinition = "varchar(50)")
    private String lastname;

    @Column(nullable = false, columnDefinition = "varchar(150)")
    private String password;

    @Column(nullable = false, columnDefinition = "tinyint default 1")
    private boolean enabled;

    // Many to Many: ROLES
    @ManyToMany
    @JoinTable(
            name = "authorities",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "authority"))
    List<Role> roles;
    public void addRole(Role role) {
        roles.add(role);
        role.getUsers().add(this);
    }

    public void removeRole(Role role) {
        roles.remove(role);
        role.getUsers().remove(this);
    }

    public User(String username, String password, List roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.enabled = true;
    }
}
