[**<< Voltar ao README**](../README.md#documentação-de-conceitos)

# Arquivo ``application.properties``

**Este documento descreve os conceitos das propriedades utilizadas nas configurações no arquivo ``application.properties``.**

## Spring Data JPA e Hibernate

### Definir configurações do banco de dados
```properties
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
```properties
spring.jpa.properties.hibernate.jdbc.time_zone=UTC
```
Garante que os dados temporais (como ``Timestamp`` e ``Date``) sejam armazenados e recuperados corretamente, independentemente do fuso horário do sistema operacional ou da aplicação

**Por que usar?**
- Evita inconsistências ao lidar com dados temporais em sistemas distribuídos ou aplicativos com usuários em diferentes fusos horários
- O UTC é frequentemente usado como referência universal.

### Gerar o esquema do banco de dados (DDL) com base nas entidades mapeadas
```properties
spring.jpa.generate-ddl=true
```
**Por que usar?:**
- Útil em ambientes de desenvolvimento para criar automaticamente tabelas, chaves estrangeiras, e outras estruturas do banco com base nas anotações JPA.

**Limitação:**
- Não é recomendado para produção, pois pode sobrescrever tabelas existentes inadvertidamente.

### Controlar como o Hibernate gerencia o esquema do banco de dados ao iniciar a aplicação
```properties
spring.jpa.hibernate.ddl-auto=create
```
**Os valores possíveis incluem:**
- ``none:`` Não faz nenhuma ação no banco de dados.
- ``validate:`` Valida se o esquema no banco de dados está de acordo com as entidades mapeadas. Não faz alterações.
- ``update:`` Altera o esquema existente no banco para refletir alterações nas entidades (sem excluir dados).
- ``create:`` Apaga e recria todas as tabelas toda vez que a aplicação é iniciada.
- ``create-drop:`` Igual ao ``create``, mas exclui o esquema ao encerrar a aplicação.


**Por que usar?**
- O valor ``create`` é útil em desenvolvimento ou testes para garantir que o banco sempre reflita as entidades.

**Atenção:**
- Não deve ser usado em produção, pois apagará todos os dados existentes no banco a cada inicialização.

### Gerar arquivo DDL com base no mapeamento ORM

```properties
spring.jpa.properties.javax.persistence.schema-generation.scripts.action=create
spring.jpa.properties.javax.persistence.schema-generation.scripts.create-target=src/main/resources/ddl.sql
```
**Por que usar?**
- Útil para obter o scrip SQL completo de criação das tabelas do banco de dados. O arquivo será salvo no diretório especificado na segunda linha de configuração.

**Atenção:**
- Este recurso deve ser utilizado temporariamente. Adicionando as configurações, executando o projeto e as removendo novamente.
- É importante revisar o arquivo gerado antes de utiliza-lo.

**Dica:**
- Combinar com ``spring.jpa.properties.hibernate.format_sql=true`` para gerar o sql já formatado.
- Usar o a valor ``update`` na propriedade ``action``, faz com que a geração do arquivo seja incremental. Iá gerar apenas as novas entidades.



### Habilitar o log das consultas SQL executadas pelo Hibernate no console da aplicação
```properties
spring.jpa.show-sql=true
```
**Por que usar?:**
- Facilita o desenvolvimento e depuração, permitindo verificar as consultas geradas automaticamente.

**Dica:**
- Combine com ``spring.jpa.properties.hibernate.format_sql=true`` para formatar as consultas no log.

### Especifica o dialeto a ser usado pelo Hibernate para interagir com o banco de dados.
```properties
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```
**Por que usar?:**
- Cada banco de dados tem sintaxes e recursos próprios (como funções ou tipos de dados específicos). O dialeto é a classe que informa ao Hibernate como gerar consultas SQL otimizadas para o banco configurado.
- O MySQLDialect ajusta as consultas para o MySQL.

### Definir localizações dos arquivos das migrações do flyway.

```properties
 spring.flyway.locations=classpath:db/migration,classpath:db/testData
```
Mais detalhes [**Aqui**](flyway.md#localização-dos-arquivos-de-migrations).

### Dinifir configurações do Pool de conexões padrão do Spring: HikariCP

[**HikariCP**](https://github.com/brettwooldridge/HikariCP) é um pool de conexões ao banco de dados utilizado por padrão no Spring Boot.

```properties
spring.datasource.hikari.maximum-poll-size=5
spring.datasource.hikari.minimum-idle=3
spring.datasource.hikari.idle-timeout=10000
```
- ``spring.datasource.hikari.maximum-pool-size``: 
  - Define o número máximo de conexões que o pool pode conter. 
  - Quando esse limite é atingido, solicitações adicionais de conexão serão colocadas em espera até que uma conexão seja liberada ou ocorra um timeout.
  - Útil para limitar o número de conexões simultâneas ao banco de dados, garantindo que ele não fique sobrecarregado.
- ``spring.datasource.hikari.minimum-idle``: 
  - Define o número mínimo de conexões ociosas (não utilizadas) que o pool deve manter. Se não for configurado, assumirá a mesma quantidade do máximo.
  - Se o número de conexões ociosas cair abaixo desse valor, o pool criará novas conexões até atingir o mínimo configurado.
  - Evita que o pool precise criar novas conexões com frequência, reduzindo a latência em situações de alta demanda.
- ``spring.datasource.hikari.idle-timeout=10000``:
  - Especifica o tempo (em milissegundos) que uma conexão ociosa pode permanecer no pool antes de ser fechada.
  - Se o número de conexões ociosas no pool for maior que o configurado, as conexões adicionais serão fechadas após o período configurado.
  - Ajuda a liberar conexões que não estão sendo usadas para reduzir o consumo de recursos.

Essas configurações equilibram performance, uso eficiente de recursos e tempo de resposta para uma aplicação que utiliza conexões com o banco de dados.


## Controle de Logs

- `spring.mvc.log-resolved-exception`:
  - Habilita o log de exceções resolvidas pelo Spring MVC, como erros de validação ou exceções lançadas por controladores.
  - Útil para depuração, pois permite ver detalhes das exceções que ocorrem durante o processamento de requisições HTTP.
  - Valores possíveis: `true` ou `false` (padrão é `false`).
- `logging.level.org.springframework.web=DEBUG`:
  - Define o nível de log para a categoria `org.springframework.web`, que inclui componentes relacionados ao processamento de requisições web.
  - Configurar para `DEBUG` permite ver mensagens detalhadas sobre o fluxo de requisições, incluindo informações sobre controladores, interceptadores e resolvers.
  - Útil para identificar problemas específicos relacionados ao processamento de requisições HTTP.
  - Valores possíveis: `TRACE`, `DEBUG`, `INFO`, `WARN`, `ERROR`, `FATAL`, `OFF` (sendo `DEBUG` um nível detalhado, mas menos verboso que `TRACE`).
- `logging.level.org.hibernate.SQL=DEBUG`:
  - Define o nível de log para a categoria `org.hibernate.SQL`, que é responsável por registrar as consultas SQL geradas pelo Hibernate.
  - Configurar para `DEBUG` permite ver as consultas SQL completas que estão sendo executadas, o que é útil para depuração e otimização de desempenho.
  - Valores possíveis: `TRACE`, `DEBUG`, `INFO`, `WARN`, `ERROR`, `FATAL`, `OFF` (sendo `DEBUG` um nível detalhado, mas menos verboso que `TRACE`).
- `logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE`:
  - Define o nível de log para a categoria `org.hibernate.type.descriptor.sql.BasicBinder`, que é responsável por registrar os valores dos parâmetros vinculados nas consultas SQL.
  - Configurar para `TRACE` permite ver os valores exatos que estão sendo passados para as consultas SQL, o que é extremamente útil para depuração, especialmente quando se suspeita de problemas relacionados a parâmetros ou tipos de dados.
  - Valores possíveis: `TRACE`, `DEBUG`, `INFO`, `WARN`, `ERROR`, `FATAL`, `OFF` (sendo `TRACE` o nível mais detalhado, mostrando informações completas sobre os parâmetros).
- `logging.level.org.hibernate.type.descriptor.sql.BasicExtractor=TRACE`:
  - Define o nível de log para a categoria `org.hibernate.type.descriptor.sql.BasicExtractor`, que é responsável por registrar os valores extraídos das consultas SQL.
  - Configurar para `TRACE` permite ver os valores exatos que estão sendo retornados pelas consultas SQL, o que é útil para depuração, especialmente quando se suspeita de problemas relacionados a resultados ou tipos de dados.
  - Valores possíveis: `TRACE`, `DEBUG`, `INFO`, `WARN`, `ERROR`, `FATAL`, `OFF` (sendo `TRACE` o nível mais detalhado, mostrando informações completas sobre os resultados).
- `logging.level.org.hibernate.type=TRACE`:
  - Define o nível de log para a categoria `org.hibernate.type`, que é responsável por registrar informações sobre os tipos de dados usados nas consultas SQL.
  - Configurar para `TRACE` permite ver detalhes sobre os tipos de dados envolvidos nas consultas, o que pode ser útil para depuração, especialmente quando se suspeita de problemas relacionados a tipos ou conversões.
  - Valores possíveis: `TRACE`, `DEBUG`, `INFO`, `WARN`, `ERROR`, `FATAL`, `OFF` (sendo `TRACE` o nível mais detalhado, mostrando informações completas sobre os tipos de dados).
- `logging.level.root`: 
  - Define o nível de log para a categoria raiz (root), que é a categoria base para todos os logs na aplicação.
  - Configurar para `INFO` significa que mensagens de log com nível `INFO` ou superior (como `WARN`, `ERROR`, `FATAL`) serão registradas, enquanto mensagens de nível `DEBUG` ou `TRACE` serão ignoradas.
  - Útil para controlar o volume geral de logs na aplicação, garantindo que apenas mensagens relevantes sejam registradas em ambientes de produção.
  - Valores possíveis: `TRACE`, `DEBUG`, `INFO`, `WARN`, `ERROR`, `FATAL`, `OFF` (sendo `INFO` um nível equilibrado para produção, mostrando informações importantes sem ser excessivamente detalhado).

## Others
- `spring.jackson.deserialization.fail-on-unknown-properties=true`:
  - Configura o comportamento do Jackson durante a desserialização de JSON para objetos Java.
  - Quando definido como `true`, o Jackson lançará uma exceção (`UnrecognizedPropertyException`) se o JSON contiver propriedades que não correspondam a campos na classe Java de destino.
  - Útil para garantir que o JSON recebido esteja estritamente de acordo com a estrutura esperada, evitando que dados inesperados sejam ignorados silenciosamente. Uso principalmente recomendado em APIs para garantir a integridade dos dados recebidos.
- `spring.jackson.deserialization.fail-on-ignored-properties=true`:
  - Configura o comportamento do Jackson durante a desserialização de JSON para objetos Java, especificamente em relação a propriedades que foram explicitamente ignoradas usando anotações como `@JsonIgnore`.

  
[**<< Voltar ao README**](../README.md#documentação-de-conceitos)