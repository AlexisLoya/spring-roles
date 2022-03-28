package mx.edu.utez.ss018ajpa.repository;

import mx.edu.utez.ss018ajpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    @Query(
            value = "select u.* from spring_mvc_security.users u join spring_mvc_security.authorities a ON u.username = a.username where a.authority = 'ROLE_USER';",
            nativeQuery = true)
    List<User> findAllUsers();
    @Query(
            value = "select u.* from spring_mvc_security.users u join spring_mvc_security.authorities a ON u.username = a.username where a.authority = 'ROLE_ADMINISTRATOR';",
            nativeQuery = true)
    List<User> findAllAdmins();
    @Query(
            value = "select u.* from spring_mvc_security.users u join spring_mvc_security.authorities a ON u.username = a.username where a.authority = 'ROLE_SUPER_ADMINISTRATOR';",
            nativeQuery = true)
    List<User> findAllSuperAdmins();
}
