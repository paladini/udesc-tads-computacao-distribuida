package org.exercise2;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.nio.charset.StandardCharsets;

public class Producer {
    public static void main(String[] args) throws Exception {
        // CloudAMQP MQTT broker URL
        String broker = "ssl://jackal.rmq.cloudamqp.com:8883";
        String clientId = "JavaProducer";
        String topic = "hello";
        String username = "qcfhkcku:qcfhkcku";
        String password = "RiUA_XGWrwibj9EEWERvLxmdTVX8-tFy";
        
        MemoryPersistence persistence = new MemoryPersistence();
        
        try {
            MqttClient client = new MqttClient(broker, clientId, persistence);
            
            // Configurações de conectividade
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            connOpts.setUserName(username);
            connOpts.setPassword(password.toCharArray());
            
            System.out.println("Conectando ao broker MQTT: " + broker);
            client.connect(connOpts);
            System.out.println("Conectado");
            
            // Criando a mensagem
            String message = "Olá, Fernando Paladini";
            MqttMessage mqttMessage = new MqttMessage(message.getBytes(StandardCharsets.UTF_8));
            mqttMessage.setQos(2); // QoS 2 para entrega garantida (equivalente a durable no AMQP)
            mqttMessage.setRetained(false);
            
            // Publicando a mensagem
            client.publish(topic, mqttMessage);
            System.out.println(" [x] Enviada a mensagem: '" + message + "'");
            
            client.disconnect();
            System.out.println("Desconectado");
            
        } catch (MqttException e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}