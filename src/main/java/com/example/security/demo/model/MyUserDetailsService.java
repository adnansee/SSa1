package com.example.security.demo.model;

import com.example.security.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(username);
       // User user = userRepository.findByUserName(username);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
        System.out.println(user.map((MyUserDetails::new)).get().getAuthorities());
        System.out.println(user.map((MyUserDetails::new)).get().getUsername());
        System.out.println(user.map((MyUserDetails::new)).get().getPassword());
        System.out.println(user.map((MyUserDetails::new)).get().getClass());

        return user.map(MyUserDetails::new).get();

    }



}
