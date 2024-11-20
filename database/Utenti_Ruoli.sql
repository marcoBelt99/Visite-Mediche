truncate utenti cascade; -- in questo cancello tutti i dati dati da ambo le tabelle in un colpo solo


-- Table: public.utenti

-- DROP TABLE IF EXISTS public.utenti;


CREATE TABLE IF NOT EXISTS public.utenti (
    id varchar(30) NOT NULL, -- da programmare nello strato di servizio in modo che diventi: "UT"+"0000N"
    username varchar(50) NOT NULL,
    password varchar(80) NOT NULL,
    attivo boolean,
    CONSTRAINT utenti_pkey PRIMARY KEY (id)
);


	
-- Table: public.ruoli

-- DROP TABLE IF EXISTS public.ruoli;

CREATE TABLE IF NOT EXISTS public.ruoli
(
    id SERIAL NOT NULL,
    id_utente varchar(30) COLLATE pg_catalog."default",
    ruolo character(20) COLLATE pg_catalog."default",
    CONSTRAINT ruoli_pkey PRIMARY KEY (id),
    FOREIGN KEY (id_utente) REFERENCES public.utenti(id) ON DELETE CASCADE -- 1 utente puo' avere uno o piu' ruoli
);

ALTER TABLE ruoli ALTER COLUMN id SET DEFAULT nextval('ruoli_id_seq'); -- TODO 

ALTER SEQUENCE ruoli_id_seq RESTART WITH 1; -- TODO

-- PRIMA ADMIN
INSERT INTO public.utenti
VALUES('UT000001','Admin','$2a$10$CCtUPCnPmawXiT9wmYoxmelSnhFZ3wN23ZqQ.pa9NKCi/Bv0ak92G',true);

INSERT INTO public.ruoli
VALUES(1,'UT000001','ADMIN');

INSERT INTO public.ruoli
VALUES(2,'UT000001','USER');



-- POI USER
INSERT INTO public.utenti
VALUES('UT000002','Nicola','$2a$10$FAeT9lkTgz867zh8AtD2F.BoSDed.N.qUFdEn3sz/u4xfVgwUHYZW',true);

INSERT INTO public.ruoli
VALUES(3,'UT000002','USER');



ALTER SEQUENCE ruoli_id_seq RESTART WITH 3; -- TODO


select * from ruoli;

select *  from utenti;


-- Ultimo indice autoincrement dei ruoli

SELECT last_value FROM ruoli_id_seq;

SELECT setval('ruoli_id_seq', (SELECT MAX(id) FROM ruoli));


