CREATE FUNCTION ValidateArztId(@arztId INT)
RETURNS BIT
AS
BEGIN
	DECLARE @isValid BIT = 0;
	IF @arztId <> 0
		SET @isValid = 1;
	RETURN @isValid;
END
GO

CREATE FUNCTION ValidateAge(@geburstdatum DATE)
RETURNS BIT
AS
BEGIN
	DECLARE @isValid BIT = 0;
	IF @geburstdatum > '1980-01-01'
		SET @isValid =1;
	RETURN @isValid;
END
GO

CREATE FUNCTION ValidateKrankenhaus(@krankenhausId INT)
RETURNS BIT
AS
BEGIN
	DECLARE @isValid BIT = 0;
	IF @krankenhausId BETWEEN 101 AND 109
		SET @isValid =1;
	RETURN @isValid;
END
GO



CREATE FUNCTION ValidateSpezializierung(@spezialiserungsId INT)
RETURNS BIT
AS
BEGIN
	DECLARE @isValid BIT = 1;
	IF EXISTS (
		SELECT 1 
		FROM Spezialisierung
		WHERE SpezialisierungId = @spezialiserungsId AND Name LIKE 'Endocrinologie'
		--1008 specializarea
	) BEGIN
		SET @isValid =0;
	END
	RETURN @isValid;
END
GO


CREATE PROCEDURE InsertIntoArztTable
	@ArztId INT,
	@Name VARCHAR(20),
	@Vorname VARCHAR(20),
	@Geburstdatum DATE,
	@Kontakttelefon NUMERIC(18,0),
	@SpezialisierungsId INT,
	@KabinetId INT,
	@KrankenhausId INT

AS
BEGIN
	IF dbo.ValidateArztId(@ArztId) = 0
	BEGIN
		PRINT 'Invalider Arzt Id!';
		RETURN;
	END

	IF dbo.ValidateAge(@Geburstdatum) = 0
	BEGIN
		PRINT 'Invalide Geburstdatum!';
		RETURN;
	END

	IF dbo.ValidateKrankenhaus(@KrankenhausId) = 0
	BEGIN
		PRINT 'Invalider Krankenhaus!';
		RETURN;
	END

	IF dbo.ValidateSpezializierung(@SpezialisierungsId) = 0
	BEGIN
		PRINT 'Invalide Spezialiserung!';
		RETURN;
	END

	INSERT INTO Arzt(ArztId, Name, Vorname, Geburstdatum,Kontakttelefon,SpezialisierungId,KabinetId,KrankenhausId)
	VALUES (@ArztId, @Name, @Vorname, @Geburstdatum, @Kontakttelefon, @SpezialisierungsId, @KabinetId, @KrankenhausId)

	PRINT 'Der Arzt wurde eingefugt!';
END 
GO

DROP PROCEDURE InsertIntoArztTable

EXEC InsertIntoArztTable
	@ArztId =104,
	@Name = 'Gabriel',
	@Vorname = 'Gabriel',
	@Geburstdatum = '1990-09-09',
	@Kontakttelefon = '7544566789',
	@SpezialisierungsId = 1002,
	@KabinetId = 7,
	@KrankenhausId =101;

SELECT COUNT(*) AS NR
FROM Arzt A



EXEC InsertIntoArztTable
	@ArztId =102,
	@Name = 'John',
	@Vorname = 'John',
	@Geburstdatum = '1987-09-09',
	@Kontakttelefon = '7544566654',
	@SpezialisierungsId = 1008,
	@KabinetId = 8,
	@KrankenhausId =101;

EXEC InsertIntoArztTable
	@ArztId =103,
	@Name = 'Johny',
	@Vorname = 'Johny',
	@Geburstdatum = '1970-09-09',
	@Kontakttelefon = '7544599789',
	@SpezialisierungsId = 1002,
	@KabinetId = 9,
	@KrankenhausId =109;

GO
	-----------------------------------------UBUNG 2 ----------------------------------------------

CREATE VIEW vw_BeratungInfo
AS
SELECT
	B.PatientId,
	P.Name AS NamePacient,
	P.Vorname AS VornamePacient,
	B.ArztId,
	A.Name AS NameArzt,
	A.Vorname AS VornameArzt,
	B.Datum,
	K.Name AS NameKrankheit,
	R.RezeptId
FROM Beratung B
JOIN Patient P ON B.PatientId = P.PatientId
JOIN Arzt A ON B.ArztId = A.ArztId
JOIN Krankheiten K ON B.KrankheitId = K.KrankheitId
JOIN Rezepte R ON B.RezeptId = R.RezeptId;
GO


CREATE FUNCTION GetMedikamenteInfo(@rezepteId INT)
RETURNS TABLE 
AS
RETURN 
(
	SELECT 
		RM.MedikamenteId,
		M.Name AS MedikamentName,
		RM.Menge
	FROM Rezepte_Medikamente RM
	JOIN Medikamente M ON RM.MedikamenteId = M.MedikamentId
	WHERE RM.RezeptId =@rezepteId
);
GO


