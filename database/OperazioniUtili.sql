DO $$
DECLARE
		codici_fiscali_array TEXT[]; -- Dichiaro un array di tipoi TEXT
		indice_partenza INTEGER := 1; -- Indice per il vettore anagrafiche_ids
		id_list INTEGER[] := ARRAY[23, 24, 25, 26, 27];  -- Elenco degli id da aggiornare

	BEGIN
	    -- Raccogli i valori dalla colonna 'codice_fiscale' della tabella "pazienti" in un array
	    SELECT array_agg(codice_fiscale) INTO codici_fiscali_array
	    FROM pazienti;
	
	    -- Mostra il vettore dei nomi
	    RAISE NOTICE 'Vettore dei CF: %', codici_fiscali_array;
		
	

	-- Ciclo for e assegnazione dei risultati
		FOR	i in 1 .. array_length(codici_fiscali_array, 1) LOOP
		--FOR	i in 23 .. 27 LOOP
			
			-- indice_partenza = i;
			UPDATE anagrafiche -- per pazienti	
			SET codice_fiscale = codici_fiscali_array[ i ]
			WHERE id = id_list[i];
			
			
			-- indice_partenza := indice_partenza +1;
			-- indice_partenza := indice_partenza +1; -- Incremento l'indice/contatore ad ogni iterazione
			RAISE NOTICE ' codici_fiscali_array[ indice_partenza ] vale: %', codici_fiscali_array[ i ]; 
			-- RAISE NOTICE 'indice_partenza vale: %', indice_partenza; -- stampo il suo valore
		END LOOP;
	
END $$;



-- RICERCA DUPLICATI NELLA COLONNA codice_fiscale
SELECT codice_fiscale, COUNT(*) as occurrences
FROM anagrafiche
GROUP BY codice_fiscale
HAVING COUNT(*) > 1;



select * from anagrafiche
