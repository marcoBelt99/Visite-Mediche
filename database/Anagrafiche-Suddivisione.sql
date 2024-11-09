select * from medici;
select * from pazienti;


select * from anagrafiche;

-- INSERIMENTO ANAGRAFICHE
INSERT INTO anagrafiche ( telefono, email)
VALUES
-- Dati che rappresentano i medici
-- (3403183855, 'annapier@gmail.com'),
-- (3503183866, 'alesm@hotmail.com'),
-- (3463183877, 'cicasimob@libero.com'),
-- (3473183888, 'diltessbnd@gmail.com');

-- Dati che rappresentano paziente
(3413183811, 'sarav@gmail.com'),
(3503186822, 'sof@hotmail.com'),
(3463183833, 'emma@libero.com'),
(3673183844, 'lisbianchi@gmail.com'),
(3403183865, 'morifa@gmail.com'),
(3503183866, 'rossimar@hotmail.com'),
(3163183877, 'zambrimar@libero.com'),
(6473183888, 'mosgiud@hotmail.com'),
(3403183801, 'romafaso@gmail.com'),
(3503183602, 'lore@hotmail.com'),
(6503183803, 'moirabeltra@gmail.com'),
(3463186804, 'annamarche@libero.com'),
(3473183805, 'mischattiele@gmail.com'),
(3463183806, 'ferrar@libero.com'),
(3473183807, 'brgenr@gmail.com'),
(3406183808, 'jopiazza@gmail.com'),
(3503683809, 'donnam@hotmail.com');




-- ########################################################
-- ########################################################
-- ASSEGNAZIONE PRIMI  N ID ANAGRAFICHE A MEDICI
-- MODIFICARE I LIMITI PER ASSEGNARE i RIMANENTI (TOTALE_PAZIENTI -N) AI PAZIENTI

-- Si tratta di automatizzare una query del tipo:
UPDATE medici
SET id_anagrafica = 1, 
	WHERE nome="Marco";




DO $$
DECLARE
    anagrafiche_ids INT[];  -- Dichiarazione di un array di tipo INT
	medici_nomi TEXT[]; -- Dichiaro un array di tipoi TEXT
	pazienti_nomi TEXT[]; -- Dichiaro un array di tipoi TEXT

	indice_partenza INTEGER := 1; -- Indice per il vettore anagrafiche_ids
	
BEGIN
    -- Raccogli i valori dalla colonna 'id' della tabella "anagrafiche" in un array
    SELECT array_agg(id) INTO anagrafiche_ids
    FROM anagrafiche;

	
	-- Raccogli i valori dalla colonna 'nome' della tabella "medici" in un array
	SELECT array_agg(nome) INTO medici_nomi
	FROM medici;


	-- Raccogli i valori dalla colonna 'nome' della tabella "pazienti" in un array
	SELECT array_agg(nome) INTO pazienti_nomi
	FROM pazienti;

	

    -- Mostra il vettore dei nomi
    RAISE NOTICE 'Vettore degli id: %', anagrafiche_ids;
	RAISE NOTICE 'indice_partenza all inizio vale: %', indice_partenza; -- stampo il suo valore
    -- RAISE NOTICE 'Vettore dei nomi dei medici: %', medici_nomi;
    RAISE NOTICE 'Vettore dei nomi dei pazienti: %', pazienti_nomi;
	RAISE NOTICE 'Da che indice di anagrafica parti ad assegnare la anagrafica ai pazienti? : %', (array_length(medici_nomi, 1)+1);
	RAISE NOTICE 'Quanti pazienti ho? : %', array_length(pazienti_nomi, 1);
	

	-- Ciclo for e assegnazione dei risultati
		-- FOR	i in 1 .. array_length(medici_nomi, 1) LOOP -- Per medici
		FOR i in (array_length(medici_nomi, 1)+1) .. array_length(pazienti_nomi, 1) LOOP -- Per pazienti
			--UPDATE medici -- per medici	
			UPDATE pazienti -- per pazienti	
			SET id_anagrafica = anagrafiche_ids[ i ] 
			-- WHERE medici.nome= medici_nomi[i]; -- per medici
			WHERE pazienti.nome = pazienti_nomi[ indice_partenza ]; -- per pazienti
			
			indice_partenza := indice_partenza +1; -- Incremento l'indice/contatore ad ogni iterazione
			RAISE NOTICE 'i vale: %', i; 
			RAISE NOTICE 'indice_partenza vale: %', indice_partenza; -- stampo il suo valore
		END LOOP;
	
END $$;

select * from pazienti;


-- SE DA DOPPIONI:
	
	-- update pazienti 
	-- set id_anagrafica=7
	-- where cognome = 'Rossi' and nome = 'Mario';


UPDATE pazienti
SET id_anagrafica = NULL;
