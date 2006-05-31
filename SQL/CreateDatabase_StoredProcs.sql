--//////////////////////////////////////////////////////////////////////////////
-- Copyright © 2005-2006 iNetVOD, Inc. All Rights Reserved.
-- iNetVOD Confidential and Proprietary.  See LEGAL.txt.
--//////////////////////////////////////////////////////////////////////////////

use [iNetVOD]
GO

--//////////////////////////////////////////////////////////////////////////////

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[Provider_Get]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[Provider_Get]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[Provider_GetAll]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[Provider_GetAll]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ProviderConnection_Get]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[ProviderConnection_Get]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ProviderConnection_Insert]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[ProviderConnection_Insert]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ProviderConnection_Update]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[ProviderConnection_Update]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ProviderConnection_Delete]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[ProviderConnection_Delete]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ProviderConnection_GetByProviderID]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[ProviderConnection_GetByProviderID]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ProviderConnection_GetByProviderIDConnectionType]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[ProviderConnection_GetByProviderIDConnectionType]
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

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[MemberLogon_Get]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[MemberLogon_Get]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[MemberLogon_Insert]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[MemberLogon_Insert]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[MemberLogon_Update]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[MemberLogon_Update]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[MemberLogon_Delete]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[MemberLogon_Delete]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[MemberLogon_GetByEmail]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[MemberLogon_GetByEmail]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[MemberLogon_GetByLogonIDPIN]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[MemberLogon_GetByLogonIDPIN]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[MemberAccount_Get]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[MemberAccount_Get]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[MemberAccount_Insert]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[MemberAccount_Insert]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[MemberAccount_Update]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[MemberAccount_Update]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[MemberAccount_Delete]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[MemberAccount_Delete]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[MemberPrefs_Get]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[MemberPrefs_Get]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[MemberPrefs_Insert]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[MemberPrefs_Insert]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[MemberPrefs_Update]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[MemberPrefs_Update]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[MemberPrefs_Delete]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[MemberPrefs_Delete]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[MemberSession_Get]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[MemberSession_Get]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[MemberSession_Insert]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[MemberSession_Insert]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[MemberSession_Update]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[MemberSession_Update]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[MemberSession_Delete]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[MemberSession_Delete]
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

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[Show_Insert]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[Show_Insert]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[Show_Update]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[Show_Update]
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

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[Show_GetByNameReleasedYear]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[Show_GetByNameReleasedYear]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[Show_GetByNameReleasedOn]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[Show_GetByNameReleasedOn]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ShowProvider_Insert]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[ShowProvider_Insert]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ShowProvider_Update]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[ShowProvider_Update]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ShowProvider_Delete]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[ShowProvider_Delete]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ShowProvider_GetByShowIDProviderID]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[ShowProvider_GetByShowIDProviderID]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ShowProvider_GetByShowID]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[ShowProvider_GetByShowID]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ShowProvider_GetByProviderIDProviderShowID]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[ShowProvider_GetByProviderIDProviderShowID]
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

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ShowProvider_MarkUnavailByProviderConnectionID]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[ShowProvider_MarkUnavailByProviderConnectionID]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ShowCategory_Insert]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[ShowCategory_Insert]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ShowCategory_Delete]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[ShowCategory_Delete]
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

CREATE PROCEDURE dbo.ProviderConnection_Get
	@ProviderConnectionID uniqueidentifier
AS
	select ProviderConnectionID, ProviderID, ProviderConnectionType, Disabled,
		ConnectionURL, AdminUserID, AdminPassword, UseFieldForName, UseFieldForEpisodeName
	from ProviderConnection
	where (ProviderConnectionID = @ProviderConnectionID)
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.ProviderConnection_Insert
	@ProviderConnectionID uniqueidentifier,
	@ProviderID varchar(64),
	@ProviderConnectionType varchar(16),
	@Disabled bit,
	@ConnectionURL varchar(4096),
	@AdminUserID varchar(128),
	@AdminPassword varchar(32),
	@UseFieldForName varchar(32),
	@UseFieldForEpisodeName varchar(32)
AS
	insert into ProviderConnection
	(
		ProviderConnectionID,
		ProviderID,
		ProviderConnectionType,
		Disabled,
		ConnectionURL,
		AdminUserID,
		AdminPassword,
		UseFieldForName,
		UseFieldForEpisodeName
	)
	values
	(
		@ProviderConnectionID,
		@ProviderID,
		@ProviderConnectionType,
		@Disabled,
		@ConnectionURL,
		@AdminUserID,
		@AdminPassword,
		@UseFieldForName,
		@UseFieldForEpisodeName
	)
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.ProviderConnection_Update
	@ProviderConnectionID uniqueidentifier,
	@ProviderID varchar(64),
	@ProviderConnectionType varchar(16),
	@Disabled bit,
	@ConnectionURL varchar(4096),
	@AdminUserID varchar(128),
	@AdminPassword varchar(32),
	@UseFieldForName varchar(32),
	@UseFieldForEpisodeName varchar(32)
AS
	update ProviderConnection set
		ProviderID = @ProviderID,
		ProviderConnectionType = @ProviderConnectionType,
		Disabled = @Disabled,
		ConnectionURL = @ConnectionURL,
		AdminUserID = @AdminUserID,
		AdminPassword = @AdminPassword,
		UseFieldForName = @UseFieldForName,
		UseFieldForEpisodeName = @UseFieldForEpisodeName
	where ProviderConnectionID = @ProviderConnectionID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.ProviderConnection_Delete
	@ProviderConnectionID uniqueidentifier
AS
	delete from ProviderConnection where ProviderConnectionID = @ProviderConnectionID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.ProviderConnection_GetByProviderID
	@ProviderID varchar(64)
