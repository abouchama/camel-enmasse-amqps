package com.aboucham.amqp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.camel.component.amqp.AMQPConnectionDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

/**
 * The Spring-boot main class.
 */
@SpringBootApplication
@ImportResource({"classpath:spring/camel-context.xml"})
public class Application {
	
	@Value("${activemq.broker.url}")
	private String AMQ_BROKER_URL;
	
	@Value("${activemq.broker.username}")
	private String AMQ_BROKER_USERNAME;
	
	@Value("${activemq.broker.password}")
	private String AMQ_BROKER_PASSWORD;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	AMQPConnectionDetails amqpConnection() {
		return new AMQPConnectionDetails(AMQ_BROKER_URL, AMQ_BROKER_USERNAME, AMQ_BROKER_PASSWORD);
	}

}
