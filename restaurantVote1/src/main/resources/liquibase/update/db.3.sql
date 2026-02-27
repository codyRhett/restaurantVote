create table if not exists votes
(
    id bigint default nextval('id_seq'::regclass) not null
            constraint votes_pkey
                primary key,
    restaurant_id bigint
         constraint fk_votes_restaurant_id
             references restaurants,
    date_created date,
    rating bigint not null
);

ALTER TABLE votes ADD COLUMN IF NOT EXISTS comment varchar(512);