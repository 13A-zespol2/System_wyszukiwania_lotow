package com;

import com.anonymus.AmadeusFacade;
import com.server.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
public class MainServer {
    @Autowired
    private MyService myService;

    public static void main(String[] args) {
        SpringApplication.run(MainServer.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {


        return args -> {
            myService.start(8892);
        };
    }

}
