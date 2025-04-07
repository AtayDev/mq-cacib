package com.example.mq_cacib.service;

import com.example.mq_cacib.config.MqProperties;
import com.example.mq_cacib.model.MessageEntity;
import com.example.mq_cacib.repository.MessageRepository;
import com.ibm.mq.*;
import com.ibm.mq.constants.MQConstants;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MqReaderService {

    private final MQQueueManager queueManager;
    private final MqProperties props;
    private final MessageRepository messageRepo;

    public MqReaderService(MQQueueManager queueManager, MqProperties props, MessageRepository messageRepo) {
        this.queueManager = queueManager;
        this.props = props;
        this.messageRepo = messageRepo;
    }

    public void readAndStoreMessages() {
        try {
            MQQueue queue = queueManager.accessQueue(
                    props.getQueue(),
                    MQConstants.MQOO_INPUT_AS_Q_DEF + MQConstants.MQOO_OUTPUT
            );

            MQMessage message = new MQMessage();
            MQGetMessageOptions options = new MQGetMessageOptions();

            while (true) {
                try {
                    queue.get(message, options);
                    String content = message.readStringOfByteLength(message.getDataLength());
                    messageRepo.save(new MessageEntity(null, content, LocalDateTime.now()));
                } catch (MQException e) {
                    if (e.reasonCode == MQException.MQRC_NO_MSG_AVAILABLE) break;
                }
            }

            queue.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
