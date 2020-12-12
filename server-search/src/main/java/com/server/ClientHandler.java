package com.server;

import com.ServerException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@Slf4j
public class ClientHandler implements Runnable {
    private final Socket clientSocket;


    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        try {
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            log.info("<Wait to in>");
            in = new ObjectInputStream(clientSocket.getInputStream());
            log.info("<Wait to out>");
            Object request = in.readObject();
            System.out.println("Dasd");
        } catch (IOException | ClassNotFoundException e) {
            throw new ServerException("Client Handler : " + e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                    clientSocket.close();
                }
            } catch (IOException e) {
                throw new ServerException("Client Handler : " + e);
            }
        }


    }
}
