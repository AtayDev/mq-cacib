package com.example.mq_cacib.controllers;

import com.example.mq_cacib.model.MessageEntity;
import com.example.mq_cacib.model.Partner;
import com.example.mq_cacib.repository.MessageRepository;
import com.example.mq_cacib.repository.PartnerRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")

public class MessageController {
    private final MessageRepository messageRepository;
    private final PartnerRepository partnerRepository;

    public MessageController(MessageRepository messageRepository, PartnerRepository partnerRepository) {
        this.messageRepository = messageRepository;
        this.partnerRepository = partnerRepository;
    }

    @GetMapping
    public List<MessageEntity> getAll() {
        return messageRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageEntity> getOne(@PathVariable Long id) {
        return messageRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/api/partners")
    public ResponseEntity<?> addPartner(@Valid @RequestBody Partner partner) {
        return ResponseEntity.ok(partnerRepository.save(partner));
    }




}
