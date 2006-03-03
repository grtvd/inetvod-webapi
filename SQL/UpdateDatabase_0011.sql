--//////////////////////////////////////////////////////////////////////////////
-- Copyright © 2006 iNetVOD, Inc. All Rights Reserved.
-- Confidential and Proprietary
--//////////////////////////////////////////////////////////////////////////////

use [iNetVOD]
GO

--//////////////////////////////////////////////////////////////////////////////

BEGIN TRANSACTION

--//////////////////////////////////////////////////////////////////////////////

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ProviderConnection]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[ProviderConnection]
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE TABLE [dbo].[ProviderConnection] (
	[ProviderConnectionID] uniqueidentifier NOT NULL ROWGUIDCOL ,
	[ProviderID] [varchar] (64) NOT NULL ,
	[ProviderConnectionType] [varchar] (16) NOT NULL ,
	[ConnectionURL] [varchar] (4096) NULL
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[ProviderConnection] ADD
	CONSTRAINT [PK_ProviderConnection] PRIMARY KEY  CLUSTERED
	(
		[ProviderConnectionID]
	)  ON [PRIMARY]
GO

CREATE  INDEX [IX_ProviderConnection_ProviderID] ON [dbo].[ProviderConnection]([ProviderID]) ON [PRIMARY]
GO

ALTER TABLE [dbo].[ProviderConnection] ADD
	CONSTRAINT [FK_ProviderConnection_Provider] FOREIGN KEY
	(
		[ProviderID]
	) REFERENCES [dbo].[Provider] (
		[ProviderID]
	) ON DELETE CASCADE  ON UPDATE CASCADE
GO

--//////////////////////////////////////////////////////////////////////////////

COMMIT

--//////////////////////////////////////////////////////////////////////////////

--delete from ProviderConnection

insert ProviderConnection (ProviderConnectionID, ProviderID, ProviderConnectionType, ConnectionURL)
values (newid(), 'rocketboom', 'Rss2', 'http://localhost:8080/samplefeeds/rss2.xml')
go


--//////////////////////////////////////////////////////////////////////////////
--//////////////////////////////////////////////////////////////////////////////
