drop view if exists restaurant_vote_list;

create view restaurant_vote_list as
select
        v.id,
        r.id as restaurant_id,
        r.name,
        v.user_id,
        v.rating
from restaurants as r
    inner join votes v on v.restaurant_id = r.id;