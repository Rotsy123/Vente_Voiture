create database voiture;
\c voiture;

create table bouquet(
    id serial primary key,
    nom varchar(50),
    pourcentage_commission double precision
);

create table marque(
    id serial primary key,
    nom varchar(50)
);

create table modele(
    id serial primary key,
    nom varchar(50)
);

create table carburant(
    id serial primary key,
    nom varchar(50)
);

create table transmission(
    id serial primary key,
    nom varchar(50)
);

create table voiture(
    id serial primary key,
    idmarque int,
    idmodele int,
    sortie Date, 
    FOREIGN KEY(idmarque) REFERENCES marque(id),
    FOREIGN KEY(idmodele) REFERENCES modele(id)
);

create table detailsvoiture(
    idvoiture int,
    kilometrage double precision,
    nbplaces int,
    nbportes int,
    etat_interieur double precision,
    etat_exterieur double precision,
    consommation double precision,
    idcarburant int,
    idtransmission int,
    FOREIGN KEY (idcarburant) REFERENCES carburant(id),
    FOREIGN KEY(idvoiture) REFERENCES voiture(id),
    FOREIGN KEY(idtransmission) REFERENCES transmission(id)
);

create table photos(
    idvoiture int,
    photos bytea,
    FOREIGN KEY(idvoiture) REFERENCES voiture(id)
); 

ALTER TABLE detailsvoiture add column photos bytea;
alter table detailsvoiture drop column photos;
ALTER TABLE detailsvoiture add column prix double precision;
 
create table annonce(
    id serial primary key,
    idvoiture int,
    idpersonne int, 
    datepublication timestamp,
    idbouquet int,
    FOREIGN KEY(idvoiture) REFERENCES voiture(id),
    FOREIGN KEY(idbouquet) REFERENCES bouquet(id),
    FOREIGN KEY(idpersonne) REFERENCES personne(id)
);
ALTER TABLE ANNONCE ADD COLUMN ETAT int;

 
create table personne(
    id serial primary key,
    nom varchar(50),
    prenom varchar(50),
    contact varchar(10),
    email varchar(50),
    dtn Date
);

insert into personne(nom, prenom, contact, email, dtn) values
('RAKOTONDRAINY', 'Rotsy', '0345770369','rakotondrainyrotsy@gmail.com','2003-10-17'),
('RAKOTONDRAINY', 'Miasy', '0345770369','rakotondrainyMiasy@gmail.com','2003-10-18');

insert into carburant(nom) values ('gasoil'), ('essence');
insert into transmission(nom) values ('boite automatique'), ('boite semie-automatique'),('boite manuelle');

-- Insertion dans la table 'marque'
INSERT INTO marque (nom) VALUES ('Toyota');
INSERT INTO marque (nom) VALUES ('Honda');
INSERT INTO marque (nom) VALUES ('Ford');
 
-- Insertion dans la table 'modele'
INSERT INTO modele (nom) VALUES ('Sedan');
INSERT INTO modele (nom) VALUES ('SUV');
INSERT INTO modele (nom) VALUES ('Hatchback');
INSERT INTO modele (nom) VALUES ('Convertible');
INSERT INTO modele (nom) VALUES ('Coupe');

-- Insertion dans la table 'voiture'
INSERT INTO voiture (idmarque, idmodele, sortie) VALUES (1, 1, '2022-01-01');
INSERT INTO voiture (idmarque, idmodele, sortie) VALUES (2, 2, '2022-02-15');
INSERT INTO voiture (idmarque, idmodele, sortie) VALUES (1, 3, '2022-03-20');
INSERT INTO voiture (idmarque, idmodele, sortie) VALUES (3, 4, '2022-04-10');
INSERT INTO voiture (idmarque, idmodele, sortie) VALUES (2, 5, '2022-05-25');

insert into detailsvoiture values 
(1,1000,5,5,5.2,7,15,1,1);

insert into annonce (idvoiture, idpersonne, datepublication, idbouquet) values
    (2,1,'2023-12-14',2);
    (1,1,'2023-12-12',1),
    (2,2,'2023-12-14',2);

insert into bouquet (nom, pourcentage_commission) values 
('bouquet1',0),
('bouquet2',10),
('bouquet3',20),
('bouquet4',30);



insert into bouquet (nom, pourcentage_commission) values 
('bouquet1',0),
('bouquet2',10),
('bouquet3',20),
('bouquet4',30);


create table personne(
    id serial primary key,
    nom varchar(50),
    prenoms varchar(100),
    datedenaissance date,
    mail varchar(100),
    telephone varchar(10),
    adresse varchar(100),
    motdepasse varchar(50)
);
INSERT INTO personne (nom, prenoms, datedenaissance, mail, telephone, adresse, motdepasse)
VALUES
  ('Doe', 'John', '1990-01-15', 'john.doe@email.com', '1234567890', '123 Main St, City', 'motdepasse123'),
  ('Smith', 'Alice', '1985-05-20', 'alice.smith@email.com', '9876543210', '456 Oak St, Town', 'mdpsecret456'),
  ('Johnson', 'Bob', '1982-11-10', 'bob.johnson@email.com', '5551112233', '789 Pine St, Village', 'supermdp789');

create table messagerie(
    id serial primary key,
    expediteur int,
    destinataire int,
    messages text,
    dateenvoie TIMESTAMP 
);

mongod --dbpath "C:\Program Files\MongoDB\Server\7.0\data\db"
delete from annonce;
delete from detailsvoiture;
delete from voiture;

{
    "voiture":{
        "marque":{"id": 2},
        "modele":{"id": 2},
        "sortie":"2024-01-11"
    },
    "detailsVoiture": {
        "kilometrique":3000.0,
        "nbplaces":6,
        "nbportes":4,
        "etat_interieur":8.5,
        "etat_exterieur":9,
        "consommation":7,
        "prix":1000,
        "carburant":{"id": 2},
        "transmission":{"id":1}
    },
    "annonce":{
        "personne":{"id":2},
        "dateplublication":"2024-01-11T14:00:00",
        "bouquet":{"id": 1}
    }
}