AS
	select ProviderConnectionID, ProviderID, ProviderConnectionType, Disabled,
		ConnectionURL, AdminUserID, AdminPassword, UseFieldForName, UseFieldForEpisodeName
	from ProviderConnection
	where (ProviderID = @ProviderID)
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.ProviderConnection_GetByProviderIDConnectionType
	@ProviderID varchar(64),
	@ProviderConnectionType varchar(16)
AS
	select ProviderConnectionID, ProviderID, ProviderConnectionType, Disabled,
		ConnectionURL, AdminUserID, AdminPassword, UseFieldForName, UseFieldForEpisodeName
	from ProviderConnection
	where (ProviderID = @ProviderID)
	and (ProviderConnectionType = @ProviderConnectionType)
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
	@MemberID uniqueidentifier
AS
	select
		MemberID,
		FirstName,
		LastName
	from Member
	where MemberID = @MemberID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.Member_Insert
	@MemberID uniqueidentifier,
	@FirstName varchar(32),
	@LastName varchar(32)
AS
	insert into Member
	(
		MemberID,
		FirstName,
		LastName
	)
	values
	(
		@MemberID,
		@FirstName,
		@LastName
	)
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.Member_Update
	@MemberID uniqueidentifier,
	@FirstName varchar(32),
	@LastName varchar(32)
AS
	update Member set
		FirstName = @FirstName,
		LastName = @LastName
	where MemberID = @MemberID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.Member_Delete
	@MemberID uniqueidentifier
AS
	delete from Member where MemberID = @MemberID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.MemberLogon_Get
	@MemberID uniqueidentifier
AS
	select
		MemberID,
		Email,
		Password,
		LogonID,
		PIN,

		SecretQuestion,
		SecretAnswer,
		TermsAcceptedOn,
		TermsAcceptedVersion,
		LogonFailedAt,
		LogonFailedCount,
		LogonDisabled
	from MemberLogon
	where MemberID = @MemberID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.MemberLogon_Insert
	@MemberID uniqueidentifier,
	@Email varchar(64),
	@Password varchar(32),
	@LogonID int,
	@PIN varchar(32),

	@SecretQuestion varchar(64),
	@SecretAnswer varchar(32),
	@TermsAcceptedOn datetime,
	@TermsAcceptedVersion varchar(16),
	@LogonFailedAt datetime,
	@LogonFailedCount tinyint,
	@LogonDisabled bit
AS
	insert into MemberLogon
	(
		MemberID,
		EmailKey,
		Email,
		Password,
		--LogonID,
		PIN,

		SecretQuestion,
		SecretAnswer,
		TermsAcceptedOn,
		TermsAcceptedVersion,
		LogonFailedAt,
		LogonFailedCount,
		LogonDisabled
	)
	values
	(
		@MemberID,
		upper(@Email),
		@Email,
		@Password,
		--@LogonID,
		@PIN,

		@SecretQuestion,
		@SecretAnswer,
		@TermsAcceptedOn,
		@TermsAcceptedVersion,
		@LogonFailedAt,
		@LogonFailedCount,
		@LogonDisabled
	)
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.MemberLogon_Update
	@MemberID uniqueidentifier,
	@Email varchar(64),
	@Password varchar(32),
	@LogonID int,
	@PIN varchar(32),

	@SecretQuestion varchar(64),
	@SecretAnswer varchar(32),
	@TermsAcceptedOn datetime,
	@TermsAcceptedVersion varchar(16),
	@LogonFailedAt datetime,
	@LogonFailedCount tinyint,
	@LogonDisabled bit
AS
	update MemberLogon set
		EmailKey = upper(@Email),
		Email = @Email,
		Password = @Password,
		--LogonID = @LogonID,
		SecretQuestion = @SecretQuestion,
		SecretAnswer = @SecretAnswer,
		TermsAcceptedOn = @TermsAcceptedOn,
		TermsAcceptedVersion = @TermsAcceptedVersion,
		PIN = @PIN,
		LogonFailedAt = @LogonFailedAt,
		LogonFailedCount = @LogonFailedCount,
		LogonDisabled = @LogonDisabled
	where MemberID = @MemberID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.MemberLogon_Delete
	@MemberID uniqueidentifier
AS
	delete from MemberLogon where MemberID = @MemberID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.MemberLogon_GetByEmail
	@Email varchar(64)
AS
	declare @emailSrch varchar(64)
	set @emailSrch = upper(@Email)

	select
		MemberID,
		Email,
		Password,
		LogonID,
		PIN,

		SecretQuestion,
		SecretAnswer,
		TermsAcceptedOn,
		TermsAcceptedVersion,
		LogonFailedAt,
		LogonFailedCount,
		LogonDisabled
	from MemberLogon
	where (EmailKey = @emailSrch)
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.MemberLogon_GetByLogonIDPIN
	@LogonID int,
	@PIN varchar(32)
AS
	select
		MemberID,
		Email,
		Password,
		LogonID,
		PIN,

		SecretQuestion,
		SecretAnswer,
		TermsAcceptedOn,
		TermsAcceptedVersion,
		LogonFailedAt,
		LogonFailedCount,
		LogonDisabled
	from MemberLogon
	where (LogonID = @LogonID) and (PIN = @PIN)
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.MemberAccount_Get
	@MemberID uniqueidentifier
