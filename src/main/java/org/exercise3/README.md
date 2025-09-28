## Exercício 3 - Hello World com Adafruit.io (MQTT)

*Faça um hello world usando o broker Adafruit.io (MQTT) e tire prints do programa funcionando. A mensagem deve ser "Olá, <seu nome completo>!"*

### Pré-requisitos

1. **Criar conta no Adafruit.io**:
   - Acesse: https://io.adafruit.com/
   - Crie uma conta gratuita

2. **Obter credenciais**:
   - Após login, vá em "My Key" (canto superior direito)
   - Anote seu **Username** e **AIO Key**

3. **Criar um Feed**:
   - Vá em "Feeds" → "New Feed"
   - Nome: `hello`
   - Deixe as outras configurações padrão

### Configuração

**IMPORTANTE**: Antes de executar, edite os arquivos e substitua:
- `SEU_USERNAME_ADAFRUIT` pelo seu username do Adafruit.io
- `SUA_AIO_KEY_ADAFRUIT` pela sua AIO Key

### Como executar

1. **Compile o projeto**:
   ```bash
   mvn compile
   ```

2. **Execute o Consumer** (em um terminal):
   ```bash
   mvn exec:java -Dexec.mainClass="org.exercise3.Consumer"
   ```

3. **Execute o Producer** (em outro terminal):
   ```bash
   mvn exec:java -Dexec.mainClass="org.exercise3.Producer"
   ```

### Características do Adafruit.io

- **Broker**: `ssl://io.adafruit.com:8883` (MQTT over SSL)
- **Tópicos**: Formato `username/feeds/nome-do-feed`
- **QoS**: Recomendado QoS 1 (entrega pelo menos uma vez)
- **Autenticação**: Username + AIO Key
- **Limite gratuito**: 30 mensagens por minuto

### Exemplo de saída esperada

**Producer**:
```
Conectando ao broker Adafruit.io: ssl://io.adafruit.com:8883
Conectado ao Adafruit.io!
 [x] Enviada a mensagem: 'Olá, Fernando Paladini!'
 [x] Tópico: fernando/feeds/hello
Desconectado do Adafruit.io
```

**Consumer**:
```
Conectando ao broker Adafruit.io: ssl://io.adafruit.com:8883
Conectado ao Adafruit.io!
 [*] Inscrito no tópico: fernando/feeds/hello
 [*] Aguardando mensagens. Para sair, pressione CTRL+C
 [x] Recebido do Adafruit.io: 'Olá, Fernando Paladini!'
 [x] Do tópico: fernando/feeds/hello
```

### Visualização

Você também pode visualizar as mensagens no dashboard do Adafruit.io em tempo real!Exercício 3