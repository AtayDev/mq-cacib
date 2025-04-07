package com.example.mq_cacib.config;

import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQQueueManager;
import lombok.Data;
import lombok.Value;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MqConfiguration {

    private final MqProperties props;

    public MqConfiguration(@Qualifier("mqProps") MqProperties props) {
        this.props = props;
    }

    @Bean
    public MQQueueManager queueManager() throws MQException {
        String[] connParts = props.getConnName().split("\\(");
        MQEnvironment.hostname = connParts[0];
        MQEnvironment.port = Integer.parseInt(connParts[1].replace(")", ""));
        MQEnvironment.channel = props.getChannel();
        MQEnvironment.userID = props.getUser();
        MQEnvironment.password = props.getPassword();

        System.out.println("➡️ Connecting to MQ");
        System.out.println("QueueManager: " + props.getQueueManager());
        System.out.println("Channel: " + props.getChannel());
        System.out.println("Host: " + props.getConnName());
        System.out.println("User: " + props.getUser());

        return new MQQueueManager(props.getQueueManager());
    }
}
