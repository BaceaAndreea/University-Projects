--Aufagabe2
SELECT K.Name, COUNT(B.ArztId) AS ANZAHL_BERATUNGEN
FROM Krankenhaus K
JOIN Arzt A ON A.KrankenhausId = K.KrankenhausId
JOIN  Beratung B ON A.ArztId = B.ArztId
GROUP BY K.Name, K.KrankenhausId
HAVING COUNT(*) >=1
ORDER BY COUNT(*) ASC;

------------------------schimbat
SELECT A.Name AS DoctorName, A.Vorname AS DoctorVorname
FROM Arzt A
INNER JOIN Krankenhaus K ON A.KrankenhausId = K.KrankenhausId
WHERE K.Name IN ('Spitalul Judetean', 'Spitalul de Urgenta ','Spitalul Regina Maria')
AND A.ArztId NOT IN(
	SELECT A1.ArztId
	FROM Arzt A1 
	JOIN Beratung B ON A.ArztId = B.ArztId
	JOIN Rezepte_Medikamente RM ON B.RezeptId = RM.RezeptId
	JOIN Medikamente M ON RM.MedikamenteId = M.MedikamentId
	WHERE M.Name = 'Pantozol'
);


SELECT TOP(5) K.Name AS Krankenhaus, COUNT(A.ArztId) AS NrArzte, S.Name AS Spezialisierung
FROM Krankenhaus K
INNER JOIN Arzt A ON A.KrankenhausId = K.KrankenhausId
INNER JOIN Spezialisierung S ON A.SpezialisierungId = S.SpezialisierungId
GROUP BY K.Name, S.Name, K.KrankenhausId, S.SpezialisierungId
HAVING COUNT(A.ArztId) >=1
ORDER BY COUNT(A.ArztId) DESC;


SELECT DISTINCT P.Name AS Name, P.Vorname AS Vorname
FROM Patient P
WHERE P.PatientId IN(
	SELECT  B1.PatientId 
	FROM Beratung B1
	WHERE B1.Datum BETWEEN '2018-01-01' AND '2022-12-31'
	EXCEPT 
	SELECT B2.PatientId
	FROM Beratung B2
	INNER JOIN Arzt A1 ON B2.ArztId = A1.ArztId
	WHERE A1.Name Like 'Daci%'
);


SELECT P.PatientId, P.Name, P.Vorname,  P.Geburtsdatum
FROM Patient P
WHERE P.Geburtsdatum >= ALL(SELECT P1.Geburtsdatum FROM Patient P1)
UNION
SELECT A.ArztId, A.Name, A.Vorname, A.Geburstdatum
FROM Arzt A
WHERE A.Geburstdatum <= ALL( SELECT A1.Geburstdatum FROM Arzt A1);


SELECT P.Name, P.Vorname, P.Geburtsdatum
FROM Patient P
WHERE P.Kontaktelefon LIKE '7%' AND YEAR(P.Geburtsdatum) > 1990
OR P.Name IN ( 
	SELECT P2.Name
	FROM Patient P2
	WHERE P2.Kontaktelefon LIKE '4%'AND YEAR(P2.Geburtsdatum) >1990
);


SELECT A.ArztId, A.Name AS NameDoctor, A.Vorname AS VornameName
FROM Arzt A
WHERE A.ArztId IN (
    SELECT A1.ArztId
    FROM Arzt A1
    INNER JOIN Beratung B ON A1.ArztId = B.ArztId
	INNER JOIN Rezepte R ON B.RezeptId = R.RezeptId
	WHERE R.Dauern_in_Tage >8
)
INTERSECT
SELECT A.ArztId, A.Name AS NameDoctor, A.Vorname AS VornameName
FROM Arzt A
WHERE A.ArztId NOT IN (
    SELECT A2.ArztId
    FROM Arzt A2
    INNER JOIN Beratung B ON A2.ArztId = B.ArztId
	INNER JOIN Rezepte R ON B.RezeptId = R.RezeptId
	INNER JOIN Rezepte_Medikamente RM ON R.RezeptId = RM.RezeptId
	INNER JOIN Medikamente M ON RM.MedikamenteId = M.MedikamentId
	WHERE M.Name = 'Paracetamol'
);


SELECT A.Name AS NameDoctor, A.Vorname AS VornameDoctor, SUM(RM.Menge) AS SumaMedikament
FROM Arzt A
INNER JOIN Beratung B ON A.ArztId = B.ArztId
INNER JOIN Rezepte R ON B.RezeptId = R.RezeptId
INNER JOIN Rezepte_Medikamente RM ON R.RezeptId = RM.RezeptId
GROUP BY A.Name, A.Vorname, A.ArztId
HAVING SUM(RM.Menge) >=20;


SELECT A.Name, A.Vorname
FROM Arzt A
WHERE ArztId = ANY(
	SELECT B.ArztId 
	FROM Beratung B
	WHERE B.Datum BETWEEN '2019-01-01' AND '2022-12-31')
EXCEPT 
SELECT A.Name, A.Vorname
FROM Arzt A
WHERE A.KrankenhausId = (SELECT K.KrankenhausId FROM Krankenhaus K WHERE K.Name LIKE 'Spitalul transilvania');


SELECT  P.Name AS NamePatient, P.Vorname AS VornamePatient, A.Name AS NameDoctor, A.Vorname AS VornameDoctor
FROM Patient P
LEFT JOIN Beratung B ON P.PatientID = B.PatientID
LEFT JOIN Arzt A ON B.ArztID = A.ArztID;

