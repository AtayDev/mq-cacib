package com.example.mq_cacib.config;

import com.example.mq_cacib.model.MessageEntity;
import com.example.mq_cacib.model.Partner;
import com.example.mq_cacib.repository.MessageRepository;
import com.example.mq_cacib.repository.PartnerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class DevDataLoader implements CommandLineRunner {

    private final MessageRepository messageRepository;
    private final PartnerRepository partnerRepository;

    public DevDataLoader(MessageRepository messageRepository, PartnerRepository partnerRepository) {
        this.messageRepository = messageRepository;
        this.partnerRepository = partnerRepository;
    }

    @Override
    public void run(String... args) {
        // Load sample messages
        messageRepository.save(new MessageEntity(null, "Test message 1 from MQ", LocalDateTime.now()));
        messageRepository.save(new MessageEntity(null, "Test message 2 from MQ", LocalDateTime.now()));

        // Load sample partners
        Partner p1 = new Partner();
        p1.setAlias("MQ-PARTNER-001");
        p1.setType("Internal");
        p1.setDirection(Partner.Direction.INBOUND);
        p1.setApplication("PaymentApp");
        p1.setProcessedFlowType(Partner.FlowType.MESSAGE);
        p1.setDescription("Test partner for inbound flow");

        Partner p2 = new Partner();
        p2.setAlias("MQ-PARTNER-002");
        p2.setType("External");
        p2.setDirection(Partner.Direction.OUTBOUND);
        p2.setApplication("ExternalSystem");
        p2.setProcessedFlowType(Partner.FlowType.NOTIFICATION);
        p2.setDescription("Outbound notifications");

        partnerRepository.save(p1);
        partnerRepository.save(p2);

        System.out.println("Test data loaded successfully.");
    }
}

