use master
IF EXISTS (SELECT name FROM master.dbo.sysdatabases WHERE name = N'iNetVOD')
	DROP DATABASE [iNetVOD]
GO

CREATE DATABASE [iNetVOD]  ON (NAME = N'iNetVOD_Data', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL\Data\iNetVOD_Data.MDF' , SIZE = 1, FILEGROWTH = 10%) LOG ON (NAME = N'iNetVOD_Log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL\Data\iNetVOD_Log.LDF' , SIZE = 1, FILEGROWTH = 10%)
 COLLATE SQL_Latin1_General_CP1_CI_AS
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

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[Provider_Get]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[Provider_Get]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[Provider_GetAll]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[Provider_GetAll]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[Category_Get]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[Category_Get]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[Category_GetAll]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[Category_GetAll]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[Rating_Get]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[Rating_Get]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[Rating_GetAll]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[Rating_GetAll]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[Member_Get]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[Member_Get]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[Member_Insert]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[Member_Insert]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[Member_Update]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[Member_Update]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[Member_Delete]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[Member_Delete]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[MemberPrefs_Get]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[MemberPrefs_Get]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[SerialNumber_Get]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[SerialNumber_Get]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[SerialNumber_Update]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[SerialNumber_Update]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[MemberProvider_Get]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[MemberProvider_Get]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[MemberProvider_Insert]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[MemberProvider_Insert]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[MemberProvider_Update]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[MemberProvider_Update]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[MemberProvider_GetByMemberIDProviderID]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[MemberProvider_GetByMemberIDProviderID]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[MemberProvider_GetByMemberID]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[MemberProvider_GetByMemberID]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[Show_Get]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[Show_Get]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[Show_Search]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[Show_Search]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[Show_GetByProviderID]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[Show_GetByProviderID]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[Show_GetByCategoryID]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[Show_GetByCategoryID]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[Show_GetByRentedShowMemberID]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[Show_GetByRentedShowMemberID]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ShowProvider_GetByShowIDProviderID]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[ShowProvider_GetByShowIDProviderID]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ShowProvider_Search]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[ShowProvider_Search]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ShowProvider_GetByProviderID]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[ShowProvider_GetByProviderID]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ShowProvider_GetByCategoryID]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[ShowProvider_GetByCategoryID]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ShowCategory_Search]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[ShowCategory_Search]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ShowCategory_GetByShowID]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[ShowCategory_GetByShowID]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ShowCategory_GetByCategoryID]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[ShowCategory_GetByCategoryID]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ShowCategory_GetByRentedShowMemberID]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[ShowCategory_GetByRentedShowMemberID]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[RentedShow_Get]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[RentedShow_Get]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[RentedShow_Insert]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[RentedShow_Insert]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[RentedShow_Update]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[RentedShow_Update]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[RentedShow_Delete]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[RentedShow_Delete]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[RentedShow_GetByMemberID]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[RentedShow_GetByMemberID]
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

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[SerialNumber]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[SerialNumber]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[MemberPrefs]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[MemberPrefs]
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

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[Provider]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[Provider]
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE TABLE [dbo].[Provider] (
	[ProviderID] [varchar] (64) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[Name] [varchar] (64) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Provider] ADD
	CONSTRAINT [PK_Provider] PRIMARY KEY  CLUSTERED
	(
		[ProviderID]
	)  ON [PRIMARY]
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE TABLE [dbo].[Category] (
	[CategoryID] [varchar] (32) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[Name] [varchar] (32) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL
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
	[RatingID] [varchar] (32) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[Name] [varchar] (32) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL
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
	[MemberID] [varchar] (64) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[FirstName] [varchar] (32) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[LastName] [varchar] (32) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[AddrStreet1] [varchar] (64) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[AddrStreet2] [varchar] (64) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[City] [varchar] (64) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[State] [varchar] (32) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[PostalCode] [varchar] (32) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[Country] [varchar] (32) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[Phone] [varchar] (32) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[BirthDate] [datetime]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Member] ADD
	CONSTRAINT [PK_Member] PRIMARY KEY  CLUSTERED
	(
		[MemberID]
	)  ON [PRIMARY]
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE TABLE [dbo].[MemberPrefs] (
	[MemberID] [varchar] (64) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[IncludeAdult] [varchar] (32) COLLATE SQL_Latin1_General_CP1_CI_AS NULL
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
	)
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE TABLE [dbo].[SerialNumber] (
	[SerialNumberID] [varchar] (64) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[Active] [bit] NOT NULL ,
	[MemberID] [varchar] (64) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[PIN] [varchar] (16) COLLATE SQL_Latin1_General_CP1_CI_AS NULL
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[SerialNumber] ADD
	CONSTRAINT [PK_SerialNumber] PRIMARY KEY  CLUSTERED
	(
		[SerialNumberID]
	)  ON [PRIMARY]
GO

-- Can't be unique because of NULL MemberIDs
CREATE  INDEX [IX_SerialNumber_MemberID] ON [dbo].[SerialNumber]([MemberID]) ON [PRIMARY]
GO

ALTER TABLE [dbo].[SerialNumber] ADD
	CONSTRAINT [FK_SerialNumber_Member] FOREIGN KEY
	(
		[MemberID]
	) REFERENCES [dbo].[Member] (
		[MemberID]
	)
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE TABLE [dbo].[MemberProvider] (
	[MemberProviderID] [varchar] (64) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[MemberID] [varchar] (64) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[ProviderID] [varchar] (64) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[EncryptedUserName] [varchar] (128) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[EncryptedPassword] [varchar] (64) COLLATE SQL_Latin1_General_CP1_CI_AS NULL
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
	[ShowID] [varchar] (64) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[Name] [varchar] (64) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[EpisodeName] [varchar] (64) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[EpisodeNumber] [varchar] (32) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[ReleasedOn] [datetime] NULL ,
	[ReleasedYear] [smallint] NULL ,
	[Description] [text] COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[RunningMins] [smallint] NULL ,
	[PictureURL] [varchar] (4096) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[RatingID] [varchar] (32) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
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
	[ShowProviderID] [varchar] (64) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[ShowID] [varchar] (64) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[ProviderID] [varchar] (64) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[ProviderShowID] [varchar] (64) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[ShowCost_ShowCostType] [varchar] (32) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[ShowCost_Money_CurrencyID] [varchar] (3) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[ShowCost_Money_Amount] [decimal] (17,2) NULL ,
	[ShowCost_Description] [varchar] (32) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[RentalHours] [smallint] NULL
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


ALTER TABLE [dbo].[ShowProvider] ADD
	CONSTRAINT [FK_ShowProvider_Show] FOREIGN KEY
	(
		[ShowID]
	) REFERENCES [dbo].[Show] (
		[ShowID]
	) ON DELETE CASCADE  ON UPDATE CASCADE
GO

ALTER TABLE [dbo].[ShowProvider] ADD
	CONSTRAINT [FK_ShowProvider_Provider] FOREIGN KEY
	(
		[ProviderID]
	) REFERENCES [dbo].[Provider] (
		[ProviderID]
	) ON DELETE CASCADE  ON UPDATE CASCADE
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE TABLE [dbo].[ShowCategory] (
	[ShowCategoryID] [varchar] (64) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[ShowID] [varchar] (64) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[CategoryID] [varchar] (32) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL
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
	[RentedShowID] [varchar] (64) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[MemberID] [varchar] (64) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[ShowID] [varchar] (64) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[ProviderID] [varchar] (64) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[ShowURL] [varchar] (4096) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[ShowCost_ShowCostType] [varchar] (32) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[ShowCost_Money_CurrencyID] [varchar] (3) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[ShowCost_Money_Amount] [decimal] (17,2) NULL ,
	[ShowCost_Description] [varchar] (32) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[RentalHours] [smallint] NULL ,
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
	) ON DELETE CASCADE  ON UPDATE CASCADE
GO

ALTER TABLE [dbo].[RentedShow] ADD
	CONSTRAINT [FK_RentedShow_Provider] FOREIGN KEY
	(
		[ProviderID]
	) REFERENCES [dbo].[Provider] (
		[ProviderID]
	) ON DELETE CASCADE  ON UPDATE CASCADE
GO

--//////////////////////////////////////////////////////////////////////////////

