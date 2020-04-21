-- creates the mitarbeiter table
CREATE TABLE mitarbeiter (
	pers_nr INTEGER PRIMARY KEY,
	name VARCHAR(40) NOT NULL,
	vorname VARCHAR(40) NOT NULL,
	geb_datum DATE,
	gehalt NUMERIC(7,2),
	abt_nr INTEGER NOT NULL,
	geschlecht CHAR(1) NOT NULL
);