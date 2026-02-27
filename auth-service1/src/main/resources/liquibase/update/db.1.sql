CREATE EXTENSION IF NOT EXISTS pgcrypto;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
------------------------------------------------------------------------------------------------------------------------
create table if not exists users
(
    id UUID default  uuid_generate_v4() not null
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
    id UUID default uuid_generate_v4() not null
            constraint role_pkey
                primary key,
    name varchar(256) not null
);

create table if not exists user_roles
(
   user_id UUID not null
        constraint fk_user_roles_user
            references users,
   role_id UUID not null
        constraint fk_user_roles_roles
            references roles
);
INSERT into roles(name) VALUES
('ROLE_ADMIN'),
('ROLE_USER');

insert into users (user_name, first_name, last_name, email, password) values
('admin', 'admin', 'admin', 'admin@ya.ru', '$2y$10$E1ga4sumP.Bfum4CbgkJEuRDOlhNh2..p//nc/philDtVCFQ6Ow9i');

INSERT INTO user_roles (user_id, role_id)
SELECT
    (SELECT id FROM users WHERE user_name = 'admin'),
    (SELECT id FROM roles WHERE name = 'ROLE_ADMIN');