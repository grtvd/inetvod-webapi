--//////////////////////////////////////////////////////////////////////////////
-- Copyright © 2005-2006 iNetVOD, Inc. All Rights Reserved.
-- iNetVOD Confidential and Proprietary.  See LEGAL.txt.
--//////////////////////////////////////////////////////////////////////////////

use [iNetVOD]
GO

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


--//////////////////////////////////////////////////////////////////////////////

DROP TABLE [dbo].[MemberSession]
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE TABLE [dbo].[MemberSession] (
	[MemberSessionID] uniqueidentifier NOT NULL ROWGUIDCOL ,
	[MemberID] [varchar] (64) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[PlayerID] uniqueidentifier NOT NULL ,
	[StartedOn] [datetime] NOT NULL ,
	[ExpiresAt] [datetime] NOT NULL ,
	[ShowAdult] [bit] NOT NULL
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[MemberSession] ADD
	CONSTRAINT [PK_MemberSession] PRIMARY KEY  CLUSTERED
	(
		[MemberSessionID]
	)  ON [PRIMARY]
GO

CREATE  INDEX [IX_MemberSession_MemberID] ON [dbo].[MemberSession]([MemberID]) ON [PRIMARY]
GO

ALTER TABLE [dbo].[MemberSession] ADD
	CONSTRAINT [FK_MemberSession_Member] FOREIGN KEY
	(
		[MemberID]
	) REFERENCES [dbo].[Member] (
		[MemberID]
	) ON DELETE CASCADE  ON UPDATE CASCADE
GO

--//////////////////////////////////////////////////////////////////////////////

COMMIT

--//////////////////////////////////////////////////////////////////////////////

