package org.exercise1.multiqueues;

import com.rabbitmq.client.*;
import java.nio.charset.StandardCharsets;
public class Consumer {
    private static final String EXCHANGE = "demo.fanout";
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.err.println("Uso: java Consumer<queueName>");
            System.exit(1);
        }
        String queueName = args[0];
        String url = "amqps://qcfhkcku:RiUA_XGWrwibj9EEWERvLxmdTVX8-tFy@jackal.rmq.cloudamqp.com/qcfhkcku";

        if (url == null || url.isBlank()) {
            url = "amqp://guest:guest@localhost:5672/"; // fallback local
        }
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(url);
        Connection conn = factory.newConnection();
        Channel ch = conn.createChannel();

        // 1) garante a exchange fanout (idempotente)
        ch.exchangeDeclare(EXCHANGE, "fanout", true); // durable=true

        // 2) declara (ou garante) a fila própria deste consumidor
        boolean durable = true, exclusive = false, autoDelete = false;
        ch.queueDeclare(queueName, durable, exclusive, autoDelete, null);

        // 3) faz o bind da fila na exchange fanout (routing key ignorada)
        ch.queueBind(queueName, EXCHANGE, "");

        // 4) consome mensagens desta fila
        // para simplicidade, autoAck=true (poderia ser false + basicAck se preferir)
        System.out.printf(" [*] Consumindo da fila '%s'. CTRL+C para sair.\n", queueName);
        DeliverCallback deliver = (tag, delivery) -> {
            String msg = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.printf(" [%s] Recebido: %s%n", queueName, msg);
        };
        ch.basicConsume(queueName, true, deliver, tag -> {});
    }
}