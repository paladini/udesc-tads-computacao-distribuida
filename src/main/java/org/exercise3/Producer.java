package org.exercise3;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.nio.charset.StandardCharsets;

public class Producer {
    public static void main(String[] args) throws Exception {
        // Adafruit.io MQTT broker
        String broker = "ssl://io.adafruit.com:8883";
        String clientId = "JavaProducer";
        
        // CREDENCIAIS DO ADAFRUIT.IO
        String username = "paladini";
        String aioKey = "XXXXX";
        
        // Tópico no formato: username/feeds/nome-do-feed
        String topic = username + "/feeds/hello";
        
        MemoryPersistence persistence = new MemoryPersistence();
        
        try {
            MqttClient client = new MqttClient(broker, clientId, persistence);
            
            // Configurações de conectividade
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            connOpts.setUserName(username);
            connOpts.setPassword(aioKey.toCharArray());
            
            System.out.println("Conectando ao broker Adafruit.io: " + broker);
            client.connect(connOpts);
            System.out.println("Conectado ao Adafruit.io!");
            
            // Criando a mensagem
            String message = "Olá, Fernando Paladini!";
            MqttMessage mqttMessage = new MqttMessage(message.getBytes(StandardCharsets.UTF_8));
            mqttMessage.setQos(1); // QoS 1 é suficiente para Adafruit.io
            mqttMessage.setRetained(false);
            
            // Publicando a mensagem
            client.publish(topic, mqttMessage);
            System.out.println(" [x] Enviada a mensagem: '" + message + "'");
            System.out.println(" [x] Tópico: " + topic);
            
            client.disconnect();
            System.out.println("Desconectado do Adafruit.io");
            
        } catch (MqttException e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}