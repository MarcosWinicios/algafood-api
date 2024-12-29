[**Voltar ao README**](../README.md#documentação-de-conceitos)

# Anotações

**Este documento descreve os conceitos das anotações estudadas e utializadas ao decorrer deste projeto.**

## Mapeamento objeto relacional - ORM
São anotações do **JPA** e do **Hibernate** utilizadas para controlar o mapeamento entre objetos Java e tabelas do banco de dados.

``@Entity``: Marca uma classe Java como uma entidade que será mapeada para uma tabela no banco de dados

``@Table``: Especifica detalhes sobre a tabela do banco de dados correspondente à entidade.
- ``name``: Nome da tabela.
- ``schema``: Esquema onde a tabela reside.

``@Id``: Define o atributo como a chave primária da entidade. Uso obrigatório em cada entidfade.

``@GeneratedValue``: Especifica como o valor da chave primária será gerado automaticamente.
- ``strategy``: Estratégia de geração
    - ``AUTO``: Segue a estratégia já definida no banco de dados ou no provedor de persitencia(hibernate)
    - ``IDENTITY``: O banco de dados é responsável por gerar a PK, geralmente em colunas auto-incrementadas. Ideal para bancos que têm suporte nativo para auto-incremento.
    - ``SEQUENCE``: Usa sequências de banco de dados para gerar valores exclusivos para a chave primária. Ideal para bancos que suportam sequências e requer a definição de uma sequência no banco.
    - ``TABLE``: Usa uma tabela específica no banco para gerar valores exclusivos para a chave primária.

``@Column``: Mapeia um atributo da classe a uma coluna da tabela no banco de dados, permitindo configurar detalhes como nome, tamanho, e se é nula.
- ``name:`` Nome da coluna.
- ``nullable:`` Define se a coluna pode conter valores nulos.
- ``length:`` Define o tamanho máximo da coluna.

``@JoinColumn``: Especifica a coluna que será usada para o relacionamento entre entidades.
- ``name``: Nome da coluna da junção
- ``referenceColumnName``: Nome da coluna na tabela referenciada

``@Transient``: Indicar que um atributo de uma classe não deve ser mapeado para uma coluna no banco de dados. Será ignorado pelo provedor de persitência (Hibernate).

``@ManyToOne``: Indica que muitos registros desta entidade estão relacionados a um único registro de outra entidade. Relacionamento muitos-para-um.

``@OneToMany``: Indica que um registro desta entidade está relacionado a muitos registros de outra entidade. Relacionamento um-para-muitos.
- ``mappedBy``: Indica o atributo na outra entidade que mapeia esse relacionamento.

## Jackson
Jackson é um framework e controla como os objetos Java são serializados em JSON e vice-versa.

``@JsonIgnore``: Ignora a serialização de um atributo específico.
``@JsonIgnoreProperties``:  Ignora múltiplos atributos ou propriedades durante a serialização.



[**Voltar ao README**](../README.md#documentação-de-conceitos)