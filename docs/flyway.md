[**<< Voltar ao README**](../README.md#documentação-de-conceitos)

# Flyway - Controle de Versionamento de Banco de Dados

**Este documento descreve conceitos referentes a utilização do [Flyway](https://www.red-gate.com/products/flyway/)**

## O que é o Flyway?
Flyway é uma ferramenta de versionamento de banco de dados que gerencia e automatiza migrações, garantindo que o esquema esteja sempre atualizado e consistente entre ambientes.

## O que são migrations?
Migrations são scripts versionados que aplicam mudanças no banco de dados, como criação/alteração de tabelas, inserção de dados e ajustes em índices. 
Cada migration é executada sequencialmente conforme sua numeração.

## Convenção de Nomenclatura
Os arquivos de migrations seguem um padrão:
- `V{versão}__{descrição}.sql` (Ex.: `V1__create_users_table.sql`)
- `{versão}` define a ordem de execução.
- `{descrição}` indica a mudança aplicada.

## Localização dos arquivos de migrations

Por padrão, o Flyway busca migrations em ``resources/db/migration``. Para adicionar outras localizações, configure o application.properties:

```properties
 spring.flyway.locations=classpath:db/migration,classpath:db/testData
```
Isso permite organizar arquivos de migrations e dados de teste em diretórios separados, como no exemplo acima, facilitando a manutenção do projeto.

Um exemplo de uso seria adicionar novo diretório de teste com um arquivo com carga de dados de teste utilizando o arquivo de callback ``afterMigrate.sql``.

## Principais Comandos
- `migrate`: Executa as migrations pendentes.
- `info`: Exibe o status das migrations.
- `validate`: Valida se as migrations aplicadas estão corretas.
- `baseline`: Marca um estado inicial para o banco.
- `repair`: Corrige metadados do histórico de migrations.

### Configuração Necessária para Usar os Comandos
Para que o Flyway possa se conectar ao banco de dados e executar comandos como `migrate`, `repair` e `info`, é necessário configurar um arquivo `flyway.properties` com as credenciais de acesso.

### Exemplo de `flyway.properties`
```properties
flyway.url=jdbc:mysql://localhost:3306/meubanco
flyway.user=root
flyway.password=senha
flyway.schemas=public
```
### Exemplo de uso de comandos

```
mvn flyway:repair -Dflyway.configFiles=src/main/resources/flyway.properties
```

## Histórico de Migrations
Flyway mantém um histórico das migrations aplicadas na tabela `flyway_schema_history`, garantindo rastreabilidade.

## Arquivos de Callback
Flyway permite executar ações automáticas antes ou depois de eventos específicos no ciclo de migração, usando arquivos de callback.

### Nomeação dos Callbacks
Os arquivos de callback seguem o padrão:
- `{evento}__{descrição}.sql` (Ex.: `beforeMigrate__log_start.sql`)

### Eventos Suportados
- `beforeMigrate` / `afterMigrate` – Antes/depois de executar migrations.
- `beforeEachMigrate` / `afterEachMigrate` – Antes/depois de cada migration.
- `beforeBaseline` / `afterBaseline` – Antes/depois de definir um baseline.
- `beforeRepair` / `afterRepair` – Antes/depois de corrigir metadados.
- `beforeClean` / `afterClean` – Antes/depois de limpar o esquema.

### Uso Comum
Os callbacks podem ser usados para:
- Criar logs antes/depois das execuções.
- Ajustar permissões temporárias.
- Notificar outros sistemas sobre mudanças no banco.
- Fazer inserts de dados de teste em ambiente de desenvolvimento

## Estratégia Recomendada
- Criar uma nova migration para cada alteração no banco.
- Nunca modificar uma migration já aplicada.
- Aplicar migrations em ambientes de teste antes de produção.


## Links Úteis

[**Remover arquivos de carga de dados de teste do artefato empacotado**](https://maven.apache.org/plugins/maven-resources-plugin/examples/include-exclude.html#:~:text=Thus%2C%20we%20may%20have%20to,add%20an%20element.&text=And%20to%20exclude%20a%20resource,add%20an%20element)



[**<< Voltar ao README**](../README.md#documentação-de-conceitos)