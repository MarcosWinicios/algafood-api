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

## Estratégia Recomendada
- Criar uma nova migration para cada alteração no banco.
- Nunca modificar uma migration já aplicada.
- Aplicar migrations em ambientes de teste antes de produção.

[**<< Voltar ao README**](../README.md#documentação-de-conceitos)