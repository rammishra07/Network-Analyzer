package com.network.controller;

import com.network.model.Packet;
import com.network.repository.PacketRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/packets")
@CrossOrigin(origins = "*")
public class PacketController {

    private final PacketRepository repo;

    public PacketController(PacketRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Packet save(@RequestBody Packet packet) {
        return repo.save(packet);
    }

    @GetMapping
    public List<Packet> getAll() {
        return repo.findTop50ByOrderByIdDesc();
    }
}