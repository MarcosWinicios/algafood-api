CREATE TABLE tb_order(
	id BIGINT NOT NULL AUTO_INCREMENT,
    sub_total DECIMAL(10,2) NOT NULL,
    shipping_fee DECIMAL(10,2) NOT NULL,
    total_value DECIMAL(10,2) NOT NULL,

    restaurant_id BIGINT NOT NULL,
    user_client_id BIGINT NOT NULL,
    payment_method_id BIGINT NOT NULL,

    address_city_id BIGINT,
    address_postal_code VARCHAR(9),
    address_public_place VARCHAR(255),
    address_number VARCHAR(255),
    address_complement VARCHAR(255),
    address_neighborhood VARCHAR(255),

    order_status enum ('CREATED','CANCELED','CONFIRMED','DELIVERED') NOT NULL,
    created_at DATETIME NOT NULL,
    confirmation_date DATETIME,
    cancellation_date DATETIME,
    delivery_date DATETIME,

    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE tb_order
   ADD CONSTRAINT fk_order_address_city
   FOREIGN KEY (address_city_id)
   REFERENCES tb_city (id);

ALTER TABLE tb_order
   ADD CONSTRAINT fk_order_restaurant
   FOREIGN KEY (restaurant_id)
   REFERENCES tb_restaurant (id);

ALTER TABLE tb_order
   ADD CONSTRAINT fk_order_user
   FOREIGN KEY (user_client_id)
   REFERENCES tb_user (id);

ALTER TABLE tb_order
   ADD CONSTRAINT fk_order_payment_method
   FOREIGN KEY (payment_method_id)
   REFERENCES tb_payment_method (id);

CREATE TABLE tb_order_item (
	id BIGINT NOT NULL AUTO_INCREMENT,
    quantity SMALLINT(6) NOT NULL,
    total_price DECIMAL(10,2) NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,
    observation VARCHAR(255) NOT NULL,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,

    PRIMARY KEY (id),
    UNIQUE KEY uk_order_item_product (order_id, product_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE tb_order_item 
   ADD CONSTRAINT fk_order_item_order 
   FOREIGN KEY (order_id) 
   REFERENCES tb_order (id);

ALTER TABLE tb_order_item 
   ADD CONSTRAINT fk_order_item_product 
   FOREIGN KEY (product_id) 
   REFERENCES tb_product (id);