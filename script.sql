create table voiture;
\c voiture;

create table bouquet(
    id serial primary key,
    nom varchar(50),
    pourcentage_commission double precision
);

insert into bouquet (nom, pourcentage_commission) values 
('bouquet1',0),
('bouquet2',10),
('bouquet3',20),
('bouquet4',30);

