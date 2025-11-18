[**<< Voltar ao README**](../README.md#documentação-de-conceitos)

# Anotações

**Este documento descreve os conceitos das anotações estudadas e utializadas ao decorrer deste projeto.**

## Sptring Data JPA / JPA / Hibernate
### Hibernate
``@CreationTimestamp``: Marca um campo para ser preenchido automaticamente com a data/hora no momento em que a entidade é **persistida pela primeira vez** (inserção no banco).
    
``@UpdateTimestamp``:Marca um campo para ser preenchido automaticamente com a data/hora sempre que a entidade é **atualizada**.


### Mapeamento objeto relacional - ORM
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
- ``nullable:`` Define se a coluna pode conter valores nulos. Se não for definido assumo o valor padrão "true".
- ``length:`` Define o tamanho máximo da coluna.

``@JoinColumn``: Especifica a coluna que será usada para o relacionamento entre entidades.
- ``name``: Define o nome da coluna da junção
- ``referenceColumnName``: Define o nome da coluna na tabela referenciada
- ``foireignKey``: Define o nome de uma KF. Recebe o valor da anotação `@FoireignKey`
- ``nullable:`` Define se a coluna pode conter valores nulos. Se não for definido assumo o valor padrão "true".
  - ``false``:
    - Faz o mapeamento da coluna **COM** a constraint ``not null`` no DDL.
    - O Hibernate usa ``inner join`` em consultas pois entende que sempre haverá um valor sendo a FK de outra tabela.
  - ``true``:
    - Faz o mapeamento da coluna **SEM** a constraint ``not null`` no DDL.
    - O Hibernate usa ``left outer join`` em consultas para previnir casos em que a coluna esteja nula, pois sabe que pode acontecer.
- ``length:`` Define o tamanho máximo da coluna.

``@ForeignKey``: Definir o nome de uma FK a partir da propriedade `name`

``@Transient``: Indicar que um atributo de uma classe não deve ser mapeado para uma coluna no banco de dados. Será ignorado pelo provedor de persitência (Hibernate).

``@ManyToOne``: Indica que muitos registros desta entidade estão relacionados a um único registro de outra entidade. Relacionamento muitos-para-um. Usa o carregamento ``EAGER LOADING`` por padrão.

``@OneToMany``: Indica que um registro desta entidade está relacionado a muitos registros de outra entidade. Relacionamento um-para-muitos.
- ``mappedBy``: Indica o atributo na outra entidade que mapeia esse relacionamento.

``@ManyToMany``: Indica que muitos registros desta entidade estão realcionado a muitos registros da outra entidade. Relacionamento muitos-para-muitos.
Também cria automaticamente uma tabela de relacionamento que pode ser costomizada a partir da anotação ``@JoinTable``

``@JoinTable``: Permite fazer customizações especificas de uma tabela de relacionamento. É usada em conjunto com a anotação ``@ManyToMany``
- ``name``: Define o nome da tabela
- ``joinColumns``: Define o nome da coluna que será a FK da entidade atual na tabela de relacionamento. Necessário receber da anotação ``@JoinColumn``.
- ``inverseJoinColumns``: Define o nome da coluna que será a FK da tabela relacionada dentro da tabela de relacionamento.  Necessário receber da anotação ``@JoinColumn``.


``@Embedded``: Usada para mapear uma classe que não tem identidade própria (sem chave primária) e que será incorporada como parte da tabela da entidade "pai". 
Essa classe é decorada com a anotação ``@Embeddable``.

``@Embeddable``: Usada para marcar uma classe como incorporável em uma entidade. É usada em conjunto com a anotação ``@Embedded``.

``@Enumerated``: Usada no JPA para mapear um atributo do tipo enum em uma entidade para uma coluna no banco de dados.
- ``EnumType.ORDINAL``:  (Padrão) -> Armazena a posição do enum como um número (0, 1, 2...).
- ``EnumType.STRING``: Armazena o nome do enum como texto no banco.

### Anotações de ciclo de vida do JPA
São anotações usadas para adicionar lógica personalizada durante eventos do ciclo de vida de uma entidade.
Basta adicionar um método na classe que representa a entidade a ser manipulada e anotá-lo com um das seguintes anotações

``@PrePersist:`` Antes de salvar.

``@PostPersist:`` Após salvar.

``@PreUpdate:`` Antes de atualizar.

``@PostUpdate:`` Após atualizar.

``@PreRemove:`` Antes de excluir.

``@PostRemove:`` Após excluir.
## Jackson
Jackson é um framework e controla como os objetos Java são serializados em JSON e vice-versa.

``@JsonIgnore``: Ignora a serialização de um atributo específico. Ao utilizar em um método **getter**, o atributo será ignorado apenas na consulta.
``@JsonIgnoreProperties``:  Ignora múltiplos atributos ou propriedades durante a serialização.

## Spring Framework

``@Transactional``:  Configura o comportamento transacional, como:
- Abrir e fechar transações.
- Realizar commit ou rollback.
- Definir escopo de transações (exemplo: leitura apenas com ``readOnly = true``).
- Definir quais exceções devem causar rollback usando a propriedade ``rollbackFor = CustomException.class``.

É frequentemente usada em métodos que realizam alterações no banco de dados.

``@RestController``: Define o comportamento da classe como um controlador REST

``RequestMapping``: Mapeia rotas e métodos HTTP para uma classe ou método específico

``@GetMapping``, ``@PostMapping``, ``@PutMapping``, ``@PatchMapping``, ``@DeleteMapping``: São usadas no Spring Framework para mapear requisições HTTP a métodos específicos de um controlador.
Cada uma representa um tipo de operação HTTP (**GET**, **POST**, **PUT**, **PACH** e **DELETE**).

``@PathVariable``: Liga variáveis de caminho da URL de um endpoint a uma parâmetro do método. Usada para para capturar
partes dinâmicas de URL. É usada em conjunto com as anotações de mapeamento de requisições HTTP.

``@RequestParam``: Liga parâmetros de consulta (query parameters) ou dados de formulário a um parâmetro do método. Usado para capturar valores enviados em URLs (ex.: ?key=value).
É útil para valores simples ou filtros enviados na URL.

``@RequestBody``: Liga o corpo da requisição HTTP ao parâmetro de um método. É usada para deserializar o corpo da requisição (JSON, XML, etc.) em um objeto Java.

``@Service``: É usada no Spring Framework para marcar uma classe como um componente de serviço. Ela indica que a classe contém a lógica de negócios da aplicação.

``@ResponseStatus``: Define o código de status HTTP que deve ser retornado quando o método ou a exceção anotada for executado.  
É usada para:

- Especificar diretamente o status HTTP de sucesso ou erro (ex.: `HttpStatus.NO_CONTENT`).
- Associar automaticamente um status a uma exceção customizada, evitando o uso explícito de `try/catch` nos controladores.
- Tornar os retornos da API mais expressivos e alinhados ao comportamento esperado.

Pode ser aplicada em:
- Métodos de controladores.
- Classes de exceção personalizadas para mapear erros a um status HTTP específico.


[**<< Voltar ao README**](../README.md#documentação-de-conceitos)
