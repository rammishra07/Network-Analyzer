package com.network.packetcapture.logger;
import com.network.model.Packet;

import java.io.FileWriter;
import java.io.IOException;

public class PacketLogger {
    private static final String FILE_NAME = "packets.log";

    public static synchronized void log(Packet packet) {

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {

            writer.write(packet.toString());
            writer.write("\n");

        } catch (IOException e) {

            System.out.println("Logging failed: " + e.getMessage());

        }
    }
}

