# Requisitos

<strong>Aplicação</strong>:
- Desenvolver uma aplicação utilizando Spring Boot - OK
- Criar uma operação baseada em uma entidade simples de relacionamento
  - Pessoa com Automóvel (Tanto automóvel quanto pessoa podem 
 existir sem ter dependência um do outro ou podem ter uma relação (Um-Para-Um)) - OK
  - Pedido com Produtos - OK (Um Produto pode existir sem um Pedido, porém
    um pedido necessita de no mínimo um produto (Um para muitos)) - OK
 - Realizar operações de CRUD - OK
   - INSERT,DELETE,UPDATE,CREATE
 - Banco de Dados MySQL - OK
 - Aplicação do frontend utilizando as próprias plataformas do Java/Spring - OK
   (Swagger)

   
# Tecnologias
- Padrão REST na construção da API
- Payloads enviados para a API em formato JSON
- Spring Boot com Java 17
- Banco de dados MySQL
- Swagger para documentação 
- Docker para criação de banco e tabelas

# Observações
- A documentação da API com swagger ao rodar o projeto fica disponível por meio do link
  http://localhost:8080/swagger-ui/index.html#/
- Necessário ter o Docker devidamente instalado e configurado
- Necessário IDE IntelliJ ou Similar com Java 17 

# Execução
- Para rodar o projeto suba o banco utilizando docker-compose up -d
- Em seguida rode o ConcessionariaApplication para conectar
- Após isso acesse http://localhost:8080/swagger-ui/index.html#/ para 
executar os testes

# Posts feitos:
- Pessoa pode receber posts das seguintes formas: 
  - {
    "nome": "Chevrolet da Silva Ford",
    "cpf": "00300400587",
    "estado": "PA",
    "automovelEntity": {
    "id": 1
    }
    }
  - 
    {
    "nome": "Chevrolet da Silva Ford",
    "cpf": "00300400587",
    "estado": "PA"
      }


