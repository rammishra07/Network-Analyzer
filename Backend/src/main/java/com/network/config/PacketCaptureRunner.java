package com.network.config;

import com.network.packetcapture.capturer.AnalyzerServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PacketCaptureRunner {

    @Bean
    public CommandLineRunner startServer() {
        return args -> {
            new Thread(() -> {
                try {
                    System.out.println("🚀 Starting Packet Capture Server...");
                    AnalyzerServer server = new AnalyzerServer();
                    server.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        };
    }
}