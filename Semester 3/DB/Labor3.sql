CREATE OR ALTER PROCEDURE Create_All
AS
BEGIN
	CREATE TABLE CurrentVersion (CurrentVersion INT PRIMARY KEY);
	INSERT INTO CurrentVersion (CurrentVersion) VALUES (0);

	CREATE TABLE VersionHistory(
		VersionId INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
		ProcedureName VARCHAR(128)
	);

	CREATE TABLE VersionParams(
		VersionId INT,
		ParamName VARCHAR(128),
		ParamValue VARCHAR(128),
		PRIMARY KEY (VersionId, ParamName),
		FOREIGN KEY (VersionId) REFERENCES VersionHistory(VersionId)
		);
END;
GO

----------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR ALTER PROCEDURE Rollback_Create_All
AS
BEGIN
	DROP TABLE VersionParams;
	DROP TABLE VersionHistory;
	DROP TABLE CurrentVersion;
END;
GO



CREATE OR ALTER PROCEDURE CreateTable(
	@tableName VARCHAR(128),
	@columns VARCHAR(MAX),
	@addToVersionHistory BIT = 1
) AS
BEGIN
	DECLARE @SQL VARCHAR(MAX);
	SET @SQL = ' CREATE TABLE ' + @tableName + ' (' + @columns + ')';
	PRINT @SQL;
	EXEC(@SQL);

	IF @addToVersionHistory = 1
	BEGIN
		INSERT INTO VersionHistory (ProcedureName) VALUES ('CreateTable');
		DECLARE @versionId INT = SCOPE_IDENTITY();

		INSERT INTO VersionParams (VersionId, ParamName, ParamValue) VALUES
			(@versionId, 'tableName', @tableName),
			(@versionId, 'columns', @columns);

		UPDATE CurrentVersion SET CurrentVersion = @versionId;
	END;
END;
GO

CREATE OR ALTER PROCEDURE RevertCreateTable(
	@tableName VARCHAR(128)
) AS
BEGIN
	IF OBJECT_ID(@tableName, 'U') IS NOT NULL
	--u = USER TABLE
	BEGIN
		DECLARE @SQL VARCHAR(MAX);
		SET @SQL = 'DROP TABLE ' + @tableName;
		PRINT @SQL;
		EXEC (@SQL);
	END
END;
GO

----------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR ALTER PROCEDURE AddColumn(
	@tableName VARCHAR(128),
	@columnName VARCHAR(128),
	@columnType VARCHAR(128),
	@addToVersionHistory BIT = 1
) AS
BEGIN
	DECLARE @SQL VARCHAR(MAX);
	SET @SQL = 'ALTER TABLE ' + @tableName + ' ADD ' + @columnName + ' ' + @columnType;
	PRINT @SQL;
	EXEC (@SQL);

	IF @addToVersionHistory = 1
	BEGIN
		INSERT INTO VersionHistory (ProcedureName) VALUES ('AddColumn');
		DECLARE @versionId INT = SCOPE_IDENTITY();

		INSERT INTO VersionParams (VersionId, ParamName, ParamValue) VALUES
			(@versionId, 'tableName', @tableName),
			(@versionId, 'columnName', @columnName),
			(@versionId, 'columnType', @columnType);

		UPDATE CurrentVersion SET CurrentVersion = @versionId;
	END
END;
GO

CREATE OR ALTER PROCEDURE RevertAddColumn(
	@tableName VARCHAR(128),
	@columnName VARCHAR(128)
) AS
BEGIN
	DECLARE @SQL VARCHAR(MAX);
	SET @SQL = 'ALTER TABLE ' + @tableName + ' DROP COLUMN ' + @columnName;
	PRINT @SQL;
	EXEC (@SQL);
END;
GO

