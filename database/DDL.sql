---------------------------------------------
------------ CREAZIONE DATABASE --------------
---------------------------------------------

-- Database: visite_mediche

-- DROP DATABASE IF EXISTS visite_mediche;

CREATE DATABASE visite_mediche
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'it_IT.UTF-8'
    LC_CTYPE = 'it_IT.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

COMMENT ON DATABASE visite_mediche
    IS 'Database per portare avanti l''effettivo progettino tra medici e pazienti.
Il progetto in se'' mi servira'' per imparare a fare i test unitari (e di integrazione).';

---------------------------------------------
------------ CREAZIONE TABELLE --------------
---------------------------------------------
        -- Table: public.medici

-- DROP TABLE IF EXISTS public.medici;

CREATE TABLE IF NOT EXISTS public.medici
(
    codice character varying COLLATE pg_catalog."default" NOT NULL,
    cognome character varying COLLATE pg_catalog."default" NOT NULL,
    nome character varying COLLATE pg_catalog."default" NOT NULL,
    citta character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT medici_pkey PRIMARY KEY (codice)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.medici
    OWNER to postgres;



-- Table: public.pazienti

-- DROP TABLE IF EXISTS public.pazienti;

CREATE TABLE IF NOT EXISTS public.pazienti
(
    codice_fiscale character varying(16) COLLATE pg_catalog."default" NOT NULL,
    cognome character varying COLLATE pg_catalog."default" NOT NULL,
    nome character varying COLLATE pg_catalog."default" NOT NULL,
    medico character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pazienti_pkey PRIMARY KEY (codice_fiscale),
    CONSTRAINT pazienti_fk FOREIGN KEY (medico)
    REFERENCES public.medici (codice) MATCH SIMPLE
    ON UPDATE SET NULL
    ON DELETE SET NULL
    NOT VALID
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.pazienti
    OWNER to postgres;