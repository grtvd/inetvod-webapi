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

SET QUOTED_IDENTIFIER ON
GO
SET ANSI_NULLS OFF
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.Provider_Get
	@ProviderID varchar(64)
AS
	select ProviderID, Name
	from Provider
	where ProviderID = @ProviderID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.Provider_GetAll
AS
	select ProviderID, Name
	from Provider
	order by Name
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.Category_Get
	@CategoryID varchar(32)
AS
	select CategoryID, Name
	from Category
	where CategoryID = @CategoryID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.Category_GetAll
AS
	select CategoryID, Name
	from Category
	order by Name
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.Rating_Get
	@RatingID varchar(32)
AS
	select RatingID, Name
	from Rating
	where RatingID = @RatingID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.Rating_GetAll
AS
	select RatingID, Name
	from Rating
	order by Name
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.Member_Get
	@MemberID varchar(64)
AS
	select
		MemberID,
		FirstName,
		LastName,
		AddrStreet1,
		AddrStreet2,
		City,
		State,
		PostalCode,
		Country,
		Phone,
		BirthDate
	from Member
	where MemberID = @MemberID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.Member_Insert
	@MemberID varchar(64),
	@FirstName varchar(32),
	@LastName varchar(32),
	@AddrStreet1 varchar(64),
	@AddrStreet2 varchar(64),
	@City varchar(64),
	@State varchar(32),
	@PostalCode varchar(32),
	@Country varchar(32),
	@Phone varchar(32),
	@BirthDate datetime
AS
	insert into Member
	(
		MemberID,
		FirstName,
		LastName,
		AddrStreet1,
		AddrStreet2,
		City,
		State,
		PostalCode,
		Country,
		Phone,
		BirthDate
	)
	values
	(
		@MemberID,
		@FirstName,
		@LastName,
		@AddrStreet1,
		@AddrStreet2,
		@City,
		@State,
		@PostalCode,
		@Country,
		@Phone,
		@BirthDate
	)
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.Member_Update
	@MemberID varchar(64),
	@FirstName varchar(32),
	@LastName varchar(32),
	@AddrStreet1 varchar(64),
	@AddrStreet2 varchar(64),
	@City varchar(64),
	@State varchar(32),
	@PostalCode varchar(32),
	@Country varchar(32),
	@Phone varchar(32),
	@BirthDate datetime
AS
	update Member set
		FirstName = @FirstName,
		LastName = @LastName,
		AddrStreet1 = @AddrStreet1,
		AddrStreet2 = @AddrStreet2,
		City = @City,
		State = @State,
		PostalCode = @PostalCode,
		Country = @Country,
		Phone = @Phone,
		BirthDate = @BirthDate
	where MemberID = @MemberID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.Member_Delete
	@MemberID varchar(64)
AS
	delete from Member where MemberID = @MemberID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.MemberPrefs_Get
	@MemberID varchar(64)
AS
	select
		MemberID,
		IncludeAdult
	from MemberPrefs
	where MemberID = @MemberID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.SerialNumber_Get
	@SerialNumberID varchar(64)
AS
	select
		SerialNumberID,
		Active,
		MemberID,
		PIN
	from SerialNumber
	where SerialNumberID = @SerialNumberID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.SerialNumber_Update
	@SerialNumberID varchar(64),
	@Active bit,
	@MemberID varchar(64),
	@PIN varchar(16)
AS
	update SerialNumber set
		Active = @Active,
		MemberID = @MemberID,
		PIN = @PIN
	where SerialNumberID = @SerialNumberID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.MemberProvider_Get
	@MemberProviderID varchar(64)
AS
	select MemberProviderID, MemberID, ProviderID, EncryptedUserName, EncryptedPassword
	from MemberProvider
	where (MemberProviderID = @MemberProviderID)
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.MemberProvider_Insert
	@MemberProviderID varchar(64),
	@MemberID varchar(64),
	@ProviderID varchar(64),
	@EncryptedUserName varchar(128),
	@EncryptedPassword varchar(64)
AS
	insert into MemberProvider
	(
		MemberProviderID,
		MemberID,
		ProviderID,
		EncryptedUserName,
		EncryptedPassword
	)
	values
	(
		@MemberProviderID,
		@MemberID,
		@ProviderID,
		@EncryptedUserName,
		@EncryptedPassword
	)
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.MemberProvider_Update
	@MemberProviderID varchar(64),
	@MemberID varchar(64),
	@ProviderID varchar(64),
	@EncryptedUserName varchar(128),
	@EncryptedPassword varchar(64)
