package com.example.security.demo;

import com.example.security.demo.model.User;
import com.example.security.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class DBSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    /**
     * This class simply enters some data via command line to test
     **/

    @Override
    public void run(String... args) {

        User user1 = new User();
        user1.setActive(true);
        user1.setPassword("pass");
        user1.setUserName("admin");
        user1.setRoles("ADMIN");

        User user2 = new User();
        user2.setActive(true);
        user2.setPassword("pass");
        user2.setUserName("user");
        user2.setRoles("USER");


        List<User> camps = Arrays.asList(user2, user1);
        this.userRepository.saveAll(camps);
    }
}