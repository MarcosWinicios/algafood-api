
    create table tb_city (
        id bigint not null auto_increment,
        state_id bigint not null,
        name varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

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

    create table tb_order (
        order_status tinyint not null check (order_status between 0 and 3),
        shipping_fee decimal(38,2) not null,
        sub_total decimal(38,2) not null,
        total_value decimal(38,2) not null,
        address_city_id bigint,
        cancellation_date datetime(6),
        confirmation_date datetime(6),
        created_at datetime(6) not null,
        delivery_date datetime(6),
        id bigint not null auto_increment,
        payment_id bigint not null,
        restaurant_id bigint not null,
        user_id bigint not null,
        address_complement varchar(255),
        address_neighborhood varchar(255),
        address_number varchar(255),
        address_postal_code varchar(255),
        address_public_place varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table tb_order_item (
        quantity integer not null,
        total_price decimal(38,2) not null,
        unit_price decimal(38,2) not null,
        id bigint not null auto_increment,
        order_id bigint not null,
        product_id bigint not null,
        observation varchar(255) not null,
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

    create table tb_state (
        id bigint not null auto_increment,
        name varchar(255) not null,
        primary key (id)
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

    alter table tb_city 
       add constraint FK1rn7oty4mwqviyw8vk67crapo 
       foreign key (state_id) 
       references tb_state (id);

    alter table tb_group_permission 
       add constraint FKc2rc154h8udl1bmo76luik7jj 
       foreign key (permission_id) 
       references tb_permission (id);

    alter table tb_group_permission 
       add constraint FKfabw19737lr4x9u74m9bhs9cx 
       foreign key (group_id) 
       references tb_group (id);

    alter table tb_order 
       add constraint FK2p4n9ciui39792tk5qdpcxq1w 
       foreign key (user_id) 
       references tb_user (id);

    alter table tb_order 
       add constraint FKirn2pfdnd4jlyf3tj5dlpj4pj 
       foreign key (address_city_id) 
       references tb_city (id);

    alter table tb_order 
       add constraint FKsq6usggm5bajhqj7sqx9378du 
       foreign key (payment_id) 
       references tb_payment_method (id);

    alter table tb_order 
       add constraint FKcub1ihx891msieq8q6fnk6jcg 
       foreign key (restaurant_id) 
       references tb_restaurant (id);

    alter table tb_order_item 
       add constraint FKgeobgl2xu916he8vhljktwxnx 
       foreign key (order_id) 
       references tb_order (id);

    alter table tb_order_item 
       add constraint FK4h5xid5qehset7qwe5l9c997x 
       foreign key (product_id) 
       references tb_product (id);

    alter table tb_product 
       add constraint FKsaq6oeo2b1pkq49bjacej31k2 
       foreign key (restaurant_id) 
       references tb_restaurant (id);

    alter table tb_restaurant 
       add constraint FK61k02rmf9ud5lu2rdde3ofblh 
       foreign key (address_city_id) 
       references tb_city (id);

    alter table tb_restaurant 
       add constraint FKo4yy82dlq4ig3ebquw7ojjjwp 
       foreign key (kitchen_id) 
       references tb_kitchen (id);

    alter table tb_restaurant_payment_method 
       add constraint FK943afhe0bxmfbxycnm1jwtas7 
       foreign key (payment_method_id) 
       references tb_payment_method (id);

    alter table tb_restaurant_payment_method 
       add constraint FK14uaqf86qejtt97qoxxnx8bup 
       foreign key (restaurant_id) 
       references tb_restaurant (id);

    alter table tb_user_group 
       add constraint FKid96lv86ri5sahn1i5vfutj7u 
       foreign key (group_id) 
       references tb_group (id);

    alter table tb_user_group 
       add constraint FK2jb7cj4c0qrsqutdjr9wpuo6l 
       foreign key (user_id) 
       references tb_user (id);
