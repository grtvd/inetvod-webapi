--//////////////////////////////////////////////////////////////////////////////
-- Copyright © 2006 iNetVOD, Inc. All Rights Reserved.
-- iNetVOD Confidential and Proprietary.  See LEGAL.txt.
--//////////////////////////////////////////////////////////////////////////////

use [iNetVOD]
GO

--//////////////////////////////////////////////////////////////////////////////

update rating set ratingid = 'tvy', name = 'TV-Y' where ratingid = 'tv7'

--//////////////////////////////////////////////////////////////////////////////

BEGIN TRANSACTION
SET QUOTED_IDENTIFIER ON
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE
SET ARITHABORT ON
SET NUMERIC_ROUNDABORT OFF
SET CONCAT_NULL_YIELDS_NULL ON
SET ANSI_NULLS ON
SET ANSI_PADDING ON
SET ANSI_WARNINGS ON
COMMIT

--//////////////////////////////////////////////////////////////////////////////

BEGIN TRANSACTION
ALTER TABLE dbo.MemberPrefs
	DROP CONSTRAINT FK_MemberPrefs_Member
GO
COMMIT
BEGIN TRANSACTION
CREATE TABLE dbo.Tmp_MemberPrefs
	(
	MemberID uniqueidentifier NOT NULL ROWGUIDCOL,
	IncludeAdult varchar(32) NULL,
	AdultPIN varchar(32) NULL,
	IncludeRatingIDList varchar(128) NULL,
	IncludeDownload bit NULL,
	IncludeStreaming bit NULL,
	ConnectionSpeed varchar(32) NULL
	)  ON [PRIMARY]
GO
IF EXISTS(SELECT * FROM dbo.MemberPrefs)
	 EXEC('INSERT INTO dbo.Tmp_MemberPrefs (MemberID, IncludeAdult, AdultPIN, IncludeRatingIDList, IncludeDownload, IncludeStreaming, ConnectionSpeed)
		SELECT MemberID, IncludeAdult, AdultPIN, IncludeRatingIDList, IncludeDownload, IncludeStreaming, ConnectionSpeed FROM dbo.MemberPrefs TABLOCKX')
GO
DROP TABLE dbo.MemberPrefs
GO
EXECUTE sp_rename N'dbo.Tmp_MemberPrefs', N'MemberPrefs', 'OBJECT'
GO
ALTER TABLE dbo.MemberPrefs ADD CONSTRAINT
	PK_MemberPrefs PRIMARY KEY CLUSTERED 
	(
	MemberID
	) ON [PRIMARY]

GO
ALTER TABLE dbo.MemberPrefs WITH NOCHECK ADD CONSTRAINT
	FK_MemberPrefs_Member FOREIGN KEY
	(
	MemberID
	) REFERENCES dbo.Member
	(
	MemberID
	) ON UPDATE CASCADE
	 ON DELETE CASCADE
	
GO
COMMIT

--//////////////////////////////////////////////////////////////////////////////

BEGIN TRANSACTION
ALTER TABLE dbo.MemberLogon
	DROP CONSTRAINT FK_MemberLogon_Member
GO
COMMIT
BEGIN TRANSACTION
ALTER TABLE dbo.MemberLogon
	DROP CONSTRAINT DF__MemberLog__Logon__1E704FB5
GO
ALTER TABLE dbo.MemberLogon
	DROP CONSTRAINT DF__MemberLog__Logon__1F6473EE
GO
CREATE TABLE dbo.Tmp_MemberLogon
	(
	MemberID uniqueidentifier NOT NULL ROWGUIDCOL,
	EmailKey varchar(64) NOT NULL,
	Email varchar(64) NOT NULL,
	Password varchar(32) NOT NULL,
	LogonID int NOT NULL IDENTITY (100000200, 1),
	PIN varchar(32) NULL,
	SecretQuestion varchar(64) NOT NULL,
	SecretAnswer varchar(32) NOT NULL,
	TermsAcceptedOn datetime NOT NULL,
	TermsAcceptedVersion varchar(16) NOT NULL,
	LogonFailedAt datetime NULL,
	LogonFailedCount tinyint NOT NULL,
	LogonDisabled bit NOT NULL
	)  ON [PRIMARY]
GO
ALTER TABLE dbo.Tmp_MemberLogon ADD CONSTRAINT
	DF__MemberLog__Logon__1E704FB5 DEFAULT (0) FOR LogonFailedCount
GO
ALTER TABLE dbo.Tmp_MemberLogon ADD CONSTRAINT
	DF__MemberLog__Logon__1F6473EE DEFAULT (0) FOR LogonDisabled
GO
SET IDENTITY_INSERT dbo.Tmp_MemberLogon ON
GO
IF EXISTS(SELECT * FROM dbo.MemberLogon)
	 EXEC('INSERT INTO dbo.Tmp_MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion, LogonFailedAt, LogonFailedCount, LogonDisabled)
		SELECT MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, CONVERT(varchar(32), SecretAnswer), TermsAcceptedOn, TermsAcceptedVersion, LogonFailedAt, LogonFailedCount, LogonDisabled FROM dbo.MemberLogon TABLOCKX')
GO
SET IDENTITY_INSERT dbo.Tmp_MemberLogon OFF
GO
DROP TABLE dbo.MemberLogon
GO
EXECUTE sp_rename N'dbo.Tmp_MemberLogon', N'MemberLogon', 'OBJECT'
GO
ALTER TABLE dbo.MemberLogon ADD CONSTRAINT
	PK_MemberLogon PRIMARY KEY CLUSTERED 
	(
	MemberID
	) ON [PRIMARY]

GO
CREATE UNIQUE NONCLUSTERED INDEX IX_MemberLogon_Email ON dbo.MemberLogon
	(
	EmailKey
	) ON [PRIMARY]
GO
CREATE UNIQUE NONCLUSTERED INDEX IX_MemberLogon_LogonID ON dbo.MemberLogon
	(
	LogonID
	) ON [PRIMARY]
GO
ALTER TABLE dbo.MemberLogon WITH NOCHECK ADD CONSTRAINT
	FK_MemberLogon_Member FOREIGN KEY
	(
	MemberID
	) REFERENCES dbo.Member
	(
	MemberID
	) ON UPDATE CASCADE
	 ON DELETE CASCADE
	
GO
COMMIT

--//////////////////////////////////////////////////////////////////////////////
--//////////////////////////////////////////////////////////////////////////////
