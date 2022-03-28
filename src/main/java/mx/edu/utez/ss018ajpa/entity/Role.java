package mx.edu.utez.ss018ajpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@ToString
public class Role {
    @Id
    @Column(nullable = false, unique = true, columnDefinition = "varchar(25)")
    private String authority;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Role(String authority) {
        this.authority = authority;
    }
}
