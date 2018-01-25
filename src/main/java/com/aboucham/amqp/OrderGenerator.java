package com.aboucham.amqp;

import org.apache.camel.CamelContext;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Random;

/**
 * To generate random orders
 */
@Component
public class OrderGenerator {

    private int count = 1;
    private Random random = new Random();

    public InputStream generateOrder(CamelContext camelContext) {
        int number = random.nextInt(5) + 1;

        String name = "data/order" + number + ".xml";

        return camelContext.getClassResolver().loadResourceAsStream(name);
    }

    public String generateFileName() {
        return "order" + count++ + ".xml";
    }
}
