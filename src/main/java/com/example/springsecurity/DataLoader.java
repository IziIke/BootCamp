package com.example.springsecurity;

import com.example.springsecurity.model.Role;
import com.example.springsecurity.model.User;
import com.example.springsecurity.repository.RoleRepository;
import com.example.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        roleRepository.save(new Role("ADMIN"));
        roleRepository.save(new Role("LEITOR"));
        roleRepository.save(new Role("BASICO"));
        roleRepository.save(new Role("AVANCADO"));

        Role adminRole = roleRepository.findByRole("ADMIN");
        Role leitorRole = roleRepository.findByRole("LEITOR");
        Role basicoRole = roleRepository.findByRole("BASICO");
        Role avancadoRole = roleRepository.findByRole("AVANCADO");

        User user = new User("admin@code.com", passwordEncoder.encode("password"), "admin" );
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);

        user = new User("leitor@code.com", passwordEncoder.encode("password"),"LEITOR" );
        user.setRoles(Arrays.asList(leitorRole));
        userRepository.save(user);

        user = new User("basico@code.com", passwordEncoder.encode("password"),"basico" );
        user.setRoles(Arrays.asList(basicoRole));
        userRepository.save(user);

        user = new User("avancado@code.com", passwordEncoder.encode("password"),"avancado" );
        user.setRoles(Arrays.asList(leitorRole));
        userRepository.save(user);

    }
}
