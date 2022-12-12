package com.example.demo.config;

import com.example.demo.entities.User;
import com.example.demo.entities.UserRol;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@ComponentScan
public class DataLoader  implements ApplicationRunner {
    private UserRepository repository;

    @Autowired
    public void DataLoader(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pass = encoder.encode("password");
        String pass2 = encoder.encode("password2");

        repository.save(new User("user", pass, UserRol.ROLE_USER));
        repository.save(new User("admin", pass2, UserRol.ROLE_ADMIN));

    }
}
