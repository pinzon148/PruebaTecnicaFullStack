# 🧾 Prueba Técnica Fullstack - Sistema de Evaluación de Créditos

## 📌 Descripción General

Este proyecto implementa una arquitectura basada en microservicios para la evaluación de solicitudes de crédito.

El sistema simula un flujo real donde un cliente solicita un crédito, se evalúa su perfil financiero y se determina si el crédito es aprobado o rechazado.

---

## 🧠 Arquitectura de la Solución

El sistema está compuesto por tres proyectos principales:

```plaintext
Frontend (Angular)
        ↓
ms-orchestrator (Backend principal)
        ↓
ms-risk (Microservicio externo simulado)
        ↓
PostgreSQL (Base de datos)
```

---

## 🧩 Microservicios

### 🔹 1. ms-risk

Simula un servicio externo de análisis financiero.

#### Funcionalidades:

* Genera score crediticio aleatorio
* Genera deudas simuladas
* Simula latencia (como servicios reales)

#### Endpoints:

```http
GET /risk/score/{cedula}
GET /risk/debts/{cedula}
```

---

### 🔹 2. ms-orchestrator

Es el núcleo del sistema. Aquí se implementa la lógica de negocio.

#### Funcionalidades:

* Recibe solicitudes de crédito
* Valida datos
* Consulta ms-risk
* Aplica reglas de negocio
* Guarda resultados en base de datos
* Retorna resultado final

#### Endpoints:

```http
POST /v1/credit-evaluations
GET  /v1/credit-evaluations
```

---

## ⚙️ Lógica de Negocio

Un crédito es aprobado si:

* Score > 70
* (Deuda total + monto solicitado) < 40% del salario

---

## 🗄️ Base de Datos

Se utiliza PostgreSQL.

### Tabla:

* `CreditEvaluation`

Campos:

* cedula
* monto
* estado
* score

---

## 🧱 Estructura del Backend

```plaintext
ms-orchestrator/
└── src/main/java/com/example/orchestrator/
    ├── client/        → consumo de ms-risk
    ├── resource/      → endpoints REST
    ├── service/       → lógica de negocio
    ├── model/         → request/response
    ├── entity/        → entidades BD
    ├── repository/    → acceso a datos
    ├── exception/     → manejo de errores
    └── util/          → validaciones
```

---

## 🚀 Cómo ejecutar el proyecto

### 🔹 1. Requisitos

* Java 17+
* Maven
* PostgreSQL

---

### 🔹 2. Ejecutar ms-risk

```bash
cd ms-risk
mvn quarkus:dev
```

Corre en:

```
http://localhost:8081
```

---

### 🔹 3. Ejecutar ms-orchestrator

```bash
cd ms-orchestrator
mvn quarkus:dev
```

Corre en:

```
http://localhost:8080
```

---

### 🔹 4. Configuración Base de Datos

Crear base de datos:

```sql
CREATE DATABASE creditdb;
```

Configurar en `application.properties`:

```properties
quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/creditdb
quarkus.datasource.username=postgres
quarkus.datasource.password=1234
```

---

## 🧪 Pruebas

### 🔹 Crear evaluación

```http
POST http://localhost:8080/v1/credit-evaluations
```

Body:

```json
{
  "cedula": "123456789",
  "monto": 500,
  "tiempo": 2,
  "salario": 2000
}
```

---

### 🔹 Obtener historial

```http
GET http://localhost:8080/v1/credit-evaluations
```

---

## ⚠️ Manejo de errores

El sistema incluye manejo global de excepciones:

```json
{
  "error": "Mensaje de error",
  "status": 400
}
```

---

## 🧠 Decisiones Técnicas

* Uso de arquitectura por capas (resource, service, repository)
* Separación de responsabilidades
* Uso de microservicios
* Simulación de servicios externos
* Persistencia con ORM (Panache)
* Validación de datos (incluye algoritmo módulo 10)

---

## 🚀 Posibles Mejoras

* Tests unitarios
* Seguridad (JWT)
* Dockerización
* Deploy en AWS
* Circuit breaker (resiliencia)
* Logs estructurados

---

## 🏁 Conclusión

La solución implementa un flujo completo de evaluación de créditos con buenas prácticas de desarrollo backend, simulando un entorno real con microservicios, persistencia y lógica de negocio desacoplada.

---
