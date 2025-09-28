package org.exercise2;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.nio.charset.StandardCharsets;

public class Consumer {
    public static void main(String[] args) throws Exception {
        // CloudAMQP MQTT broker URL
        String broker = "ssl://jackal.rmq.cloudamqp.com:8883";
        String clientId = "JavaConsumer";
        String topic = "hello";
        String username = "qcfhkcku:qcfhkcku";
        String password = "RiUA_XGWrwibj9EEWERvLxmdTVX8-tFy";
        
        MemoryPersistence persistence = new MemoryPersistence();
        
        try {
            MqttClient client = new MqttClient(broker, clientId, persistence);
            
            // Configurações de conectividade
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(false); // false para persistir sessão (equivalente a durable)
            connOpts.setUserName(username);
            connOpts.setPassword(password.toCharArray());
            
            // Callback para mensagens recebidas
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    System.out.println("Conexão perdida: " + cause.getMessage());
                }
                
                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    String msg = new String(message.getPayload(), StandardCharsets.UTF_8);
                    System.out.println(" [x] Recebido: '" + msg + "'");
                }
                
                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    // Não usado para consumer
                }
            });
            
            System.out.println("Conectando ao broker MQTT: " + broker);
            client.connect(connOpts);
            System.out.println("Conectado");
            
            // Inscrevendo-se no tópico com QoS 2
            client.subscribe(topic, 2);
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
