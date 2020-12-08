package com.spring.demo;

import com.example.User;
import com.spring.demo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyService {
    @Autowired

    private UserRepository userRepository;

    public void test() {
        ArrayList<User> users = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            users.add(new User(i, "Wojtek" + i, "wojtek" + i + "gmail.com"));
        }

        users.forEach(e -> userRepository.save(e));
    }


}
