package com.client;


import com.repository.UserRepository;
import com.repository.model.communication.LoginUserRequest;
import com.repository.model.communication.LoginUserResponse;
import com.repository.model.database.User;
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
    @Autowired
    private UserRepository userRepository;


    public void start(int port) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(port);
        log.info("START SERVER");
        while (true) {
            Socket clientSocket = serverSocket.accept();
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            Object request = in.readObject();


            if (request instanceof LoginUserRequest) {
                LoginUserResponse loginUserResponse = findUserToLogin((LoginUserRequest) request);
                out.writeObject(loginUserResponse);
            }


            close(clientSocket, out, in);
        }
    }


    private LoginUserResponse findUserToLogin(LoginUserRequest loginUserRequest) {
        User user = userRepository.findUserByEmailAndPassword(loginUserRequest.getEmail(), loginUserRequest.getPassword());
        if (user != null) {
            log.info("znaleziono");
            return new LoginUserResponse(user, "ZALOGOWANO");
        }

        return new LoginUserResponse("BLEDNY UZYTKOWNIK");

    }


    private void close(Socket clientSocket, ObjectOutputStream out, ObjectInputStream in) throws IOException {
        out.close();
        in.close();
        clientSocket.close();
    }
}
