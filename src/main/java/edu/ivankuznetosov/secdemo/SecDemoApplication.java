package edu.ivankuznetosov.secdemo;

import edu.ivankuznetosov.secdemo.entity.Role;
import edu.ivankuznetosov.secdemo.entity.User;
import edu.ivankuznetosov.secdemo.repository.RoleRepository;
import edu.ivankuznetosov.secdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootApplication
public class SecDemoApplication {
    private static UserRepository userRepository = null;
    private static RoleRepository repository = null;

    public SecDemoApplication(UserRepository userRepository, RoleRepository repository) {
        this.userRepository = userRepository;
        this.repository = repository;
    }


    public static void main(String[] args) {
        SpringApplication.run(SecDemoApplication.class, args);
        User user = new User("admin",new BCryptPasswordEncoder().encode("admin"));

        Role role = new Role();
        role.setRoleName("ADMIN");
        userRepository.save(user);
        repository.save(role);
        user.setRoles(List.of(role));
        userRepository.save(user);
        role.setUsers(List.of(user));
        repository.save(role);
    }


}
