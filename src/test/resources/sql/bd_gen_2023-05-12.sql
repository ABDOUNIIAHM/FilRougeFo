DROP DATABASE IF EXISTS filrouge_bdd;
CREATE DATABASE filrouge_bdd;
USE filrouge_bdd;

create table Admins
(
    id           int auto_increment
        primary key,
    isSuperAdmin bit          not null,
    password     varchar(255) null,
    username     varchar(255) null,
    constraint UK_sr28qnsnytjjht54j6fa88uvn
        unique (username)
);

create table Categories
(
    id   int auto_increment
        primary key,
    name varchar(255) null,
    constraint UK_cqsfg7tv7vfw6vjx36u4ym0i9
        unique (name)
);

create table Clients
(
    id        bigint auto_increment
        primary key,
    avatarUrl varchar(255) null,
    email     varchar(255) null,
    firstName varchar(255) null,
    lastName  varchar(255) null,
    password  varchar(255) null,
    constraint UK_96icggkuasv49rrd8osfg6g7w
        unique (email)
);

create table Adresses
(
    id         bigint auto_increment
        primary key,
    city       varchar(255) null,
    complement varchar(255) null,
    number     varchar(255) null,
    roadName   varchar(255) null,
    roadPrefix varchar(255) null,
    title      varchar(255) null,
    zipCode    varchar(255) null,
    id_client  bigint       null,
    constraint FKalivqho1bc99sh3rbn3pl40ow
        foreign key (id_client) references Clients (id)
);

create table Months
(
    id   int auto_increment
        primary key,
    name varchar(255) null
);

create table OrderStatuses
(
    id   bigint auto_increment
        primary key,
    name varchar(255) null
);

create table PaymentMethods
(
    id   bigint auto_increment
        primary key,
    name varchar(255) null
);

create table Orders
(
    id               bigint auto_increment
        primary key,
    date             date   null,
    id_client        bigint null,
    id_paymentMethod bigint null,
    id_status        bigint null,
    constraint FKlqi0cg9pm1dv88xnqhdl6lxmw
        foreign key (id_paymentMethod) references PaymentMethods (id),
    constraint FKnjt0mo5eqgqpdsl0qkxm8pr5e
        foreign key (id_status) references OrderStatuses (id),
    constraint FKnkb0ephnwm59q78xaedb3m95
        foreign key (id_client) references Clients (id)
);

create table PhoneNumbers
(
    id          bigint auto_increment
        primary key,
    phoneNumber varchar(255) null,
    id_client   bigint       null,
    constraint FKqntxxridd3hhrjqk7pnebr6ax
        foreign key (id_client) references Clients (id)
);

create table Products
(
    id           int auto_increment
        primary key,
    description  varchar(255)   null,
    imgUrl       varchar(255)   null,
    name         varchar(255)   null,
    pricePerUnit decimal(38, 2) null,
    stock        decimal(38, 2) null,
    unit         varchar(255)   null,
    vat          decimal(38, 2) null,
    idCategory   int            null,
    constraint UK_fx9yjtfi73058657et69e5ywj
        unique (name),
    constraint FK31ime15l4wjjes1jgg9qlada
        foreign key (idCategory) references Categories (id)
);

create table OrderLines
(
    id        bigint auto_increment
        primary key,
    discount  decimal(38, 2) null,
    quantity  decimal(38, 2) null,
    idOrder   bigint         null,
    idProduct int            null,
    constraint FKcvf51kqpiiym3m1kkejb0xsy9
        foreign key (idOrder) references Orders (id),
    constraint FKfa5s8mrn2qj7ymwde5pf4odmp
        foreign key (idProduct) references Products (id)
);

create table product_months
(
    idProduct int not null,
    idMonth   int not null,
    constraint FK5islsgc6x5ar3giura6qibvdk
        foreign key (idProduct) references Products (id),
    constraint FK5yicrrtwgu1jo5pi1ax32k023
        foreign key (idMonth) references Months (id)
);