AS
	update MemberProvider set
		MemberID = @MemberID,
		ProviderID = @ProviderID,
		EncryptedUserName = @EncryptedUserName,
		EncryptedPassword = @EncryptedPassword
	where MemberProviderID = @MemberProviderID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.MemberProvider_GetByMemberIDProviderID
	@MemberID varchar(64),
	@ProviderID varchar(64)
AS
	select MemberProviderID, MemberID, ProviderID, EncryptedUserName, EncryptedPassword
	from MemberProvider
	where (MemberID = @MemberID)
	and (ProviderID = @ProviderID)
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.MemberProvider_GetByMemberID
	@MemberID varchar(64)
AS
	select MemberProviderID, MemberID, ProviderID, EncryptedUserName, EncryptedPassword
	from MemberProvider
	where (MemberID = @MemberID)
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.Show_Get
	@ShowID varchar(64)
AS
	select ShowID, Name, EpisodeName, EpisodeNumber, ReleasedOn, ReleasedYear,
		Description, RunningMins, PictureURL, RatingID, IsAdult
	from Show
	where ShowID = @ShowID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.Show_Search
	@PartialName varchar(64)
AS
	select ShowID, Name, EpisodeName, EpisodeNumber, ReleasedOn, ReleasedYear,
		Description, RunningMins, PictureURL, RatingID, IsAdult
	from Show
	where Name like '%' + isnull(@PartialName, '') + '%'
	order by Name
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.Show_GetByProviderID
	@ProviderID varchar(32)
AS
	select s.ShowID, s.Name, s.EpisodeName, s.EpisodeNumber, s.ReleasedOn, s.ReleasedYear,
		s.Description, s.RunningMins, s.PictureURL, s.RatingID, s.IsAdult
	from Show s
	join ShowProvider sp on sp.ShowID = s.ShowID
	where sp.ProviderID = @ProviderID
	order by s.Name
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.Show_GetByCategoryID
	@CategoryID varchar(32)
AS
	select s.ShowID, s.Name, s.EpisodeName, s.EpisodeNumber, s.ReleasedOn, s.ReleasedYear,
		s.Description, s.RunningMins, s.PictureURL, s.RatingID, s.IsAdult
	from Show s
	join ShowCategory sc on sc.ShowID = s.ShowID
	where sc.CategoryID = @CategoryID
	order by s.Name
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.Show_GetByRentedShowMemberID
	@MemberID varchar(64)
AS
	select s.ShowID, s.Name, s.EpisodeName, s.EpisodeNumber, s.ReleasedOn, s.ReleasedYear,
		s.Description, s.RunningMins, s.PictureURL, s.RatingID, s.IsAdult
	from Show s
	join RentedShow rs on rs.ShowID = s.ShowID
	where rs.MemberID = @MemberID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.ShowProvider_GetByShowIDProviderID
	@ShowID varchar(64),
	@ProviderID varchar(64)
AS
	select ShowProviderID, ShowID, ProviderID, ProviderShowID, ShowCost_ShowCostType,
		ShowCost_Money_CurrencyID, ShowCost_Money_Amount, ShowCost_Description, RentalHours
	from ShowProvider
	where (ShowID = @ShowID)
	and (ProviderID = @ProviderID)
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.ShowProvider_Search
	@PartialName varchar(64)
AS
	select sp.ShowProviderID, sp.ShowID, ProviderID, ProviderShowID,
		ShowCost_ShowCostType, ShowCost_Money_CurrencyID, ShowCost_Money_Amount,
		ShowCost_Description, RentalHours
	from ShowProvider sp
	join Show s on s.ShowID = sp.ShowID
	where s.Name like '%' + isnull(@PartialName, '') + '%'
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.ShowProvider_GetByProviderID
	@ProviderID varchar(32)
AS
	select ShowProviderID, ShowID, ProviderID, ProviderShowID,
		ShowCost_ShowCostType, ShowCost_Money_CurrencyID, ShowCost_Money_Amount,
		ShowCost_Description, RentalHours
	from ShowProvider
	where ProviderID = @ProviderID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.ShowProvider_GetByCategoryID
	@CategoryID varchar(32)
AS
	select sp.ShowProviderID, sp.ShowID, ProviderID, ProviderShowID,
		ShowCost_ShowCostType, ShowCost_Money_CurrencyID, ShowCost_Money_Amount,
		ShowCost_Description, RentalHours
	from ShowProvider sp
	join ShowCategory sc on sc.ShowID = sp.ShowID
	where sc.CategoryID = @CategoryID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.ShowCategory_Search
	@PartialName varchar(64)
