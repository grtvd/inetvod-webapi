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

EXECUTE sp_rename N'dbo.ShowProvider.ShowCost_Money_CurrencyID', N'Tmp_ShowCost_Cost_CurrencyID', 'COLUMN'
GO
EXECUTE sp_rename N'dbo.ShowProvider.ShowCost_Money_Amount', N'Tmp_ShowCost_Cost_Amount_1', 'COLUMN'
GO
EXECUTE sp_rename N'dbo.ShowProvider.ShowCost_Description', N'Tmp_ShowCost_CostDisplay', 'COLUMN'
GO
EXECUTE sp_rename N'dbo.ShowProvider.Tmp_ShowCost_Cost_CurrencyID', N'ShowCost_Cost_CurrencyID', 'COLUMN'
GO
EXECUTE sp_rename N'dbo.ShowProvider.Tmp_ShowCost_Cost_Amount_1', N'ShowCost_Cost_Amount', 'COLUMN'
GO
EXECUTE sp_rename N'dbo.ShowProvider.Tmp_ShowCost_CostDisplay', N'ShowCost_CostDisplay', 'COLUMN'
GO

--//////////////////////////////////////////////////////////////////////////////

EXECUTE sp_rename N'dbo.RentedShow.ShowCost_Money_CurrencyID', N'Tmp_ShowCost_Cost_CurrencyID_3', 'COLUMN'
GO
EXECUTE sp_rename N'dbo.RentedShow.ShowCost_Money_Amount', N'Tmp_ShowCost_Cost_Amount_4', 'COLUMN'
GO
EXECUTE sp_rename N'dbo.RentedShow.ShowCost_Description', N'Tmp_ShowCost_CostDisplay_5', 'COLUMN'
GO
EXECUTE sp_rename N'dbo.RentedShow.Tmp_ShowCost_Cost_CurrencyID_3', N'ShowCost_Cost_CurrencyID', 'COLUMN'
GO
EXECUTE sp_rename N'dbo.RentedShow.Tmp_ShowCost_Cost_Amount_4', N'ShowCost_Cost_Amount', 'COLUMN'
GO
EXECUTE sp_rename N'dbo.RentedShow.Tmp_ShowCost_CostDisplay_5', N'ShowCost_CostDisplay', 'COLUMN'
GO

--//////////////////////////////////////////////////////////////////////////////

COMMIT

--//////////////////////////////////////////////////////////////////////////////

