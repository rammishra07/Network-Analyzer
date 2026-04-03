package com.network.repository;

import com.network.model.Packet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PacketRepository
        extends JpaRepository<Packet, Long> {
    List<Packet> findTop50ByOrderByIdDesc();
}
