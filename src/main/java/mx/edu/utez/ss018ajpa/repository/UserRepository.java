package mx.edu.utez.ss018ajpa.repository;

import mx.edu.utez.ss018ajpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
