<img src="https://img.shields.io/badge/STATUS-CONCLUÍDO-green"/>

<p align="center">
 <img src="https://user-images.githubusercontent.com/89096854/200204007-be5a94b9-a319-4704-819e-b10ef2bc7a91.svg" width=20%>
</p>

<h1 align="center">🏁 MJV Racing Team Management 🏁</h1>

<h2>Sumário</h2>
<ul>
 <li><a href="#introducao">Introdução</a></li>
 <li><a href="#sobre_o_projeto">Sobre o Projeto</a></li>
 <li><a href="#tecnologias_utilizadas">Tecnologias Utilizadas</a></li>
 <li><a href="#diagrama_de_classes">Diagrama de Classes</a></li>
 <li><a href="#regras_de_negocio">Regras de Negócio</a></li>
 <li><a href="#acesso_a_aplicacao">Acesso à Aplicação</a></li>
 <li><a href="#demonstracao">Demonstração</a></li> 
 <li><a href="#screenshots">Screenshots</a></li>
 <li><a href="#endpoints">Endpoints</a></li> 
 <li><a href="#como_rodar_o_projeto">Como Rodar o Projeto</a></li>
 <li><a href="#pontos_de_melhoria">Pontos de Melhoria</a></li> 
</ul>

<h2 id="introducao">Introdução</h2>

Projeto final desenvolvido para MJV School Java. Para criação desse projeto foi considerado um contexto hipotético, o tema do projeto foi escolhido mediante a um dos requisitos propostos, no qual era o desenvolvimento de API relacionada a categoria de Esportes.

<h2 id="sobre_o_projeto">Sobre o Projeto</h2>

O Projeto MJV Racing Team Management trata-se de uma API REST, uma plataforma para gerenciamento de funcionários e criação de ordens de serviços internas de uma equipe de Fórmula 1. A aplicação possui uma estrutura de CRUD com os principais métodos HTTP (GET/PUT/UPDATE/DELETE).

O sistema foi desenvolvido em Java e Spring com as configurações realizadas com Spring Boot, as entidades foram mapeadas utilizando Spring Data Jpa e Hibernate e os dados foram persistidos no Banco de Dados PostgresSQL.

Para gerenciamento de dependências foi utilizado o Maven, e na parte de segurança foram feitas as configurações com o Spring Security para Autenticação/Autorização de tokens via JWT.

Além do mais, foi usado o Design Pattern MVC, empregando o uso da camada de Repository com a função de agir como intermédio entre outras camadas, o Service para proteger a lógica de negócios da aplicação, os Controllers com a responsabilidade de processar as requisições e gerar as respostas, inclusive, também foi utilizado o padrão de arquitetura de DTOs (Data Transfer Objects), para aumentar a segurança das classes na transferência de dados. Além disso, foram criadas Exceptions personalizadas para gerar logs, e mensagens de erros mais coerentes ao usuário.

Os Testes unitários na camada de Service foram realizados mediante, JUnit, Mockito e Spring MockMVC.

No Frontend as tecnologias utilizadas foram Angular com TypeScript, no qual foram criados componentes visuais a partir do Angular Material.

### Estratégia Single Table 
Foi utilizada a estratégia de Single Table para mapeamento das entidades, desse modo todas as entidades da estrutura de herança são mapeadas em uma única tabela. Essa abordagem torna as consultas mais eficientes e oferece melhor desempenho. Quando é feita a persistência dessa forma, o Hibernate precisa determinar a classe de cada entidade, essa informação é armazenada em uma coluna discriminadora (que não é um atributo de entidade). Essa coluna vem com nome por padrão de Dtype, porém com a anotação __@DiscriminatorColumn__ essa coluna foi alterada para __“job_roles”.__

