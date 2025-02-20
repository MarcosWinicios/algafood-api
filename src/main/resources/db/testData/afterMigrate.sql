SET FOREIGN_KEY_CHECKS = 0;

DELETE FROM tb_city;
DELETE FROM tb_group;
DELETE FROM tb_group_permission;
DELETE FROM tb_kitchen;
DELETE FROM tb_payment_method;
DELETE FROM tb_permission;
DELETE FROM tb_product;
DELETE FROM tb_restaurant;
DELETE FROM tb_restaurant_payment_method;
DELETE FROM tb_state;
DELETE FROM tb_user;
DELETE FROM tb_user_group;
DELETE FROM tb_order_item;
DELETE FROM tb_order;

SET FOREIGN_KEY_CHECKS = 1;

ALTER TABLE tb_city AUTO_INCREMENT = 1;
ALTER TABLE tb_group AUTO_INCREMENT = 1;
ALTER TABLE tb_kitchen AUTO_INCREMENT = 1;
ALTER TABLE tb_payment_method AUTO_INCREMENT = 1;
ALTER TABLE tb_permission AUTO_INCREMENT = 1;
ALTER TABLE tb_product AUTO_INCREMENT = 1;
ALTER TABLE tb_restaurant AUTO_INCREMENT = 1;
ALTER TABLE tb_state AUTO_INCREMENT = 1;
ALTER TABLE tb_user AUTO_INCREMENT = 1;
ALTER TABLE tb_order_item AUTO_INCREMENT = 1;
ALTER TABLE tb_order AUTO_INCREMENT = 1;


INSERT INTO tb_state(id, name) VALUES (1, "Acre");
INSERT INTO tb_state(id, name) VALUES (2, "Alagoas");
INSERT INTO tb_state(id, name) VALUES (3, "Amapá");
INSERT INTO tb_state(id, name) VALUES (4, "Amazonas");
INSERT INTO tb_state(id, name) VALUES (5, "Bahia");
INSERT INTO tb_state(id, name) VALUES (6, "Ceará");
INSERT INTO tb_state(id, name) VALUES (7, "Distrito Federal");
INSERT INTO tb_state(id, name) VALUES (8, "Espírito Santo");
INSERT INTO tb_state(id, name) VALUES (9, "Goiás");
INSERT INTO tb_state(id, name) VALUES (10, "Maranhão");
INSERT INTO tb_state(id, name) VALUES (11, "Mato Grosso");
INSERT INTO tb_state(id, name) VALUES (12, "Mato Grosso do Sul");
INSERT INTO tb_state(id, name) VALUES (13, "Minas Gerais");
INSERT INTO tb_state(id, name) VALUES (14, "Pará");
INSERT INTO tb_state(id, name) VALUES (15, "Paraíba");
INSERT INTO tb_state(id, name) VALUES (16, "Paraná");
INSERT INTO tb_state(id, name) VALUES (17, "Pernambuco");
INSERT INTO tb_state(id, name) VALUES (18, "Piauí");
INSERT INTO tb_state(id, name) VALUES (19, "Rio de Janeiro");
INSERT INTO tb_state(id, name) VALUES (20, "Rio Grande do Norte");
INSERT INTO tb_state(id, name) VALUES (21, "Rio Grande do Sul");
INSERT INTO tb_state(id, name) VALUES (22, "Rondônia");
INSERT INTO tb_state(id, name) VALUES (23, "Roraima");
INSERT INTO tb_state(id, name) VALUES (24, "Santa Catarina");
INSERT INTO tb_state(id, name) VALUES (25, "São Paulo");
INSERT INTO tb_state(id, name) VALUES (26, "Sergipe");
INSERT INTO tb_state(id, name) VALUES (27, "Tocantins");


-- Acre
INSERT INTO tb_city(id, name, state_id) VALUES (1, "Rio Branco", 1);
INSERT INTO tb_city(id, name, state_id) VALUES (2, "Cruzeiro do Sul", 1);
INSERT INTO tb_city(id, name, state_id) VALUES (3, "Sena Madureira", 1);
INSERT INTO tb_city(id, name, state_id) VALUES (4, "Tarauacá", 1);
INSERT INTO tb_city(id, name, state_id) VALUES (5, "Feijó", 1);
INSERT INTO tb_city(id, name, state_id) VALUES (6, "Brasiléia", 1);