----------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR ALTER PROCEDURE ChangeColumnType(
	@tableName VARCHAR(128),
	@columnName VARCHAR(128),
	@newDataType VARCHAR(128),
	@addToVersionHistory BIT = 1
) AS
BEGIN
	DECLARE @oldDataType as VARCHAR(128);
	SET @oldDataType = (SELECT C.DATA_TYPE 
						FROM INFORMATION_SCHEMA.COLUMNS C
						WHERE TABLE_NAME = @tableName AND COLUMN_NAME = @columnName);

	DECLARE @length as VARCHAR(128)
	SET @length = (SELECT C2.CHARACTER_MAXIMUM_LENGTH 
				   FROM INFORMATION_SCHEMA.COLUMNS C2
				   WHERE TABLE_NAME = @tableName AND COLUMN_NAME = @columnName);

	IF @length IS NOT NULL
		SET @oldDataType = @oldDataType + '(' + @length + ')';

	DECLARE @SQL VARCHAR(MAX);
	SET @SQL = 'ALTER TABLE ' + @tableName + ' ALTER COLUMN ' + @columnName + ' ' + @newDataType;
	PRINT @SQL;
	EXEC (@SQL);

	IF @addToVersionHistory = 1
	BEGIN
		INSERT INTO VersionHistory (ProcedureName) VALUES ('ChangeColumnType');
		DECLARE @versionId INT = SCOPE_IDENTITY();

		INSERT INTO VersionParams (VersionId, ParamName, ParamValue) VALUES
			(@versionId, 'tableName', @tableName),
			(@versionId, 'columnName', @columnName),
			(@versionId, 'newDataType', @newDataType),
			(@versionId, 'oldDataType', @oldDataType);

		UPDATE CurrentVersion SET CurrentVersion = @versionId;
	END
END;
GO

CREATE OR ALTER PROCEDURE RevertChangeColumnType(
	@tableName VARCHAR(128),
	@columnName VARCHAR(128),
	@oldDataType VARCHAR(128)
) AS
BEGIN
	DECLARE @SQL VARCHAR(MAX);
	SET @SQL = 'ALTER TABLE ' + @tableName + ' ALTER COLUMN ' + @columnName + ' ' + @oldDataType;
	PRINT @SQL;
	EXEC (@SQL);
END;
GO

