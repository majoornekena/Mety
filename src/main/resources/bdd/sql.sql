create table tokenexp (
    duree time not null
);
INSERT INTO tokenexp(duree) VALUES('00:05:00');
    
CREATE PROCEDURE removetoken()
LANGUAGE SQL
AS $$
    delete from token where dateins < current_timestamp - (select duree from tokenexp);
$$;

create or replace view vassur as 
    select a.idvehicule id,
        v.marque,
        a.mois,
        a.jour
    from assurancerestant a
    left join vehicule v 
    on a.idvehicule = v.id;