--aufgabe1.b
INSERT INTO Arzt(ArztId, Name, Vorname, Geburstdatum, Kontakttelefon, SpezialisierungId, KabinetId, KrankenhausId)
SELECT 230, 'Berghean', 'Claudiu', '2001-02-11', 787788765, 1002, 2, 103
FROM Spezialisierung, Kabinette, Krankenhaus
WHERE SpezialisierungId = 1002 AND KabinetId = 2 AND KrankenhausId = 103;

INSERT INTO Medikamente(MedikamentId, Name, Verabreichungsweg, Menge_Stoc)
VALUES (10008, 'Brufen', 'Oral', 70)

INSERT INTO Arzt(ArztId, Name, Vorname, Geburstdatum, Kontakttelefon, SpezialisierungId, KabinetId, KrankenhausId)
VALUES (200, 'Gajdos', 'Radu', '2002-03-09', 7856456, 1005, 6, 103);

--aufgabe1.c
INSERT INTO Arzt(ArztId, Name, Vorname, Geburstdatum, Kontakttelefon, SpezialisierungId, KabinetId, KrankenhausId)
SELECT 190, 'Gajdos', 'Radu', '2002-03-09', 7856456, 1020, 6, 103
FROM Spezialisierung, Kabinette, Krankenhaus
WHERE SpezialisierungId = 1020 AND KabinetId = 6 AND KrankenhausId = 103;

INSERT INTO Arzt(ArztId, Name, Vorname, Geburstdatum, Kontakttelefon, SpezialisierungId, KabinetId, KrankenhausId)
VALUES (190, 'Gajdos', 'Radu', '2002-03-09', 7856456, 1020, 6, 103);

--aufgabe1.d mit update 
UPDATE Arzt
SET Kontakttelefon = '711156778'
WHERE KrankenhausId = 101 AND SpezialisierungId = 1002

UPDATE Arzt
SET Geburstdatum = '1992-03-08'
WHERE Geburstdatum IS NULL;

UPDATE Arzt
SET Kontakttelefon = '786554432'
WHERE KabinetId IN (1,2);

UPDATE Rezepte
SET Dauern_in_Tage = 14
WHERE RezeptId BETWEEN 1202 AND 1204;

UPDATE Patient
SET Name = 'Claudia'
WHERE Name LIKE 'Fu%';

--mit delete
DELETE FROM Beratung
WHERE ArztId = (SELECT ArztId FROM Arzt WHERE Geburstdatum = '2001-10-07' AND KrankenhausId = 107);

DELETE FROM Patient
WHERE Kontaktelefon IS NULL;

DELETE FROM Krankheiten
WHERE KrankheitId IN (2026, 2027);

DELETE FROM Beratung
WHERE Datum BETWEEN '2013-03-09' AND '2015-01-01';

DELETE FROM Beratung
WHERE PatientId = (SELECT PatientId FROM Patient WHERE Name LIKE 'Emines__');