-- Alagoas
INSERT INTO tb_city(id, name, state_id) VALUES (7, "Maceió", 2);
INSERT INTO tb_city(id, name, state_id) VALUES (8, "Arapiraca", 2);
INSERT INTO tb_city(id, name, state_id) VALUES (9, "Palmeira dos Índios", 2);
INSERT INTO tb_city(id, name, state_id) VALUES (10, "Penedo", 2);
INSERT INTO tb_city(id, name, state_id) VALUES (11, "Coruripe", 2);
INSERT INTO tb_city(id, name, state_id) VALUES (12, "Santana do Ipanema", 2);

-- Amapá
INSERT INTO tb_city(id, name, state_id) VALUES (13, "Macapá", 3);
INSERT INTO tb_city(id, name, state_id) VALUES (14, "Santana", 3);
INSERT INTO tb_city(id, name, state_id) VALUES (15, "Oiapoque", 3);
INSERT INTO tb_city(id, name, state_id) VALUES (16, "Laranjal do Jari", 3);
INSERT INTO tb_city(id, name, state_id) VALUES (17, "Mazagão", 3);
INSERT INTO tb_city(id, name, state_id) VALUES (18, "Porto Grande", 3);

-- Amazonas
INSERT INTO tb_city(id, name, state_id) VALUES (19, "Manaus", 4);
INSERT INTO tb_city(id, name, state_id) VALUES (20, "Parintins", 4);
INSERT INTO tb_city(id, name, state_id) VALUES (21, "Itacoatiara", 4);
INSERT INTO tb_city(id, name, state_id) VALUES (22, "Manacapuru", 4);
INSERT INTO tb_city(id, name, state_id) VALUES (23, "Coari", 4);
INSERT INTO tb_city(id, name, state_id) VALUES (24, "Tabatinga", 4);

-- Bahia
INSERT INTO tb_city(id, name, state_id) VALUES (25, "Salvador", 5);
INSERT INTO tb_city(id, name, state_id) VALUES (26, "Feira de Santana", 5);
INSERT INTO tb_city(id, name, state_id) VALUES (27, "Vitória da Conquista", 5);
INSERT INTO tb_city(id, name, state_id) VALUES (28, "Ilhéus", 5);
INSERT INTO tb_city(id, name, state_id) VALUES (29, "Juazeiro", 5);
INSERT INTO tb_city(id, name, state_id) VALUES (30, "Barreiras", 5);

-- Ceará
INSERT INTO tb_city(id, name, state_id) VALUES (31, "Fortaleza", 6);
INSERT INTO tb_city(id, name, state_id) VALUES (32, "Juazeiro do Norte", 6);
INSERT INTO tb_city(id, name, state_id) VALUES (33, "Sobral", 6);
INSERT INTO tb_city(id, name, state_id) VALUES (34, "Maracanaú", 6);
INSERT INTO tb_city(id, name, state_id) VALUES (35, "Crato", 6);
INSERT INTO tb_city(id, name, state_id) VALUES (36, "Caucaia", 6);

-- Distrito Federal
INSERT INTO tb_city(id, name, state_id) VALUES (37, "Brasília", 7);

-- Espírito Santo
INSERT INTO tb_city(id, name, state_id) VALUES (38, "Vitória", 8);
INSERT INTO tb_city(id, name, state_id) VALUES (39, "Vila Velha", 8);
INSERT INTO tb_city(id, name, state_id) VALUES (40, "Serra", 8);
INSERT INTO tb_city(id, name, state_id) VALUES (41, "Cariacica", 8);
INSERT INTO tb_city(id, name, state_id) VALUES (42, "Guarapari", 8);
INSERT INTO tb_city(id, name, state_id) VALUES (43, "Linhares", 8);

