
    create table tb_group (
        id bigint not null auto_increment,
        name varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table tb_group_permission (
        group_id bigint not null,
        permission_id bigint not null
    ) engine=InnoDB;

    create table tb_kitchen (
        id bigint not null auto_increment,
        name varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table tb_payment_method (
        id bigint not null auto_increment,
        description varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table tb_permission (
        id bigint not null auto_increment,
        description varchar(255) not null,
        name varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table tb_product (
        is_active bit not null,
        price decimal(38,2) not null,
        id bigint not null auto_increment,
        restaurant_id bigint not null,
        description varchar(255) not null,
        name varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table tb_restaurant (
        is_active bit not null,
        is_open bit not null,
        shipping_fee decimal(38,2) not null,
        address_city_id bigint,
        created_at datetime not null,
        id bigint not null auto_increment,
        kitchen_id bigint not null,
        updated_at datetime not null,
        address_complement varchar(255),
        address_neighborhood varchar(255),
        address_number varchar(255),
        address_postal_code varchar(255),
        address_public_place varchar(255),
        name varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table tb_restaurant_payment_method (
        payment_method_id bigint not null,
        restaurant_id bigint not null
    ) engine=InnoDB;

    create table tb_user (
        created_at datetime not null,
        id bigint not null auto_increment,
        email varchar(255) not null,
        name varchar(255) not null,
        password varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table tb_user_group (
        group_id bigint not null,
        user_id bigint not null
    ) engine=InnoDB;

    alter table tb_group_permission
       add constraint fk_group_permission_permission
       foreign key (permission_id)
       references tb_permission (id);

    alter table tb_group_permission
       add constraint fk_group_permission_group
       foreign key (group_id)
       references tb_group (id);

    alter table tb_product
       add constraint fk_product_restaurant
       foreign key (restaurant_id)
       references tb_restaurant (id);

    alter table tb_restaurant
       add constraint fk_restaurant_city
       foreign key (address_city_id)
       references tb_city (id);

    alter table tb_restaurant
       add constraint fk_restaurant_kitchen
       foreign key (kitchen_id)
       references tb_kitchen (id);

    alter table tb_restaurant_payment_method
       add constraint fk_restaurant_payment_method_payment_method
       foreign key (payment_method_id)
       references tb_payment_method (id);

    alter table tb_restaurant_payment_method
       add constraint fk_restaurant_payment_method_restaurant
       foreign key (restaurant_id)
       references tb_restaurant (id);

    alter table tb_user_group
       add constraint fk_user_group_group
       foreign key (group_id)
       references tb_group (id);

    alter table tb_user_group
       add constraint fk_user_group_user
       foreign key (user_id)
       references tb_user (id);