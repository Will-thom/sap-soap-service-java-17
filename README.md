# SAP SOAP Integration Service (Java 17) — Enterprise Ready

A robust integration service for communicating with SAP via SOAP, designed with a strong focus on resilience, observability, contract-first design, and architectural evolution (SOAP → REST).

---

## 🎯 Purpose

This project implements a reliable integration layer between modern systems and SAP SOAP-based services, addressing real-world enterprise challenges:

- Interoperability with legacy systems
- Strong contract enforcement (WSDL/XSD)
- Resilience to network/SAP instability
- Idempotency and reprocessing control
- Observability and troubleshooting

---

## 🧱 Tech Stack

- Java 17
- Spring Boot
- Spring Web Services (Spring-WS)
- Apache CXF (optional)
- Maven
- JAXB (XML Binding)
- Resilience4j
- Micrometer + OpenTelemetry
- Docker

---

## 🏗️ Architecture

### 🔷 High-Level Overview

```

[ Client / REST API ]
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

## 🔩 Architectural Components

### 1. Adapter Layer (Anti-Corruption Layer)

Decouples the REST world from the SOAP contract.

- REST → SOAP translation
- Payload normalization
- API versioning

> Prevents XML/SOAP leakage into the domain layer

---

### 2. Application Service

Orchestrates the integration flow:

- Input validation
- Idempotency control
- Retry policies
- Semantic error handling

---

### 3. SOAP Client (Contract-First)

Generated from WSDL:

- Strong typing
- Contract safety
- Reduced runtime failure risk

---

### 4. Resilience Layer

Implemented with Resilience4j:

- Retry with exponential backoff
- Circuit Breaker
- Controlled timeouts
- Bulkhead isolation

---

### 5. Observability Layer

- Structured logging (JSON)
- Metrics (Micrometer)
- Distributed tracing (OpenTelemetry)

---

## 🔁 Idempotency & Reprocessing

A classic SAP integration challenge:

- Timeout ≠ actual failure
- Retries may cause duplication

### Strategy:

- Idempotency key (e.g., requestId)
- State persistence (PENDING, SUCCESS, FAILED)
- Optimistic locking
- Call deduplication

---

## 🔌 Contract-First (WSDL)

Automatic class generation:

```bash
mvn clean generate-sources
````

Using:

* `jaxws-maven-plugin` or
* Apache CXF Codegen

---

## ⚙️ Configuration

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

## 🚀 Running the Service

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

## 🧪 Testing

### Strategy

* Unit → business logic
* Integration → mock SOAP server
* Contract tests → WSDL validation

Tools:

* JUnit 5
* WireMock (SOAP mocking)
* SoapUI

---

## 📊 Observability

### Exposed metrics:

* SOAP operation latency
* Error rate
* Retry count
* Circuit breaker state

### Logs include:

* correlationId
* requestId
* reduced payload (safe logging)

---

## ⚠️ Real-World Problems (and how this project solves them)

### ❌ Problem: SAP instability

✔ Solution: Retry + Circuit Breaker

---

### ❌ Problem: duplicate processing

✔ Solution: Idempotency

---

### ❌ Problem: hard debugging (large XML payloads)

✔ Solution: Structured logging + tracing

---

### ❌ Problem: tight coupling to SOAP

✔ Solution: REST Adapter layer

---

## 🔄 Architectural Evolution

### Current state:

SOAP Integration Layer

### Next steps:

* API Gateway in front
* Event-driven architecture (Kafka)
* SAP response caching
* CQRS for high-performance reads

---

## 📁 Project Structure

```
src/main/java/com/company/integration/

├── adapter/          # REST → SOAP
├── application/      # Orchestration
├── domain/           # Business rules
├── infrastructure/
│   ├── soap/         # SOAP client
│   ├── config/       # Configuration
│   └── resilience/   # Retry/CircuitBreaker
├── observability/    # Logs, metrics, tracing
└── idempotency/      # Deduplication control
```

---

## 🔐 Security

* HTTPS enforced
* Optional support for:

  * WS-Security
  * Basic Auth
  * OAuth2 (REST adapter)

---

## 🤝 Contributing

Standard workflow:

1. Fork
2. Create branch `feature/...`
3. Submit PR with clear technical description

---

## 📜 License

MIT

---

## 💡 Why this matters

SAP SOAP integrations are:

* Critical
* Failure-sensitive
* Hard to evolve

This project shows how to bring modern engineering practices into a legacy context without breaking compatibility.

---

## 🧠 Technical Highlights

* Well-defined Anti-Corruption Layer
* Real idempotency (not superficial)
* Production-grade resilience
* Observability from day one
* Ready for event-driven evolution
