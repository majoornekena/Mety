create table assureur(
    id serial primary key,
    designation varchar(20)
);

create table assurance(
    id serial primary key,
    idvehicule int,
    idassureur int,
    datedebut timestamp default now(),
    datefin timestamp,
    cotisation numeric(4, 2) NOT NULL
);
alter table assurance add foreign key(idvehicule) references vehicule(id);
alter table assurance add foreign key(idassureur) references assureur(id);

insert into assureur (idassureur,designation)values('1','MAMA');

insert into vehicule (Marque)values('Nissan');
insert into vehicule (Marque)values('toyota');

insert into assurance(id,idvehicule,idassureur,datedebut,datefin,cotisation) values (default,'1','1','2022-12-01 08:00:00','2022-12-31 08:00:00','22.22');
insert into assurance(id,idvehicule,idassureur,datedebut,datefin,cotisation) values (default,'1','1','2023-01-01 08:00:00','2023-01-31 08:00:00','22.22');
insert into assurance(id,idvehicule,idassureur,datedebut,datefin,cotisation) values (default,'2','1','2022-12-02 08:00:00','2023-01-02 08:00:00','23.22');
insert into assurance(id,idvehicule,idassureur,datedebut,datefin,cotisation) values (default,'2','1','2023-01-02 08:00:00','2023-02-28 08:00:00','23.22');

select extract (month from age(datefin,now())) as mois,extract (day from age(datefin,now())) as jour from assurance where idvehicule='1';
select idvehicule,max(datedebut) as datedebut from assurance group by idvehicule;

create or replace view maxdatevh as (select idvehicule,max(datedebut) as datedebut from assurance group by idvehicule);

create or replace view assurancerestant as(
select assurance.id as idassurance,maxdatevh.idvehicule as idvehicule,
extract (month from age(assurance.datefin,now())) as mois,
extract (day from age(assurance.datefin,now())) as jour,
row_number() over() id
from assurance 
join maxdatevh on maxdatevh.idvehicule=assurance.idvehicule 
and maxdatevh.datedebut=assurance.datedebut
);
--update assurance set datefin='2022-12-31 08:00:00';

------------triger---------
create or replace view vassu as
select a.*,
    ar.designation
from assurance a
join assureur ar 
on a.idassureur=ar.id;


create or replace function testins () returns trigger as 
$$
    Declare
        seqar int;
    BEGIN
        select nextval('assureur_id_seq') into seqar;
        insert into assureur(id,designation)values(seqar,new.designation);
        insert into assurance(idvehicule,idassureur,datedebut,datefin,cotisation) values 
        (new.idvehicule,seqar,new.datedebut,new.datefin,new.cotisation);
        return null;
    END;
$$ language plpgsql;

create trigger trigtestins
instead of insert on vassu
for each row execute procedure testins();

insert into vassu(idvehicule,datedebut,datefin,cotisation,designation)values(1,'2023-02-01 08:00:00','2023-02-28 08:00:00',22.00,'test');





e