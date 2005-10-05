--//////////////////////////////////////////////////////////////////////////////
-- Copyright � 2005 iNetVOD, Inc. All Rights Reserved.
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

--//////////////////////////////////////////////////////////////////////////////

EXECUTE sp_rename N'dbo.ShowProvider.RentalHours', N'Tmp_RentalHours', 'COLUMN'
GO
EXECUTE sp_rename N'dbo.ShowProvider.Tmp_RentalHours', N'ShowCost_RentalHours', 'COLUMN'
GO

--//////////////////////////////////////////////////////////////////////////////

EXECUTE sp_rename N'dbo.RentedShow.RentalHours', N'Tmp_RentalHours', 'COLUMN'
GO
EXECUTE sp_rename N'dbo.RentedShow.Tmp_RentalHours', N'ShowCost_RentalHours', 'COLUMN'
GO

--//////////////////////////////////////////////////////////////////////////////

COMMIT

--//////////////////////////////////////////////////////////////////////////////

