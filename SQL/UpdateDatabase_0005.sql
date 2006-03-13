--//////////////////////////////////////////////////////////////////////////////
-- Copyright © 2005-2006 iNetVOD, Inc. All Rights Reserved.
-- iNetVOD Confidential and Proprietary.  See LEGAL.txt.
--//////////////////////////////////////////////////////////////////////////////

use [iNetVOD]
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