AS
	select
		MemberID,

		HomeAddress_AddrStreet1,
		HomeAddress_AddrStreet2,
		HomeAddress_City,
		HomeAddress_State,
		HomeAddress_PostalCode,
		HomeAddress_Country,
		HomeAddress_Phone,

		CreditCard_NameOnCC,
		CreditCard_CCType,
		CreditCard_CCNumber,
		CreditCard_CCSIC,
		CreditCard_ExpireDate,

		CreditCard_BillingAddress_AddrStreet1,
		CreditCard_BillingAddress_AddrStreet2,
		CreditCard_BillingAddress_City,
		CreditCard_BillingAddress_State,
		CreditCard_BillingAddress_PostalCode,
		CreditCard_BillingAddress_Country,
		CreditCard_BillingAddress_Phone,

		BirthDate
	from MemberAccount
	where MemberID = @MemberID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.MemberAccount_Insert
	@MemberID uniqueidentifier,

	@HomeAddress_AddrStreet1 varchar(64),
	@HomeAddress_AddrStreet2 varchar(64),
	@HomeAddress_City varchar(64),
	@HomeAddress_State varchar(64),
	@HomeAddress_PostalCode varchar(32),
	@HomeAddress_Country varchar(2),
	@HomeAddress_Phone varchar(32),

	@CreditCard_NameOnCC varchar(64),
	@CreditCard_CCType varchar(16),
	@CreditCard_CCNumber varchar(32),
	@CreditCard_CCSIC varchar(16),
	@CreditCard_ExpireDate varchar(16),

	@CreditCard_BillingAddress_AddrStreet1 varchar(64),
	@CreditCard_BillingAddress_AddrStreet2 varchar(64),
	@CreditCard_BillingAddress_City varchar(64),
	@CreditCard_BillingAddress_State varchar(64),
	@CreditCard_BillingAddress_PostalCode varchar(32),
	@CreditCard_BillingAddress_Country varchar(2),
	@CreditCard_BillingAddress_Phone varchar(32),

	@BirthDate datetime
AS
	insert into MemberAccount
	(
		MemberID,

		HomeAddress_AddrStreet1,
		HomeAddress_AddrStreet2,
		HomeAddress_City,
		HomeAddress_State,
		HomeAddress_PostalCode,
		HomeAddress_Country,
		HomeAddress_Phone,

		CreditCard_NameOnCC,
		CreditCard_CCType,
		CreditCard_CCNumber,
		CreditCard_CCSIC,
		CreditCard_ExpireDate,

		CreditCard_BillingAddress_AddrStreet1,
		CreditCard_BillingAddress_AddrStreet2,
		CreditCard_BillingAddress_City,
		CreditCard_BillingAddress_State,
		CreditCard_BillingAddress_PostalCode,
		CreditCard_BillingAddress_Country,
		CreditCard_BillingAddress_Phone,

		BirthDate
	)
	values
	(
		@MemberID,

		@HomeAddress_AddrStreet1,
		@HomeAddress_AddrStreet2,
		@HomeAddress_City,
		@HomeAddress_State,
		@HomeAddress_PostalCode,
		@HomeAddress_Country,
		@HomeAddress_Phone,

		@CreditCard_NameOnCC,
		@CreditCard_CCType,
		@CreditCard_CCNumber,
		@CreditCard_CCSIC,
		@CreditCard_ExpireDate,

		@CreditCard_BillingAddress_AddrStreet1,
		@CreditCard_BillingAddress_AddrStreet2,
		@CreditCard_BillingAddress_City,
		@CreditCard_BillingAddress_State,
		@CreditCard_BillingAddress_PostalCode,
		@CreditCard_BillingAddress_Country,
		@CreditCard_BillingAddress_Phone,

		@BirthDate
	)
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.MemberAccount_Update
	@MemberID uniqueidentifier,

	@HomeAddress_AddrStreet1 varchar(64),
	@HomeAddress_AddrStreet2 varchar(64),
	@HomeAddress_City varchar(64),
	@HomeAddress_State varchar(64),
	@HomeAddress_PostalCode varchar(32),
	@HomeAddress_Country varchar(2),
	@HomeAddress_Phone varchar(32),

	@CreditCard_NameOnCC varchar(64),
	@CreditCard_CCType varchar(16),
	@CreditCard_CCNumber varchar(32),
	@CreditCard_CCSIC varchar(16),
	@CreditCard_ExpireDate varchar(16),

	@CreditCard_BillingAddress_AddrStreet1 varchar(64),
	@CreditCard_BillingAddress_AddrStreet2 varchar(64),
	@CreditCard_BillingAddress_City varchar(64),
	@CreditCard_BillingAddress_State varchar(64),
	@CreditCard_BillingAddress_PostalCode varchar(32),
	@CreditCard_BillingAddress_Country varchar(2),
	@CreditCard_BillingAddress_Phone varchar(32),

	@BirthDate datetime
AS
	update MemberAccount set
		HomeAddress_AddrStreet1 = @HomeAddress_AddrStreet1,
		HomeAddress_AddrStreet2 = @HomeAddress_AddrStreet2,
		HomeAddress_City = @HomeAddress_City,
		HomeAddress_State = @HomeAddress_State,
		HomeAddress_PostalCode = @HomeAddress_PostalCode,
		HomeAddress_Country = @HomeAddress_Country,
		HomeAddress_Phone = @HomeAddress_Phone,

		CreditCard_NameOnCC = @CreditCard_NameOnCC,
		CreditCard_CCType = @CreditCard_CCType,
		CreditCard_CCNumber = @CreditCard_CCNumber,
		CreditCard_CCSIC = @CreditCard_CCSIC,
		CreditCard_ExpireDate = @CreditCard_ExpireDate,

		CreditCard_BillingAddress_AddrStreet1 = @CreditCard_BillingAddress_AddrStreet1,
		CreditCard_BillingAddress_AddrStreet2 = @CreditCard_BillingAddress_AddrStreet2,
		CreditCard_BillingAddress_City = @CreditCard_BillingAddress_City,
		CreditCard_BillingAddress_State = @CreditCard_BillingAddress_State,
		CreditCard_BillingAddress_PostalCode = @CreditCard_BillingAddress_PostalCode,
		CreditCard_BillingAddress_Country = @CreditCard_BillingAddress_Country,
		CreditCard_BillingAddress_Phone = @CreditCard_BillingAddress_Phone,

		BirthDate = @BirthDate
	where MemberID = @MemberID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.MemberAccount_Delete
	@MemberID uniqueidentifier