AS
	select sc.ShowCategoryID, sc.ShowID, sc.CategoryID
	from ShowCategory sc
	join Show s on s.ShowID = sc.ShowID
	where s.Name like '%' + isnull(@PartialName, '') + '%'
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.ShowCategory_GetByShowID
	@ShowID varchar(64)
AS
	select ShowCategoryID, ShowID, CategoryID
	from ShowCategory
	where ShowID = @ShowID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.ShowCategory_GetByCategoryID
	@CategoryID varchar(32)
AS
	select ShowCategoryID, ShowID, CategoryID
	from ShowCategory
	where CategoryID = @CategoryID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.ShowCategory_GetByRentedShowMemberID
	@MemberID varchar(64)
AS
	select sc.ShowCategoryID, sc.ShowID, sc.CategoryID
	from ShowCategory sc
	join Show s on s.ShowID = sc.ShowID
	join RentedShow rs on rs.ShowID = s.ShowID
	where rs.MemberID = @MemberID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.RentedShow_Get
	@RentedShowID varchar(64)
AS
	select
		RentedShowID,
		MemberID,
		ShowID,
		ProviderID,
		ShowURL,
		ShowCost_ShowCostType,
		ShowCost_Money_CurrencyID,
		ShowCost_Money_Amount,
		ShowCost_Description,
		RentalHours,
		RentedOn,
		AvailableUntil
	from RentedShow
	where RentedShowID = @RentedShowID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.RentedShow_Insert
	@RentedShowID varchar(64),
	@MemberID varchar(64),
	@ShowID varchar(64),
	@ProviderID varchar(64),
	@ShowURL varchar(4096),
	@ShowCost_ShowCostType varchar(32),
	@ShowCost_Money_CurrencyID varchar(3),
	@ShowCost_Money_Amount decimal(17,2),
	@ShowCost_Description varchar(32),
	@RentalHours smallint,
	@RentedOn datetime,
	@AvailableUntil datetime
AS
	insert into RentedShow
	(
		RentedShowID,
		MemberID,
		ShowID,
		ProviderID,
		ShowURL,
		ShowCost_ShowCostType,
		ShowCost_Money_CurrencyID,
		ShowCost_Money_Amount,
		ShowCost_Description,
		RentalHours,
		RentedOn,
		AvailableUntil
	)
	values
	(
		@RentedShowID,
		@MemberID,
		@ShowID,
		@ProviderID,
		@ShowURL,
		@ShowCost_ShowCostType,
		@ShowCost_Money_CurrencyID,
		@ShowCost_Money_Amount,
		@ShowCost_Description,
		@RentalHours,
		@RentedOn,
		@AvailableUntil
	)
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.RentedShow_Update
	@RentedShowID varchar(64),
	@MemberID varchar(64),
	@ShowID varchar(64),
	@ProviderID varchar(64),
	@ShowURL varchar(4096),
	@ShowCost_ShowCostType varchar(32),
	@ShowCost_Money_CurrencyID varchar(3),
	@ShowCost_Money_Amount decimal(17,2),
	@ShowCost_Description varchar(32),
	@RentalHours smallint,
	@RentedOn datetime,
	@AvailableUntil datetime
AS
	update RentedShow set
		--MemberID = @MemberID,
		--ShowID = @ShowID,
		--ProviderID = @ProviderID,
		ShowURL = @ShowURL,
		ShowCost_ShowCostType = @ShowCost_ShowCostType,
		ShowCost_Money_CurrencyID = @ShowCost_Money_CurrencyID,
		ShowCost_Money_Amount = @ShowCost_Money_Amount,
		ShowCost_Description = @ShowCost_Description,
		RentalHours = @RentalHours,
		RentedOn = @RentedOn,
		AvailableUntil = @AvailableUntil
	where RentedShowID = @RentedShowID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.RentedShow_Delete
	@RentedShowID varchar(64)
AS
	delete from RentedShow where RentedShowID = @RentedShowID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.RentedShow_GetByMemberID
	@MemberID varchar(64)
AS
	select
		RentedShowID,
		MemberID,
		ShowID,
		ProviderID,
		ShowURL,
		ShowCost_ShowCostType,
		ShowCost_Money_CurrencyID,
		ShowCost_Money_Amount,
		ShowCost_Description,
		RentalHours,
		RentedOn,
		AvailableUntil
	from RentedShow
	where MemberID = @MemberID
GO

--//////////////////////////////////////////////////////////////////////////////

SET QUOTED_IDENTIFIER OFF
GO
SET ANSI_NULLS ON
GO

