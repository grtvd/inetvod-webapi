--//////////////////////////////////////////////////////////////////////////////
-- Copyright © 2006 iNetVOD, Inc. All Rights Reserved.
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
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ShowProvider]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[ShowProvider]
GO

CREATE TABLE [dbo].[ShowProvider] (
	[ShowProviderID] uniqueidentifier NOT NULL ROWGUIDCOL ,
	[ShowID] uniqueidentifier NOT NULL ,
	[ProviderID] [varchar] (64) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[ProviderConnectionID] uniqueidentifier NOT NULL ,
	[ProviderShowID] [varchar] (128) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[ShowURL] [varchar] (4096) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[ShowCost_ShowCostType] [varchar] (32) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[ShowCost_Cost_CurrencyID] [varchar] (3) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[ShowCost_Cost_Amount] [decimal] (17,2) NULL ,
	[ShowCost_CostDisplay] [varchar] (32) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
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

COMMIT

--//////////////////////////////////////////////////////////////////////////////

BEGIN TRANSACTION

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[RentedShow]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[RentedShow]
GO

CREATE TABLE [dbo].[RentedShow] (
	[RentedShowID] uniqueidentifier NOT NULL ROWGUIDCOL ,
	[MemberID] uniqueidentifier NOT NULL ,
	[ShowID] uniqueidentifier NOT NULL ,
	[ProviderID] [varchar] (64) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[ProviderConnectionID] uniqueidentifier NOT NULL ,
	[ShowURL] [varchar] (4096) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[ShowCost_ShowCostType] [varchar] (32) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[ShowCost_Cost_CurrencyID] [varchar] (3) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[ShowCost_Cost_Amount] [decimal] (17,2) NULL ,
	[ShowCost_CostDisplay] [varchar] (32) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
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

COMMIT

--//////////////////////////////////////////////////////////////////////////////

insert into Category (CategoryID, Name)
values ('daily', 'Daily')
GO

--//////////////////////////////////////////////////////////////////////////////
--//////////////////////////////////////////////////////////////////////////////
