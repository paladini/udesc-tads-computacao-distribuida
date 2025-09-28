## Exercise 2 - MQTT Implementation

*Como ficaria se, ao invés de usar o protocolo AMQP, quiséssemos usar o MQTT no CLOUDAMQP?*

### Principais diferenças entre AMQP e MQTT:

1. **Protocolo e Porta**:
   - AMQP: `amqps://jackal.rmq.cloudamqp.com:5671`
   - MQTT: `ssl://jackal.rmq.cloudamqp.com:8883`

2. **Cliente**:
   - AMQP: RabbitMQ Java Client
   - MQTT: Eclipse Paho MQTT Client

3. **Conceitos**:
   - AMQP: Queues (filas)
   - MQTT: Topics (tópicos)

4. **Quality of Service**:
   - AMQP: Durabilidade de filas + acknowledgments
   - MQTT: QoS levels (0, 1, 2)

5. **Sessões**:
   - AMQP: Não há conceito de sessão persistente
   - MQTT: Clean Session (true/false)

### Como executar:

1. Compile o projeto: `mvn compile`
2. Execute o Consumer: `mvn exec:java -Dexec.mainClass="org.exercise2.Consumer"`
3. Em outro terminal, execute o Producer: `mvn exec:java -Dexec.mainClass="org.exercise2.Producer"`

### Configurações utilizadas:

- **QoS 2**: Garante entrega exata uma vez (equivalente à durabilidade no AMQP)
- **Clean Session false**: Para o consumer, mantém a sessão persistente
- **Clean Session true**: Para o producer, não precisa manter estado 