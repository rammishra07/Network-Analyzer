package com.network.packetcapture.capturer;

import com.network.packetcapture.analyzer.PacketAnalyzer;
import com.network.packetcapture.logger.PacketLogger;
import com.network.model.Packet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientHandler extends Thread {

    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream()));

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                Packet packet = new Packet();

                packet.setSourceIP(data[0]);
                packet.setDestinationIP(data[1]);
                packet.setPort(Integer.parseInt(data[2]));
                packet.setProtocol(data[3]);
                packet.setSize(Integer.parseInt(data[4]));

                PacketAnalyzer.analyze(packet);
                PacketLogger.log(packet);
            }

        } catch (Exception e) {
            System.out.println("Client disconnected");
        }
    }
}