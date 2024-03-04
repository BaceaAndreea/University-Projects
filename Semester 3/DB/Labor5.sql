use model

CREATE TABLE Tb (
	idB INT PRIMARY KEY,
	b2 INT,
	b3 INT
)

CREATE TABLE Ta (
	idA INT PRIMARY KEY,
	a2 INT UNIQUE,
	a3 INT
)

CREATE TABLE Tc (
	idC INT PRIMARY KEY,
	idA INT,
	idB INT

	FOREIGN KEY (idA) REFERENCES Ta(idA),
    FOREIGN KEY (idB) REFERENCES Tb(idB)
)

DROP TABLE Ta
DROP TABLE Tb
DROP TABLE Tc

CREATE OR ALTER PROCEDURE insertDataIntoTables
AS
BEGIN
	DECLARE @cnt INT;
	SET @cnt = 1;

	WHILE @cnt <= 10000
	BEGIN
		INSERT INTO Ta(idA, a2, a3)
		VALUES (@cnt, @cnt * 2, @cnt * 4);
		SET @cnt = @cnt + 1;
	END

	SET @cnt = 1;
	WHILE @cnt <= 3000
	BEGIN
		INSERT INTO Tb(idB, b2, b3)
		VALUES (@cnt, @cnt * 5, @cnt * 3);
		SET @cnt = @cnt + 1;
	END

	SET @cnt = 1;
	DECLARE @id_A INT = 1;
	DECLARE @id_B INT = 1;

	WHILE @cnt <= 30000
	BEGIN

		IF @id_A = 10001
		BEGIN
			SET @id_A = 1;
		END

		IF @id_B = 3001
		BEGIN
			SET @id_B = 1;
		END

		INSERT INTO Tc(idC, idA, idB)
		VALUES (@cnt, @id_A, @id_B);
		SET @cnt = @cnt + 1;
		SET @id_A = @id_A + 1;
		SET @id_B = @id_B + 1;
	END
END

EXEC insertDataIntoTables
DROP PROCEDURE insertDataIntoTables

SELECT * FROM Tc
SELECT * FROM Ta
SELECT * FROM Tb

SELECT COUNT(*) AS TA FROM Ta;
SELECT COUNT(*) AS Tb FROM Tb;
SELECT COUNT(*) AS Tc FROM Tc;


SELECT *
FROM sys.indexes
WHERE object_id = OBJECT_ID('Ta')


SELECT * FROM Ta WHERE a3> 10;

SELECT * FROM Ta WHERE idA = 10;

select a2 from Ta
order by a2 desc

SELECT * FROM Ta WHERE a2 > 14;


-------------------------------------------------------------------------------------
SELECT * FROM Ta WHERE a2 =35;

------------------------------------------------------------------------
--c
SELECT *
FROM Tb
WHERE b2 = 50;

CREATE NONCLUSTERED INDEX IX_b2 ON Tb(b2);


DROP INDEX IX_b2 ON Tb;

SELECT *
FROM sys.indexes
WHERE object_id = OBJECT_ID('Tb')
-------------------------------------------------------------------------------------------------
--d


SELECT c.idC
FROM Tc c
JOIN Ta a ON c.idA = a.idA
where a.idA = 56

SELECT c.idC
FROM Tc c
JOIN Tb b ON c.idB= b.idB
where b.b2 = 1007

CREATE INDEX IX_Tc_idA ON Tc(idA);
CREATE INDEX IX_Tc_idB ON Tc(idB);

DROP INDEX IX_Tc_idA ON Tc
DROP INDEX IX_Tc_idB ON Tc



-----------------------------------------------------------
SELECT * FROM Ta WHERE a2 BETWEEN 35 AND 63