AS
	delete from MemberAccount where MemberID = @MemberID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.MemberPrefs_Get
	@MemberID uniqueidentifier
AS
	select
		MemberID,
		IncludeAdult,
		AdultPIN,
		IncludeRatingIDList,
		IncludeDownload,
		IncludeStreaming,
		ConnectionSpeed
	from MemberPrefs
	where MemberID = @MemberID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.MemberPrefs_Insert
	@MemberID uniqueidentifier,
	@IncludeAdult varchar(32),
	@AdultPIN varchar(32),
	@IncludeRatingIDList varchar(128),
	@IncludeDownload bit,
	@IncludeStreaming bit,
	@ConnectionSpeed varchar (32)
AS
	insert into MemberPrefs
	(
		MemberID,
		IncludeAdult,
		AdultPIN,
		IncludeRatingIDList,
		IncludeDownload,
		IncludeStreaming,
		ConnectionSpeed
	)
	values
	(
		@MemberID,
		@IncludeAdult,
		@AdultPIN,
		@IncludeRatingIDList,
		@IncludeDownload,
		@IncludeStreaming,
		@ConnectionSpeed
	)
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.MemberPrefs_Update
	@MemberID uniqueidentifier,
	@IncludeAdult varchar(32),
	@AdultPIN varchar(32),
	@IncludeRatingIDList varchar(128),
	@IncludeDownload bit,
	@IncludeStreaming bit,
	@ConnectionSpeed varchar (32)
AS
	update MemberPrefs set
		IncludeAdult = @IncludeAdult,
		AdultPIN = @AdultPIN,
		IncludeRatingIDList = @IncludeRatingIDList,
		IncludeDownload = @IncludeDownload,
		IncludeStreaming = @IncludeStreaming,
		ConnectionSpeed = @ConnectionSpeed
	where MemberID = @MemberID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.MemberPrefs_Delete
	@MemberID uniqueidentifier
AS
	delete from MemberPrefs where MemberID = @MemberID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.MemberSession_Get
	@MemberSessionID uniqueidentifier
AS
	select
		MemberSessionID,
		MemberID,
		PlayerID,
		StartedOn,
		ExpiresAt,
		ShowAdult
	from MemberSession
	where MemberSessionID = @MemberSessionID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.MemberSession_Insert
	@MemberSessionID uniqueidentifier,
	@MemberID uniqueidentifier,
	@PlayerID uniqueidentifier,
	@StartedOn datetime,
	@ExpiresAt datetime,
	@ShowAdult bit
AS
	insert into MemberSession
	(
		MemberSessionID,
		MemberID,
		PlayerID,
		StartedOn,
		ExpiresAt,
		ShowAdult
	)
	values
	(
		@MemberSessionID,
		@MemberID,
		@PlayerID,
		@StartedOn,
		@ExpiresAt,
		@ShowAdult
	)
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.MemberSession_Update
	@MemberSessionID uniqueidentifier,
	@MemberID uniqueidentifier,
	@PlayerID uniqueidentifier,
	@StartedOn datetime,
	@ExpiresAt datetime,
	@ShowAdult bit
AS
	update MemberSession set
		MemberID = @MemberID,
		PlayerID = @PlayerID,
		StartedOn = @StartedOn,
		ExpiresAt = @ExpiresAt,
		ShowAdult = @ShowAdult
	where MemberSessionID = @MemberSessionID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.MemberSession_Delete
	@MemberSessionID uniqueidentifier
AS
	delete from MemberSession where MemberSessionID = @MemberSessionID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.MemberProvider_Get
	@MemberProviderID uniqueidentifier
AS
	select MemberProviderID, MemberID, ProviderID, EncryptedUserName, EncryptedPassword
	from MemberProvider
	where (MemberProviderID = @MemberProviderID)
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.MemberProvider_Insert
	@MemberProviderID uniqueidentifier,
	@MemberID uniqueidentifier,
	@ProviderID varchar(64),
	@EncryptedUserName varchar(128),
	@EncryptedPassword varchar(32)
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
	@MemberProviderID uniqueidentifier,
	@MemberID uniqueidentifier,
	@ProviderID varchar(64),
	@EncryptedUserName varchar(128),
	@EncryptedPassword varchar(32)
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
	@MemberID uniqueidentifier,
	@ProviderID varchar(64)
AS
	select MemberProviderID, MemberID, ProviderID, EncryptedUserName, EncryptedPassword
	from MemberProvider
	where (MemberID = @MemberID)
	and (ProviderID = @ProviderID)
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.MemberProvider_GetByMemberID
	@MemberID uniqueidentifier
AS
	select MemberProviderID, MemberID, ProviderID, EncryptedUserName, EncryptedPassword
	from MemberProvider
	where (MemberID = @MemberID)
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.Show_Get
	@ShowID uniqueidentifier
AS
	select ShowID, Name, EpisodeName, EpisodeNumber, ReleasedOn, ReleasedYear,
		Description, RunningMins, PictureURL, RatingID, IsAdult
	from Show
	where ShowID = @ShowID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.Show_Insert
	@ShowID uniqueidentifier,
	@Name varchar(64),
	@EpisodeName varchar(64),
	@EpisodeNumber varchar(32),
	@ReleasedOn datetime,
	@ReleasedYear smallint,
	@Description text,
	@RunningMins smallint,
	@PictureURL varchar(4096),
	@RatingID varchar(32),
	@IsAdult bit
