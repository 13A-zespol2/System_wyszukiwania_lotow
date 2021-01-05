package com;

import com.repository.model.communication.CreateUserRequest;
import com.repository.model.communication.CreateUserResponse;
import com.repository.model.database.User;
import com.server.ClientControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;


@SpringBootApplication()
public class ClientApplication {
    @Autowired(required = true)
    ClientControl myService;

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            User user = new User(1, "Wojtek" + 20, "wojtek123456789" + 20 + "gmail.com");
            CreateUserRequest createUserRequest = new CreateUserRequest(user);
            Object send = myService.send(createUserRequest);
        };
    }

}
