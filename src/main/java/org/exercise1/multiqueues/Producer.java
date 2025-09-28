package org.exercise1.multiqueues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import java.nio.charset.StandardCharsets;
public class Producer {
    private static final String EXCHANGE = "demo.fanout";
    public static void main(String[] args) throws Exception {
        String url = "amqps://qcfhkcku:RiUA_XGWrwibj9EEWERvLxmdTVX8-tFy@jackal.rmq.cloudamqp.com/qcfhkcku";
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(url);
        try (Connection conn = factory.newConnection();
             Channel ch = conn.createChannel()) {
             // 1) cria/garante a exchange fanout (idempotente)
             ch.exchangeDeclare(EXCHANGE, "fanout", true); // durable=true
             String message = "Olá, broadcast de Fernando Paladini!";
             // 2) publica na exchange fanout (routing key é ignorada nesse tipo)
             ch.basicPublish(EXCHANGE, "", MessageProperties.PERSISTENT_TEXT_PLAIN,
                    message.getBytes(StandardCharsets.UTF_8));
             System.out.println(" [x] Enviada a mensagem: " + message);
        }
    }
}