DECLARE @targetRezeptId INT =1201;
SELECT
	BI.PatientId,
	BI.NamePacient,
	BI.VornamePacient,
	BI.ArztId,
	BI.NameArzt,
	BI.VornameArzt,
	BI.Datum, 
	BI.NameKrankheit,
	MD.MedikamentName,
	MD.Menge

FROM vw_BeratungInfo BI
INNER JOIN GetMedikamenteInfo(@targetRezeptId) MD ON BI.RezeptId = @targetRezeptId
WHERE BI.RezeptId = @targetRezeptId;

SELECT * FROM vw_BeratungInfo;



--------------------------------------UBUNG 3-----------------------------------------
CREATE TABLE LogTable (
	LogId INT PRIMARY KEY IDENTITY(1,1),
	RunTime DATETIME,
	CommandType CHAR(1),
	TableName VARCHAR(50),
	AffectedRows INT
);
GO


CREATE TRIGGER ArztTrigger
ON Arzt
AFTER INSERT, UPDATE, DELETE
AS 
BEGIN
	declare @nrrow as int
	set @nrrow = @@ROWCOUNT

	IF EXISTS (SELECT * FROM inserted) AND  EXISTS (SELECT * FROM deleted)
	BEGIN
		INSERT INTO LogTable(RunTime,CommandType,TableName,AffectedRows)
		SELECT DISTINCT GETDATE(), 'U', 'Arzt', @nrrow
	END

	IF EXISTS (SELECT * FROM inserted) AND  NOT EXISTS (SELECT * FROM deleted)
	BEGIN
		INSERT INTO LogTable(RunTime,CommandType,TableName,AffectedRows)
		SELECT DISTINCT GETDATE(), 'I', 'Arzt', @nrrow
		FROM inserted
	END

	IF EXISTS (SELECT * FROM deleted) AND  NOT EXISTS (SELECT * FROM inserted)
	BEGIN
		INSERT INTO LogTable(RunTime,CommandType,TableName,AffectedRows)
		SELECT DISTINCT GETDATE(), 'D', 'Arzt', @nrrow
		FROM deleted
	END
END;

DROP TRIGGER ArztTrigger
		
INSERT INTO Arzt (ArztId, Name, Vorname, Geburstdatum, Kontakttelefon, SpezialisierungId, KabinetId, KrankenhausId)
VALUES (103,'John', 'Doee', '1990-01-01', '123456789', 1007, 7, 108),
	(106,'John', 'Doee', '1990-01-01', '123456789', 1007, 7, 108);;


UPDATE Arzt
SET Name = 'UpdatedName'
WHERE ArztID =103;

DELETE FROM Arzt WHERE ArztID IN (103,106);

SELECT * FROM Arzt;
SELECT COUNT(*) FROM Arzt;

SELECT * FROM LogTable;
DROP TABLE LogTable

GO
-----------------------------------------------UBUNG 4----------------------------------------------------------
CREATE PROCEDURE updateBeratungPrice
	@ArztID INT,
	@PatientID INT,
	@Datum DATETIME,
	@KrankheitID INT,
	@RezeptID INT,
	@Preis INT
AS
BEGIN
	DECLARE @Age INT;

	SELECT @Age = DATEDIFF(YEAR, Geburtsdatum, GETDATE())
	FROM Patient
	WHERE PatientID = @PatientID;

	IF @Age > 60
	BEGIN
		UPDATE Beratung
		SET  Preis = 0
		WHERE ArztId = @ArztID AND PatientId = @PatientID AND Datum = @Datum AND KrankheitId = @KrankheitID AND RezeptId = @RezeptID;

		PRINT 'Pacientul cu ID ' + CAST(@PatientID AS NVARCHAR(10)) + ' a primit consultatia gratuita. '
	END;
END;

DECLARE @ArztID INT, @PatientID INT, @Datum DATETIME, @KrankheitID INT, @RezeptID INT, @Preis INT;

DECLARE BeratungCursor CURSOR FOR
SELECT B.ArztId, B.PatientId, B.Datum, B.KrankheitId, B.RezeptId, B.Preis
FROM Beratung B

OPEN BeratungCursor;
FETCH NEXT FROM BeratungCursor INTO @ArztID, @PatientID, @Datum, @KrankheitID, @RezeptID, @Preis;

WHILE @@FETCH_STATUS = 0   
BEGIN

	EXEC updateBeratungPrice @ArztID, @PatientID, @Datum, @KrankheitID, @RezeptID, @Preis;

	FETCH NEXT FROM BeratungCursor INTO @ArztID, @PatientID, @Datum, @KrankheitID, @RezeptID, @Preis;
END

CLOSE BeratungCursor;
DEALLOCATE BeratungCursor;

SELECT * FROM Beratung;

DROP PROCEDURE updateBeratungPrice;