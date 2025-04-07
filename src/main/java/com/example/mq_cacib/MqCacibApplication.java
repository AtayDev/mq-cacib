package com.example.mq_cacib;

import com.example.mq_cacib.config.MqProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(MqProperties.class)
public class MqCacibApplication {

	public static void main(String[] args) {
		SpringApplication.run(MqCacibApplication.class, args);
	}

}