-- Goiás
INSERT INTO tb_city(id, name, state_id) VALUES (44, "Goiânia", 9);
INSERT INTO tb_city(id, name, state_id) VALUES (45, "Anápolis", 9);
INSERT INTO tb_city(id, name, state_id) VALUES (46, "Aparecida de Goiânia", 9);
INSERT INTO tb_city(id, name, state_id) VALUES (47, "Rio Verde", 9);
INSERT INTO tb_city(id, name, state_id) VALUES (48, "Luziânia", 9);
INSERT INTO tb_city(id, name, state_id) VALUES (49, "Catalão", 9);

-- Maranhão
INSERT INTO tb_city(id, name, state_id) VALUES (50, "São Luís", 10);
INSERT INTO tb_city(id, name, state_id) VALUES (51, "Imperatriz", 10);
INSERT INTO tb_city(id, name, state_id) VALUES (52, "Caxias", 10);
INSERT INTO tb_city(id, name, state_id) VALUES (53, "Timon", 10);
INSERT INTO tb_city(id, name, state_id) VALUES (54, "Bacabal", 10);
INSERT INTO tb_city(id, name, state_id) VALUES (55, "Codó", 10);

-- Mato Grosso
INSERT INTO tb_city(id, name, state_id) VALUES (56, "Cuiabá", 11);
INSERT INTO tb_city(id, name, state_id) VALUES (57, "Várzea Grande", 11);
INSERT INTO tb_city(id, name, state_id) VALUES (58, "Rondonópolis", 11);
INSERT INTO tb_city(id, name, state_id) VALUES (59, "Sinop", 11);
INSERT INTO tb_city(id, name, state_id) VALUES (60, "Tangará da Serra", 11);
INSERT INTO tb_city(id, name, state_id) VALUES (61, "Barra do Garças", 11);

-- Mato Grosso do Sul
INSERT INTO tb_city(id, name, state_id) VALUES (62, "Campo Grande", 12);
INSERT INTO tb_city(id, name, state_id) VALUES (63, "Dourados", 12);
INSERT INTO tb_city(id, name, state_id) VALUES (64, "Três Lagoas", 12);
INSERT INTO tb_city(id, name, state_id) VALUES (65, "Corumbá", 12);
INSERT INTO tb_city(id, name, state_id) VALUES (66, "Ponta Porã", 12);
INSERT INTO tb_city(id, name, state_id) VALUES (67, "Naviraí", 12);

-- Minas Gerais
INSERT INTO tb_city(id, name, state_id) VALUES (68, "Belo Horizonte", 13);
INSERT INTO tb_city(id, name, state_id) VALUES (69, "Uberlândia", 13);
INSERT INTO tb_city(id, name, state_id) VALUES (70, "Contagem", 13);
INSERT INTO tb_city(id, name, state_id) VALUES (71, "Juiz de Fora", 13);
INSERT INTO tb_city(id, name, state_id) VALUES (72, "Betim", 13);
INSERT INTO tb_city(id, name, state_id) VALUES (73, "Montes Claros", 13);

-- Pará
INSERT INTO tb_city(id, name, state_id) VALUES (74, "Belém", 14);
INSERT INTO tb_city(id, name, state_id) VALUES (75, "Santarém", 14);
INSERT INTO tb_city(id, name, state_id) VALUES (76, "Marabá", 14);
INSERT INTO tb_city(id, name, state_id) VALUES (77, "Ananindeua", 14);
INSERT INTO tb_city(id, name, state_id) VALUES (78, "Castanhal", 14);
INSERT INTO tb_city(id, name, state_id) VALUES (79, "Altamira", 14);

-- Paraíba
INSERT INTO tb_city(id, name, state_id) VALUES (80, "João Pessoa", 15);
INSERT INTO tb_city(id, name, state_id) VALUES (81, "Campina Grande", 15);
INSERT INTO tb_city(id, name, state_id) VALUES (82, "Patos", 15);
INSERT INTO tb_city(id, name, state_id) VALUES (83, "Santa Rita", 15);
INSERT INTO tb_city(id, name, state_id) VALUES (84, "Bayeux", 15);


INSERT INTO tb_kitchen (id, name) VALUES (1, 'Tailandesa');
INSERT INTO tb_kitchen (id, name) VALUES (2, 'Indiana');
INSERT INTO tb_kitchen (id, name) VALUES (3, 'Argentina');
INSERT INTO tb_kitchen (id, name) VALUES (4, 'Brasileira');

