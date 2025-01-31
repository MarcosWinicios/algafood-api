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

## Principais Comandos
- `migrate`: Executa as migrations pendentes.
- `info`: Exibe o status das migrations.
- `validate`: Valida se as migrations aplicadas estão corretas.
- `baseline`: Marca um estado inicial para o banco.
- `repair`: Corrige metadados do histórico de migrations.

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