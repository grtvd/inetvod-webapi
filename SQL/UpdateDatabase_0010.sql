--//////////////////////////////////////////////////////////////////////////////
-- Copyright © 2006 iNetVOD, Inc. All Rights Reserved.
-- iNetVOD Confidential and Proprietary.  See LEGAL.txt.
--//////////////////////////////////////////////////////////////////////////////

use [iNetVOD]
GO

--//////////////////////////////////////////////////////////////////////////////

BEGIN TRANSACTION

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[SerialNumber]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[SerialNumber]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[SerialNumber_Get]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[SerialNumber_Get]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[SerialNumber_Update]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[SerialNumber_Update]
GO

COMMIT

--//////////////////////////////////////////////////////////////////////////////

insert MemberPrefs (MemberID, IncludeAdult, AdultPIN, IncludeRatingIDList, IncludeDownload, IncludeStreaming, ConnectionSpeed)
values ('52273fbc-b0a5-4f12-a5e9-96a310bd76da', 'Always', null, null, 1, 1, '1500K')
go


--//////////////////////////////////////////////////////////////////////////////
--//////////////////////////////////////////////////////////////////////////////
