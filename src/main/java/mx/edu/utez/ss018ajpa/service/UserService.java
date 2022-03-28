package mx.edu.utez.ss018ajpa.service;

import mx.edu.utez.ss018ajpa.entity.Role;
import mx.edu.utez.ss018ajpa.entity.User;
import mx.edu.utez.ss018ajpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAllUsers();
    }

    public List<User> findAllAdmins() {
        return (List<User>) userRepository.findAllAdmins();
    }

    public List<User> findAllSuperAdmins() {
        return (List<User>) userRepository.findAllSuperAdmins();
    }




    public Optional<User> findOne(String id) {
        return userRepository.findById(id);
    }

    public Optional<User> save(User entity) {
        return Optional.of(userRepository.save(entity));
    }

    public Optional<User> update(User entity) {
        Optional<User> updatedEntity = Optional.empty();
        updatedEntity = userRepository.findById(entity.getUsername());
        if (!updatedEntity.isEmpty())
            userRepository.save(entity);
        return updatedEntity;
    }

    public Optional<User> partialUpdate(String  id, Map<Object, Object> fields) {
        try {
            User entity = findOne(id).get();
            if (entity == null) {
                return Optional.empty();
            }
            Optional<User> updatedEntity = Optional.empty();
            fields.forEach((updatedField, value) -> {
                Field field = ReflectionUtils.findField(User.class, (String) updatedField);
                field.setAccessible(true);
                ReflectionUtils.setField(field, entity, value);
            });
            userRepository.save(entity);
            updatedEntity = Optional.of(entity);
            return updatedEntity;
        } catch (Exception exception) {
            System.err.println(exception);
            return Optional.empty();
        }
    }

    public Boolean delete(String id) {
        boolean entity = userRepository.existsById(id);
        if (entity) {
            userRepository.deleteById(id);
        }
        return entity;
    }
    public void fillInitialData() {
        /*if (userRepository.count() > 0) return;
*/
        List<User> inicial = new ArrayList<>();
        User admin = new User("admin",passwordEncoder.encode("admin"),
                Arrays.asList(
                        roleService.findOne("ROLE_ADMINISTRATOR").get(),
                        roleService.findOne("ROLE_USER").get()));

        User superAdmin = new User("super",passwordEncoder.encode("admin"),
                Arrays.asList(roleService.findOne(
                        "ROLE_USER").get(),
                        roleService.findOne("ROLE_ADMINISTRATOR").get(),
                        roleService.findOne("ROLE_SUPER_ADMINISTRATOR").get()
                        ));

        User user = new User("user",passwordEncoder.encode("admin"),
                Arrays.asList(roleService.findOne("ROLE_USER").get()));

        inicial.add(admin);
        inicial.add(superAdmin);
        inicial.add(user);

        userRepository.saveAll(inicial);
    }
}