![image](https://user-images.githubusercontent.com/89096854/199834954-d2d45287-5f84-4a92-9b63-a311566a8205.png)
 
- Exemplo: 
![image](https://user-images.githubusercontent.com/89096854/199834927-ffe03b6e-3b88-44ad-a467-e7d4ec6c4f24.png)


<h2 id="tecnologias_utilizadas">Tecnologias Utilizadas</h2>

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

<h2 id="diagrama_de_classes">Diagrama de Classes</h2>

<div align-itens = center>

<p align="center">
    <img src="https://user-images.githubusercontent.com/89096854/200203487-5720a025-bd46-4602-a2ad-a86011c71852.PNG" width=60%/>
</p>

<h2 id="regras_de_negocio">Regras de Negócio</h2>

- Uma das regras de negócio do projeto, é que todos os funcionários (Driver, Engineer, Mechanic) são automaticamente cadastrados como Usuários (USER), exceto funcionários que forem Gerentes (Manager), que serão cadastrados também como Administrador (ADMIN).

- Apenas os Gerentes poderão criar, atualizar ou remover o cadastro de outro funcionário.

- Todos os funcionários possuem acesso a plataforma através de email e senha previamente cadastrados por um Gerente.

- As Ordens de Serviço (Requests) podem ser criadas relacionando Engenheiros e Mecânicos.

- As Ordens de Serviços possuem Status e Prioridade e é possível filtra-las através desses atributos.

- As Ordens de Serviço não podem ser excluídas, apenas deverão ter seu Status alterado para Fechada (Closed)

<h2 id="acesso_a_aplicacao">Acesso à Aplicação</h2>

>__Note__
O projeto foi criado em duas pontas (backend e frontend). O backend foi hospeado no Heroku e o frontend na plataforma Vercel.
<br><br>__Repositório Frontend:__ https://github.com/juliuscavalcante/mjv-projeto-final-front
<br><br>__Link da Aplicação:__ [https://mjv-racing.vercel.app/login](https://mjv-racing.vercel.app/login)
<br> __login:__ julius@email.com
<br> __password:__ 123

<h2 id="demonstracao">Demonstração</h2>
 
 ### Video Demonstração Postman
 Video demonstração dos endpoints sendo utilizados via Postman.
 
 https://user-images.githubusercontent.com/89096854/200322866-fe343bd6-8071-4f48-b382-3f6ae3d7ab02.mp4
 
 ### Video Demonstração Frontend
 Video demonstração da utilização da plataforma.
 
 https://user-images.githubusercontent.com/89096854/200322899-b25f4303-5c4a-4f92-91fc-de8ce17cf61a.mp4
 
 <h2 id="screenshots">Screenshots</h2>
 
 <p align="center">
    <img src="https://user-images.githubusercontent.com/89096854/200324780-efe2a3a5-b1ce-4cc1-ad69-92c42fb8e940.PNG" width=90%/>
    <img src="https://user-images.githubusercontent.com/89096854/200324785-bc0c9d28-d4d0-4b59-97b7-61ae3ebe0ef1.PNG" width=90%/>
    <img src="https://user-images.githubusercontent.com/89096854/200328742-8cdb0352-6f3f-4dd9-a74c-3cea06b02a28.PNG" width=30%/>
    <img src="https://user-images.githubusercontent.com/89096854/200328448-5948b969-2f43-48a2-8160-077ebc77abb1.PNG" width=30%/>
    <img src="https://user-images.githubusercontent.com/89096854/200328918-56e04b0b-241a-4311-a16b-52b9129b996c.PNG" width=30%/>
</p>

## Endpoints 
 
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
 
<h2 id="como_rodar_o_projeto">Como Rodar o Projeto</h2>

<h3>Instalação</h3>

<p>O projeto é gerenciado pelo Maven, então para usa-lo basta importa-lo para uma IDE. </p>

<h3>Configurações do banco de dados</h3>

<p>Você pode criar um banco de dados PostgreSQL com o nome o nome de sua preferência, porém é necessario adequar o projeto de acordo com as suas configurações. Para isso abra o arquivo application.properties.dev, localizado em src/main/resources/application.properties.dev e altere os seguintes comandos ao arquivo:</p>

```
spring.datasource.url = jdbc:postgresql://localhost:5432/nome-do-seu-banco-de-dados
spring.datasource.username = seu-usuario
spring.datasource.password = sua-senha
```
 
<h3>Execução</h3>

<p>Para executar os endpoints através do Postman, utilize esta Collection: 

[![Run in Postman](https://run.pstmn.io/button.svg)](https://god.gw.postman.com/run-collection/21173863-dbe8440e-b00c-4fc5-a171-aac3dbb7ff4b?action=collection%2Ffork&collection-url=entityId%3D21173863-dbe8440e-b00c-4fc5-a171-aac3dbb7ff4b%26entityType%3Dcollection%26workspaceId%3D328b068a-d6a3-4ee7-ba5f-69fba036c584) </p>
 
<h2 id="pontos_de_melhoria">Pontos de Melhoria</h2>

- [ ] Correção de erros visuais;
- [ ] Implementação do método PATCH para alteração do STATUS das Ordens de Serviço; 
- [ ] Correção de máscara de dados;
- [ ] Migrations para criação de tabelas e popular Database (Flyway);
- [ ] Deploy em outra plataforma (AWS ou GCP ou Azure);
- [ ] Testes de integração na camada Controller;

