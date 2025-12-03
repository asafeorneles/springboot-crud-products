# 📝 Projeto Spring Boot - CRUD Básico

Este é o meu primeiro projeto utilizando **Spring Boot**, criado com o objetivo de aprender os conceitos fundamentais do framework. Aqui eu pratico desde a estrutura básica do Spring até a conexão com banco de dados, criação de endpoints e uso de Docker para facilitar o ambiente de desenvolvimento.

---

## 🚀 Tecnologias Utilizadas

* **Java 17**
* **Spring Boot 3** (Web, JPA/Hibernate, Validation)
* **MySQL**
* **Docker & Docker Compose**
* **Maven**

---

## 📌 Objetivo do Projeto

O objetivo deste projeto é aprender de forma prática:

* Como estruturar um projeto Spring Boot
* Como criar um CRUD completo (Create, Read, Update, Delete) no padrão MVC
* Como conectar a aplicação a um banco de dados MySQL
* Como utilizar Docker para subir serviços facilmente
* Como organizar Controllers, Models, DTOs e Repositories

---

## 🗂️ Estrutura do Projeto

```
src/
 └── main/
     ├── java/
     │    └── ... (controllers, models, repositories, dtos)
     └── resources/
          ├── application.properties
          └── ...
```

---

## 🐳 Docker

Um arquivo **docker-compose.yml** foi usado para subir o container do MySQL:

* Banco exposto em: `localhost:3306`
* Usuário: `root`
* Senha: `root`

Para rodar:

```bash
docker-compose up -d
```

---

## 📮 Endpoints Principais (CRUD)

### Criar

```
POST /products
```

### Listar

```
GET /products
```

### Atualizar

```
PUT /products/{id}
```

### Deletar

```
DELETE /products/{id}
```

---

## 🧠 Aprendizados

Durante este projeto, pude aprender sobre:

* Como o Spring gerencia Beans e Injeção de Dependência
* Como o Spring Data JPA facilita o acesso ao banco
* Como utilizar DTOs para validação
* Como mapear entidades com JPA
* Como containerizar o banco de dados com Docker

---

## 📦 Como Rodar o Projeto

1. Suba o MySQL com Docker:

```bash
docker-compose up -d
```

2. Execute o projeto pelo IntelliJ/NetBeans ou via terminal:

```bash
mvn spring-boot:run
```

3. Acesse os endpoints na porta padrão `8080`.

---


## Sobre o Projeto

Este projeto foi criado com foco total em aprendizado e prática. É simples, mas foi um grande passo no meu estudo de **Java + Spring Boot**.
