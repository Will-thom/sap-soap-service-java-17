# SAP SOAP Integration Service (Java 17)

Serviço de integração robusto para comunicação com SAP via SOAP, projetado com foco em resiliência, observabilidade, contratos fortes (contract-first) e evolução arquitetural (SOAP → REST).

---

## 🎯 Objetivo

Este projeto implementa uma camada de integração confiável entre sistemas modernos e serviços SAP baseados em SOAP, abordando desafios reais de ambientes enterprise:

- Interoperabilidade com sistemas legados
- Garantia de contrato (WSDL/XSD)
- Resiliência a falhas de rede/SAP
- Idempotência e controle de reprocessamento
- Observabilidade e troubleshooting

---

## 🧱 Stack Tecnológica

- Java 17
- Spring Boot
- Spring Web Services (Spring-WS)
- Apache CXF (opcional)
- Maven
- JAXB (XML Binding)
- Resilience4j
- Micrometer + OpenTelemetry
- Docker

---

## 🏗️ Arquitetura

### 🔷 Visão de Alto Nível

```

[ Client / API REST ]
↓
[ Adapter Layer (REST → SOAP) ]
↓
[ Application Service ]
↓
[ SOAP Client (Contract-first) ]
↓
[ SAP Backend ]

````

---

## 🔩 Componentes Arquiteturais

### 1. Adapter Layer (Anti-Corruption Layer)

Responsável por desacoplar o mundo REST do contrato SOAP.

- Tradução REST → SOAP
- Normalização de payload
- Versionamento de API

> Evita vazamento de XML/SOAP para o domínio

---

### 2. Application Service

Orquestra o fluxo de integração:

- Validação de entrada
- Controle de idempotência
- Retry policies
- Tratamento de erro semântico

---

### 3. SOAP Client (Contract-First)

Gerado a partir de WSDL:

- Forte tipagem
- Segurança de contrato
- Menor risco de quebra em runtime

---

### 4. Resilience Layer

Implementado com Resilience4j:

- Retry com backoff exponencial
- Circuit Breaker
- Timeout controlado
- Bulkhead (isolamento)

---

### 5. Observability Layer

- Logs estruturados (JSON)
- Métricas (Micrometer)
- Tracing distribuído (OpenTelemetry)

---

## 🔁 Idempotência e Reprocessamento

Problema clássico em integrações SAP:

- Timeout ≠ falha real
- Reenvio pode gerar duplicidade

### Estratégia adotada:

- Chave de idempotência (ex: requestId)
- Persistência de estado (PENDING, SUCCESS, FAILED)
- Lock otimista
- Deduplicação de chamadas

---

## 🔌 Contract-First (WSDL)

Geração automática de classes:

```bash
mvn clean generate-sources
````

Usando:

* `jaxws-maven-plugin` ou
* Apache CXF Codegen

---

## ⚙️ Configuração

### application.yml

```yaml
soap:
  client:
    base-url: https://sap.example.com/ws
    timeout: 3000
    retry:
      max-attempts: 3
```

---

## 🚀 Execução

```bash
mvn clean install
mvn spring-boot:run
```

---

## 🐳 Docker

```bash
docker build -t sap-soap-service .
docker run -p 8080:8080 sap-soap-service
```

---

## 🧪 Testes

### Estratégia

* Unitários → lógica de negócio
* Integração → mock SOAP server
* Contract tests → validação WSDL

Ferramentas:

* JUnit 5
* WireMock (mock SOAP)
* SoapUI

---

## 📊 Observabilidade

### Métricas expostas:

* Latência por operação SOAP
* Taxa de erro
* Retry count
* Circuit breaker state

### Logs incluem:

* correlationId
* requestId
* payload reduzido (safe logging)

---

## ⚠️ Problemas Reais (e como o projeto resolve)

### ❌ Problema: instabilidade do SAP

✔ Solução: Retry + Circuit Breaker

---

### ❌ Problema: duplicidade de processamento

✔ Solução: Idempotência

---

### ❌ Problema: debugging difícil (XML gigante)

✔ Solução: Logging estruturado + tracing

---

### ❌ Problema: acoplamento forte com SOAP

✔ Solução: Adapter REST

---

## 🔄 Evolução Arquitetural

### Estado atual:

SOAP Integration Layer

### Próximos passos:

* API Gateway na frente
* Event-driven (Kafka) para desacoplamento
* Cache de responses SAP
* CQRS para leitura performática

---

## 📁 Estrutura do Projeto

```
src/main/java/com/company/integration/

├── adapter/          # REST → SOAP
├── application/      # Orquestração
├── domain/           # Regras de negócio
├── infrastructure/
│   ├── soap/         # Client SOAP
│   ├── config/       # Beans/config
│   └── resilience/   # Retry/CircuitBreaker
├── observability/    # Logs, metrics, tracing
└── idempotency/      # Controle de duplicidade
```

---

## 🔐 Segurança

* HTTPS obrigatório
* Possível suporte a:

  * WS-Security
  * Basic Auth
  * OAuth2 (no adapter REST)

---

## 🤝 Contribuição

Fluxo padrão:

1. Fork
2. Branch `feature/...`
3. PR com descrição técnica clara

---

## 📜 Licença

MIT

---

## 💡 Por que isso importa?

Integrações SOAP com SAP são:

* Críticas
* Sensíveis a falhas
* Difíceis de evoluir

Este projeto demonstra como trazer práticas modernas para um contexto legado sem quebrar compatibilidade.

---

## 🧠 Diferenciais Técnicos

* Anti-Corruption Layer bem definida
* Idempotência real (não fake)
* Resiliência production-grade
* Observabilidade desde o início
* Pronto para evolução para event-driven