AS
	insert into Show
	(
		ShowID,
		Name,
		EpisodeName,
		EpisodeNumber,
		ReleasedOn,
		ReleasedYear,
		Description,
		RunningMins,
		PictureURL,
		RatingID,
		IsAdult
	)
	values
	(
		@ShowID,
		@Name,
		@EpisodeName,
		@EpisodeNumber,
		@ReleasedOn,
		@ReleasedYear,
		@Description,
		@RunningMins,
		@PictureURL,
		@RatingID,
		@IsAdult
	)
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.Show_Update
	@ShowID uniqueidentifier,
	@Name varchar(64),
	@EpisodeName varchar(64),
	@EpisodeNumber varchar(32),
	@ReleasedOn datetime,
	@ReleasedYear smallint,
	@Description text,
	@RunningMins smallint,
	@PictureURL varchar(4096),
	@RatingID varchar(32),
	@IsAdult bit
AS
	update Show set
		Name = @Name,
		EpisodeName = @EpisodeName,
		EpisodeNumber = @EpisodeNumber,
		ReleasedOn = @ReleasedOn,
		ReleasedYear = @ReleasedYear,
		Description = @Description,
		RunningMins = @RunningMins,
		PictureURL = @PictureURL,
		RatingID = @RatingID,
		IsAdult = @IsAdult
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
	@MemberID uniqueidentifier
AS
	select s.ShowID, s.Name, s.EpisodeName, s.EpisodeNumber, s.ReleasedOn, s.ReleasedYear,
		s.Description, s.RunningMins, s.PictureURL, s.RatingID, s.IsAdult
	from Show s
	join RentedShow rs on rs.ShowID = s.ShowID
	where rs.MemberID = @MemberID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.Show_GetByNameReleasedYear
	@Name varchar(64),
	@EpisodeName varchar(64),
	@ReleasedYear smallint
AS
	select ShowID, Name, EpisodeName, EpisodeNumber, ReleasedOn, ReleasedYear,
		Description, RunningMins, PictureURL, RatingID, IsAdult
	from Show
	where (Name = @Name) and (ReleasedYear = @ReleasedYear) and
	(
		((EpisodeName is null) and (@EpisodeName is null))
		or ((not EpisodeName is null) and (not @EpisodeName is null)
			and (EpisodeName = @EpisodeName))
	)
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.Show_GetByNameReleasedOn
	@Name varchar(64),
	@EpisodeName varchar(64),
	@ReleasedOn datetime
AS
	select ShowID, Name, EpisodeName, EpisodeNumber, ReleasedOn, ReleasedYear,
		Description, RunningMins, PictureURL, RatingID, IsAdult
	from Show
	where (Name = @Name) and (ReleasedOn = @ReleasedOn) and
	(
		((EpisodeName is null) and (@EpisodeName is null))
		or ((not EpisodeName is null) and (not @EpisodeName is null)
			and (EpisodeName = @EpisodeName))
	)
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.ShowProvider_Insert
	@ShowProviderID uniqueidentifier,
	@ShowID uniqueidentifier,
	@ProviderID varchar(64),
	@ProviderConnectionID uniqueidentifier,
	@ProviderShowID varchar(128),
	@ShowURL varchar(4096),
	@ShowFormatMime varchar(32),
	@ShowCost_ShowCostType varchar(32),
	@ShowCost_Cost_CurrencyID varchar(3),
	@ShowCost_Cost_Amount decimal(17,2),
	@ShowCost_CostDisplay varchar(32),
	@ShowCost_RentalWindowDays smallint,
	@ShowCost_RentalPeriodHours smallint,
	@ShowAvail varchar(32)
AS
	insert into ShowProvider
	(
		ShowProviderID,
		ShowID,
		ProviderID,
		ProviderConnectionID,
		ProviderShowID,
		ShowURL,
		ShowFormatMime,
		ShowCost_ShowCostType,
		ShowCost_Cost_CurrencyID,
		ShowCost_Cost_Amount,
		ShowCost_CostDisplay,
		ShowCost_RentalWindowDays,
		ShowCost_RentalPeriodHours,
		ShowAvail
	)
	values
	(
		@ShowProviderID,
		@ShowID,
		@ProviderID,
		@ProviderConnectionID,
		@ProviderShowID,
		@ShowURL,
		@ShowFormatMime,
		@ShowCost_ShowCostType,
		@ShowCost_Cost_CurrencyID,
		@ShowCost_Cost_Amount,
		@ShowCost_CostDisplay,
		@ShowCost_RentalWindowDays,
		@ShowCost_RentalPeriodHours,
		@ShowAvail
	)
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.ShowProvider_Update
	@ShowProviderID uniqueidentifier,
	@ShowID uniqueidentifier,
	@ProviderID varchar(64),
	@ProviderConnectionID uniqueidentifier,
	@ProviderShowID varchar(128),
	@ShowURL varchar(4096),
	@ShowFormatMime varchar(32),
	@ShowCost_ShowCostType varchar(32),
	@ShowCost_Cost_CurrencyID varchar(3),
	@ShowCost_Cost_Amount decimal(17,2),
	@ShowCost_CostDisplay varchar(32),
	@ShowCost_RentalWindowDays smallint,
	@ShowCost_RentalPeriodHours smallint,
	@ShowAvail varchar(32)
