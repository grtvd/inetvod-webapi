--//////////////////////////////////////////////////////////////////////////////
-- Copyright © 2006 iNetVOD, Inc. All Rights Reserved.
-- iNetVOD Confidential and Proprietary.  See LEGAL.txt.
--//////////////////////////////////////////////////////////////////////////////

use [iNetVOD]
GO

--//////////////////////////////////////////////////////////////////////////////

BEGIN TRANSACTION

--//////////////////////////////////////////////////////////////////////////////

ALTER TABLE dbo.Provider
	DROP COLUMN RequestURL, AdminUserID, AdminPassword
GO

--//////////////////////////////////////////////////////////////////////////////

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ProviderConnection]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[ProviderConnection]
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE TABLE [dbo].[ProviderConnection] (
	[ProviderConnectionID] uniqueidentifier NOT NULL ROWGUIDCOL ,
	[ProviderID] [varchar] (64) NOT NULL ,
	[ProviderConnectionType] [varchar] (16) NOT NULL ,
	[Disabled] [bit] DEFAULT(0) NOT NULL ,
	[ConnectionURL] [varchar] (4096) NULL,
	[AdminUserID] [varchar] (128) NULL ,
	[AdminPassword] [varchar] (32) NULL ,
	[UseFieldForName] [varchar] (32) NULL ,
	[UseFieldForEpisodeName] [varchar] (32) NULL
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

insert ProviderConnection (ProviderConnectionID, ProviderID, ProviderConnectionType, ConnectionURL, AdminUserID, AdminPassword)
values (newid(), 'internetvideos', 'ProviderAPI', 'http://localhost/provider_internetvideos/providerapi', 'super', 'superpassword')

insert ProviderConnection (ProviderConnectionID, ProviderID, ProviderConnectionType, ConnectionURL, AdminUserID, AdminPassword)
values (newid(), 'moviesmovies', 'ProviderAPI', 'http://localhost/provider_moviesmovies/providerapi', 'super', 'superpassword')

insert ProviderConnection (ProviderConnectionID, ProviderID, ProviderConnectionType, ConnectionURL, AdminUserID, AdminPassword)
values (newid(), 'vodflicks', 'ProviderAPI', 'http://localhost/provider_vodflicks/providerapi', 'super', 'superpassword')

insert ProviderConnection (ProviderConnectionID, ProviderID, ProviderConnectionType, ConnectionURL, AdminUserID, AdminPassword)
values (newid(), 'excellentvideos', 'ProviderAPI', 'http://localhost/provider_excellentvideos/providerapi', 'super', 'superpassword')

insert ProviderConnection (ProviderConnectionID, ProviderID, ProviderConnectionType, ConnectionURL, AdminUserID, AdminPassword)
values (newid(), 'mlb', 'ProviderAPI', 'http://localhost/provider_mlb/providerapi', 'super', 'superpassword')

insert ProviderConnection (ProviderConnectionID, ProviderID, ProviderConnectionType, ConnectionURL, UseFieldForName)
values (newid(), 'rocketboom', 'Rss2', 'http://www.rocketboom.com/vlog/win_media_player_daily_enclosures.xml', 'provider.name')
go


--//////////////////////////////////////////////////////////////////////////////
--//////////////////////////////////////////////////////////////////////////////
