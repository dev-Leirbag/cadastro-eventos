# 📅 API de Eventos

Uma API RESTful completa para gerenciamento de eventos, com suporte a:

- Criação, consulta e filtragem de eventos
- Upload de imagens para a **AWS S3**
- Associação de cupons de desconto
- Migrações de banco de dados com **Flyway**

---

## 📚 Índice

- [Funcionalidades](#funcionalidades)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Pré-requisitos](#pré-requisitos)
- [Como Executar](#como-executar)
- [Documentação da API (Endpoints)](#documentação-da-api-endpoints)
- [Como Executar os Testes](#como-executar-os-testes)

---

## ✅ Funcionalidades

- ✅ **Criação de Eventos:** com upload opcional de imagem
- ✅ **Consulta de Eventos:** lista os próximos eventos com paginação
- ✅ **Busca com Filtros:** por título, cidade, estado e período
- ✅ **Detalhes do Evento:** retorna informações detalhadas + cupons
- ✅ **Criação de Cupons:** vincula cupons a um evento existente

---

## ⚙️ Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot 3**
- **Spring Data JPA / Hibernate**
- **PostgreSQL** (produção/desenvolvimento)
- **H2 Database** (testes)
- **Flyway** (migrações)
- **Amazon S3** (upload de arquivos)
- **Maven** (build/dependências)
- **Lombok** (boilerplate reduction)

---

## 📁 Estrutura do Projeto

com.evento.api

├── controller # Endpoints REST

├── service # Regras de negócio

├── domain # Entidades JPA e DTOs

├── repositories # Interfaces de acesso ao banco

└── resources

└── db/migration # Scripts SQL gerenciados pelo Flyway


---

## 🧰 Pré-requisitos

- Java 17+
- Maven
- PostgreSQL
- Conta na AWS com bucket S3 + Access Key/Secret

---

## ▶️ Como Executar

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   cd seu-repositorio

2. Configure o banco de dados PostgreSQL:

Crie um banco chamado eventos
O Flyway criará as tabelas automaticamente

3. Configure o arquivo application.properties:
# PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/eventos

spring.datasource.username=seu_usuario_postgres

spring.datasource.password=sua_senha_postgres

# AWS
aws.access.key.id=SUA_ACCESS_KEY

aws.access.key.secret=SUA_SECRET_KEY

aws.region=us-east-1

aws.bucket.name=seu-bucket-de-imagens

# Configurações gerais
server.port=6060

spring.flyway.locations=classpath:db/migration/postgresql

4. Execute a aplicação:
   mvn spring-boot:run

A API estará disponível em: http://localhost:6060

---

## 📌 Documentação da API (Endpoints)

## 📌 Eventos
1. Criar Evento
POST /api/event

Tipo de conteúdo: multipart/form-data

Campos do formulário:

title (String, obrigatório)

description (String, opcional)

date (Long, timestamp, obrigatório)

city (String, obrigatório)

uf (String, obrigatório)

remote (Boolean, obrigatório)

eventUrl (String, obrigatório)

image (File, opcional)

Resposta:
{
  "id": "uuid-gerado",
  "title": "Evento X",
  ...
}


2. Listar Próximos Eventos
GET /api/event

Query Params:

page (default: 0)

size (default: 10)

Resposta:
[
  {
    "id": "uuid",
    "title": "Conferência",
    "city": "São Paulo",
    "uf": "SP",
    ...
  }
]


3. Filtrar Eventos
GET /api/event/filter

Query Params:

page, size

title, city, uf

startDate, endDate (formato: YYYY-MM-DD)

Resposta: lista de eventos filtrados


4. Detalhes do Evento
GET /api/event/{eventId}

Path Param: eventId (UUID)

Resposta:
{
  "id": "uuid",
  "title": "Evento",
  "coupons": [
    {
      "code": "PROMO10",
      "discount": 10,
      "valid": "2025-10-19T23:59:59.000+00:00"
    }
  ]
}

---

## 🎟️ Cupons

1. Criar Cupom
POST /api/coupon/event/{eventId}

Path Param: eventId (UUID)

Body JSON:
{
  "code": "DESCONTO20",
  "discount": 20,
  "valid": 1766238000000
}

Resposta:
{
  "id": "uuid",
  "code": "DESCONTO20",
  ...
}

---

## 🧪 Como Executar os Testes

mvn test

Os testes usam banco H2 em memória

Arquivo de configuração separado: application-test.properties

---

## 📌 Projeto desenvolvido para fins educacionais e de prática com Spring Boot + AWS + PostgreSQL.

