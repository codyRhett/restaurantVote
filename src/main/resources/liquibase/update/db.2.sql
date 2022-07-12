create table if not exists restaurants
(
    id bigint default nextval('id_seq'::regclass) not null
            constraint restaurant_pkey
                primary key,
    name varchar(256) not null,
    cuisine varchar(256) not null,
    rating bigint
);