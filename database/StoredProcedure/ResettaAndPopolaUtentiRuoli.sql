CREATE OR REPLACE PROCEDURE resetta_e_popola_utenti_ruoli()
LANGUAGE plpgsql AS $$
BEGIN
    -- Truncate utenti (cascading delete on ruoli)
TRUNCATE TABLE utenti CASCADE;

-- Drop and recreate the utenti table
--DROP TABLE IF EXISTS utenti;
CREATE TABLE IF NOT EXISTS utenti (
                                      id VARCHAR(30) NOT NULL, -- ID formato UT0000N
    username VARCHAR(50) NOT NULL,
    password VARCHAR(80) NOT NULL,
    attivo BOOLEAN,
    CONSTRAINT utenti_pkey PRIMARY KEY (id)
    );

-- Drop and recreate the ruoli table
--DROP TABLE IF EXISTS ruoli;
CREATE TABLE IF NOT EXISTS ruoli (
                                     id SERIAL NOT NULL,
                                     id_utente VARCHAR(30) NOT NULL,
    ruolo CHARACTER(20) NOT NULL,
    CONSTRAINT ruoli_pkey PRIMARY KEY (id),
    FOREIGN KEY (id_utente) REFERENCES utenti(id) ON DELETE CASCADE
    );

-- Ensure sequence for ruoli is correctly set
ALTER SEQUENCE ruoli_id_seq RESTART WITH 1;

-- Insert ADMIN user and roles
INSERT INTO utenti (id, username, password, attivo)
VALUES ('UT000001', 'Admin', '$2a$10$CCtUPCnPmawXiT9wmYoxmelSnhFZ3wN23ZqQ.pa9NKCi/Bv0ak92G', TRUE);

INSERT INTO ruoli (id_utente, ruolo)
VALUES ('UT000001', 'ADMIN'), ('UT000001', 'USER');

-- Insert USER and roles
INSERT INTO utenti (id, username, password, attivo)
VALUES ('UT000002', 'Nicola', '$2a$10$FAeT9lkTgz867zh8AtD2F.BoSDed.N.qUFdEn3sz/u4xfVgwUHYZW', TRUE);

INSERT INTO ruoli (id_utente, ruolo)
VALUES ('UT000002', 'USER');

-- Update the sequence value to the max ID
PERFORM setval('ruoli_id_seq', (SELECT MAX(id) FROM ruoli));

END;
$$;