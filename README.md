# Computação Distribuída - Exercícios de Messaging

Este repositório contém implementações práticas de sistemas de messaging utilizando diferentes protocolos e brokers, desenvolvido para a disciplina de Computação Distribuída da UDESC TADS.

## 📋 Sobre o Projeto

O projeto explora conceitos fundamentais de messaging em sistemas distribuídos através de implementações práticas usando:

- **AMQP** (Advanced Message Queuing Protocol) com RabbitMQ
- **MQTT** (Message Queuing Telemetry Transport) com CloudAMQP e Adafruit.io

## 🏗️ Estrutura do Projeto

```
src/main/java/org/
├── exercise1/          # Hello World com RabbitMQ (AMQP)
│   ├── single/         # Implementação com fila única
│   └── multiqueues/    # Implementação com múltiplas filas
├── exercise2/          # Comparação AMQP vs MQTT (CloudAMQP)
└── exercise3/          # MQTT com Adafruit.io
```

## 🚀 Exercícios Implementados

### Exercise 1: Hello World RabbitMQ (AMQP)
- **Objetivo**: Implementar comunicação básica producer/consumer usando RabbitMQ
- **Variações**:
  - Fila única simples
  - Múltiplas filas com roteamento
- **Broker**: CloudAMQP
- **Protocolo**: AMQP over SSL (porta 5671)

### Exercise 2: AMQP vs MQTT
- **Objetivo**: Comparar e adaptar código AMQP para MQTT
- **Funcionalidade**: Mesmo hello world, protocolos diferentes
- **Broker**: CloudAMQP (suporte MQTT)
- **Protocolo**: MQTT over SSL (porta 8883)
- **Aprendizados**: Diferenças conceituais entre queues e topics

### Exercise 3: MQTT com Adafruit.io
- **Objetivo**: Implementar MQTT com broker IoT especializado
- **Broker**: Adafruit.io
- **Protocolo**: MQTT over SSL
- **Funcionalidades extras**: Dashboard web, visualização em tempo real

## 🛠️ Tecnologias Utilizadas

### Linguagem
- **Java 25** - Linguagem principal do projeto

### Dependências Maven
- **RabbitMQ AMQP Client** (5.21.0) - Cliente AMQP para RabbitMQ
- **Eclipse Paho MQTT Client** (1.2.5) - Cliente MQTT

### Brokers
- **CloudAMQP** - Broker RabbitMQ em nuvem (AMQP + MQTT)
- **Adafruit.io** - Plataforma IoT com broker MQTT

## ⚙️ Pré-requisitos

- Java 25 ou superior
- Maven 3.6+
- Contas nos brokers:
  - [CloudAMQP](https://www.cloudamqp.com/) (plano gratuito)
  - [Adafruit.io](https://io.adafruit.com/) (plano gratuito)

## 🔧 Como Executar

### 1. Clone o repositório
```bash
git clone https://github.com/paladini/udesc-tads-computacao-distribuida.git
cd TestRabbitMQ
```

### 2. Compile o projeto
```bash
mvn compile
```

### 3. Execute os exercícios

#### Exercise 1 (RabbitMQ/AMQP)
```bash
# Fila única
mvn exec:java -Dexec.mainClass="org.exercise1.single.Consumer"
mvn exec:java -Dexec.mainClass="org.exercise1.single.Producer"

# Múltiplas filas
mvn exec:java -Dexec.mainClass="org.exercise1.multiqueues.Consumer"
mvn exec:java -Dexec.mainClass="org.exercise1.multiqueues.Producer"
```

#### Exercise 2 (MQTT/CloudAMQP)
```bash
mvn exec:java -Dexec.mainClass="org.exercise2.Consumer"
mvn exec:java -Dexec.mainClass="org.exercise2.Producer"
```

#### Exercise 3 (MQTT/Adafruit.io)
**⚠️ Configure suas credenciais nos arquivos antes de executar!**
```bash
mvn exec:java -Dexec.mainClass="org.exercise3.Consumer"
mvn exec:java -Dexec.mainClass="org.exercise3.Producer"
```

## 🔒 Configuração de Credenciais

Para evitar exposição de credenciais:

1. **CloudAMQP**: Credenciais estão hardcoded nos exercises 1 e 2 (apenas para fins educacionais)
2. **Adafruit.io**: Configure no código antes de executar:
   - Substitua `SEU_USERNAME_ADAFRUIT` pelo seu username
   - Substitua `SUA_AIO_KEY_ADAFRUIT` pela sua AIO Key

## 📊 Comparação AMQP vs MQTT

| Aspecto | AMQP | MQTT |
|---------|------|------|
| **Modelo** | Queue-based | Topic-based |
| **Overhead** | Maior | Menor |
| **Garantias** | ACK + Durabilidade | QoS Levels |
| **Roteamento** | Complexo (Exchanges) | Simples (Topics) |
| **Uso típico** | Enterprise/Banking | IoT/Mobile |
| **Payload** | Qualquer | Principalmente texto |

## 📖 Documentação Detalhada

Cada exercício possui sua própria documentação detalhada:

- [Exercise 1 - RabbitMQ AMQP](src/main/java/org/exercise1/README.md)
- [Exercise 2 - AMQP vs MQTT](src/main/java/org/exercise2/README.md)
- [Exercise 3 - Adafruit.io MQTT](src/main/java/org/exercise3/README.md)

## 👨‍💻 Autor

**Fernando Paladini**
- Curso: Tecnologia em Análise e Desenvolvimento de Sistemas
- Universidade: UDESC (Universidade do Estado de Santa Catarina)
- Disciplina: Computação Distribuída

## 📄 Licença

Este projeto é desenvolvido para fins educacionais como parte do curso de Computação Distribuída.

---

*Desenvolvido com ☕ e muito aprendizado em sistemas distribuídos!*