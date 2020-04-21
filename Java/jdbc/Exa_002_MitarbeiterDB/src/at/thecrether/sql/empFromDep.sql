-- get the employees from a specific department (?) will be replaced in the program
SELECT *
FROM mitarbeiter
WHERE abt_nr = ?
ORDER BY name, vorname;