INSERT INTO tb_restaurant(id, name, shipping_fee, kitchen_id, created_at, updated_at, address_city_id, address_postal_code, address_public_place, address_number, address_neighborhood) VALUES (1, 'Thai Gourmet', 10.00, 1, utc_timestamp, utc_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
INSERT INTO tb_restaurant(id, name, shipping_fee, kitchen_id, created_at, updated_at) VALUES (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp);
INSERT INTO tb_restaurant(id, name, shipping_fee, kitchen_id, created_at, updated_at) VALUES (3, 'Tuk Tuk Comida Indiana', 15.00, 2, utc_timestamp, utc_timestamp);
INSERT INTO tb_restaurant(id, name, shipping_fee, kitchen_id, created_at, updated_at) VALUES (4, 'Java Steakhouse', 12, 3, utc_timestamp, utc_timestamp);
INSERT INTO tb_restaurant(id, name, shipping_fee, kitchen_id, created_at, updated_at) VALUES (5, 'Lanchonete do Tio Sam', 11, 4, utc_timestamp, utc_timestamp);
INSERT INTO tb_restaurant(id, name, shipping_fee, kitchen_id, created_at, updated_at) VALUES (6, 'Bar da Maria', 6, 4, utc_timestamp, utc_timestamp);

INSERT INTO tb_payment_method(id, description) VALUES (1, "Cartão de crédito");
INSERT INTO tb_payment_method(id, description) VALUES (2, "Cartão de débito");
INSERT INTO tb_payment_method(id, description) VALUES (3, "Dinheiro");


INSERT INTO tb_restaurant_payment_method (restaurant_id, payment_method_id) VALUES (1, 1);
INSERT INTO tb_restaurant_payment_method (restaurant_id, payment_method_id) VALUES (1, 2);
INSERT INTO tb_restaurant_payment_method (restaurant_id, payment_method_id) VALUES (1, 3);
INSERT INTO tb_restaurant_payment_method (restaurant_id, payment_method_id) VALUES (2, 3);
INSERT INTO tb_restaurant_payment_method (restaurant_id, payment_method_id) VALUES (3, 2);
INSERT INTO tb_restaurant_payment_method (restaurant_id, payment_method_id) VALUES (3, 3);
INSERT INTO tb_restaurant_payment_method (restaurant_id, payment_method_id) VALUES (4, 1);
INSERT INTO tb_restaurant_payment_method (restaurant_id, payment_method_id) VALUES (4, 2);
INSERT INTO tb_restaurant_payment_method (restaurant_id, payment_method_id) VALUES (5, 1);
INSERT INTO tb_restaurant_payment_method (restaurant_id, payment_method_id) VALUES (5, 2);
INSERT INTO tb_restaurant_payment_method (restaurant_id, payment_method_id) VALUES (6, 3);


-- products -> restaurant 1
INSERT INTO tb_product (name, description, price, is_active, restaurant_id) VALUES ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, true, 1);
INSERT INTO tb_product (name, description, price, is_active, restaurant_id) VALUES ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, true, 1);
-- products -> restaurant 2
INSERT INTO tb_product (name, description, price, is_active, restaurant_id) VALUES ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);

-- products -> restaurant 3
INSERT INTO tb_product (name, description, price, is_active, restaurant_id) VALUES ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, true, 3);
INSERT INTO tb_product (name, description, price, is_active, restaurant_id) VALUES ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, true, 3);

-- products -> restaurant 4
INSERT INTO tb_product (name, description, price, is_active, restaurant_id) VALUES ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, true, 4);
INSERT INTO tb_product (name, description, price, is_active, restaurant_id) VALUES ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, true, 4);

-- products -> restaurant 5
INSERT INTO tb_product (name, description, price, is_active, restaurant_id) VALUES ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, true, 5);

-- products -> restaurant 6
INSERT INTO tb_product (name, description, price, is_active, restaurant_id) VALUES ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, true, 6);


INSERT INTO tb_permission (id, name, description) VALUES (1, 'GET_KITCHEN', 'Permite consultar cozinhas');
INSERT INTO tb_permission (id, name, description) VALUES (2, 'UPDATE_KITCHEN', 'Permite editar cozinhas');