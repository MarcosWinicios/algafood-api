[**Voltar ao README**](../README.md#documentação-de-conceitos)

# Arquivo ``application.properties``

**Este documento descreve os conceitos das propriedades utilizadas nas configurações no arquivo ``application.properties``.**

## Spring JPA e Hibernate

### Definir configurações do banco de dados
```
  spring.datasource.url=jdbc:mysql://localhost:3306/algafood?createDatabaseIfNotExist=true
  spring.datasource.username=root
  spring.datasource.password=123456
```
- ``spring.datasource.url``
  - ``jdbc:mysql://:`` Define que o driver JDBC para MySQL será usado.
  - ``localhost:`` Refere-se ao host onde o banco de dados está sendo executado (neste caso, o próprio computador local).
  - ``3306:`` Porta padrão do MySQL.
  - ``algafood:`` Nome do banco de dados que será acessado.
      ?createDatabaseIfNotExist=true: Parâmetro adicional que indica ao MySQL que o banco de dados será criado automaticamente, caso ele ainda não exista.
- ``spring.datasource.username``: Define o nome do usuário usado para se conectar ao banco de dados
- ``spring.datasource.password``: Define a senha associada ao usuário definido em ``spring.datasource.username``.

### Definir o fuso horário padrão para conexões JDBC
```
spring.jpa.properties.hibernate.jdbc.time_zone=UTC
```
Garante que os dados temporais (como ``Timestamp`` e ``Date``) sejam armazenados e recuperados corretamente, independentemente do fuso horário do sistema operacional ou da aplicação

**Por que usar?**
- Evita inconsistências ao lidar com dados temporais em sistemas distribuídos ou aplicativos com usuários em diferentes fusos horários
- O UTC é frequentemente usado como referência universal.

### Gerar o esquema do banco de dados (DDL) com base nas entidades mapeadas
```
spring.jpa.generate-ddl=true
```
**Por que usar?:**
- Útil em ambientes de desenvolvimento para criar automaticamente tabelas, chaves estrangeiras, e outras estruturas do banco com base nas anotações JPA.

**Limitação:**
- Não é recomendado para produção, pois pode sobrescrever tabelas existentes inadvertidamente.

### Controlar como o Hibernate gerencia o esquema do banco de dados ao iniciar a aplicação
```
spring.jpa.hibernate.ddl-auto=create
```
**Os valores possíveis incluem:**
- ``none:`` Não faz nenhuma ação no banco de dados.
- ``validate:`` Valida se o esquema no banco de dados está de acordo com as entidades mapeadas. Não faz alterações.
- ``update:`` Altera o esquema existente no banco para refletir alterações nas entidades (sem excluir dados).
- ``create:`` Apaga e recria todas as tabelas toda vez que a aplicação é iniciada.
- ``create-drop:`` Igual ao ``create``, mas exclui o esquema ao encerrar a aplicação.


**Por que usar?:**
- O valor ``create`` é útil em desenvolvimento ou testes para garantir que o banco sempre reflita as entidades.

**Atenção:**
- Não deve ser usado em produção, pois apagará todos os dados existentes no banco a cada inicialização.

### Habilitar o log das consultas SQL executadas pelo Hibernate no console da aplicação
```
spring.jpa.show-sql=true
```
**Por que usar?:**
- Facilita o desenvolvimento e depuração, permitindo verificar as consultas geradas automaticamente.

**Dica:**
- Combine com ``spring.jpa.properties.hibernate.format_sql=true`` para formatar as consultas no log.

### Especifica o dialeto a ser usado pelo Hibernate para interagir com o banco de dados.
```
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```
**Por que usar?:**
- Cada banco de dados tem sintaxes e recursos próprios (como funções ou tipos de dados específicos). O dialeto é a classe que informa ao Hibernate como gerar consultas SQL otimizadas para o banco configurado.
- O MySQLDialect ajusta as consultas para o MySQL.

[**Voltar ao README**](../README.md#documentação-de-conceitos)