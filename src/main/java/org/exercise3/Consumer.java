package org.exercise3;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.nio.charset.StandardCharsets;

public class Consumer {
    public static void main(String[] args) throws Exception {
        // Adafruit.io MQTT broker
        String broker = "ssl://io.adafruit.com:8883";
        String clientId = "JavaConsumer";
        
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
            
            // Callback para mensagens recebidas
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    System.out.println("Conexão perdida com Adafruit.io: " + cause.getMessage());
                }
                
                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    String msg = new String(message.getPayload(), StandardCharsets.UTF_8);
                    System.out.println(" [x] Recebido do Adafruit.io: '" + msg + "'");
                    System.out.println(" [x] Do tópico: " + topic);
                }
                
                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    // Não usado para consumer
                }
            });
            
            System.out.println("Conectando ao broker Adafruit.io: " + broker);
            client.connect(connOpts);
            System.out.println("Conectado ao Adafruit.io!");
            
            // Inscrevendo-se no tópico com QoS 1
            client.subscribe(topic, 1);
            System.out.println(" [*] Inscrito no tópico: " + topic);
            System.out.println(" [*] Aguardando mensagens. Para sair, pressione CTRL+C");
            
            // Mantém o programa rodando
            while (true) {
                Thread.sleep(1000);
            }
            
        } catch (MqttException e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