----------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR ALTER PROCEDURE CreateDefaultConstraint(
	@tableName VARCHAR(128),
	@columnName VARCHAR(128),
	@defaultValue VARCHAR(128),
	@addToVersionHistory BIT = 1
) AS
BEGIN
	DECLARE @SQL VARCHAR(MAX);
	SET @SQL = 'ALTER TABLE ' + @tableName + ' ADD CONSTRAINT DF_' + @tableName + '_' + 
			   @columnName + ' DEFAULT ''' + @defaultValue + ''' FOR ' + @columnName;
	PRINT @SQL;
	EXEC (@SQL);

	IF @addToVersionHistory = 1
	BEGIN
		INSERT INTO VersionHistory (ProcedureName) VALUES ('CreateDefaultConstraint');
		DECLARE @versionId INT = SCOPE_IDENTITY();

		INSERT INTO VersionParams (VersionId, ParamName, ParamValue) VALUES
			(@versionId, 'tableName', @tableName),
			(@versionId, 'columnName', @columnName),
			(@versionId, 'defaultValue', @defaultValue);

		UPDATE CurrentVersion SET CurrentVersion = @versionId;
	END
END;
GO

CREATE OR ALTER PROCEDURE RevertCreateDefaultConstraint(
	@tableName VARCHAR(128),
	@columnName VARCHAR(128)
) AS
BEGIN
	DECLARE @SQL VARCHAR(MAX);
	SET @SQL = 'ALTER TABLE ' + @tableName + ' DROP CONSTRAINT DF_' + @tableName + '_' + @columnName;
	PRINT @SQL;
	EXEC(@SQL);
END;
GO
----------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR ALTER PROCEDURE AddForeignkeyConstraint(
	@tableName VARCHAR(128),
	@columnName VARCHAR(128),
	@referencedTable VARCHAR(128),
	@referencedColumn VARCHAR(128),
	@addToVersionHistory BIT = 1
) AS
BEGIN
	DECLARE @SQL VARCHAR(MAX);
	SET @SQL = 'ALTER TABLE ' + @tableName + ' ADD CONSTRAINT FK_' + @tableName + '_' + @columnName +
			   ' FOREIGN KEY (' + @columnName + ') REFERENCES ' + @referencedTable + '(' + @referencedColumn + ')';
	PRINT @SQL;
	EXEC(@SQL);

		IF @addToVersionHistory = 1
	BEGIN
		INSERT INTO VersionHistory (ProcedureName) VALUES ('AddForeignkeyConstraint');
		DECLARE @versionId INT = SCOPE_IDENTITY();

		INSERT INTO VersionParams (VersionId, ParamName, ParamValue) VALUES
			(@versionId, 'tableName', @tableName),
			(@versionId, 'columnName', @columnName),
			(@versionId, 'referencedTable', @referencedTable),
			(@versionId, 'referencedColumn', @referencedColumn);

		UPDATE CurrentVersion SET CurrentVersion = @versionId;
	END
END;

GO

CREATE OR ALTER PROCEDURE RevertAddForeignkeyConstraint(
	@tableName VARCHAR(128),
	@columnName VARCHAR(128)
) AS
BEGIN
	DECLARE @SQL VARCHAR(MAX);
	SET @SQL = 'ALTER TABLE ' + @tableName + ' DROP CONSTRAINT FK_' + @tableName + '_' + @columnName;
	PRINT @SQL;
	EXEC(@SQL);
END;
GO


----------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR ALTER PROCEDURE GoToVersion(@targetVersion INT)
AS
BEGIN
	DECLARE @currentVersion INT;
	DECLARE @procedureName VARCHAR(128);
	DECLARE @tableName VARCHAR(128);
	DECLARE @columns VARCHAR(128);
	DECLARE @columnName VARCHAR(128);
	DECLARE @columnType VARCHAR(128);
	DECLARE @newDataType VARCHAR(128);
	DECLARE @oldDataType VARCHAR(128);
	DECLARE @defaultValue VARCHAR(128);
	DECLARE @referencedTable VARCHAR(128);
	DECLARE @referencedColumn VARCHAR(128);

	SELECT @currentVersion = CurrentVersion FROM CurrentVersion;

	IF @targetVersion >= 0 AND @currentVersion > @targetVersion
	BEGIN
		WHILE @currentVersion > @targetVersion
		BEGIN
			SELECT @procedureName = ProcedureName 
			FROM VersionHistory 
			WHERE VersionId = @currentVersion;

			SELECT @tableName = ParamValue 
			FROM VersionParams 
			WHERE VersionId = @currentVersion AND ParamName = 'tableName';

			SELECT @columnName = ParamValue 
			FROM VersionParams 
			WHERE VersionId = @currentVersion AND ParamName = 'columnName';

			SELECT @oldDataType = ParamValue 
			FROM VersionParams 
			WHERE VersionId = @currentVersion AND ParamName = 'oldDataType';

			DECLARE @rollBackProcedure VARCHAR(128) = 'Revert' + @procedureName;

			IF @procedureName = 'CreateTable'
				BEGIN
					EXEC @rollBackProcedure @tableName;
				END
			ELSE IF @procedureName = 'AddColumn'
				BEGIN
					EXEC @rollBackProcedure @tableName, @columnName;
				END
			ELSE IF @procedureName = 'ChangeColumnType'
				BEGIN
					EXEC @rollBackProcedure @tableName, @columnName, @oldDataType;
				END
			ELSE IF @procedureName = 'CreateDefaultConstraint'
				BEGIN
					EXEC @rollBackProcedure @tableName, @columnName;
				END
			ELSE IF @procedureName = 'AddForeignkeyConstraint'
				BEGIN
					EXEC @rollBackProcedure @tableName, @columnName;
				END

			 
			SET @currentVersion = @currentVersion - 1;
			UPDATE CurrentVersion SET CurrentVersion = @currentVersion;
		END
	END

	ELSE IF @currentVersion < @targetVersion AND @targetVersion <= (SELECT MAX(VersionID) FROM VersionHistory)
	BEGIN
		WHILE @currentVersion < @targetVersion
		BEGIN
			SELECT @procedureName = ProcedureName 
			FROM VersionHistory 
			WHERE VersionId = (@currentVersion + 1);

			SELECT @tableName = ParamValue 
			FROM VersionParams 
			WHERE VersionId = (@currentVersion + 1) AND ParamName = 'tableName';

			SELECT @columns = ParamValue 
			FROM VersionParams 
			WHERE VersionId = (@currentVersion + 1) AND ParamName = 'columns';

			SELECT @columnName = ParamValue 
			FROM VersionParams 
			WHERE VersionId = (@currentVersion + 1) AND ParamName = 'columnName';

			SELECT @columnType = ParamValue 
			FROM VersionParams 
			WHERE VersionId = (@currentVersion + 1) AND ParamName = 'columnType';

			SELECT @referencedTable = ParamValue 
			FROM VersionParams 
			WHERE VersionId = (@currentVersion + 1) AND ParamName = 'referencedTable';

			SELECT @referencedColumn = ParamValue 
			FROM VersionParams 
			WHERE VersionId = (@currentVersion + 1) AND ParamName = 'referencedColumn';

			SELECT @defaultValue = ParamValue 
			FROM VersionParams 
			WHERE VersionId = (@currentVersion + 1) AND ParamName = 'defaultValue';

			SELECT @newDataType = ParamValue FROM VersionParams WHERE VersionId = (@currentVersion + 1) AND ParamName = 'newDataType';

			IF @procedureName = 'CreateTable'
				BEGIN
					EXEC @procedureName @tableName, @columns, 0;
				END
			ELSE IF @procedureName = 'AddColumn'
				BEGIN
					EXEC @procedureName @tableName, @columnName, @columnType, 0;
				END
			ELSE IF @procedureName = 'ChangeColumnType'
				BEGIN
					EXEC @procedureName @tableName, @columnName, @newDataType, 0;
				END
			ELSE IF @procedureName = 'CreateDefaultConstraint'
				BEGIN
					EXEC @procedureName @tableName, @columnName, @defaultValue, 0;
				END
			ELSE IF @procedureName = 'AddForeignkeyConstraint'
				BEGIN
					EXEC @procedureName @tableName, @columnName, @referencedTable, @referencedColumn, 0;
				END
			
			SET @currentVersion = @currentVersion + 1;
			UPDATE CurrentVersion SET CurrentVersion = @currentVersion;
		END
	END
END;



EXEC Create_All;
EXEC Rollback_Create_All;

EXEC CreateTable 'Geschaft', 'Name VARCHAR(128) PRIMARY KEY, Gekauft int';
SELECT * FROM CurrentVersion;
SELECT * FROM VersionHistory;
EXEC AddColumn 'Geschaft', 'NameStandort', 'VARCHAR(200)';
SELECT * FROM CurrentVersion;
SELECT * FROM VersionHistory;
EXEC ChangeColumnType 'Geschaft', 'NameStandort', 'NVARCHAR(100)';
SELECT * FROM CurrentVersion;
SELECT * FROM VersionHistory;
EXEC CreateDefaultConstraint 'Geschaft', 'Gekauft', 0;
EXEC CreateDefaultConstraint 'Geschaft', 'Name', test;
SELECT * FROM CurrentVersion;
SELECT * FROM VersionHistory;
SELECT * FROM VersionParams;
EXEC CreateTable 'Standort', 'NameStandort VARCHAR(100) PRIMARY KEY, rating int '; 
SELECT * FROM CurrentVersion;
SELECT * FROM VersionHistory;
EXEC AddColumn 'Geschaft', 'Variablia', 'VARCHAR(100)';
EXEC AddForeignkeyConstraint 'Geschaft', 'Variablia', 'Standort', 'NameStandort';
SELECT * FROM CurrentVersion;
SELECT * FROM VersionHistory;


EXEC RevertCreateTable 'Geschaft';
EXEC RevertCreateTable 'Standort';


EXEC GoToVersion 5;
EXEC GoToVersion 4;
EXEC GoToVersion 3;
EXEC GoToVersion 1;
EXEC GoToVersion 2;
EXEC GoToVersion 8;
EXEC GoToVersion 6;
EXEC GoToVersion 0;

