package com.network.packetcapture.capturer;

import java.net.ServerSocket;
import java.net.Socket;

public class AnalyzerServer {
    private static final int PORT = 6000;

    public void start() {

        try {

            ServerSocket serverSocket =
                    new ServerSocket(PORT);

            System.out.println("Analyzer Server Started...");

            while (true) {

                Socket socket =
                        serverSocket.accept();

                System.out.println("Client connected");

                ClientHandler handler =
                        new ClientHandler(socket);

                handler.start();

            }

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}

