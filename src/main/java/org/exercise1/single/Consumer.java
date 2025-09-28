package org.exercise1.single;

import com.rabbitmq.client.*;
import java.nio.charset.StandardCharsets;

public class Consumer {
    public static void main(String[] args) throws Exception {
        String url = "amqps://qcfhkcku:RiUA_XGWrwibj9EEWERvLxmdTVX8-tFy@jackal.rmq.cloudamqp.com/qcfhkcku";
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(url);
        Connection conn = factory.newConnection();
        Channel ch = conn.createChannel();
        boolean durable = true;
        ch.queueDeclare("hello", durable, false, false, null);
        ch.basicQos(1);
        DeliverCallback deliver = (tag, delivery) -> {
            String msg = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Recebido: '" + msg + "'");
            ch.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        };
        ch.basicConsume("hello", false, deliver, tag -> {});
        System.out.println(" [*] Aguardando mensagens. Para sair, pressione CTRL+C");
    }
}
