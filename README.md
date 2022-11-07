<img src="https://img.shields.io/badge/STATUS-EM DESENVOLVIMENTO-yellow"/>

<p align="center">
 <img src="https://user-images.githubusercontent.com/89096854/200204007-be5a94b9-a319-4704-819e-b10ef2bc7a91.svg" width=20%>
</p>

<h1 align="center">🏁 MJV Racing Team Management 🏁</h1>

<h2>Sumário</h2>
<ul>
 <li><a href="#objetivo">Objetivo</a></li> 
 <li><a href="#sobre-o-projeto">Sobre o Projeto</a></li>
 <li><a href="#tecnologias-utilizadas">Tecnologias Utilizadas</a></li>
 <li><a href="#demonstracao">Demonstração</a></li> 
 <li><a href="#screenshots">Screenshots</a></li> 
 <li><a href="#como-rodar-o-projeto">Como Rodar o Projeto</a></li>
 <li><a href="#pontos-de-melhoria">Pontos de Melhoria</a></li> 
</ul>

# MJV Racing Team Management 🏁

## Introdução

Projeto final desenvolvido para MJV School Java. Para criação desse projeto foi considerado um contexto hipotético, o tema do projeto foi escolhido mediante a um dos requisitos propostos, no qual era o desenvolvimento de API relacionada a categoria de Esportes.

## Conceituação

O Projeto MJV Racing Team Management é uma plataforma criada com intuíto de auxiliar uma equipe de Formula 1. A aplicação permite o cadastramento, leitura, atualização e remoção de funcionários, assim como a criação ordens de serviços internas entre a equipe.

<h2 id="tecnologias-utilizadas">Tecnologias Utilizadas:</h2>

<p>
   <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white"/>
   <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"/>
   <img src="https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot"/>
   <img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white"/>
   <img src="https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white"/>
   <img src="https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white"/>
   <img src="https://img.shields.io/badge/Junit5-25A162?style=for-the-badge&logo=junit5&logoColor=white"/>
   <img src="https://img.shields.io/badge/angular-%23DD0031.svg?style=for-the-badge&logo=angular&logoColor=white"/>
   <img src="https://img.shields.io/badge/typescript-%23007ACC.svg?style=for-the-badge&logo=typescript&logoColor=white"/>
</p>

## Diagrama de Classes

<div align-itens = center>

<p align="center">
    <img src="https://user-images.githubusercontent.com/89096854/200203487-5720a025-bd46-4602-a2ad-a86011c71852.PNG" width=60%/>
</p>

## Regras de Negócio

- Uma das regras de negócio do projeto, é que todos os funcionários (Driver, Engineer, Mechanic) são automaticamente cadastrados como Usuários (USER), exceto funcionários que forem Gerentes (Manager), que serão cadastrados também como Administrador (ADMIN).

- Apenas os Gerentes (Managers) poderão criar, atualizar ou remover o cadastro de outro funcionário.

- É possível filtrar funcionários por nome, cpf, email ou data de nascimento

- Todos os funcionários possuem acesso a plataforma através de email e senha previamente cadastrados por um Gerente (Manager).

- As Ordens de Serviço (Requests) podem ser criadas relacionando Engenheiros (Engineer) e Mecânicos (Mechanic).

- As Ordens de Serviços (Request) possuem Status e Prioridade e é possível filtra-las através desses atributos.

- As Ordens de Serviço não podem ser excluídas, apenas deverão ter seu Status alterado para Fechada (Closed)

## Arquitetura 

![image](https://user-images.githubusercontent.com/89096854/199824633-256ed329-a244-40ab-af78-4c4afdd75221.png)

| Camada  | Definição |
| ------------- | ------------- |
| Repository  | Coleção de objetos com a função de agir como intermédio com outra camada. |
| Service  | Permite proteger a lógica de negócios da aplicação.  |
| Controller  | São os responsáveis pelo processamento das requisições e gerar as respostas. |
| Security  | Camada de segurança com as configurações JWT Authentication/Authorization. |
| Exceptions  | Exceptions personalizadas para poder gerar logs e mensagens de erro mais coerentes ao usuário. |
| DTOs  | Padrão de arquitetura para blindar as classes, fazendo com que os dados sejam encapsulandos para transferência.  |

### Estratégia Single Table 
Foi utilizada a estratégia de Single Table para mapeamento das entidades, desse modo todas as entidades da estrutura de herança são mapeadas em uma única tabela. Essa abordagem torna as consultas mais eficientes e oferece melhor desempenho. Quando é feita a persistência dessa forma, o Hibernate precisa determinar a classe de cada entidade, essa informação é armazenada em uma coluna discriminadora (que não é um atributo de entidade). Essa coluna vem com nome por padrão de Dtype, porém com a anotação __@DiscriminatorColumn__ essa coluna foi alterada para “job_roles”. 

![image](https://user-images.githubusercontent.com/89096854/199834954-d2d45287-5f84-4a92-9b63-a311566a8205.png)

![image](https://user-images.githubusercontent.com/89096854/199834927-ffe03b6e-3b88-44ad-a467-e7d4ec6c4f24.png)


## Demonstração

O projeto foi feito em duas pontas (backend e frontend) o backend foi hospeado no Heroku e o frontend na plataforma Vercel.

Repositório Frontend: https://github.com/juliuscavalcante/mjv-projeto-final-front

Link da Aplicação: [https://mjv-racing.vercel.app/login](https://mjv-racing.vercel.app/login)

### Endpoint /drivers

Método POST para criar um novo(a) Driver/Engineer/Mechanic/Manager

- cpf e email precisam estar no formato correto
    - cpf: “000.000.000-00”
    - email: “exemplo@email.com”

```json
{
    "name": "string",
    "cpf": "string",
    "email": "string",
    "password": "string",
    "birthDate": "dd/MM/yyyy"
}
```

### Endpoint /requests

Método POST para criar uma nova Ordem de Serviço

- Os campos devem ser preenchidos da seguinte forma:
    - priority: 0 = LOW | 1 = MEDIUM | 2 = HIGH
    - status: 0 = OPEN | 1 = PROGRESS | 2 = CLOSED
    - engineer: id do engenheiro correspondente
    - mechanic: id do mecânico correspondente

```json
{
    "priority": int,
    "status": int,
    "title": "string",
    "notes": "string",
    "engineer": int,
    "mechanic": int
}
```

### Pontos de Melhoria

- [ ] Correção de erros visuais;
- [ ] Correção de máscara de dados;
- [ ] Migrations para criação de tabelas e popular Database (Flyway);
- [ ] Deploy em outra plataforma (AWS ou GCP ou Azure);
- [ ] Conteinerização (Kubernetes ou Docker);
- [ ] Serviço de Mensageria (Kafka ou RabbitMQ);
- [ ] Testes de integração na camada Controller;

