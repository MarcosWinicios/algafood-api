
    CREATE TABLE tb_group (
        id BIGINT NOT NULL AUTO_INCREMENT,
        name VARCHAR(60) NOT NULL,

        PRIMARY KEY (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

    CREATE TABLE tb_group_permission (
        group_id BIGINT NOT NULL,
        permission_id BIGINT NOT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

    CREATE TABLE tb_payment_method (
        id BIGINT NOT NULL AUTO_INCREMENT,
        description VARCHAR(60) NOT NULL,

        PRIMARY KEY (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

    CREATE TABLE tb_permission (
        id BIGINT NOT NULL AUTO_INCREMENT,
        name VARCHAR(255) NOT NULL,
        description VARCHAR(255) NOT NULL,

        PRIMARY KEY (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

    CREATE TABLE tb_product (
        id BIGINT NOT NULL AUTO_INCREMENT,
        name VARCHAR(80) NOT NULL,
        description TEXT NOT NULL,
        price DECIMAL(10,2) NOT NULL,
        is_active TINYINT(1) NOT NULL,
        restaurant_id BIGINT NOT NULL,

        PRIMARY KEY (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

    CREATE TABLE tb_restaurant (
        id BIGINT NOT NULL AUTO_INCREMENT,
        name VARCHAR(255) NOT NULL,
        shipping_fee DECIMAL(10,2) NOT NULL,
        updated_at DATETIME NOT NULL,
        created_at DATETIME NOT NULL,
        address_city_id BIGINT,
        address_postal_code VARCHAR(9),
        address_public_place VARCHAR(255),
        address_number VARCHAR(255),
        address_complement VARCHAR(255),
        address_neighborhood VARCHAR(255),
        kitchen_id BIGINT NOT NULL,

        PRIMARY KEY (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

    CREATE TABLE tb_restaurant_payment_method (
        payment_method_id BIGINT NOT NULL,
        restaurant_id BIGINT NOT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

    CREATE TABLE tb_user (
        id BIGINT NOT NULL AUTO_INCREMENT,
        name VARCHAR(80) NOT NULL,
        email VARCHAR(255) NOT NULL,
        password VARCHAR(255) NOT NULL,
        created_at DATETIME NOT NULL,

        PRIMARY KEY (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

    CREATE TABLE tb_user_group (
        group_id BIGINT NOT NULL,
        user_id BIGINT NOT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

    ALTER TABLE tb_group_permission
       ADD CONSTRAINT fk_group_permission_permission
       FOREIGN KEY (permission_id)
       REFERENCES tb_permission (id);

    ALTER TABLE tb_group_permission
       ADD CONSTRAINT fk_group_permission_group
       FOREIGN KEY (group_id)
       REFERENCES tb_group (id);

    ALTER TABLE tb_product
       ADD CONSTRAINT fk_product_restaurant
       FOREIGN KEY (restaurant_id)
       REFERENCES tb_restaurant (id);

    ALTER TABLE tb_restaurant
       ADD CONSTRAINT fk_restaurant_city
       FOREIGN KEY (address_city_id)
       REFERENCES tb_city (id);

    ALTER TABLE tb_restaurant
       ADD CONSTRAINT fk_restaurant_kitchen
       FOREIGN KEY (kitchen_id)
       REFERENCES tb_kitchen (id);

    ALTER TABLE tb_restaurant_payment_method
       ADD CONSTRAINT fk_restaurant_payment_method_payment_method
       FOREIGN KEY (payment_method_id)
       REFERENCES tb_payment_method (id);

    ALTER TABLE tb_restaurant_payment_method
       ADD CONSTRAINT fk_restaurant_payment_method_restaurant
       FOREIGN KEY (restaurant_id)
       REFERENCES tb_restaurant (id);

    ALTER TABLE tb_user_group
       ADD CONSTRAINT fk_user_group_group
       FOREIGN KEY (group_id)
       REFERENCES tb_group (id);

    ALTER TABLE tb_user_group
       ADD CONSTRAINT fk_user_group_user
       FOREIGN KEY (user_id)
       REFERENCES tb_user (id);