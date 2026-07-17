<div align="center">

# 🛒 Workshop Spring Boot 4 + JPA

### API REST para um sistema de pedidos (e-commerce), construída como estudo aprofundado de **Java, Spring Boot e Spring Data JPA/Hibernate**.

![Java](https://img.shields.io/badge/Java-25-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.5-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![H2](https://img.shields.io/badge/H2%20Database-1F5C5C?style=for-the-badge&logo=databricks&logoColor=white)

</div>

---

## 📌 Sobre a API

Esta API modela o backend de um pequeno e-commerce, expondo recursos para **usuários**, **produtos**, **categorias** e **pedidos**. O foco do projeto está na modelagem de domínio (entidades e seus relacionamentos), na arquitetura em camadas do Spring e no tratamento padronizado de erros HTTP — pilares essenciais de qualquer API REST profissional em Java.

---

## 🎯 Objetivo do Projeto

Este projeto foi desenvolvido com fins **exclusivamente educacionais**, como parte da minha jornada de estudos em desenvolvimento **Backend com Java e Spring Boot**. O objetivo é consolidar, na prática, os principais conceitos utilizados na construção de APIs REST profissionais: arquitetura em camadas, persistência com JPA/Hibernate, relacionamentos entre entidades, tratamento de exceções e boas práticas de organização de código.

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Finalidade |
|---|---|
| **Java 25** | Linguagem principal do projeto |
| **Spring Boot 4** | Framework para construção da aplicação e do servidor web |
| **Spring Data JPA** | Abstração para persistência e acesso a dados |
| **Hibernate** | Implementação de ORM (Mapeamento Objeto-Relacional) |
| **Maven** | Gerenciador de dependências e build do projeto |
| **H2 Database** | Banco de dados relacional em memória, usado em desenvolvimento e testes |
| **REST API** | Padrão arquitetural para exposição dos recursos |
| **JSON** | Formato de serialização das requisições e respostas |
| **Postman** | Ferramenta utilizada para testar manualmente os endpoints |
| **Git & GitHub** | Controle de versão e hospedagem do código-fonte |

---

## 📚 Conceitos e Aprendizados

Durante o desenvolvimento deste projeto, foram estudados e aplicados os seguintes conceitos:

### 🏗️ Arquitetura em Camadas
O projeto segue uma separação clara de responsabilidades, dividida em três camadas principais:
- **Resource** (`resources`): camada de controladores REST, responsável por receber as requisições HTTP e devolver as respostas.
- **Service** (`services`): camada de regras de negócio, intermediando a comunicação entre os recursos e o acesso a dados.
- **Repository** (`repositories`): camada de persistência, responsável pelo acesso direto ao banco de dados via Spring Data JPA.

### 🌐 APIs REST
Implementação de endpoints seguindo os princípios REST, utilizando os verbos HTTP (`GET`, `POST`, `PUT`, `DELETE`) de forma semântica sobre os recursos da aplicação.

### 🔁 CRUD
Implementação de operações de **Create, Read, Update e Delete** para o recurso `User`, cobrindo o ciclo completo de manipulação de dados via API.

### 🗂️ Mapeamento Objeto-Relacional (ORM)
Uso do Hibernate como provedor JPA para converter entidades Java em tabelas relacionais de forma transparente, através de anotações como `@Entity`, `@Table`, `@Id` e `@GeneratedValue`.

### 🔗 Relacionamentos JPA
O modelo de domínio explora os principais tipos de relacionamento entre entidades:
- **`@OneToMany` / `@ManyToOne`** → um `User` possui vários `Order`, e cada `Order` pertence a um único `User`.
- **`@OneToOne`** → cada `Order` possui um único `Payment` associado (com `@MapsId`, compartilhando a mesma chave primária).
- **`@ManyToMany`** → `Product` e `Category` se relacionam entre si através de uma tabela de junção (`tb_product_category`).

### 🔑 Chaves Compostas (`@EmbeddedId`)
A entidade `OrderItem` (item de pedido) utiliza uma chave primária composta (`OrderItemPK`), formada pela combinação de `Order` e `Product`, resolvendo o relacionamento N:N entre pedidos e produtos com atributos adicionais (`quantity` e `price`).

### 🔢 Enum Persistido no Banco
A entidade `Order` armazena seu status (`OrderStatus`) como um valor `Integer` no banco de dados, convertendo-o de/para o enum na camada Java — uma alternativa ao `@Enumerated`, controlada manualmente via getters/setters.

### ⚠️ Tratamento de Exceções
Uso de `@ControllerAdvice` e `@ExceptionHandler` para centralizar o tratamento de erros da API, com respostas padronizadas (`StandardError`) para os cenários de:
- Recurso não encontrado (`ResourceNotFoundException` → `404 Not Found`)
- Violação de integridade no banco de dados (`DatabaseException` → `400 Bad Request`)

### 💉 Injeção de Dependências
Uso extensivo de `@Autowired` para inversão de controle entre as camadas `Resource → Service → Repository`, gerenciadas pelo container do Spring.

### 📦 `ResponseEntity`
Uso de `ResponseEntity` para controle explícito dos códigos de status HTTP retornados (`200 OK`, `201 Created`, `204 No Content`, etc.), incluindo a construção da URI do recurso criado com `ServletUriComponentsBuilder`.

### 🔄 Serialização JSON
Uso de anotações do Jackson (`@JsonIgnore`, `@JsonFormat`) para controlar como as entidades são convertidas em JSON, evitando referências cíclicas entre entidades relacionadas (ex: `User ↔ Order`) e formatando datas (`Instant`) em um padrão legível.

### 🧹 Boas Práticas de Organização
Separação do código por pacotes de responsabilidade (`entities`, `repositories`, `services`, `resources`, `exceptions`, `config`), além do uso de uma classe `TestConfig` (`CommandLineRunner`) para popular o banco H2 com dados de exemplo (*seed*) ao iniciar a aplicação no perfil de testes.

---

## 🧩 Modelo de Domínio

O domínio da aplicação é composto por seis entidades principais:

| Entidade | Descrição |
|---|---|
| **User** | Cliente do sistema, que pode realizar vários pedidos. |
| **Order** | Pedido realizado por um usuário, com data/hora (`moment`) e status. |
| **OrderItem** | Item de um pedido — associa um `Order` a um `Product`, com quantidade e preço (chave composta). |
| **Payment** | Pagamento vinculado a um único pedido (`Order`). |
| **Product** | Produto disponível para venda, podendo pertencer a várias categorias. |
| **Category** | Categoria de produtos (ex: Eletrônicos, Livros, Informática). |

**Relacionamentos:**

```
User (1) ──────< (N) Order (1) ────── (1) Payment
                    │
                    │ (1)
                    ∨
                  (N) OrderItem (N) ────── (1) Product (N) ──────< (N) Category
```

- Um **User** possui vários **Orders**.
- Um **Order** pertence a um único **User** e possui um único **Payment**.
- Um **Order** é composto por vários **OrderItems**, que conectam o pedido aos **Products** comprados (com quantidade e preço registrados no próprio item).
- Um **Product** pode pertencer a várias **Categories**, e uma **Category** pode conter vários **Products**.

---

## 📁 Estrutura do Projeto

```
src/main/java/com/caduaraujo/webservices
 ├── config
 │    └── TestConfig.java              # Popula o banco H2 com dados de exemplo (perfil "test")
 │
 ├── entities
 │    ├── User.java
 │    ├── Order.java
 │    ├── OrderItem.java
 │    ├── Payment.java
 │    ├── Product.java
 │    ├── Category.java
 │    ├── enums
 │    │    └── OrderStatus.java
 │    └── pk
 │         └── OrderItemPK.java        # Chave composta de OrderItem
 │
 ├── repositories
 │    ├── UserRepository.java
 │    ├── OrderRepository.java
 │    ├── OrderItemRepository.java
 │    ├── ProductRepository.java
 │    └── CategoryRepository.java
 │
 ├── resources
 │    ├── UserResource.java
 │    ├── OrderResource.java
 │    ├── ProductResource.java
 │    ├── CategoryResource.java
 │    └── exceptions
 │         ├── ResourceExceptionHandler.java  # @ControllerAdvice
 │         └── StandardError.java
 │
 ├── services
 │    ├── UserService.java
 │    ├── OrderService.java
 │    ├── ProductService.java
 │    ├── CategoryService.java
 │    └── exceptions
 │         ├── ResourceNotFoundException.java
 │         └── DatabaseException.java
 │
 └── WebservicesApplication.java
```

---

## ✅ Funcionalidades

✔️ Listar todos os usuários

✔️ Buscar usuário por ID

✔️ Criar um novo usuário

✔️ Atualizar um usuário existente

✔️ Remover um usuário

✔️ Listar todos os pedidos

✔️ Buscar pedido por ID (com itens, pagamento e total calculado)

✔️ Listar todos os produtos

✔️ Buscar produto por ID (com suas categorias)

✔️ Listar todas as categorias

✔️ Buscar categoria por ID

✔️ Tratamento centralizado de erros com respostas HTTP padronizadas

> 💡 O CRUD completo foi implementado no recurso `User` para demonstrar o fluxo integral de manipulação de dados. Os demais recursos (`Order`, `Product`, `Category`) expõem, por ora, apenas as operações de leitura.

---

## 🔌 Endpoints

### 👤 Usuários — `/users`

| Método | Endpoint | Descrição |
|---|---|---|
| `GET` | `/users` | Lista todos os usuários |
| `GET` | `/users/{id}` | Busca um usuário pelo ID |
| `POST` | `/users` | Cria um novo usuário |
| `PUT` | `/users/{id}` | Atualiza um usuário existente |
| `DELETE` | `/users/{id}` | Remove um usuário |

### 📦 Pedidos — `/orders`

| Método | Endpoint | Descrição |
|---|---|---|
| `GET` | `/orders` | Lista todos os pedidos |
| `GET` | `/orders/{id}` | Busca um pedido pelo ID |

### 🛍️ Produtos — `/products`

| Método | Endpoint | Descrição |
|---|---|---|
| `GET` | `/products` | Lista todos os produtos |
| `GET` | `/products/{id}` | Busca um produto pelo ID |

### 🏷️ Categorias — `/categories`

| Método | Endpoint | Descrição |
|---|---|---|
| `GET` | `/categories` | Lista todas as categorias |
| `GET` | `/categories/{id}` | Busca uma categoria pelo ID |

---

## 🗄️ Banco de Dados

Durante o desenvolvimento, foi utilizado o **H2 Database**, um banco de dados relacional em memória, ideal para agilizar o ciclo de testes sem depender de um SGBD externo instalado na máquina.

Ao subir a aplicação (perfil `test`), a classe `TestConfig` popula automaticamente o banco com dados de exemplo (usuários, produtos, categorias e pedidos), permitindo testar a API imediatamente após a inicialização.

O console web do H2 está habilitado e pode ser acessado para inspecionar as tabelas e executar consultas SQL diretamente pelo navegador.

---

## ▶️ Como Executar

**1. Clone o repositório**
```bash
git clone https://github.com/caduaraujjo/workshop-springbot4-jpa.git
```

**2. Abra o projeto na sua IDE de preferência** (IntelliJ IDEA, Eclipse, VS Code, etc.)

**3. Execute a aplicação**

Pela IDE, rode a classe `WebservicesApplication`, ou via terminal com o Maven Wrapper:
```bash
./mvnw spring-boot:run
```

**4. Acesse a API**

A aplicação sobe por padrão em:
```
http://localhost:8080
```

**5. Teste os endpoints via Postman** (ou Insomnia/cURL), utilizando as rotas listadas na seção [Endpoints](#-endpoints). Exemplo:
```bash
curl http://localhost:8080/users
```

**6. Acesse o Console do H2** *(opcional)*

```
http://localhost:8080/h2-console
```
- **JDBC URL:** `jdbc:h2:mem:testdb`
- **User:** `sa`
- **Password:** *(em branco)*

---

## 🚀 Melhorias Futuras

Este projeto tem propósito educacional e pode evoluir com as seguintes melhorias:

- 🔐 Autenticação e autorização com **JWT**
- 🛡️ Integração com **Spring Security**
- 🐘 Migração para banco de dados **PostgreSQL**
- 🐳 Containerização com **Docker**
- ✅ **Testes unitários** (JUnit/Mockito)
- 🔄 **Testes de integração**
- 📄 Documentação interativa com **Swagger/OpenAPI**
- ☁️ **Deploy** em ambiente de nuvem
- ⚙️ Pipeline de **CI/CD**
- 📑 **Paginação** de resultados
- 🔍 **Filtros** de busca avançados
- ✔️ **Validações** com Bean Validation
- 📝 Implementação de **Logs**
- 📊 **Monitoramento** da aplicação

---

## 👨‍💻 Sobre o Projeto

Este projeto faz parte da minha jornada de aprendizado em desenvolvimento **Backend** utilizando **Java** e **Spring Boot**. Foi desenvolvido com foco em compreender, na prática, os principais conceitos envolvidos na construção de **APIs REST profissionais** — desde a modelagem de domínio e o mapeamento objeto-relacional até o tratamento de erros e a organização em camadas — servindo como base sólida para projetos mais complexos no futuro.

<div align="center">

**Desenvolvido por [Cadu Araujo](https://github.com/caduaraujjo)**

</div>
