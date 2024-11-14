-- Table: public.articoli

-- DROP TABLE IF EXISTS public.articoli;
CREATE TABLE IF NOT EXISTS public.articoli
(
    codart character varying(20) COLLATE pg_catalog."default" NOT NULL,
    descrizione character varying(60) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    um character(2) COLLATE pg_catalog."default" DEFAULT NULL::bpchar,
    codstat character varying(20) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    pzcart smallint,
    pesonetto double precision,
    idiva integer,
    idstatoart character(1) COLLATE pg_catalog."default" DEFAULT NULL::bpchar,
    datacreazione date,
    idfamass integer,
    CONSTRAINT articoli_pkey PRIMARY KEY (codart)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.articoli
    OWNER to postgres;
-- Index: idx_descrizione

-- DROP INDEX IF EXISTS public.idx_descrizione;

CREATE INDEX IF NOT EXISTS idx_descrizione
    ON public.articoli USING btree
    (descrizione COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;







-- Table: public.ingredienti

-- DROP TABLE IF EXISTS public.ingredienti;

CREATE TABLE IF NOT EXISTS public.ingredienti
(
    codart character varying(20) COLLATE pg_catalog."default" NOT NULL,
    info character varying(300) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT ingredienti_pkey PRIMARY KEY (codart)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.ingredienti
    OWNER to postgres;




-- POPOLO TABELLA ARTICOLI
INSERT INTO public.articoli(
	codart, descrizione, um, codstat, pzcart, pesonetto, idiva, idstatoart, datacreazione, idfamass)
	VALUES 
	('7999182',		'GHIACCIO 2LT IS MORI'				,	'PZ',	'          ',	6	,	2	,	22	  ,   '1' ,	     '2015-07-03' ,	   40     ),
	('80066987',	'Pane Fresco F.lli Rossi'			,	'KG',	'null',			0	,	0.1	,	4	   ,  '1' ,	     '2018-02-17' ,	   25),
	('SOND02',		'NO'								,	'PZ',	'          ',	1	,	0	,	0	   ,  '1' ,	     '2017-06-12' ,	   90),
	('000001501',	'KIMBO CAFFE AROMA ITALIA.GR.250X2'	,   'PZ',	'          ',	10	,	0.5	,	22	  ,   '1' ,	     '2011-07-20' ,	   1),
	('000001502',	'OROGEL INSALATA RUSSA 450 GR'		,	'PZ',	'          ',	12	,	0.45,	4	   ,  '1' ,	     '2010-06-14' ,	   40),
	('000001503',	'ARBOREA PANEDDA'					,	'KG',	'          ',	1	,	1	,    4	,     '2' ,	     '2010-06-14' ,	   15),
	('000016103',	'REBECCHI CIOCCOLATO CCE G.600'		,   'PZ',	'          ',	0.6	,	10	,	1    ,    '3' ,	     '2010-06-14' ,      1),
	('000016301',	'REBECCHI CODETTE ARCOBALENO GR.650',	'PZ',	'          ',	0.65,	10	,    1 ,      '3' ,	     '2010-06-14' ,      1),
	('000016601',	'ALICE SEPPIA PUL.INDIA IQF 8/12'	,	'KG',	'          ',	0	,	1	,    10,	  '3' ,	     '2010-06-14' ,	   40),
	('000016602',	'ALICE SEPPIA PUL.INDIA IQF 50/100'	,   'KG',	'          ',	0	,	1	,    10,	  '1' ,	     '2013-03-29' ,      90),
	('000016644',	'SEVADAS FRESCHE X 6 GR.400'		,	'Pz',	'          ',	0	,    10	,    1 ,      '3' ,	     '2010-06-14' ,	    0),
	('000016901',	'PUDDU PASTA STELLINE N.27 GR.500'	,	'PZ',	'null',			0	,	0	,    4	,     '1' ,	     '2010-06-14' ,	    1),
	('000016902',	'PUDDU PASTA PUNTINE N.23 GR.500'	,	'PZ',	'          ',	24	,	0.5	,    4	,     '1' ,	     '2010-06-14' ,	    1),
	('000016903',	'PUDDU PASTA FILINI N.14 GR.500'	,	'PZ',	'          ',	24	,	0.5	,    4	,     '1' ,	     '2010-06-14' ,	    1),
	('000016904',	'PUDDU PASTA TEMPESTINE N.21 GR.500',	'PZ',	'          ',	24	,	0.5	,    4	,     '1' ,	     '2010-06-14' ,	    1);



-- POPOLO TABELLA INGREDIENTI
INSERT INTO public.ingredienti(
	codart, info)
	VALUES
('7999182',			'INGREDIENTI:carne(pancetta) di suino 94%,acqua, sale, destrosio, aromi, aroma di affumicatura, stabilizzante:carragenina; antiossidante:eritorbato di sodio; conservante:nitrito di sodio. Affumicatura naturale. **SENZA LATTOSIO, GLUTTAMATO NE GLUTINE **'),
('80066987',	'LATTE, SALE, CAGLIO, FERMENTI LATTICI.'),
('SOND02',	    	'PETTO DI TACCHINO,ACQUA,SALE,ZUCCHERI:(DESTROSIO,SACCAROSIO),AROMI,SPEZIE.ANTIOSSIDANTI: E316.CONSERVANTI:E250CONSERVARE IN FRI FRA 0 E +4 GRADI.'),
('000001501',		'INGR: COSCIA DI SUINO,SALE,SACCAROSIO,SPEZIE, AROMI NATURALI. CONSERVANTI: E250,E252. CONSERVARE FRA 2 E +4 GRADI.'),
('000001502',		'INGR: LATTE,SALE,CAGLIO. FERMENTI LATTICI.'),
('000001503',		'LATTE DI PECORA, CAGLIO, SALE. CROSTA NONN EDIBILE. TRATTATO IN CROSTA CON OLIO DI OLIVA. DA CONSERVARE AL FRESCO MAX +6/15 °C'),
('000016103',		'INGR:LATTE DI CAPRA,SALE,CAGLIO TRATTATO IN SUPERFICIE CON CONSERVANTE E235. CROSTA NON EDIBILE.'),
('000016301',		'LATTE, SALE, CAGLIO, FERMENTI LATTICI.'),
('000016601',		'Latte e latte di pecora termizzati, sale, caglio e fermenti lattici selezionati'),
('000016602',		'LATTE, SALE, CAGLIO, FERMENTI LATTICI, CONSERVANTI IN SUPERFICIE: E203-E235, AROMA: AROMATIZZANTE DI AFFUMICATURA, CROSTA NON EDIBILE'),
('000016644',		'INGR:TRIPPINO DI SUINO, MAGROS UINO DI TESTA, ACQUA, CRNE DI SUINO SEPARATA MECCANICAMENTE, VINO, SALE, AROMI, PISTACCHI (0,4%), ZUCCHERO, ESALTATORE DI SAPIDITA:GLUTAMMATO MONOSODICO, SPEZIE, ANTIOSSIDANTE:ASCORBATO DI SODIO, CONSERVANTE:NITRITO DI SODIO. SENZA GLUTINE, SENZA DERIVATI DEL LATTE'),
('000016901',		'CARNE (COSCIA) DI SUINO 85%, ACQUA, SALE, DESTROSIO, AROMI, AROMA DI AFFUMICATURA, STABILIZZANTI: CARRAGENINA, E451, ANTIOSSIDANTE: ASCORBATO DI SODIO, CONSERVANTE: NITRITO DI SODIO, AFFUMICATURA NATURALE.'),
('000016902',		'INGREDIENTI:CARNE SUINA, SALE, DESTROSIO, SPEZIE, AROMI NATURALI, ANTIOSSIDANTE E301, CONSERVANTI E252 E250. NON CONTIENE FONTI DI GLUTINE'),
('000016903',		'PEPE NERO MACINATO'),
('000016904',		'INGR:CARNE SUINA,SALE. CONSERVARE IN FRI TRA 0 E 4 GRADI.')
;



select * from articoli, ingredienti where articoli.codart = ingredienti.codart;