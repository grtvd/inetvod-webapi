--//////////////////////////////////////////////////////////////////////////////
-- Copyright © 2005-2006 iNetVOD, Inc. All Rights Reserved.
-- iNetVOD Confidential and Proprietary.  See LEGAL.txt.
--//////////////////////////////////////////////////////////////////////////////

-- CHANGES
-- Added ShowProvider.ShowCost_RentalWindowDays
-- Renamed ShowProvider.ShowCost_RentalHours to ShowCost_RentalPeriodHours
-- Added RentedShow.ShowCost_RentalWindowDays
-- Renamed RentedShow.ShowCost_RentalHours to ShowCost_RentalPeriodHours
-- CHANGES

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
BEGIN TRANSACTION
ALTER TABLE dbo.ShowProvider
	DROP CONSTRAINT FK_ShowProvider_Show
GO
COMMIT
BEGIN TRANSACTION
CREATE TABLE dbo.Tmp_ShowProvider
	(
	ShowProviderID varchar(64) NOT NULL,
	ShowID varchar(64) NOT NULL,
	ProviderID varchar(64) NOT NULL,
	ProviderShowID varchar(64) NOT NULL,
	ShowCost_ShowCostType varchar(32) NOT NULL,
	ShowCost_Cost_CurrencyID varchar(3) NULL,
	ShowCost_Cost_Amount decimal(17, 2) NULL,
	ShowCost_CostDisplay varchar(32) NOT NULL,
	ShowCost_RentalWindowDays smallint NULL,
	ShowCost_RentalPeriodHours smallint NULL
	)  ON [PRIMARY]
GO
IF EXISTS(SELECT * FROM dbo.ShowProvider)
	 EXEC('INSERT INTO dbo.Tmp_ShowProvider (ShowProviderID, ShowID, ProviderID, ProviderShowID, ShowCost_ShowCostType, ShowCost_Cost_CurrencyID, ShowCost_Cost_Amount, ShowCost_CostDisplay, ShowCost_RentalPeriodHours)
		SELECT ShowProviderID, ShowID, ProviderID, ProviderShowID, ShowCost_ShowCostType, ShowCost_Cost_CurrencyID, ShowCost_Cost_Amount, ShowCost_CostDisplay, ShowCost_RentalHours FROM dbo.ShowProvider TABLOCKX')
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

BEGIN TRANSACTION
ALTER TABLE dbo.RentedShow
	DROP CONSTRAINT FK_RentedShow_Provider
GO
COMMIT
BEGIN TRANSACTION
ALTER TABLE dbo.RentedShow
	DROP CONSTRAINT FK_RentedShow_Show
GO
COMMIT
BEGIN TRANSACTION
ALTER TABLE dbo.RentedShow
	DROP CONSTRAINT FK_RentedShow_Member
GO
COMMIT
BEGIN TRANSACTION
CREATE TABLE dbo.Tmp_RentedShow
	(
	RentedShowID varchar(64) NOT NULL,
	MemberID varchar(64) NOT NULL,
	ShowID varchar(64) NOT NULL,
	ProviderID varchar(64) NOT NULL,
	ShowURL varchar(4096) NOT NULL,
	ShowCost_ShowCostType varchar(32) NOT NULL,
	ShowCost_Cost_CurrencyID varchar(3) NULL,
	ShowCost_Cost_Amount decimal(17, 2) NULL,
	ShowCost_CostDisplay varchar(32) NOT NULL,
	ShowCost_RentalWindowDays smallint NULL,
	ShowCost_RentalPeriodHours smallint NULL,
	RentedOn datetime NOT NULL,
	AvailableUntil datetime NULL
	)  ON [PRIMARY]
GO
IF EXISTS(SELECT * FROM dbo.RentedShow)
	 EXEC('INSERT INTO dbo.Tmp_RentedShow (RentedShowID, MemberID, ShowID, ProviderID, ShowURL, ShowCost_ShowCostType, ShowCost_Cost_CurrencyID, ShowCost_Cost_Amount, ShowCost_CostDisplay, ShowCost_RentalPeriodHours, RentedOn, AvailableUntil)
		SELECT RentedShowID, MemberID, ShowID, ProviderID, ShowURL, ShowCost_ShowCostType, ShowCost_Cost_CurrencyID, ShowCost_Cost_Amount, ShowCost_CostDisplay, ShowCost_RentalHours, RentedOn, AvailableUntil FROM dbo.RentedShow TABLOCKX')
GO
DROP TABLE dbo.RentedShow
GO
EXECUTE sp_rename N'dbo.Tmp_RentedShow', N'RentedShow', 'OBJECT'
GO
ALTER TABLE dbo.RentedShow ADD CONSTRAINT
	PK_RentedShow PRIMARY KEY CLUSTERED
	(
	RentedShowID
	) ON [PRIMARY]

GO
CREATE NONCLUSTERED INDEX IX_RentedShow_MemberID ON dbo.RentedShow
	(
	MemberID
	) ON [PRIMARY]
GO
CREATE NONCLUSTERED INDEX IX_RentedShow_ShowID ON dbo.RentedShow
	(
	ShowID
	) ON [PRIMARY]
GO
CREATE NONCLUSTERED INDEX IX_RentedShow_ProviderID ON dbo.RentedShow
	(
	ProviderID
	) ON [PRIMARY]
GO
ALTER TABLE dbo.RentedShow WITH NOCHECK ADD CONSTRAINT
	FK_RentedShow_Member FOREIGN KEY
	(
	MemberID
	) REFERENCES dbo.Member
	(
	MemberID
	) ON UPDATE CASCADE
	 ON DELETE CASCADE

GO
ALTER TABLE dbo.RentedShow WITH NOCHECK ADD CONSTRAINT
	FK_RentedShow_Show FOREIGN KEY
	(
	ShowID
	) REFERENCES dbo.Show
	(
	ShowID
	) ON UPDATE CASCADE
	 ON DELETE CASCADE

GO
ALTER TABLE dbo.RentedShow WITH NOCHECK ADD CONSTRAINT
	FK_RentedShow_Provider FOREIGN KEY
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
--//////////////////////////////////////////////////////////////////////////////