AS
	update ShowProvider set
		--ShowProviderID = @ShowProviderID,
		--ShowID = @ShowID,
		--ProviderID = @ProviderID,
		--ProviderConnectionID = @ProviderConnectionID,
		--ProviderShowID = @ProviderShowID,
		ShowURL = @ShowURL,
		ShowFormatMime = @ShowFormatMime,
		ShowCost_ShowCostType = @ShowCost_ShowCostType,
		ShowCost_Cost_CurrencyID = @ShowCost_Cost_CurrencyID,
		ShowCost_Cost_Amount = @ShowCost_Cost_Amount,
		ShowCost_CostDisplay = @ShowCost_CostDisplay,
		ShowCost_RentalWindowDays = @ShowCost_RentalWindowDays,
		ShowCost_RentalPeriodHours = @ShowCost_RentalPeriodHours,
		ShowAvail = @ShowAvail
	where ShowProviderID = @ShowProviderID
GO


--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.ShowProvider_Delete
	@ShowProviderID uniqueidentifier
AS
	delete from ShowProvider where ShowProviderID = @ShowProviderID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.ShowProvider_GetByShowIDProviderID
	@ShowID uniqueidentifier,
	@ProviderID varchar(64)
AS
	select ShowProviderID, ShowID, ProviderID, ProviderConnectionID, ProviderShowID,
		ShowURL, ShowFormatMime, ShowCost_ShowCostType, ShowCost_Cost_CurrencyID,
		ShowCost_Cost_Amount, ShowCost_CostDisplay, ShowCost_RentalWindowDays,
		ShowCost_RentalPeriodHours, ShowAvail
	from ShowProvider
	where (ShowID = @ShowID)
	and (ProviderID = @ProviderID)
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.ShowProvider_GetByShowID
	@ShowID uniqueidentifier
AS
	select ShowProviderID, ShowID, ProviderID, ProviderConnectionID, ProviderShowID,
		ShowURL, ShowFormatMime, ShowCost_ShowCostType, ShowCost_Cost_CurrencyID,
		ShowCost_Cost_Amount, ShowCost_CostDisplay, ShowCost_RentalWindowDays,
		ShowCost_RentalPeriodHours, ShowAvail
	from ShowProvider
	where (ShowID = @ShowID)
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.ShowProvider_GetByProviderIDProviderShowID
	@ProviderID varchar(64),
	@ProviderShowID varchar(128)
AS
	select ShowProviderID, ShowID, ProviderID, ProviderConnectionID, ProviderShowID,
		ShowURL, ShowFormatMime, ShowCost_ShowCostType, ShowCost_Cost_CurrencyID,
		ShowCost_Cost_Amount, ShowCost_CostDisplay, ShowCost_RentalWindowDays,
		ShowCost_RentalPeriodHours, ShowAvail
	from ShowProvider
	where (ProviderID = @ProviderID)
	and (ProviderShowID = @ProviderShowID)
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.ShowProvider_Search
	@PartialName varchar(64)
AS
	select sp.ShowProviderID, sp.ShowID, ProviderID, ProviderConnectionID,
		ProviderShowID, ShowURL, ShowFormatMime, ShowCost_ShowCostType,
		ShowCost_Cost_CurrencyID, ShowCost_Cost_Amount, ShowCost_CostDisplay,
		ShowCost_RentalWindowDays, ShowCost_RentalPeriodHours, ShowAvail
	from ShowProvider sp
	join Show s on s.ShowID = sp.ShowID
	where s.Name like '%' + isnull(@PartialName, '') + '%'
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.ShowProvider_GetByProviderID
	@ProviderID varchar(64)
AS
	select ShowProviderID, ShowID, ProviderID, ProviderConnectionID, ProviderShowID,
		ShowURL, ShowFormatMime, ShowCost_ShowCostType, ShowCost_Cost_CurrencyID,
		ShowCost_Cost_Amount, ShowCost_CostDisplay, ShowCost_RentalWindowDays,
		ShowCost_RentalPeriodHours, ShowAvail
	from ShowProvider
	where ProviderID = @ProviderID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.ShowProvider_GetByCategoryID
	@CategoryID varchar(32)
AS
	select sp.ShowProviderID, sp.ShowID, ProviderID, ProviderConnectionID,
		ProviderShowID, ShowURL, ShowFormatMime, ShowCost_ShowCostType,
		ShowCost_Cost_CurrencyID, ShowCost_Cost_Amount, ShowCost_CostDisplay,
		ShowCost_RentalWindowDays, ShowCost_RentalPeriodHours, ShowAvail
	from ShowProvider sp
	join ShowCategory sc on sc.ShowID = sp.ShowID
	where sc.CategoryID = @CategoryID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.ShowProvider_MarkUnavailByProviderConnectionID
	@ProviderConnectionID uniqueidentifier
AS
	update ShowProvider
		set ShowAvail = 'Unavailable'
	where ProviderConnectionID = @ProviderConnectionID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.ShowCategory_Insert
	@ShowCategoryID uniqueidentifier,
	@ShowID uniqueidentifier,
	@CategoryID varchar(32)
AS
	insert into ShowCategory
	(
		ShowCategoryID,
		ShowID,
		CategoryID
	)
	values
	(
		@ShowCategoryID,
		@ShowID,
		@CategoryID
	)
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.ShowCategory_Delete
	@ShowCategoryID uniqueidentifier
AS
	delete from ShowCategory where ShowCategoryID = @ShowCategoryID
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
	@ShowID uniqueidentifier
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
	@MemberID uniqueidentifier
AS
	select sc.ShowCategoryID, sc.ShowID, sc.CategoryID
	from ShowCategory sc
	join Show s on s.ShowID = sc.ShowID
	join RentedShow rs on rs.ShowID = s.ShowID
	where rs.MemberID = @MemberID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.RentedShow_Get
	@RentedShowID uniqueidentifier
