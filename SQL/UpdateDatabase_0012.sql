--//////////////////////////////////////////////////////////////////////////////
-- Copyright © 2006 iNetVOD, Inc. All Rights Reserved.
-- Confidential and Proprietary
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
ALTER TABLE dbo.ShowProvider
	DROP CONSTRAINT FK_ShowProvider_Provider
GO
COMMIT

--//////////////////////////////////////////////////////////////////////////////

BEGIN TRANSACTION
ALTER TABLE dbo.ShowProvider
	DROP CONSTRAINT FK_ShowProvider_Show
GO
COMMIT

--//////////////////////////////////////////////////////////////////////////////

BEGIN TRANSACTION
CREATE TABLE dbo.Tmp_ShowProvider
	(
	ShowProviderID uniqueidentifier NOT NULL ROWGUIDCOL,
	ShowID uniqueidentifier NOT NULL,
	ProviderID varchar(64) NOT NULL,
	ProviderShowID varchar(128) NOT NULL,
	ShowURL varchar(4096) NULL,
	ShowCost_ShowCostType varchar(32) NOT NULL,
	ShowCost_Cost_CurrencyID varchar(3) NULL,
	ShowCost_Cost_Amount decimal(17, 2) NULL,
	ShowCost_CostDisplay varchar(32) NOT NULL,
	ShowCost_RentalWindowDays smallint NULL,
	ShowCost_RentalPeriodHours smallint NULL
	)  ON [PRIMARY]
GO
IF EXISTS(SELECT * FROM dbo.ShowProvider)
	 EXEC('INSERT INTO dbo.Tmp_ShowProvider (ShowProviderID, ShowID, ProviderID, ProviderShowID, ShowCost_ShowCostType, ShowCost_Cost_CurrencyID, ShowCost_Cost_Amount, ShowCost_CostDisplay, ShowCost_RentalWindowDays, ShowCost_RentalPeriodHours)
		SELECT ShowProviderID, ShowID, ProviderID, ProviderShowID, ShowCost_ShowCostType, ShowCost_Cost_CurrencyID, ShowCost_Cost_Amount, ShowCost_CostDisplay, ShowCost_RentalWindowDays, ShowCost_RentalPeriodHours FROM dbo.ShowProvider TABLOCKX')
GO
DROP TABLE dbo.ShowProvider
GO
EXECUTE sp_rename N'dbo.Tmp_ShowProvider', N'ShowProvider', 'OBJECT'
GO
ALTER TABLE dbo.ShowProvider ADD CONSTRAINT
	PK_ShowProvider PRIMARY KEY CLUSTERED 
	(
	ShowProviderID
	) ON [PRIMARY]

GO
CREATE NONCLUSTERED INDEX IX_ShowProvider_ShowID ON dbo.ShowProvider
	(
	ShowID
	) ON [PRIMARY]
GO
CREATE NONCLUSTERED INDEX IX_ShowProvider_ProviderID ON dbo.ShowProvider
	(
	ProviderID
	) ON [PRIMARY]
GO
ALTER TABLE dbo.ShowProvider WITH NOCHECK ADD CONSTRAINT
	FK_ShowProvider_Show FOREIGN KEY
	(
	ShowID
	) REFERENCES dbo.Show
	(
	ShowID
	) ON UPDATE CASCADE
	 ON DELETE CASCADE
	
GO
ALTER TABLE dbo.ShowProvider WITH NOCHECK ADD CONSTRAINT
	FK_ShowProvider_Provider FOREIGN KEY
	(
	ProviderID
	) REFERENCES dbo.Provider
	(
	ProviderID
	) ON UPDATE CASCADE
	 ON DELETE CASCADE
	
GO
COMMIT

--//////////////////////////////////////////////////////////////////////////////

insert into Category (CategoryID, Name)
values ('daily', 'Daily')
GO

--//////////////////////////////////////////////////////////////////////////////
--//////////////////////////////////////////////////////////////////////////////
