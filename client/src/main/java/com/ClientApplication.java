package com;

import com.repository.model.communication.CreateUserDocumentRequest;
import com.repository.model.communication.CreateUserPhoneRequest;
import com.repository.model.communication.CreateUserRequest;
import com.repository.model.communication.CreateUserResponse;
import com.repository.model.database.User;
import com.repository.model.database.UserDocument;
import com.repository.model.database.UserPhone;
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

            User user = new User(1, "Wojtek", "wojtek123456789" + 20 + "gmail.com", "01.01.1990", 1, 1, 1);
            UserPhone userPhone = new UserPhone(48, 123456789, "MOBILE");
            UserDocument userDocument = new UserDocument(1, "PASPORT", "12.12.2022", "PL", "PL", "true");
            CreateUserRequest createUserRequest = new CreateUserRequest(user);
            CreateUserPhoneRequest createUserPhoneRequest = new CreateUserPhoneRequest(userPhone);
            CreateUserDocumentRequest createUserDocumentRequest = new CreateUserDocumentRequest(userDocument);

            Object sendUser = myService.send(createUserRequest);
            Object sendPhone = myService.send(createUserPhoneRequest);
            Object sendDocument = myService.send(createUserDocumentRequest);

        };
    }

}
