INSERT INTO public.medici(
	codice, cognome, nome, citta)
	VALUES 
	('abcd1', 'Beltrame', 'Marco', 'Rovigo'),
	('abcd2', 'Pierin', 'Annalisa', 'Rovigo'),
	('abcd3', 'Smacchia', 'Alessandro', 'Trento'),
	('abcd4', 'Cicalone', 'Simone', 'Roma'),
	('abcd5', 'Biondi', 'Diletta', 'Catania')
	;


INSERT INTO public.pazienti(
	codice_fiscale, cognome, nome, medico)
	VALUES 

	-- Pazienti visitati dal medico abcd1:
	('AAADDD33C33C333C', 'Piazza', 'Joemy', 'abcd1'),
	('AAAEEE44D44D444D', 'Donetti', 'Marta', 'abcd1'),
	('AAAFFF55E55E555E', 'Alborghetti', 'Lorenzo', 'abcd1'),


	-- Pazienti visitati dal medico abcd2:
	('AAABBB11A11A111A', 'Beltrame', 'Moira', 'abcd2'),
	('AAACCC22B22B222B', 'Marchesini', 'Anna', 'abcd2'),


	-- -- Pazienti visitati dal medico abcd3
	('BBBCCC44C44C444C', 'Mischiatti', 'Elena', 'abcd3'),
	('CCCDDD55E55E555E', 'Ferrari', 'Giacomo', 'abcd3'),


    -- Pazienti visitati dal medico abcd4:
    ('XXXGGG66F66F666F', 'Rossi', 'Mario', 'abcd4'),
    ('YYYMMM77G77G777G', 'Verdi', 'Sara', 'abcd4'),
    ('ZZZNNN88H88H888H', 'Bruni', 'Sofia', 'abcd4'),
    ('TTTOOO99I99I999I', 'Lupi', 'Emma', 'abcd4'),
    ('UUUPPP11L11L111L', 'Bianchi', 'Lisetta', 'abcd4'),
    ('VVVQQQ22M22M222M', 'Mori', 'Fabrizio', 'abcd4'),


	-- Pazienti visitati dal medico abcd5:
	('AAAGGG66F66F666F', 'Zambrini', 'Mario', 'abcd5'),
	('AAAHHH77G77G777G', 'Moscato', 'Giuliana', 'abcd5'),
	('AAAIII88I88I888I', 'Romagnollo', 'Fasil', 'abcd5')
	;



-- select * from medici;

-- select * from pazienti;