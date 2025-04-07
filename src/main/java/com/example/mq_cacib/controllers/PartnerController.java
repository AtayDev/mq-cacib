package com.example.mq_cacib.controllers;

import com.example.mq_cacib.model.Partner;
import com.example.mq_cacib.repository.PartnerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partners")
public class PartnerController {

    private final PartnerRepository partnerRepository;

    public PartnerController(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }


    @GetMapping
    public List<Partner> getAllPartners() {
        return partnerRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Partner> createPartner(@RequestBody Partner partner) {
        Partner saved = partnerRepository.save(partner);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePartner(@PathVariable Long id) {
        if (!partnerRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        partnerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
