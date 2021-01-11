package com.server;


import com.ServerException;

import com.repository.UserPhoneRepository;
import com.repository.UserRepository;
import com.repository.model.communication.*;
import com.repository.model.database.User;
import com.repository.model.database.UserPhone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;


@Slf4j
@Service

public class MyService implements Serializable {

    private ServerSocket serverSocket;
    @Autowired
    private UserRepository userRepository;
    private UserPhoneRepository userPhoneRepository;




    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            Object request = null;
            ObjectOutputStream out = null;
            ObjectInputStream in = null;
            while (true) {

                log.info("<Wait for connection>");
                Socket clientSocket = serverSocket.accept();

                try {
                    out = new ObjectOutputStream(clientSocket.getOutputStream());
                    log.info("<Wait to in>");
                    in = new ObjectInputStream(clientSocket.getInputStream());
                    log.info("<Wait to out>");
                    request = in.readObject();
                    if (request instanceof CreateUserRequest) {
                        log.info("<Wait to dasd>");
                        CreateUserRequest createUserRequest = (CreateUserRequest) request;

                        User user = createUserRequest.getUser();

                        userRepository.save(user);

                        CreateUserResponse createUserResponse = new CreateUserResponse();
                        createUserResponse.setStatus("status");
                        out.writeObject(createUserResponse);


                    }


                } catch (IOException | ClassNotFoundException e) {
                    throw new ServerException("Client Handler : " + e);
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null)
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    throw new ServerException("Server close exception: " + e);
                }

        }


    }

}