AS
	select
		RentedShowID,
		MemberID,
		ShowID,
		ProviderID,
		ProviderConnectionID,
		ShowURL,
		ShowCost_ShowCostType,
		ShowCost_Cost_CurrencyID,
		ShowCost_Cost_Amount,
		ShowCost_CostDisplay,
		ShowCost_RentalWindowDays,
		ShowCost_RentalPeriodHours,
		RentedOn,
		AvailableUntil
	from RentedShow
	where RentedShowID = @RentedShowID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.RentedShow_Insert
	@RentedShowID uniqueidentifier,
	@MemberID uniqueidentifier,
	@ShowID uniqueidentifier,
	@ProviderID varchar(64),
	@ProviderConnectionID uniqueidentifier,
	@ShowURL varchar(4096),
	@ShowCost_ShowCostType varchar(32),
	@ShowCost_Cost_CurrencyID varchar(3),
	@ShowCost_Cost_Amount decimal(17,2),
	@ShowCost_CostDisplay varchar(32),
	@ShowCost_RentalWindowDays smallint,
	@ShowCost_RentalPeriodHours smallint,
	@RentedOn datetime,
	@AvailableUntil datetime
AS
	insert into RentedShow
	(
		RentedShowID,
		MemberID,
		ShowID,
		ProviderID,
		ProviderConnectionID,
		ShowURL,
		ShowCost_ShowCostType,
		ShowCost_Cost_CurrencyID,
		ShowCost_Cost_Amount,
		ShowCost_CostDisplay,
		ShowCost_RentalWindowDays,
		ShowCost_RentalPeriodHours,
		RentedOn,
		AvailableUntil
	)
	values
	(
		@RentedShowID,
		@MemberID,
		@ShowID,
		@ProviderID,
		@ProviderConnectionID,
		@ShowURL,
		@ShowCost_ShowCostType,
		@ShowCost_Cost_CurrencyID,
		@ShowCost_Cost_Amount,
		@ShowCost_CostDisplay,
		@ShowCost_RentalWindowDays,
		@ShowCost_RentalPeriodHours,
		@RentedOn,
		@AvailableUntil
	)
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.RentedShow_Update
	@RentedShowID uniqueidentifier,
	@MemberID uniqueidentifier,
	@ShowID uniqueidentifier,
	@ProviderID varchar(64),
	@ProviderConnectionID uniqueidentifier,
	@ShowURL varchar(4096),
	@ShowCost_ShowCostType varchar(32),
	@ShowCost_Cost_CurrencyID varchar(3),
	@ShowCost_Cost_Amount decimal(17,2),
	@ShowCost_CostDisplay varchar(32),
	@ShowCost_RentalWindowDays smallint,
	@ShowCost_RentalPeriodHours smallint,
	@RentedOn datetime,
	@AvailableUntil datetime
AS
	update RentedShow set
		--MemberID = @MemberID,
		--ShowID = @ShowID,
		--ProviderID = @ProviderID,
		--ProviderConnectionID = @ProviderConnectionID,
		ShowURL = @ShowURL,
		ShowCost_ShowCostType = @ShowCost_ShowCostType,
		ShowCost_Cost_CurrencyID = @ShowCost_Cost_CurrencyID,
		ShowCost_Cost_Amount = @ShowCost_Cost_Amount,
		ShowCost_CostDisplay = @ShowCost_CostDisplay,
		ShowCost_RentalWindowDays = @ShowCost_RentalWindowDays,
		ShowCost_RentalPeriodHours = @ShowCost_RentalPeriodHours,
		RentedOn = @RentedOn,
		AvailableUntil = @AvailableUntil
	where RentedShowID = @RentedShowID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.RentedShow_Delete
	@RentedShowID uniqueidentifier
AS
	delete from RentedShow where RentedShowID = @RentedShowID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.RentedShow_GetByMemberID
	@MemberID uniqueidentifier
AS
	select
		RentedShowID,
		MemberID,
		ShowID,
		ProviderID,
		ProviderConnectionID,
		ShowURL,
		ShowCost_ShowCostType,
		ShowCost_Cost_CurrencyID,
		ShowCost_Cost_Amount,
		ShowCost_CostDisplay,
		ShowCost_RentalWindowDays,
		ShowCost_RentalPeriodHours,
		RentedOn,
		AvailableUntil
	from RentedShow
	where MemberID = @MemberID
GO

--//////////////////////////////////////////////////////////////////////////////

GRANT EXECUTE ON [dbo].[Provider_Get] TO [inetvod]
GRANT EXECUTE ON [dbo].[Provider_GetAll] TO [inetvod]

GRANT EXECUTE ON [dbo].[ProviderConnection_Get] TO [inetvod]
GRANT EXECUTE ON [dbo].[ProviderConnection_Insert] TO [inetvod]
GRANT EXECUTE ON [dbo].[ProviderConnection_Update] TO [inetvod]
GRANT EXECUTE ON [dbo].[ProviderConnection_Delete] TO [inetvod]
GRANT EXECUTE ON [dbo].[ProviderConnection_GetByProviderID] TO [inetvod]
GRANT EXECUTE ON [dbo].[ProviderConnection_GetByProviderIDConnectionType] TO [inetvod]

GRANT EXECUTE ON [dbo].[Category_Get] TO [inetvod]
GRANT EXECUTE ON [dbo].[Category_GetAll] TO [inetvod]

GRANT EXECUTE ON [dbo].[Rating_Get] TO [inetvod]
GRANT EXECUTE ON [dbo].[Rating_GetAll] TO [inetvod]

