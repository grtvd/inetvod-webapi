--//////////////////////////////////////////////////////////////////////////////
-- Copyright © 2005-2006 iNetVOD, Inc. All Rights Reserved.
-- Confidential and Proprietary
--//////////////////////////////////////////////////////////////////////////////

use inetvod

--//////////////////////////////////////////////////////////////////////////////

delete from RentedShow
delete from ShowProvider
delete from ShowCategory
delete from Show
delete from Provider
delete from Category
delete from Rating
delete from MemberPrefs
delete from MemberLogon
delete from Member
go

--//////////////////////////////////////////////////////////////////////////////

insert into Provider (ProviderID, Name, RequestURL, AdminUserID, AdminPassword)
values ('internetvideos', 'Internet Videos', 'http://localhost/provider_internetvideos/providerapi', 'super', 'superpassword')

insert into Provider (ProviderID, Name, RequestURL, AdminUserID, AdminPassword)
values ('moviesmovies', 'Movies, Movies', 'http://localhost/provider_moviesmovies/providerapi', 'super', 'superpassword')

insert into Provider (ProviderID, Name, RequestURL, AdminUserID, AdminPassword)
values ('vodflicks', 'VOD Flicks', 'http://localhost/provider_vodflicks/providerapi', 'super', 'superpassword')

insert into Provider (ProviderID, Name, RequestURL, AdminUserID, AdminPassword)
values ('excellentvideos', 'Excellent Videos', 'http://localhost/provider_excellentvideos/providerapi', 'super', 'superpassword')

insert into Provider (ProviderID, Name, RequestURL, AdminUserID, AdminPassword)
values ('mlb', 'MLB.com', 'http://localhost/provider_mlb/providerapi', 'super', 'superpassword')

insert into Provider (ProviderID, Name, RequestURL, AdminUserID, AdminPassword)
values ('rocketboom', 'Rocketboom', 'http://localhost/provider_rocketboom/providerapi', 'super', 'superpassword')

go

--//////////////////////////////////////////////////////////////////////////////

insert into Category (CategoryID, Name)
values ('action', 'Action')
insert into Category (CategoryID, Name)
values ('classics', 'Classics')
insert into Category (CategoryID, Name)
values ('comedy', 'Comedy')
insert into Category (CategoryID, Name)
values ('drama', 'Drama')
insert into Category (CategoryID, Name)
values ('family', 'Family')
insert into Category (CategoryID, Name)
values ('featured', 'Featured')
insert into Category (CategoryID, Name)
values ('romance', 'Romance')
insert into Category (CategoryID, Name)
values ('scifi', 'Sci-Fi')
insert into Category (CategoryID, Name)
values ('sports', 'Sports')
insert into Category (CategoryID, Name)
values ('tv', 'TV')
insert into Category (CategoryID, Name)
values ('thriller', 'Thriller')
insert into Category (CategoryID, Name)
values ('trailers', 'Trailers')
insert into Category (CategoryID, Name)
values ('western', 'Western')
go

--//////////////////////////////////////////////////////////////////////////////

insert into Rating (RatingID, Name)
values ('g', 'G')
insert into Rating (RatingID, Name)
values ('pg', 'PG')
insert into Rating (RatingID, Name)
values ('pg13', 'PG-13')
insert into Rating (RatingID, Name)
values ('r', 'R')
insert into Rating (RatingID, Name)
values ('nc17', 'NC-17')
insert into Rating (RatingID, Name)
values ('tv7', 'TV-7')
insert into Rating (RatingID, Name)
values ('tvy7', 'TV-Y7')
insert into Rating (RatingID, Name)
values ('tvy7fv', 'TV-Y7-FV')
insert into Rating (RatingID, Name)
values ('tvg', 'TV-G')
insert into Rating (RatingID, Name)
values ('tvpg', 'TV-PG')
insert into Rating (RatingID, Name)
values ('tv14', 'TV-14')
insert into Rating (RatingID, Name)
values ('tvma', 'TV-MA')
go

--//////////////////////////////////////////////////////////////////////////////

insert Member (MemberID, FirstName, LastName)
values ('f2c3e739-85c9-4b61-b906-230986c656c5', 'Robert', 'Davidson')
go

--//////////////////////////////////////////////////////////////////////////////

SET IDENTITY_INSERT MemberLogon ON

insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion)
values ('f2c3e739-85c9-4b61-b906-230986c656c5', '1@INETVOD.ORG', '1@inetvod.org', '654321', 100000000, '123456', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
go

SET IDENTITY_INSERT MemberLogon OFF

--//////////////////////////////////////////////////////////////////////////////

insert MemberPrefs (MemberID, IncludeAdult, AdultPIN, IncludeRatingIDList, IncludeDownload, IncludeStreaming, ConnectionSpeed)
values ('f2c3e739-85c9-4b61-b906-230986c656c5', 'PromptPassword', '123456', 'g,pg,pg13,r,tv7,tvy7,tvy7fv', 1, 1, 1500)
go

--//////////////////////////////////////////////////////////////////////////////
