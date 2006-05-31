--//////////////////////////////////////////////////////////////////////////////
-- Copyright © 2005-2006 iNetVOD, Inc. All Rights Reserved.
-- iNetVOD Confidential and Proprietary.  See LEGAL.txt.
--//////////////////////////////////////////////////////////////////////////////

use master

IF NOT EXISTS (SELECT name FROM master.dbo.sysdatabases WHERE name = N'iNetVOD')
BEGIN
print 'creating database'

CREATE DATABASE [iNetVOD]  ON (NAME = N'iNetVOD_Data', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL\Data\iNetVOD_Data.MDF' , SIZE = 2, FILEGROWTH = 10%) LOG ON (NAME = N'iNetVOD_Log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL\Data\iNetVOD_Log.LDF' , SIZE = 2, FILEGROWTH = 10%)
 COLLATE SQL_Latin1_General_CP1_CI_AS

END
ELSE
print 'database not created, already existed'
GO

exec sp_dboption N'iNetVOD', N'autoclose', N'true'
GO

exec sp_dboption N'iNetVOD', N'bulkcopy', N'false'
GO

exec sp_dboption N'iNetVOD', N'trunc. log', N'true'
GO

exec sp_dboption N'iNetVOD', N'torn page detection', N'true'
GO

exec sp_dboption N'iNetVOD', N'read only', N'false'
GO

exec sp_dboption N'iNetVOD', N'dbo use', N'false'
GO

exec sp_dboption N'iNetVOD', N'single', N'false'
GO

exec sp_dboption N'iNetVOD', N'autoshrink', N'true'
GO

exec sp_dboption N'iNetVOD', N'ANSI null default', N'false'
GO

exec sp_dboption N'iNetVOD', N'recursive triggers', N'false'
GO

exec sp_dboption N'iNetVOD', N'ANSI nulls', N'false'
GO

exec sp_dboption N'iNetVOD', N'concat null yields null', N'false'
GO

exec sp_dboption N'iNetVOD', N'cursor close on commit', N'false'
GO

exec sp_dboption N'iNetVOD', N'default to local cursor', N'false'
GO

exec sp_dboption N'iNetVOD', N'quoted identifier', N'false'
GO

exec sp_dboption N'iNetVOD', N'ANSI warnings', N'false'
GO

exec sp_dboption N'iNetVOD', N'auto create statistics', N'true'
GO

exec sp_dboption N'iNetVOD', N'auto update statistics', N'true'
GO

if( (@@microsoftversion / power(2, 24) = 8) and (@@microsoftversion & 0xffff >= 724) )
	exec sp_dboption N'iNetVOD', N'db chaining', N'false'
GO

--//////////////////////////////////////////////////////////////////////////////
--//////////////////////////////////////////////////////////////////////////////

use [iNetVOD]
GO

--//////////////////////////////////////////////////////////////////////////////

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[RentedShow]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[RentedShow]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ShowCategory]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[ShowCategory]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ShowProvider]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[ShowProvider]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[Show]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[Show]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[MemberProvider]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[MemberProvider]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[MemberSession]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[MemberSession]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[MemberPrefs]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[MemberPrefs]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[MemberAccount]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[MemberAccount]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[MemberLogon]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[MemberLogon]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[Member]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[Member]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[Rating]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[Rating]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[Category]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[Category]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ProviderConnection]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[ProviderConnection]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[Provider]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[Provider]
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE TABLE [dbo].[Provider] (
	[ProviderID] [varchar] (64) NOT NULL ,
	[Name] [varchar] (64) NOT NULL ,
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Provider] ADD
	CONSTRAINT [PK_Provider] PRIMARY KEY  CLUSTERED
	(
		[ProviderID]
	)  ON [PRIMARY]
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE TABLE [dbo].[ProviderConnection] (
	[ProviderConnectionID] uniqueidentifier NOT NULL ROWGUIDCOL ,
	[ProviderID] [varchar] (64) NOT NULL ,
	[ProviderConnectionType] [varchar] (16) NOT NULL ,
	[Disabled] [bit] DEFAULT(0) NOT NULL ,
	[ConnectionURL] [varchar] (4096) NULL ,
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

CREATE TABLE [dbo].[Category] (
	[CategoryID] [varchar] (32) NOT NULL ,
	[Name] [varchar] (32) NOT NULL
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Category] ADD
	CONSTRAINT [PK_Category] PRIMARY KEY  CLUSTERED
	(
		[CategoryID]
	)  ON [PRIMARY]
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE TABLE [dbo].[Rating] (
	[RatingID] [varchar] (32) NOT NULL ,
	[Name] [varchar] (32) NOT NULL
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Rating] ADD
	CONSTRAINT [PK_Rating] PRIMARY KEY  CLUSTERED
	(
		[RatingID]
	)  ON [PRIMARY]
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE TABLE [dbo].[Member] (
	[MemberID] uniqueidentifier NOT NULL ROWGUIDCOL ,
	[FirstName] [varchar] (32) NULL ,
	[LastName] [varchar] (32) NULL ,
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Member] ADD
	CONSTRAINT [PK_Member] PRIMARY KEY  CLUSTERED
	(
		[MemberID]
	)  ON [PRIMARY]
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE TABLE [dbo].[MemberLogon] (
	[MemberID] uniqueidentifier NOT NULL ROWGUIDCOL ,
	[EmailKey] [varchar] (64) NOT NULL,
	[Email] [varchar] (64) NOT NULL,
	[Password] [varchar] (32) NOT NULL,
	[LogonID] [int] NOT NULL IDENTITY (100000200, 1),
	[PIN] [varchar] (32) NULL,

	[SecretQuestion] [varchar] (64) NOT NULL,
	[SecretAnswer] [varchar] (32) NOT NULL,
	[TermsAcceptedOn] [datetime] NOT NULL,
	[TermsAcceptedVersion] [varchar] (16) NOT NULL,
	[LogonFailedAt] [datetime] NULL,
	[LogonFailedCount] [tinyint] DEFAULT(0) NOT NULL,
	[LogonDisabled] [bit] DEFAULT(0) NOT NULL
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[MemberLogon] ADD
	CONSTRAINT [PK_MemberLogon] PRIMARY KEY  CLUSTERED
	(
		[MemberID]
	)  ON [PRIMARY]
GO

ALTER TABLE [dbo].[MemberLogon] ADD
	CONSTRAINT [FK_MemberLogon_Member] FOREIGN KEY
	(
		[MemberID]
	) REFERENCES [dbo].[Member] (
		[MemberID]
	) ON DELETE CASCADE  ON UPDATE CASCADE

GO

CREATE  UNIQUE  INDEX [IX_MemberLogon_Email] ON [dbo].[MemberLogon]([EmailKey]) ON [PRIMARY]
GO

CREATE  UNIQUE  INDEX [IX_MemberLogon_LogonID] ON [dbo].[MemberLogon]([LogonID]) ON [PRIMARY]
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE TABLE [dbo].[MemberAccount] (
	[MemberID] uniqueidentifier NOT NULL ROWGUIDCOL ,

	[HomeAddress_AddrStreet1] [varchar] (64) NULL ,
	[HomeAddress_AddrStreet2] [varchar] (64) NULL ,
	[HomeAddress_City] [varchar] (64) NULL ,
	[HomeAddress_State] [varchar] (64) NULL ,
	[HomeAddress_PostalCode] [varchar] (32) NULL ,
	[HomeAddress_Country] [varchar] (2) NULL ,
	[HomeAddress_Phone] [varchar] (32) NULL ,

	[CreditCard_NameOnCC] [varchar] (64) NULL ,
	[CreditCard_CCType] [varchar] (16) NULL ,
	[CreditCard_CCNumber] [varchar] (32) NULL ,
	[CreditCard_CCSIC] [varchar] (16) NULL ,
	[CreditCard_ExpireDate] [varchar] (16) NULL ,

	[CreditCard_BillingAddress_AddrStreet1] [varchar] (64) NULL ,
	[CreditCard_BillingAddress_AddrStreet2] [varchar] (64) NULL ,
	[CreditCard_BillingAddress_City] [varchar] (64) NULL ,
	[CreditCard_BillingAddress_State] [varchar] (64) NULL ,
	[CreditCard_BillingAddress_PostalCode] [varchar] (32) NULL ,
	[CreditCard_BillingAddress_Country] [varchar] (2) NULL ,
	[CreditCard_BillingAddress_Phone] [varchar] (32) NULL ,

	[BirthDate] [datetime]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[MemberAccount] ADD
	CONSTRAINT [PK_MemberAccount] PRIMARY KEY  CLUSTERED
	(
		[MemberID]
	)  ON [PRIMARY]
GO

ALTER TABLE [dbo].[MemberAccount] ADD
	CONSTRAINT [FK_MemberAccount_Member] FOREIGN KEY
	(
		[MemberID]
	) REFERENCES [dbo].[Member] (
		[MemberID]
	) ON DELETE CASCADE  ON UPDATE CASCADE
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE TABLE [dbo].[MemberPrefs] (
	[MemberID] uniqueidentifier NOT NULL ROWGUIDCOL ,
	[IncludeAdult] [varchar] (32) NULL ,
	[AdultPIN] [varchar] (32) NULL ,
	[IncludeRatingIDList] [varchar] (128) NULL ,
	[IncludeDownload] [bit] NULL ,
	[IncludeStreaming] [bit] NULL ,
	[ConnectionSpeed] [varchar] (32) NULL
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[MemberPrefs] ADD
	CONSTRAINT [PK_MemberPrefs] PRIMARY KEY  CLUSTERED
	(
		[MemberID]
	)  ON [PRIMARY]
GO

ALTER TABLE [dbo].[MemberPrefs] ADD
	CONSTRAINT [FK_MemberPrefs_Member] FOREIGN KEY
	(
		[MemberID]
	) REFERENCES [dbo].[Member] (
		[MemberID]
	) ON DELETE CASCADE  ON UPDATE CASCADE
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE TABLE [dbo].[MemberSession] (
	[MemberSessionID] uniqueidentifier NOT NULL ROWGUIDCOL ,
	[MemberID] uniqueidentifier NOT NULL ,
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

-- TODO: Foreign key to Player

--//////////////////////////////////////////////////////////////////////////////

CREATE TABLE [dbo].[MemberProvider] (
	[MemberProviderID] uniqueidentifier NOT NULL ROWGUIDCOL ,
	[MemberID] uniqueidentifier NOT NULL ,
	[ProviderID] [varchar] (64) NOT NULL ,
	[EncryptedUserName] [varchar] (128) NULL ,
	[EncryptedPassword] [varchar] (32) NULL
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[MemberProvider] ADD
	CONSTRAINT [PK_MemberProvider] PRIMARY KEY  CLUSTERED
	(
		[MemberProviderID]
	)  ON [PRIMARY]
GO

CREATE  INDEX [IX_MemberProvider_MemberID] ON [dbo].[MemberProvider]([MemberID]) ON [PRIMARY]
GO

CREATE  INDEX [IX_MemberProvider_ProviderID] ON [dbo].[MemberProvider]([ProviderID]) ON [PRIMARY]
GO

ALTER TABLE [dbo].[MemberProvider] ADD
	CONSTRAINT [FK_MemberProvider_Member] FOREIGN KEY
	(
		[MemberID]
	) REFERENCES [dbo].[Member] (
		[MemberID]
	) ON DELETE CASCADE  ON UPDATE CASCADE
GO

ALTER TABLE [dbo].[MemberProvider] ADD
	CONSTRAINT [FK_MemberProvider_Provider] FOREIGN KEY
	(
		[ProviderID]
	) REFERENCES [dbo].[Provider] (
		[ProviderID]
	) ON DELETE CASCADE  ON UPDATE CASCADE
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE TABLE [dbo].[Show] (
	[ShowID] uniqueidentifier NOT NULL ROWGUIDCOL ,
	[Name] [varchar] (64) NOT NULL ,
	[EpisodeName] [varchar] (64) NULL ,
	[EpisodeNumber] [varchar] (32) NULL ,
	[ReleasedOn] [datetime] NULL ,
	[ReleasedYear] [smallint] NULL ,
	[Description] [text] NULL ,
	[RunningMins] [smallint] NULL ,
	[PictureURL] [varchar] (4096) NULL ,
	[RatingID] [varchar] (32) NULL ,
	[IsAdult] [bit] NOT NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO

ALTER TABLE [dbo].[Show] ADD
	CONSTRAINT [PK_Show] PRIMARY KEY  CLUSTERED
	(
		[ShowID]
	)  ON [PRIMARY]
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE TABLE [dbo].[ShowProvider] (
	[ShowProviderID] uniqueidentifier NOT NULL ROWGUIDCOL ,
	[ShowID] uniqueidentifier NOT NULL ,
	[ProviderID] [varchar] (64) NOT NULL ,
	[ProviderConnectionID] uniqueidentifier NOT NULL ,
	[ProviderShowID] [varchar] (128) NOT NULL ,
	[ShowURL] [varchar] (4096) NULL ,
	[ShowFormatMime] [varchar] (32) NULL ,
	[ShowCost_ShowCostType] [varchar] (32) NOT NULL ,
	[ShowCost_Cost_CurrencyID] [varchar] (3) NULL ,
	[ShowCost_Cost_Amount] [decimal] (17,2) NULL ,
	[ShowCost_CostDisplay] [varchar] (32) NOT NULL ,
	[ShowCost_RentalWindowDays] [smallint] NULL ,
	[ShowCost_RentalPeriodHours] [smallint] NULL ,
	[ShowAvail] [varchar] (32) NOT NULL
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[ShowProvider] ADD
	CONSTRAINT [PK_ShowProvider] PRIMARY KEY  CLUSTERED
	(
		[ShowProviderID]
	)  ON [PRIMARY]
GO

CREATE  INDEX [IX_ShowProvider_ShowID] ON [dbo].[ShowProvider]([ShowID]) ON [PRIMARY]
GO

CREATE  INDEX [IX_ShowProvider_ProviderID] ON [dbo].[ShowProvider]([ProviderID]) ON [PRIMARY]
GO

CREATE  INDEX [IX_ShowProvider_ProviderConnectionID] ON [dbo].[ShowProvider]([ProviderConnectionID]) ON [PRIMARY]
GO


ALTER TABLE [dbo].[ShowProvider] ADD
	CONSTRAINT [FK_ShowProvider_Show] FOREIGN KEY
	(
		[ShowID]
	) REFERENCES [dbo].[Show] (
		[ShowID]
	) ON DELETE NO ACTION  ON UPDATE NO ACTION
GO

ALTER TABLE [dbo].[ShowProvider] ADD
	CONSTRAINT [FK_ShowProvider_Provider] FOREIGN KEY
	(
		[ProviderID]
	) REFERENCES [dbo].[Provider] (
		[ProviderID]
	) ON DELETE NO ACTION  ON UPDATE NO ACTION
GO

ALTER TABLE [dbo].[ShowProvider] ADD
	CONSTRAINT [FK_ShowProvider_ProviderConnection] FOREIGN KEY
	(
		[ProviderConnectionID]
	) REFERENCES [dbo].[ProviderConnection] (
		[ProviderConnectionID]
	) ON DELETE NO ACTION  ON UPDATE NO ACTION
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE TABLE [dbo].[ShowCategory] (
	[ShowCategoryID] uniqueidentifier NOT NULL ROWGUIDCOL ,
	[ShowID] uniqueidentifier NOT NULL ,
	[CategoryID] [varchar] (32) NOT NULL
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[ShowCategory] ADD
	CONSTRAINT [PK_ShowCategory] PRIMARY KEY  CLUSTERED
	(
		[ShowCategoryID]
	)  ON [PRIMARY]
GO

CREATE  INDEX [IX_ShowCategory_ShowID] ON [dbo].[ShowCategory]([ShowID]) ON [PRIMARY]
GO

CREATE  INDEX [IX_ShowCategory_CategoryID] ON [dbo].[ShowCategory]([CategoryID]) ON [PRIMARY]
GO

ALTER TABLE [dbo].[ShowCategory] ADD
	CONSTRAINT [FK_ShowCategory_Show] FOREIGN KEY
	(
		[ShowID]
	) REFERENCES [dbo].[Show] (
		[ShowID]
	) ON DELETE CASCADE  ON UPDATE CASCADE
GO

ALTER TABLE [dbo].[ShowCategory] ADD
	CONSTRAINT [FK_ShowCategory_Category] FOREIGN KEY
	(
		[CategoryID]
	) REFERENCES [dbo].[Category] (
		[CategoryID]
	) ON UPDATE CASCADE
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE TABLE [dbo].[RentedShow] (
	[RentedShowID] uniqueidentifier NOT NULL ROWGUIDCOL ,
	[MemberID] uniqueidentifier NOT NULL ,
	[ShowID] uniqueidentifier NOT NULL ,
	[ProviderID] [varchar] (64) NOT NULL ,
	[ProviderConnectionID] uniqueidentifier NOT NULL ,
	[ShowURL] [varchar] (4096) NOT NULL ,
	[ShowCost_ShowCostType] [varchar] (32) NOT NULL ,
	[ShowCost_Cost_CurrencyID] [varchar] (3) NULL ,
	[ShowCost_Cost_Amount] [decimal] (17,2) NULL ,
	[ShowCost_CostDisplay] [varchar] (32) NOT NULL ,
	[ShowCost_RentalWindowDays] [smallint] NULL ,
	[ShowCost_RentalPeriodHours] [smallint] NULL ,
	[RentedOn] [datetime] NOT NULL ,
	[AvailableUntil] [datetime] NULL ,
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[RentedShow] ADD
	CONSTRAINT [PK_RentedShow] PRIMARY KEY  CLUSTERED
	(
		[RentedShowID]
	)  ON [PRIMARY]
GO

CREATE  INDEX [IX_RentedShow_MemberID] ON [dbo].[RentedShow]([MemberID]) ON [PRIMARY]
GO

CREATE  INDEX [IX_RentedShow_ShowID] ON [dbo].[RentedShow]([ShowID]) ON [PRIMARY]
GO

CREATE  INDEX [IX_RentedShow_ProviderID] ON [dbo].[RentedShow]([ProviderID]) ON [PRIMARY]
GO

ALTER TABLE [dbo].[RentedShow] ADD
	CONSTRAINT [FK_RentedShow_Member] FOREIGN KEY
	(
		[MemberID]
	) REFERENCES [dbo].[Member] (
		[MemberID]
	) ON DELETE CASCADE  ON UPDATE CASCADE
GO

ALTER TABLE [dbo].[RentedShow] ADD
	CONSTRAINT [FK_RentedShow_Show] FOREIGN KEY
	(
		[ShowID]
	) REFERENCES [dbo].[Show] (
		[ShowID]
	) ON DELETE NO ACTION  ON UPDATE NO ACTION
GO

ALTER TABLE [dbo].[RentedShow] ADD
	CONSTRAINT [FK_RentedShow_Provider] FOREIGN KEY
	(
		[ProviderID]
	) REFERENCES [dbo].[Provider] (
		[ProviderID]
	) ON DELETE NO ACTION  ON UPDATE NO ACTION
GO

--//////////////////////////////////////////////////////////////////////////////

if exists (select * from dbo.sysusers where name = N'inetvod' and uid < 16382)
	EXEC sp_revokedbaccess  N'inetvod'
GO

if exists (select * from master.dbo.syslogins where loginname = N'inetvod')
	EXEC sp_droplogin N'inetvod'
GO

--//////////////////////////////////////////////////////////////////////////////

if not exists (select * from master.dbo.syslogins where loginname = N'inetvod')
BEGIN
	declare @logindb nvarchar(132), @loginlang nvarchar(132) select @logindb = N'iNetVOD', @loginlang = N'us_english'
	if @logindb is null or not exists (select * from master.dbo.sysdatabases where name = @logindb)
		select @logindb = N'master'
	if @loginlang is null or (not exists (select * from master.dbo.syslanguages where name = @loginlang) and @loginlang <> N'us_english')
		select @loginlang = @@language
	exec sp_addlogin N'inetvod', null, @logindb, @loginlang
END
GO

--//////////////////////////////////////////////////////////////////////////////

if not exists (select * from dbo.sysusers where name = N'inetvod' and uid < 16382)
	EXEC sp_grantdbaccess N'inetvod', N'inetvod'
GO

--//////////////////////////////////////////////////////////////////////////////

