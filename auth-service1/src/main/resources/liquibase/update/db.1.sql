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

create table if not exists user_roles
(
   user_id bigint not null
        constraint fk_user_roles_user
            references users,
   role_id bigint not null
        constraint fk_user_roles_roles
            references roles
);
INSERT into roles VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

insert into users (id, user_name, first_name, last_name, email, password) values
(1, 'admin', 'admin', 'admin', 'admin@ya.ru', '$2y$10$E1ga4sumP.Bfum4CbgkJEuRDOlhNh2..p//nc/philDtVCFQ6Ow9i');
insert into user_roles values(1,1);