GRANT EXECUTE ON [dbo].[Member_Get] TO [inetvod]
GRANT EXECUTE ON [dbo].[Member_Insert] TO [inetvod]
GRANT EXECUTE ON [dbo].[Member_Update] TO [inetvod]
GRANT EXECUTE ON [dbo].[Member_Delete] TO [inetvod]

GRANT EXECUTE ON [dbo].[MemberLogon_Get] TO [inetvod]
GRANT EXECUTE ON [dbo].[MemberLogon_Insert] TO [inetvod]
GRANT EXECUTE ON [dbo].[MemberLogon_Update] TO [inetvod]
GRANT EXECUTE ON [dbo].[MemberLogon_Delete] TO [inetvod]
GRANT EXECUTE ON [dbo].[MemberLogon_GetByEmail] TO [inetvod]
GRANT EXECUTE ON [dbo].[MemberLogon_GetByLogonIDPIN] TO [inetvod]

GRANT EXECUTE ON [dbo].[MemberAccount_Get] TO [inetvod]
GRANT EXECUTE ON [dbo].[MemberAccount_Insert] TO [inetvod]
GRANT EXECUTE ON [dbo].[MemberAccount_Update] TO [inetvod]
GRANT EXECUTE ON [dbo].[MemberAccount_Delete] TO [inetvod]

GRANT EXECUTE ON [dbo].[MemberPrefs_Get] TO [inetvod]
GRANT EXECUTE ON [dbo].[MemberPrefs_Insert] TO [inetvod]
GRANT EXECUTE ON [dbo].[MemberPrefs_Update] TO [inetvod]
GRANT EXECUTE ON [dbo].[MemberPrefs_Delete] TO [inetvod]

GRANT EXECUTE ON [dbo].[MemberSession_Get] TO [inetvod]
GRANT EXECUTE ON [dbo].[MemberSession_Insert] TO [inetvod]
GRANT EXECUTE ON [dbo].[MemberSession_Update] TO [inetvod]
GRANT EXECUTE ON [dbo].[MemberSession_Delete] TO [inetvod]

GRANT EXECUTE ON [dbo].[MemberProvider_Get] TO [inetvod]
GRANT EXECUTE ON [dbo].[MemberProvider_Insert] TO [inetvod]
GRANT EXECUTE ON [dbo].[MemberProvider_Update] TO [inetvod]
GRANT EXECUTE ON [dbo].[MemberProvider_GetByMemberIDProviderID] TO [inetvod]
GRANT EXECUTE ON [dbo].[MemberProvider_GetByMemberID] TO [inetvod]

GRANT EXECUTE ON [dbo].[Show_Get] TO [inetvod]
GRANT EXECUTE ON [dbo].[Show_Insert] TO [inetvod]
GRANT EXECUTE ON [dbo].[Show_Update] TO [inetvod]
GRANT EXECUTE ON [dbo].[Show_Search] TO [inetvod]
GRANT EXECUTE ON [dbo].[Show_GetByProviderID] TO [inetvod]
GRANT EXECUTE ON [dbo].[Show_GetByCategoryID] TO [inetvod]
GRANT EXECUTE ON [dbo].[Show_GetByRentedShowMemberID] TO [inetvod]
GRANT EXECUTE ON [dbo].[Show_GetByNameReleasedYear] TO [inetvod]
GRANT EXECUTE ON [dbo].[Show_GetByNameReleasedOn] TO [inetvod]

GRANT EXECUTE ON [dbo].[ShowProvider_Insert] TO [inetvod]
GRANT EXECUTE ON [dbo].[ShowProvider_Update] TO [inetvod]
GRANT EXECUTE ON [dbo].[ShowProvider_Delete] TO [inetvod]
GRANT EXECUTE ON [dbo].[ShowProvider_GetByShowIDProviderID] TO [inetvod]
GRANT EXECUTE ON [dbo].[ShowProvider_GetByShowID] TO [inetvod]
GRANT EXECUTE ON [dbo].[ShowProvider_GetByProviderIDProviderShowID] TO [inetvod]
GRANT EXECUTE ON [dbo].[ShowProvider_Search] TO [inetvod]
GRANT EXECUTE ON [dbo].[ShowProvider_GetByProviderID] TO [inetvod]
GRANT EXECUTE ON [dbo].[ShowProvider_GetByCategoryID] TO [inetvod]
GRANT EXECUTE ON [dbo].[ShowProvider_MarkUnavailByProviderConnectionID] TO [inetvod]

GRANT EXECUTE ON [dbo].[ShowCategory_Insert] TO [inetvod]
GRANT EXECUTE ON [dbo].[ShowCategory_Delete] TO [inetvod]
GRANT EXECUTE ON [dbo].[ShowCategory_Search] TO [inetvod]
GRANT EXECUTE ON [dbo].[ShowCategory_GetByShowID] TO [inetvod]
GRANT EXECUTE ON [dbo].[ShowCategory_GetByCategoryID] TO [inetvod]
GRANT EXECUTE ON [dbo].[ShowCategory_GetByRentedShowMemberID] TO [inetvod]

GRANT EXECUTE ON [dbo].[RentedShow_Get] TO [inetvod]
GRANT EXECUTE ON [dbo].[RentedShow_Insert] TO [inetvod]
GRANT EXECUTE ON [dbo].[RentedShow_Update] TO [inetvod]
GRANT EXECUTE ON [dbo].[RentedShow_Delete] TO [inetvod]
GRANT EXECUTE ON [dbo].[RentedShow_GetByMemberID] TO [inetvod]

--//////////////////////////////////////////////////////////////////////////////

SET QUOTED_IDENTIFIER OFF
GO
SET ANSI_NULLS ON
GO

