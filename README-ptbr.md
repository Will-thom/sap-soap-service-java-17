# SAP SOAP Service (Java 17)

Integração SOAP com serviços SAP utilizando Java 17, com foco em interoperabilidade enterprise, padronização de contratos (WSDL) e comunicação segura entre sistemas legados.

## 📌 Overview

Este projeto demonstra como consumir e/ou expor serviços SOAP em um ambiente moderno (Java 17), mantendo compatibilidade com sistemas SAP que ainda utilizam protocolos baseados em XML.

A aplicação segue um modelo orientado a contratos (contract-first), utilizando WSDL/XSD para garantir consistência na comunicação.

## 🧱 Stack Tecnológica

- Java 17
- Maven
- SOAP (JAX-WS / Spring WS)
- XML / WSDL / XSD
- (Opcional) Spring Boot

> SOAP continua sendo amplamente utilizado em integrações corporativas, especialmente com SAP e sistemas legados. :contentReference[oaicite:1]{index=1}

---

## 🏗️ Arquitetura

A aplicação segue o fluxo clássico de integração SOAP:

```

Client → SOAP Envelope → Service Layer → SAP Backend

````

### Componentes principais:

- **Client SOAP**
  - Responsável por construir e enviar requests XML
- **Binding (WSDL)**
  - Define contrato e operações disponíveis
- **Marshaller/Unmarshaller**
  - Conversão entre objetos Java e XML
- **Service Layer**
  - Orquestra chamadas e trata regras de negócio
- **Integration Layer**
  - Comunicação direta com SAP

---

## ⚙️ Pré-requisitos

- Java 17+
- Maven 3+
- Acesso ao WSDL do serviço SAP

---

## 🚀 Como executar

### 1. Clonar o repositório

```bash
git clone https://github.com/Will-thom/sap-soap-service-java-17.git
cd sap-soap-service-java-17
````

### 2. Build do projeto

```bash
mvn clean install
```

### 3. Executar

```bash
mvn spring-boot:run
```

ou (caso não use Spring Boot):

```bash
mvn exec:java
```

---

## 🔌 Consumo de serviços SOAP

O consumo é baseado no contrato WSDL.

### Exemplo de fluxo:

1. Gerar classes a partir do WSDL
2. Criar request
3. Enviar via client SOAP
4. Processar response

```java
// Exemplo simplificado
MyRequest request = new MyRequest();
request.setField("value");

MyResponse response = soapClient.call(request);
```

---

## 📄 Estrutura do Projeto

```
src/
 ├── main/
 │   ├── java/
 │   │   ├── config/        # Configurações SOAP
 │   │   ├── client/        # Cliente SOAP
 │   │   ├── service/       # Regras de negócio
 │   │   └── model/         # DTOs (gerados do WSDL)
 │   └── resources/
 │       ├── wsdl/          # Contratos WSDL
 │       └── xsd/           # Schemas XML
```

---

## ⚠️ Observações importantes (Java 17 + SOAP)

* Algumas APIs antigas (`javax.xml.soap`) não vêm mais embutidas no JDK
* Pode ser necessário adicionar dependências explicitamente
* Problemas comuns incluem:

  * `NoClassDefFoundError: SOAPException`
  * incompatibilidade de libs antigas

Isso ocorre porque mudanças no JDK removeram módulos antigos do Java EE. ([Stack Overflow][1])

---

## 🧪 Testes

Recomenda-se validar via:

* SoapUI
* Postman (com XML manual)
* Testes automatizados com mocks

---

## 📈 Possíveis melhorias

* Observabilidade (logs estruturados + tracing)
* Retry e circuit breaker (Resilience4j)
* Conversão SOAP → REST (facade)
* Cache de responses
* Timeout tuning

---

## 🤝 Contribuição

1. Fork
2. Create branch (`feature/...`)
3. Commit
4. Push
5. Open PR

---

## 📜 Licença

MIT

---

## 💡 Motivação

Apesar da ascensão de APIs REST, SOAP ainda é crítico em ambientes corporativos, especialmente em integrações com SAP e sistemas financeiros.

Este projeto serve como base para:

* Integrações enterprise
* Estudos de interoperabilidade
* Migração gradual para arquiteturas modernas
