create table if not exists wallets
(
    id uuid not null,
    amount numeric not null,
    last_update date not null,
    constraint wallets_pk primary key(id)
);

create table if not exists users
(
    id uuid not null,
    wallet_id uuid not null,
    phone numeric not null,
    password varchar(255) not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    email varchar(255) not null,
    registration_date date not null,
    last_update_date date not null,
    age numeric not null,
    constraint users_pk primary key(id),
    constraint fk_wallets foreign key(wallet_id) references wallets(id)
);

create table if not exists sessions
(
    id uuid not null,
    user_id uuid not null,
    token varchar(255) not null,
    expiration_time date not null,
    active bool not null,
    constraint sessions_pk primary key(id),
    constraint fk_users foreign key(user_id) references users(id)
);