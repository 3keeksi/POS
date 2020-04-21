-- get average salary for a gender
SELECT AVG(gehalt)
FROM mitarbeiter
WHERE geschlecht = ?;