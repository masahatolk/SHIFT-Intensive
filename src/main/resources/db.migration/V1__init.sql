create table if not exists users
(
    id bigserial not null
        constraint users_pk
            primary key,

    phone numeric not null,
    password varchar(255) not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    email varchar(255) not null,
    registration_date date not null,
    last_update_date date not null,
    age numeric not null
);