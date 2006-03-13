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

ALTER TABLE dbo.Provider ADD
	RequestURL varchar(4096) NULL,
	AdminUserID varchar(128) NULL,
	AdminPassword varchar(32) NULL
GO

update Provider set AdminUserID = 'super', AdminPassword = 'superpassword'
update Provider set RequestURL = 'http://api.inetvod.com/provider_excellentvideos/providerapi' where ProviderID = 'excellentvideos'
update Provider set RequestURL = 'http://api.inetvod.com/provider_internetvideos/providerapi' where ProviderID = 'internetvideos'
update Provider set RequestURL = 'http://api.inetvod.com/provider_moviesmovies/providerapi' where ProviderID = 'moviesmovies'
update Provider set RequestURL = 'http://api.inetvod.com/provider_vodflicks/providerapi' where ProviderID = 'vodflicks'
GO

COMMIT

--//////////////////////////////////////////////////////////////////////////////
--//////////////////////////////////////////////////////////////////////////////
