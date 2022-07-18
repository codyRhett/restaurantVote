create sequence if not exists id_seq;
------------------------------------------------------------------------------------------------------------------------
create table if not exists users
(
    id bigint default nextval('id_seq'::regclass) not null
            constraint user_pkey
                primary key,
    user_name varchar(256) not null,
    first_name varchar(256) not null,
    last_name varchar(256) not null,
    email varchar(256) not null,
    password varchar(256) not null,
    created timestamp with time zone,
    updated timestamp with time zone,
    status varchar(256) default 'ACTIVE'
);

create table if not exists roles
(
    id bigint default nextval('id_seq'::regclass) not null
            constraint role_pkey
                primary key,
    name varchar(256) not null
);

--INSERT INTO roles(id, name) VALUES
--(1, 'ROLE_ADMIN'),
--(2, 'ROLE_USER');

--INSERT INTO users(id, user_name, first_name, last_name, email, password) VALUES
--(1, 'admin', 'admin', 'admin', 'admin@yandex.ru', '$2a$12$c6QmI2pHr560Km6Pn5rpUeNji/GMLtztb7h4BInqbX/ovSlgqLD9m');

create table if not exists user_roles
(
   user_id bigint not null
        constraint fk_user_roles_user
            references users,
   role_id bigint not null
        constraint fk_user_roles_roles
            references roles
);

INSERT INTO user_roles(user_id, role_id) VALUES
(1, 1);
