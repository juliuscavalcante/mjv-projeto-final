<img src="https://img.shields.io/badge/STATUS-EM DESENVOLVIMENTO-yellow"/>

<p align="center">
 <img src="https://user-images.githubusercontent.com/89096854/200204007-be5a94b9-a319-4704-819e-b10ef2bc7a91.svg" width=20%>
</p>

<h1 align="center">🏁 MJV Racing Team Management 🏁</h1>

<h2>Sumário</h2>
<ul>
 <li><a href="#sobre-o-projeto">Sobre o Projeto</a></li>
 <li><a href="#tecnologias-utilizadas">Tecnologias Utilizadas</a></li>
 <li><a href="#demonstracao">Demonstração</a></li> 
 <li><a href="#screenshots">Screenshots</a></li> 
 <li><a href="#como-rodar-o-projeto">Como Rodar o Projeto</a></li>
 <li><a href="#pontos-de-melhoria">Pontos de Melhoria</a></li> 
</ul>

## Introdução

Projeto final desenvolvido para MJV School Java. Para criação desse projeto foi considerado um contexto hipotético, o tema do projeto foi escolhido mediante a um dos requisitos propostos, no qual era o desenvolvimento de API relacionada a categoria de Esportes.

## Sobre o Projeto

O Projeto MJV Racing Team Management trata-se de uma API REST, uma plataforma para gerenciamento de funcionários e criação de ordens de serviços internas de uma equipe de Fórmula 1. A aplicação possui uma estrutura de CRUD com os principais métodos HTTP (GET/PUT/UPDATE/DELETE).

O sistema foi desenvolvido em Java e Spring com as configurações realizadas com Spring Boot, as entidades foram mapeadas utilizando Spring Data Jpa e Hibernate e os dados foram persistidos no Banco de Dados PostgresSQL.

Para gerenciamento de dependências foi utilizado o Maven, e na parte de segurança foram feitas as configurações com o Spring Security para Autenticação/Autorização de tokens via JWT.

Além do mais, foi usado o Design Pattern MVC, empregando o uso da camada de Repository com a função de agir como intermédio entre outras camadas, o Service para proteger a lógica de negócios da aplicação, os Controllers com a responsabilidade de processar as requisições e gerar as respostas, inclusive, também foi utilizado o padrão de arquitetura de DTOs (Data Transfer Objects), para aumentar a segurança das classes na transferência de dados. Além disso, foram criadas Exceptions personalizadas para gerar logs, e mensagens de erros mais coerentes ao usuário.

Os Testes unitários na camada de Service foram realizados mediante, JUnit, Mockito e Spring MockMVC.

No Frontend as tecnologias utilizadas foram Angular com TypeScript, no qual foram criados componentes visuais a partir do Angular Material.


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

- Apenas os Gerentes poderão criar, atualizar ou remover o cadastro de outro funcionário.

- Todos os funcionários possuem acesso a plataforma através de email e senha previamente cadastrados por um Gerente.

- As Ordens de Serviço (Requests) podem ser criadas relacionando Engenheiros e Mecânicos.

- As Ordens de Serviços possuem Status e Prioridade e é possível filtra-las através desses atributos.

- As Ordens de Serviço não podem ser excluídas, apenas deverão ter seu Status alterado para Fechada (Closed)

### Estratégia Single Table 
Foi utilizada a estratégia de Single Table para mapeamento das entidades, desse modo todas as entidades da estrutura de herança são mapeadas em uma única tabela. Essa abordagem torna as consultas mais eficientes e oferece melhor desempenho. Quando é feita a persistência dessa forma, o Hibernate precisa determinar a classe de cada entidade, essa informação é armazenada em uma coluna discriminadora (que não é um atributo de entidade). Essa coluna vem com nome por padrão de Dtype, porém com a anotação __@DiscriminatorColumn__ essa coluna foi alterada para __“job_roles”.__

![image](https://user-images.githubusercontent.com/89096854/199834954-d2d45287-5f84-4a92-9b63-a311566a8205.png)
 
- Exemplo: 
![image](https://user-images.githubusercontent.com/89096854/199834927-ffe03b6e-3b88-44ad-a467-e7d4ec6c4f24.png)


## Demonstração

>__Note__
O projeto foi criado em duas pontas (backend e frontend). O backend foi hospeado no Heroku e o frontend na plataforma Vercel.
<br><br>__Repositório Frontend:__ https://github.com/juliuscavalcante/mjv-projeto-final-front
<br><br>__Link da Aplicação:__ [https://mjv-racing.vercel.app/login](https://mjv-racing.vercel.app/login)
<br> __login:__ julius@email.com
<br> __password:__ 123
 
 ### Video Demo e Screenshots

### Endpoints 
 
#### /driver /engineer /mechanic /manager
 
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

#### /requests

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

### Como Rodar o projeto
 ...
 
### Pontos de Melhoria

- [ ] Correção de erros visuais;
- [ ] Implementação do método PATCH para alteração do STATUS das Ordens de Serviço; 
- [ ] Correção de máscara de dados;
- [ ] Migrations para criação de tabelas e popular Database (Flyway);
- [ ] Deploy em outra plataforma (AWS ou GCP ou Azure);
- [ ] Conteinerização (Kubernetes ou Docker);
- [ ] Serviço de Mensageria (Kafka ou RabbitMQ);
- [ ] Testes de integração na camada Controller;

