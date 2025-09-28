package org.exercise2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.nio.charset.StandardCharsets;

public class Producer {
    public static void main(String[] args) throws Exception {
        String url = "amqps://qcfhkcku:RiUA_XGWrwibj9EEWERvLxmdTVX8-tFy@jackal.rmq.cloudamqp.com/qcfhkcku";

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(url);
        try (
                Connection conn = factory.newConnection();
                Channel ch = conn.createChannel()
        ) {
            boolean durable = true;
            ch.queueDeclare("hello", durable, false, false, null);
            String message = "Olá, Fernando Paladini";
            ch.basicPublish("", "hello",
                    MessageProperties.PERSISTENT_TEXT_PLAIN,
                    message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Enviada a mensagem: '" + message + "'");
        }
    }
}