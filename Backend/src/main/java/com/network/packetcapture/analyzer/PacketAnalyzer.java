package com.network.packetcapture.analyzer;


import com.network.model.Packet;

import java.util.HashMap;
import java.util.Map;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;

public class PacketAnalyzer {

    private static int totalPackets = 0;

    private static int tcpPackets = 0;

    private static int udpPackets = 0;

    private static Map<String, Integer> ipCount = new HashMap<>();


    public static synchronized void analyze(Packet packet) {

        totalPackets++;

        // Count protocol
        if (packet.getProtocol().equalsIgnoreCase("TCP")) {

            tcpPackets++;

        } else if (packet.getProtocol().equalsIgnoreCase("UDP")) {

            udpPackets++;

        }

        // Count IP frequency
        ipCount.put(
                packet.getSourceIP(),
                ipCount.getOrDefault(packet.getSourceIP(), 0) + 1
        );

        System.out.println("\nAnalyzing Packet...");
        System.out.println(packet);

        // Suspicious detection
        if (packet.getSize() > 1000) {

            System.out.println("⚠ Suspicious packet detected!");

        }

        // Print statistics
        printStatistics();
        sendToBackend(packet);
    }


    private static void printStatistics() {

        System.out.println("\n===== TRAFFIC STATISTICS =====");

        System.out.println("Total Packets: " + totalPackets);

        System.out.println("TCP Packets: " + tcpPackets);

        System.out.println("UDP Packets: " + udpPackets);

        System.out.println("Most Active IP: " + getMostActiveIP());

        System.out.println("==============================\n");

    }


    private static String getMostActiveIP() {

        String maxIP = null;

        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : ipCount.entrySet()) {

            if (entry.getValue() > maxCount) {

                maxCount = entry.getValue();

                maxIP = entry.getKey();

            }

        }

        return maxIP;

    }

    public static void sendToBackend(Packet packet) {

        try {

            System.out.println("🚀 Sending packet to backend...");

            URL url = new URL("http://localhost:8080/packets");

            HttpURLConnection conn =
                    (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String json =
                    "{"
                            + "\"sourceIP\":\"" + packet.getSourceIP() + "\","
                            + "\"destinationIP\":\"" + packet.getDestinationIP() + "\","
                            + "\"port\":" + packet.getPort() + ","
                            + "\"protocol\":\"" + packet.getProtocol() + "\","
                            + "\"size\":" + packet.getSize()
                            + "}";

            System.out.println("JSON: " + json);

            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes());
            os.flush();
            os.close();

            // ✅ IMPORTANT: Check response
            int responseCode = conn.getResponseCode();

            System.out.println("✅ Response Code: " + responseCode);

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

}
