package ru.netology;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class Server {

    public static void main(String[] args) {
        int port = 8080;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            try (Socket clientSocket = serverSocket.accept()) {
                System.out.println("New connection accepted");

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String clientMessage = in.readLine();
                System.out.println("Received: " + clientMessage);
                out.println(String.format("Hi %s, your port is %d", clientMessage, clientSocket.getPort()));

                out.println("Message received");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
