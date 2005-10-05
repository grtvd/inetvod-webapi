--//////////////////////////////////////////////////////////////////////////////
-- Copyright © 2005 iNetVOD, Inc. All Rights Reserved.
-- Confidential and Proprietary
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
	@MemberID varchar(64),
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
	@MemberID varchar(64),
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
		ShowCost_Cost_CurrencyID, ShowCost_Cost_Amount, ShowCost_CostDisplay, ShowCost_RentalHours
	from ShowProvider
	where (ShowID = @ShowID)
	and (ProviderID = @ProviderID)
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.ShowProvider_Search
	@PartialName varchar(64)
AS
	select sp.ShowProviderID, sp.ShowID, ProviderID, ProviderShowID,
		ShowCost_ShowCostType, ShowCost_Cost_CurrencyID, ShowCost_Cost_Amount,
		ShowCost_CostDisplay, ShowCost_RentalHours
	from ShowProvider sp
	join Show s on s.ShowID = sp.ShowID
	where s.Name like '%' + isnull(@PartialName, '') + '%'
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.ShowProvider_GetByProviderID
	@ProviderID varchar(32)
AS
	select ShowProviderID, ShowID, ProviderID, ProviderShowID,
		ShowCost_ShowCostType, ShowCost_Cost_CurrencyID, ShowCost_Cost_Amount,
		ShowCost_CostDisplay, ShowCost_RentalHours
	from ShowProvider
	where ProviderID = @ProviderID
GO

--//////////////////////////////////////////////////////////////////////////////

CREATE PROCEDURE dbo.ShowProvider_GetByCategoryID
	@CategoryID varchar(32)
AS
	select sp.ShowProviderID, sp.ShowID, ProviderID, ProviderShowID,
		ShowCost_ShowCostType, ShowCost_Cost_CurrencyID, ShowCost_Cost_Amount,
		ShowCost_CostDisplay, ShowCost_RentalHours
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
		ShowCost_Cost_CurrencyID,
		ShowCost_Cost_Amount,
		ShowCost_CostDisplay,
		ShowCost_RentalHours,
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
	@ShowCost_Cost_CurrencyID varchar(3),
	@ShowCost_Cost_Amount decimal(17,2),
	@ShowCost_CostDisplay varchar(32),
	@ShowCost_RentalHours smallint,
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
		ShowCost_Cost_CurrencyID,
		ShowCost_Cost_Amount,
		ShowCost_CostDisplay,
		ShowCost_RentalHours,
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
		@ShowCost_Cost_CurrencyID,
		@ShowCost_Cost_Amount,
		@ShowCost_CostDisplay,
		@ShowCost_RentalHours,
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
	@ShowCost_Cost_CurrencyID varchar(3),
	@ShowCost_Cost_Amount decimal(17,2),
	@ShowCost_CostDisplay varchar(32),
	@ShowCost_RentalHours smallint,
	@RentedOn datetime,
	@AvailableUntil datetime
AS
	update RentedShow set
		--MemberID = @MemberID,
		--ShowID = @ShowID,
		--ProviderID = @ProviderID,
		ShowURL = @ShowURL,
		ShowCost_ShowCostType = @ShowCost_ShowCostType,
		ShowCost_Cost_CurrencyID = @ShowCost_Cost_CurrencyID,
		ShowCost_Cost_Amount = @ShowCost_Cost_Amount,
		ShowCost_CostDisplay = @ShowCost_CostDisplay,
		ShowCost_RentalHours = @ShowCost_RentalHours,
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
		ShowCost_Cost_CurrencyID,
		ShowCost_Cost_Amount,
		ShowCost_CostDisplay,
		ShowCost_RentalHours,
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

