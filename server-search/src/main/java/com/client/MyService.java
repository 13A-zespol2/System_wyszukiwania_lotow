package com.client;


import com.repository.ReservationRepository;
import com.repository.UserRepository;
import com.repository.model.communication.LoginUserRequest;
import com.repository.model.communication.LoginUserResponse;
import com.repository.model.communication.RegisterUserRequest;
import com.repository.model.communication.RegisterUserResponse;
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
    @Autowired
    private ReservationRepository reservationRepository;

    public void start(int port) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(port);
        log.info("START SERVER");
        //TODO obsluga enuma
  /*      for (Map.Entry<String, AirportCode> entry : AirportCode.getByIata().entrySet())
        {
            log.info(entry.getKey());
            log.info(entry.getValue().name());
        }
        AirportCode warsaw = AirportCode.valueOf("WARSAW");
     */
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

    private RegisterUserResponse userToRegister(RegisterUserRequest registerUserRequest){
        User user = new User();
        user.setEmail(registerUserRequest.getEmail());
        user.setPassword(registerUserRequest.getPassword());
        user = userRepository.save(user);

        return user == null ? new RegisterUserResponse("NIE ZAREJESTROWANO"): new RegisterUserResponse("ZAREJESTROWANO");


    }


    private void close(Socket clientSocket, ObjectOutputStream out, ObjectInputStream in) throws IOException {
        out.close();
        in.close();
        clientSocket.close();
    }
}
