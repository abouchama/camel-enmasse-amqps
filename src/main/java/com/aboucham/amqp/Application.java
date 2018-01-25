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

	// You could remove all parameters and pass it via environment variables https://qpid.apache.org/releases/qpid-jms-0.26.0/docs/index.html
	// mvn clean package spring-boot:run -Djavax.net.debug=ssl -Djavax.net.ssl.keyStore=/home/rramalho/workspace/amqp/src/main/resources/amq-client.ks -Djavax.net.ssl.keyStorePassword=redhat -Djavax.net.ssl.trustStore=/home/rramalho/workspace/amqp/src/main/resources/amq-client.ts -Dtransport.trustStorePassword=redhat
	// mvn clean package spring-boot:run -Djavax.net.debug=ssl -Djavax.net.ssl.keyStore=/home/aboucham/dev/syndesis/enmasse/Abel/amqp-certs/amq-client.ks -Djavax.net.ssl.keyStorePassword=redhat -Djavax.net.ssl.trustStore=/home/aboucham/dev/syndesis/enmasse/Abel/amqp-certs/amq-client.ks -Dtransport.trustStorePassword=redhat
}
