package mx.edu.utez.ss018ajpa.util;

import mx.edu.utez.ss018ajpa.service.RoleService;
import mx.edu.utez.ss018ajpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class InitialDatabase implements CommandLineRunner {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Override
    public void run(String ... args) throws Exception {
        String myPassword = "admin";
        System.out.println( myPassword + "\nencrypt: " + passwordEncoder.encode(myPassword) );
        roleService.fillInitialData();
        userService.fillInitialData();
    }


}
